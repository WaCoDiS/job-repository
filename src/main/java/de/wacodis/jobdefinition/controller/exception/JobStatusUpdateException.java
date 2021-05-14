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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobdefinition.controller.exception;

import de.wacodis.jobdefinition.model.WacodisJobStatusUpdate;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Arne
 */
public class JobStatusUpdateException extends RuntimeException {

    public JobStatusUpdateException() {
    }

    private HttpStatus status;
    private WacodisJobStatusUpdate jobStatusUpdate;
    
    public JobStatusUpdateException(WacodisJobStatusUpdate jobStatusUpdate){
        this.jobStatusUpdate = jobStatusUpdate;
    }

    public JobStatusUpdateException(String message, WacodisJobStatusUpdate jobStatusUpdate) {
        super(message);
        this.jobStatusUpdate = jobStatusUpdate;
    }

    public JobStatusUpdateException(String message, Throwable cause, WacodisJobStatusUpdate jobStatusUpdate) {
        super(message, cause);
        this.jobStatusUpdate = jobStatusUpdate;
    }

    public JobStatusUpdateException(Throwable cause, WacodisJobStatusUpdate jobStatusUpdate) {
        super(cause);
        this.jobStatusUpdate = jobStatusUpdate;
    }

    public JobStatusUpdateException(String message, WacodisJobStatusUpdate jobStatusUpdate, HttpStatus status) {
        this(message, jobStatusUpdate);
        this.status = status;
    }

    public JobStatusUpdateException(String message, Throwable cause, WacodisJobStatusUpdate jobStatusUpdate, HttpStatus status) {
        this(message, cause, jobStatusUpdate);
        this.status = status;
    }

    public JobStatusUpdateException(Throwable cause, WacodisJobStatusUpdate jobStatusUpdate, HttpStatus status) {
        this(cause, jobStatusUpdate);
        this.status = status;
    }

    public JobStatusUpdateException(WacodisJobStatusUpdate jobStatusUpdate, HttpStatus status) {
        this(jobStatusUpdate);
        this.status = status;
    }
    
    

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public WacodisJobStatusUpdate getJobStatusUpdate() {
        return jobStatusUpdate;
    }

    public void setJobStatusUpdate(WacodisJobStatusUpdate jobStatusUpdate) {
        this.jobStatusUpdate = jobStatusUpdate;
    }
}
