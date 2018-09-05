package de.wacodis.jobrepository.controller;

import de.wacodis.api.model.*;
import de.wacodis.jobrepository.controller.JobsApiService;
import de.wacodis.jobrepository.controller.factories.JobsApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import de.wacodis.api.model.Error;
import de.wacodis.api.model.Job;
import de.wacodis.api.model.PaginatedJobResponse;

import java.util.Map;
import java.util.List;
import de.wacodis.jobrepository.controller.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/jobs")


@io.swagger.annotations.Api(description = "the jobs API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2018-09-05T11:37:50.848+02:00[Europe/Berlin]")
public class JobsApi  {
   private final JobsApiService delegate;

   public JobsApi(@Context ServletConfig servletContext) {
      JobsApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("JobsApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (JobsApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = JobsApiServiceFactory.getJobsApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Creates a new Job in the repository ", response = Job.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "job response ", response = Job.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "unexpected error ", response = Error.class) })
    public Response createJob(@ApiParam(value = "Job to add to the repository " ,required=true) Job job
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createJob(job,securityContext);
    }
    @DELETE
    @Path("/{id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "deletes a single job based on the ID supplied ", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "job deleted ", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "unexpected error ", response = Error.class) })
    public Response deleteJob(@ApiParam(value = "ID of job to delete ",required=true) @PathParam("id") String id
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteJob(id,securityContext);
    }
    @GET
    @Path("/{id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Returns a job based on a single ID ", response = Job.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "job response ", response = Job.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "unexpected error ", response = Error.class) })
    public Response retrieveJobById(@ApiParam(value = "ID of job to retrieve ",required=true) @PathParam("id") String id
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.retrieveJobById(id,securityContext);
    }
    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Returns a paginated list of jobs ", response = PaginatedJobResponse.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "job response ", response = PaginatedJobResponse.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "unexpected error ", response = Error.class) })
    public Response retrieveJobs(@ApiParam(value = "the page as an offset (default=0) ") @QueryParam("page") Integer page
,@ApiParam(value = "the maximum number of results (default=100) ") @QueryParam("size") Integer size
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.retrieveJobs(page,size,securityContext);
    }
}
