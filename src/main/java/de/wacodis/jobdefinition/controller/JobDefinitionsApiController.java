package de.wacodis.jobdefinition.controller;

import de.wacodis.api.model.PaginatedResponse;
import de.wacodis.api.model.PaginatedWacodisJobDefinitionResponse;
import de.wacodis.api.model.WacodisJobDefinition;
import de.wacodis.jobdefinition.persistence.WacodisJobDefinitionRepository;
import de.wacodis.jobdefinition.streams.StreamBinder;
import io.swagger.annotations.ApiParam;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2018-10-04T15:06:06.366+02:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.waCoDiSJobDefinition.base-path:/wacodis-job-definition-api}")
public class JobDefinitionsApiController implements JobDefinitionsApi {

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
        repo.save(job);
        streams.newJobCreated(job);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PaginatedWacodisJobDefinitionResponse> retrieveWacodisJobDefinitions(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        Optional<Integer> pageOpt = Optional.ofNullable(page);
        Optional<Integer> sizeOpt = Optional.ofNullable(size);
//        Page<WacodisJobDefinition> results = this.repo.findAll(PageRequest.of(pageOpt.orElse(0), sizeOpt.orElse(10)));
//        PaginatedWacodisJobDefinitionResponse resp = new PaginatedWacodisJobDefinitionResponse()
//                .data(results.getContent());
//        resp.page(results.getNumber())
//                .size(results.getSize())
//                .total((int) results.getTotalElements());
        Iterable<WacodisJobDefinition> results = this.repo.findAll();
        Stream<WacodisJobDefinition> data = StreamSupport.stream(results.spliterator(), false);
        PaginatedWacodisJobDefinitionResponse resp = new PaginatedWacodisJobDefinitionResponse()
                .data(data.collect(Collectors.toList()));

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    
}
