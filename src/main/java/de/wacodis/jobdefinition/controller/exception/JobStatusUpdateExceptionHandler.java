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

import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Arne
 */
@ControllerAdvice
public class JobStatusUpdateExceptionHandler {

    private static final int DEFAULTERRORCODE = 500;
    private static final MediaType RESPONSEMEDIATYPE = MediaType.APPLICATION_JSON;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JobStatusUpdateExceptionHandler.class);

    @ExceptionHandler({JobStatusUpdateException.class})
    protected ResponseEntity<de.wacodis.jobdefinition.model.Error> handleJobStatusUpdateException(JobStatusUpdateException ex, WebRequest request) {
        LOGGER.info("creating error response for failed job status updated of WacodisJobDefinition with id {}.", ex.getJobStatusUpdate().getWacodisJobIdentifier());
        ResponseEntity<de.wacodis.jobdefinition.model.Error> errorResponse;
        de.wacodis.jobdefinition.model.Error error = new de.wacodis.jobdefinition.model.Error();

        if (ex.getStatus() != null) {
            error.setCode(ex.getStatus().value());
        } else {
            error.setCode(DEFAULTERRORCODE);
        }

        if (ex.getMessage() != null) {
            error.setMessage(ex.getMessage());
        }

        errorResponse = ResponseEntity.status(DEFAULTERRORCODE).contentType(RESPONSEMEDIATYPE).body(error); //always respond with default error code 500 according to api definition

        LOGGER.debug("return error response for failed job status updated of WacodisJobDefinition with id {}. Error Response: {}", ex.getJobStatusUpdate().getWacodisJobIdentifier(), error);

        return errorResponse;
    }

}
