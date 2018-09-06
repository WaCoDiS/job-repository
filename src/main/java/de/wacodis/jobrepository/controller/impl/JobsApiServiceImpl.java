package de.wacodis.jobrepository.controller.impl;

import de.wacodis.jobrepository.controller.*;
import de.wacodis.api.model.*;

import de.wacodis.api.model.Error;
import de.wacodis.api.model.Job;
import de.wacodis.api.model.PaginatedJobResponse;

import java.util.List;
import de.wacodis.jobrepository.controller.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2018-09-05T11:02:00.597+02:00[Europe/Berlin]")
public class JobsApiServiceImpl extends JobsApiService {
    @Override
    public Response createJob(Job job, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, getDefaultMessage())).build();
    }
    @Override
    public Response deleteJob(String id, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, getDefaultMessage())).build();
    }
    @Override
    public Response retrieveJobById(String id, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, getDefaultMessage())).build();
    }
    @Override
    public Response retrieveJobs( Integer page,  Integer size, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, getDefaultMessage())).build();
    }
}
