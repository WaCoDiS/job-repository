
package de.wacodis.jobdefinition.streams;

import de.wacodis.api.model.WacodisJobDefinition;
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
        LOG.info("Send new job: {}", theJob);
    }
    
    @StreamListener(StreamChannels.JOBCREATION_INPUT)
    public void onJobCreated(WacodisJobDefinition job) {
        LOG.info("Got new job: {}", job);
    }
    
}
