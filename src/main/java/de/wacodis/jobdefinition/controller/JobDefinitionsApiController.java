package de.wacodis.jobdefinition.controller;

import com.datastax.driver.core.PagingState;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Integer actualPage = Optional.ofNullable(page).orElse(0);
        Integer actualSize = Optional.ofNullable(size).orElse(10);
        
        /** cassandra requires a paging state (it supported fetch-forward only)
         * this is a workaround at the moment which does not scale as it
         * fetches all pages
         * TODO: think about alternatives, maybe serialize the cache state
         * in the response for later usage in the next paging request
         */
        PagingState pagingState = null;
        CassandraPageRequest currentPageable = CassandraPageRequest.first(actualSize);
        Slice<WacodisJobDefinition> results = this.repo.findAll(CassandraPageRequest.of(currentPageable, pagingState));

        int currentPage = 0;        
        while (currentPage++ < actualPage) {
            if (results.nextPageable() instanceof CassandraPageRequest) {
                currentPageable = (CassandraPageRequest) results.nextPageable();
                pagingState = currentPageable.getPagingState();
                results = this.repo.findAll(CassandraPageRequest.of(currentPageable, pagingState));
            } else {
                break;
            }
        }
        
        if (results == null || results.getNumberOfElements() == 0) {
            return new ResponseEntity<>(new PaginatedWacodisJobDefinitionResponse(), HttpStatus.OK);
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
                    @PathVariable("id")
                    String id) {
        Optional<WacodisJobDefinition> jobOpt = this.repo.findById(UUID.fromString(id));
        if (jobOpt.isPresent()) {
            return new ResponseEntity<>(jobOpt.get(), HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @Override
    public ResponseEntity<Void> deleteWacodisJobDefinition(
            @ApiParam(value = "ID of WacodisJobDefinition to delete ", required = true)
                    @PathVariable("id")
                    String id) {
        Optional<WacodisJobDefinition> jobOpt = this.repo.findById(UUID.fromString(id));
        if (jobOpt.isPresent()) {
            this.repo.delete(jobOpt.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    
}
