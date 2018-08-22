
package de.wacodis.jobrepository.persistence;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
@SpringBootTest(classes = {CassandraConfig.class})
public class AbstractCassandraIT {
    
    @Autowired
    private CassandraConfig config;
    
    @After
    public void cleanup() {
        this.config.session().getObject().execute(String.format("DROP KEYSPACE %s;", config.getKeyspaceName()));
    }

}
