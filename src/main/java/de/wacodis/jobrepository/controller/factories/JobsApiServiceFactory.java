package de.wacodis.jobrepository.controller.factories;

import de.wacodis.jobrepository.controller.JobsApiService;
import de.wacodis.jobrepository.controller.impl.JobsApiServiceImpl;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2018-09-05T11:02:00.597+02:00[Europe/Berlin]")
public class JobsApiServiceFactory {

    private final static JobsApiService service = new JobsApiServiceImpl();

    public static JobsApiService getJobsApi() {
        return service;
    }
}
