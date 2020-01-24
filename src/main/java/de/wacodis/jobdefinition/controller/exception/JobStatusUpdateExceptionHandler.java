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
