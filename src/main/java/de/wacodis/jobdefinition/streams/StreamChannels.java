
package de.wacodis.jobdefinition.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
public interface StreamChannels {
    
    String JOBSTATUS_INPUT = "jobStatus";
    String JOBCREATION_INPUT = "jobCreationConfirm";
 
    @Input(JOBSTATUS_INPUT)
    SubscribableChannel jobStatus();
 
    @Output("jobCreation")
    MessageChannel jobCreation();
 
    @Output("jobDeletion")
    MessageChannel jobDeletion();
    
    @Input(JOBCREATION_INPUT)
    SubscribableChannel jobCreationConfirm();
    
}
