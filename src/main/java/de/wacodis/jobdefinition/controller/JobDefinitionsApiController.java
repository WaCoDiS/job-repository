package de.wacodis.jobdefinition.controller;

import de.wacodis.jobdefinition.controller.exception.JobStatusUpdateException;
import de.wacodis.jobdefinition.model.PaginatedWacodisJobDefinitionResponse;
import de.wacodis.jobdefinition.model.WacodisJobDefinition;
import de.wacodis.jobdefinition.model.WacodisJobStatus;
import de.wacodis.jobdefinition.model.WacodisJobStatusUpdate;
import de.wacodis.jobdefinition.persistence.WacodisJobDefinitionRepository;
import de.wacodis.jobdefinition.streams.StreamBinder;
import io.swagger.annotations.ApiParam;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2018-10-16T11:18:11.410+02:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.waCoDiSJobDefinition.base-path:}")
public class JobDefinitionsApiController implements JobDefinitionsApi {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JobDefinitionsApiController.class);

    @Autowired
    private StreamBinder streams;

    @Autowired
    private WacodisJobDefinitionRepository repo;

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public JobDefinitionsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<WacodisJobDefinition> createWacodisJobDefinition(@RequestBody WacodisJobDefinition job) {
        job.setId(UUID.randomUUID());
        job.setCreated(new DateTime());
        job.setStatus(WacodisJobStatus.WAITING);
        repo.save(job);
        streams.newJobCreated(job);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PaginatedWacodisJobDefinitionResponse> retrieveWacodisJobDefinitions(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        Integer actualPage = Optional.ofNullable(page).orElse(0);
        Integer actualSize = Optional.ofNullable(size).orElse(10);

        PageRequest currentPageable = PageRequest.of(actualPage, actualSize);
        Slice<WacodisJobDefinition> results = this.repo.findAll(currentPageable);

        if (results == null || results.getNumberOfElements() == 0) {
            PaginatedWacodisJobDefinitionResponse pr = new PaginatedWacodisJobDefinitionResponse();
            pr.page(actualPage).size(actualSize).total(0);

            return new ResponseEntity<>(pr, HttpStatus.OK);
        }

        PaginatedWacodisJobDefinitionResponse resp = new PaginatedWacodisJobDefinitionResponse()
                .data(results.getContent());
        resp.page(actualPage)
                .size(actualSize)
                .total((int) this.repo.count());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WacodisJobDefinition> retrieveWacodisJobDefinitionById(
            @ApiParam(value = "ID of WacodisJobDefinition to retrieve ", required = true)
            @PathVariable("id") String id) {
        Optional<WacodisJobDefinition> jobOpt = this.repo.findById(UUID.fromString(id));
        if (jobOpt.isPresent()) {
            return new ResponseEntity<>(jobOpt.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Void> deleteWacodisJobDefinition(
            @ApiParam(value = "ID of WacodisJobDefinition to delete ", required = true)
            @PathVariable("id") String id) {
        Optional<WacodisJobDefinition> jobOpt = this.repo.findById(UUID.fromString(id));
        if (jobOpt.isPresent()) {
            this.repo.delete(jobOpt.get());
            streams.jobDeleted(jobOpt.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<WacodisJobDefinition> updateJobStatus(
            @ApiParam(value = "WacodisJobDefinition to add to the repository ", required = true)
            @Valid
            @RequestBody WacodisJobStatusUpdate wacodisJobStatusUpdate) {
        LOGGER.info("updade status for WacodisJobDefinition with id {}", wacodisJobStatusUpdate.getWacodisJobIdentifier().toString());
        //first retrieve currently stored job to update status

        ResponseEntity<WacodisJobDefinition> getByIdResponse = null;
        HttpStatus responseStatus = null;
        try {
            getByIdResponse = retrieveWacodisJobDefinitionById(wacodisJobStatusUpdate.getWacodisJobIdentifier().toString());
            responseStatus = getByIdResponse.getStatusCode();
        } catch (Exception ex) {
            String errorMsg = "unexpected error occured while retrieving stored data for WacodisJobDefinition with id " + wacodisJobStatusUpdate.getWacodisJobIdentifier();
            LOGGER.error(errorMsg, ex);
            throw new JobStatusUpdateException("unable to update job status, " + errorMsg, ex, wacodisJobStatusUpdate);
        }

        if (responseStatus.equals(HttpStatus.OK)) {
            try {
                LOGGER.debug("retrieved current data for WacodisJobDefinition with id {} from backend", wacodisJobStatusUpdate.getWacodisJobIdentifier().toString());

                WacodisJobDefinition currentJob = getByIdResponse.getBody();

                mergeStatusAttributes(wacodisJobStatusUpdate, currentJob); //merge new status into current job definition
                WacodisJobDefinition updatedJobDefiniton = repo.save(currentJob); //store merged job definition
                LOGGER.info("successfully updated status for WacodisJobDefintion with id {} and wrote updated data to backend", wacodisJobStatusUpdate.getWacodisJobIdentifier().toString());
                return new ResponseEntity<>(updatedJobDefiniton, HttpStatus.OK); //return updated job definition
            } catch (Exception ex) {
                String errorMsg = "unexpected error occured while updating status of WacodisJobDefinition with id " + wacodisJobStatusUpdate.getWacodisJobIdentifier();
                LOGGER.error(errorMsg + ", could not write updated WacodisJobDefinition to backend", ex);
                throw new JobStatusUpdateException("unable to update job status, " + errorMsg, ex, wacodisJobStatusUpdate);
            }
        } else {
            if (responseStatus.equals(HttpStatus.NOT_FOUND)) {
                LOGGER.warn("unable to update status for WacodisJobDefintion with id {}, could not find WacodisJobDefintion with id {}", wacodisJobStatusUpdate.getWacodisJobIdentifier(), wacodisJobStatusUpdate.getWacodisJobIdentifier());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); //respond with 404 Not Found according to api definition
            } else {
                LOGGER.error("unable to retrieve WacodisJobDefinition with id {} from backend, request responded with status code {} and body {}.", wacodisJobStatusUpdate.getWacodisJobIdentifier(), responseStatus, getByIdResponse.getBody());
                throw new JobStatusUpdateException("unable to update job status, unexpected error occured while retrieving stored data for WacodisJobDefinition with id " + wacodisJobStatusUpdate.getWacodisJobIdentifier(), wacodisJobStatusUpdate, responseStatus);
            }
        }
    }

    private void mergeStatusAttributes(WacodisJobStatusUpdate newStatus, WacodisJobDefinition currentJob) {
        currentJob.setStatus(newStatus.getNewStatus());

        if (newStatus.getExecutionFinished() != null) {
            currentJob.setLastFinishedExecution(newStatus.getExecutionFinished());
        }
    }
}
