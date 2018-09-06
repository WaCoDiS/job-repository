/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobrepository;

import de.wacodis.jobrepository.configuration.JobrepositoryConfiguration;
import de.wacodis.jobrepository.controller.ApiOriginFilter;
import de.wacodis.jobrepository.controller.JobsApi;
import de.wacodis.jobrepository.controller.factories.JobsApiServiceFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;

/**
 *
 * @author <a href="mailto:s.drost@52north.org">Sebastian Drost</a>
 */
public class JobrepositoryApplication extends Application<JobrepositoryConfiguration> {

    public static void main(String[] args) throws Exception {
        new JobrepositoryApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<JobrepositoryConfiguration> bootstrap) {
        
    }
    
    @Override
    public String getName() {
        return "hello-world";
    }
    
    @Override
    public void run(JobrepositoryConfiguration configuration, Environment environment) throws Exception {
        JobsApiServiceFactory.getJobsApi().setDefaultMessage(configuration.getMessageConfiguration().getDefaultMessage());
        environment.jersey().register(new JobsApi(null));
        environment.servlets().addFilter("ApiOriginFiler", new ApiOriginFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        
    }
    
}
