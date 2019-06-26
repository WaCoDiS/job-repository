
package de.wacodis.jobdefinition.streams;

import de.wacodis.jobdefinition.model.WacodisJobDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
@EnableBinding(StreamChannels.class)
public class StreamBinder implements InitializingBean {
    
    private static final Logger LOG = LoggerFactory.getLogger(StreamBinder.class.getName());
    
    @Autowired
    private StreamChannels channels;

    @Override
    public void afterPropertiesSet() throws Exception {
    }
    
    @Async
    public void newJobCreated(WacodisJobDefinition theJob) {
        channels.jobCreation().send(MessageBuilder.withPayload(theJob).build());
        LOG.info("Published a new job: {}", theJob.getId());
        LOG.debug("Job details: {}", theJob);
    }
    
    @Async
    public void jobDeleted(WacodisJobDefinition theJob) {
        channels.jobDeletion().send(MessageBuilder.withPayload(theJob).build());
        LOG.info("Published job deletion: {}", theJob.getId());
    }
    
    @StreamListener(StreamChannels.JOBCREATION_INPUT)
    public void onJobCreated(WacodisJobDefinition job) {
        LOG.info("Job publication confirmed: {}", job.getId());
    }
    
}
