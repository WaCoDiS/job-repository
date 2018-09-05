package de.wacodis.jobrepository.controller;

import de.wacodis.jobrepository.controller.*;
import de.wacodis.api.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import de.wacodis.api.model.Error;
import de.wacodis.api.model.Job;
import de.wacodis.api.model.PaginatedJobResponse;

import java.util.List;
import de.wacodis.jobrepository.controller.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2018-09-05T11:37:50.848+02:00[Europe/Berlin]")
public abstract class JobsApiService {
    public abstract Response createJob(Job job,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteJob(String id,SecurityContext securityContext) throws NotFoundException;
    public abstract Response retrieveJobById(String id,SecurityContext securityContext) throws NotFoundException;
    public abstract Response retrieveJobs( Integer page, Integer size,SecurityContext securityContext) throws NotFoundException;
}
