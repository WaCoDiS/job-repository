/*
 * Copyright 2018-2021 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
