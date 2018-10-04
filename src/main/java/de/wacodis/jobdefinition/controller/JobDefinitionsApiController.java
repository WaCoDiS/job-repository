package de.wacodis.jobdefinition.controller;

import de.wacodis.api.model.WacodisJobDefinition;
import de.wacodis.jobdefinition.streams.StreamBinder;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2018-10-04T12:40:33.556+02:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.waCoDiSJobDefinition.base-path:/wacodis-job-definition-api}")
public class JobDefinitionsApiController implements JobDefinitionsApi {

    @Autowired
    private StreamBinder streams;
    
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
        streams.newJobCreated(job);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }
}
