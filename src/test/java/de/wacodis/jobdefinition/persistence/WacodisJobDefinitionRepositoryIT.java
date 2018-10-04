package de.wacodis.jobdefinition.persistence;

import com.datastax.driver.core.utils.UUIDs;
import de.wacodis.api.model.WacodisJobDefinition;
import java.util.Optional;
import java.util.UUID;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.joda.time.DateTime;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CassandraConfig.class, WacodisJobDefinitionRepository.class})
public class WacodisJobDefinitionRepositoryIT extends AbstractCassandraIT {
    
    @Autowired
    private WacodisJobDefinitionRepository repo;

    @Test
    public void testJobRoundtrip() {
        WacodisJobDefinition j = new WacodisJobDefinition();
        UUID id = UUIDs.timeBased();
        j.setId(id);
        j.setCreated(new DateTime());
        j.setName("weirdo wacodo jobo");
        j.setProcessingTool("de.wacodis.wps.landclassification");
        this.repo.save(j);
        
        Optional<WacodisJobDefinition> retrieved = this.repo.findById(id);
        Assert.assertThat(retrieved.isPresent(), CoreMatchers.is(true));
        Assert.assertThat(retrieved.get().getName(), CoreMatchers.equalTo("weirdo wacodo jobo"));
        Assert.assertThat(retrieved.get().getProcessingTool(), CoreMatchers.equalTo("de.wacodis.wps.landclassification"));
    }


}
