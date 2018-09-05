/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobrepository;

import de.wacodis.jobrepository.controller.JobsApi;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        JobsApi jobsApi = new JobsApi(null);
        environment.jersey().register(jobsApi);

    }

}
