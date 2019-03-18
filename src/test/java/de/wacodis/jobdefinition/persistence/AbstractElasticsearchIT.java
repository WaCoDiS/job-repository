package de.wacodis.jobdefinition.persistence;

import java.net.UnknownHostException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
@SpringBootTest(classes = {ElasticsearchConfig.class})
public class AbstractElasticsearchIT {

    @Autowired
    private ElasticsearchConfig config;

    @After
    public void cleanup() throws UnknownHostException {
        this.config.client().admin().indices().delete(new DeleteIndexRequest("wacodis")).actionGet();
    }

}
