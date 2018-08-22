package de.wacodis.jobrepository.persistence;

import com.datastax.driver.core.Cluster;
import de.wacodis.api.model.Job;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraEntityClassScanner;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
@Configuration
@EnableCassandraRepositories(
        basePackages = "de.wacodis")
public class CassandraConfig extends AbstractCassandraConfiguration {
    
    private static final Logger LOG = LoggerFactory.getLogger(CassandraConfig.class.getName());

    public static final String DEFAULT_KEYSPACE = "wacodisJobs";

    @Autowired
    private Environment environment;

    @Override
    protected String getKeyspaceName() {
        String ks = environment.getProperty("spring.data.cassandra.keyspace-name", String.class, DEFAULT_KEYSPACE);
        return ks;
    }

    @Bean
    @Override
    public CassandraClusterFactoryBean cluster() {
        Integer port = environment.getProperty("spring.data.cassandra.port", Integer.class, 9042);
        String contacts = environment.getProperty("spring.data.cassandra.contactpoints", String.class, "localhost");

        CassandraClusterFactoryBean cluster
                = new CassandraClusterFactoryBean();
        cluster.setContactPoints(contacts);
        cluster.setKeyspaceCreations(Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(getKeyspaceName()).ifNotExists()));
        cluster.setPort(port);
        
        return cluster;
    }

    @Bean
    @Override
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        CassandraMappingContext mappingContext = new CassandraMappingContext();
        mappingContext.setInitialEntitySet(CassandraEntityClassScanner.scan(Job.class.getPackage().getName()));
        
        Cluster cluster = cluster().getObject();
        mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster, getKeyspaceName()));
        return mappingContext;
    }

    @Bean
    @Override
    public CassandraConverter cassandraConverter() {
        try {
            return new MappingCassandraConverter(cassandraMapping());
        } catch (ClassNotFoundException ex) {
            LOG.warn(ex.getMessage(), ex);
            throw new RuntimeException("Could not load cassandra converter", ex);
        }
    }

    @Bean
    @Override
    public CassandraSessionFactoryBean session() {
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(getKeyspaceName());
        session.setConverter(cassandraConverter());
        session.setSchemaAction(SchemaAction.RECREATE);
        return session;
    }
}
