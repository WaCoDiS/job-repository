package de.wacodis.jobrepository.persistence;

import com.datastax.driver.core.utils.UUIDs;
import com.google.common.collect.ImmutableSet;
import de.wacodis.api.model.Job;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CassandraConfig.class, JobRepository.class})
public class JobRepositoryIT extends AbstractCassandraIT {
    
    @Autowired
    private JobRepository repo;

    @Test
    public void testJobRoundtrip() {
        Job j = new Job();
        UUID id = UUIDs.timeBased();
        j.setId(id);
        j.setCreated(new Date());
        j.setName("weirdo wacodo jobo");
        j.setProcessingTool("de.wacodis.wps.landclassification");
        this.repo.save(j);
        
        Optional<Job> retrieved = this.repo.findById(id);
        Assert.assertThat(retrieved.isPresent(), CoreMatchers.is(true));
        Assert.assertThat(retrieved.get().getName(), CoreMatchers.equalTo("weirdo wacodo jobo"));
        Assert.assertThat(retrieved.get().getProcessingTool(), CoreMatchers.equalTo("de.wacodis.wps.landclassification"));
    }


}
