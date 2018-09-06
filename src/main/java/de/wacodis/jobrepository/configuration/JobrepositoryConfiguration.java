/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobrepository.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author <a href="mailto:s.drost@52north.org">Sebastian Drost</a>
 */
public class JobrepositoryConfiguration extends Configuration {

    @Valid
    @NotNull
    private MessagesConfiguration messageConfiguration = new MessagesConfiguration();

    @JsonProperty("messages")
    public MessagesConfiguration getMessageConfiguration() {
        return messageConfiguration;
    }

    @JsonProperty("messages")
    public void setMessageConfiguration(MessagesConfiguration messageConfiguration) {
        this.messageConfiguration = messageConfiguration;
    }

}
