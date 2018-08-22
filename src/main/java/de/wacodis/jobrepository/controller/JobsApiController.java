package de.wacodis.jobrepository.controller;

import de.wacodis.api.model.Job;
import de.wacodis.jobrepository.streams.StreamBinder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-08-14T11:15:46.315+02:00[Europe/Berlin]")

@Controller
@RequestMapping("${openapi.waCoDiSJobRepository.base-path:/jobRepository}")
public class JobsApiController implements JobsApi {

    @Autowired
    private StreamBinder streams;
    
    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public JobsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        job.setId(UUID.randomUUID());
        streams.newJobCreated(job);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

}
