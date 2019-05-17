
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
    
    String JOBSTATUS_INPUT = "job-status";
    String JOBCREATION_INPUT = "job-creation-confirm";
 
    @Input(JOBSTATUS_INPUT)
    SubscribableChannel jobStatus();
 
    @Output("job-creation")
    MessageChannel jobCreation();
 
    @Output("job-deletion")
    MessageChannel jobDeletion();
    
    @Input(JOBCREATION_INPUT)
    SubscribableChannel jobCreationConfirm();
    
}
