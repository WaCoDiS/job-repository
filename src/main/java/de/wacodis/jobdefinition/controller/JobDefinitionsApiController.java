package de.wacodis.jobdefinition.controller;

import de.wacodis.jobdefinition.model.PaginatedWacodisJobDefinitionResponse;
import de.wacodis.jobdefinition.model.WacodisJobDefinition;
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
        job.setStatus(WacodisJobDefinition.StatusEnum.WAITING);
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
    public ResponseEntity<?> updateJobStatus(
            @ApiParam(value = "WacodisJobDefinition to add to the repository ", required = true)
            @Valid
            @RequestBody WacodisJobDefinition wacodisJobDefinition) {
        LOGGER.info("updade status for WacodisJobDefinition with id {}", wacodisJobDefinition.getId().toString());
        //first retrieve currently stored job to update status
        ResponseEntity<WacodisJobDefinition> getByIdResponse = retrieveWacodisJobDefinitionById(wacodisJobDefinition.getId().toString());
        HttpStatus responseStatus = getByIdResponse.getStatusCode();

        if (responseStatus.equals(HttpStatus.OK)) {
            LOGGER.debug("retrieved current data for WacodisJobDefinition with id {} from backend", wacodisJobDefinition.getId().toString());
            WacodisJobDefinition currentJob = getByIdResponse.getBody();
            //merge status
            mergeStatusAttributes(wacodisJobDefinition, currentJob);
            WacodisJobDefinition updatedJobDefiniton = repo.save(wacodisJobDefinition);
            LOGGER.info("successfully updated status for WacodisJobDefintion with id {} and wrote updated data to backend", wacodisJobDefinition.getId().toString());
            return new ResponseEntity<>(updatedJobDefiniton, HttpStatus.OK); //return updated job definition
        } else {
            if (responseStatus.equals(HttpStatus.NOT_FOUND)) {
                LOGGER.warn("unable to update status for WacodisJobDefintion with id {}, could not found WacodisJobDefintion with id {}", wacodisJobDefinition.getId().toString(), wacodisJobDefinition.getId().toString());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                LOGGER.error("unable to retrieve WacodisJobDefinition with id {} from backend, request responded with status code {} and body {}.", wacodisJobDefinition.getId().toString(), responseStatus.toString(), getByIdResponse.getBody());
                de.wacodis.jobdefinition.model.Error error = new de.wacodis.jobdefinition.model.Error();
                error.setCode(500);
                error.setMessage("unexpected error occured while retrieving current data for job " + wacodisJobDefinition.getId().toString());
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private void mergeStatusAttributes(WacodisJobDefinition jobWithNewStatus, WacodisJobDefinition currentJob) {
        currentJob.setStatus(jobWithNewStatus.getStatus());
        
        if(jobWithNewStatus.getLastFinishedExecution() != null){
            currentJob.setLastFinishedExecution(jobWithNewStatus.getLastFinishedExecution());
        }
    }
}
