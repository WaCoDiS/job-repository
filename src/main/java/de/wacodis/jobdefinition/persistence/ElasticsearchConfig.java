/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobdefinition.persistence;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.geo.CustomGeoModule;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "de.wacodis.jobdefinition.persistence")
public class ElasticsearchConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticsearchConfig.class);
    
    @Value("${data.elasticsearch.cluster.name:elasticsearch}")
    private String clusterName;

    @Value("${data.elasticsearch.host:127.0.0.1}")
    private String host;

    @Value("${data.elasticsearch.port:9200}")
    private int port;

    @Value("${data.elasticsearch.nativePort:9300}")
    private int nativePort;

    @Bean
    public Client client() throws UnknownHostException {
        Settings elasticsearchSettings = Settings.builder()
                .put("client.transport.sniff", true)
                .put("cluster.name", clusterName).build();
        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName(host), nativePort));
        LOG.info("Created elasticsearch client: {}, {}, {}", host, clusterName, nativePort);
        return client;
    }

    @Bean
    @ConditionalOnBean(Client.class)
    public ElasticsearchTemplate elasticsearchTemplate(Client client,
            ElasticsearchConverter converter) {
        try {
            return new ElasticsearchTemplate(client, converter, new DefaultResultMapper(mapper()));
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    @Bean
    public ElasticsearchConverter elasticsearchConverter(
            SimpleElasticsearchMappingContext mappingContext) {
        return new MappingElasticsearchConverter(mappingContext);
    }

    @Bean
    public SimpleElasticsearchMappingContext mappingContext() {
        return new SimpleElasticsearchMappingContext();
    }

    @Bean
    public EntityMapper mapper() {
        return new ConfigurableEntityMapper();
    }

    public static class ConfigurableEntityMapper implements EntityMapper {

        private final ObjectMapper objectMapper;

        public ConfigurableEntityMapper() {
            this.objectMapper = new ObjectMapper();
            this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            this.objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            this.objectMapper.registerModule(new CustomGeoModule());
            this.objectMapper.registerModule(new JodaModule());
            this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            this.objectMapper.setSerializationInclusion(Include.NON_NULL);
        }

        @Override
        public String mapToString(Object object) throws IOException {
            return this.objectMapper.writeValueAsString(object);
        }

        @Override
        public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
            return this.objectMapper.readValue(source, clazz);
        }

    }

}
