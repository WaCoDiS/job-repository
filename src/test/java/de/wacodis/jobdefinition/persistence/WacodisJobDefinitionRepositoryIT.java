/*
 * Copyright 2018-2021 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.wacodis.jobdefinition.persistence;

import de.wacodis.jobdefinition.model.AbstractSubsetDefinition;
import de.wacodis.jobdefinition.model.SensorWebSubsetDefinition;
import de.wacodis.jobdefinition.model.WacodisJobDefinition;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.joda.time.DateTime;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ElasticsearchConfig.class, WacodisJobDefinitionRepository.class})
public class WacodisJobDefinitionRepositoryIT extends AbstractElasticsearchIT {
    
    @Autowired
    private WacodisJobDefinitionRepository repo;

    @Test
    public void testJobRoundtrip() {
        WacodisJobDefinition j = new WacodisJobDefinition();
        UUID id = UUID.randomUUID();
        j.setId(id);
        j.setCreated(new DateTime());
        j.setName("weirdo wacodo jobo");
        j.setProcessingTool("de.wacodis.wps.landclassification");
        
        AbstractSubsetDefinition swe = new SensorWebSubsetDefinition()
                .featureOfInterest("test-feature")
                .observedProperty("obs-prop")
                .offering("offering1")
                .procedure("proc1")
                .serviceUrl("https://service.url").sourceType(AbstractSubsetDefinition.SourceTypeEnum.SENSORWEBSUBSETDEFINITION);
        
        j.setInputs(Collections.singletonList(swe));
        this.repo.save(j);
        
        Optional<WacodisJobDefinition> retrieved = this.repo.findById(id);
        Assert.assertThat(retrieved.isPresent(), CoreMatchers.is(true));
        Assert.assertThat(retrieved.get().getName(), CoreMatchers.equalTo("weirdo wacodo jobo"));
        Assert.assertThat(retrieved.get().getProcessingTool(), CoreMatchers.equalTo("de.wacodis.wps.landclassification"));
        Assert.assertThat(retrieved.get().getInputs().size(), CoreMatchers.is(1));
        
        AbstractSubsetDefinition def1 = retrieved.get().getInputs().get(0);
        Assert.assertThat(def1, CoreMatchers.instanceOf(SensorWebSubsetDefinition.class));
        SensorWebSubsetDefinition swe2 = (SensorWebSubsetDefinition) def1;
        Assert.assertThat(swe2.getFeatureOfInterest(), CoreMatchers.equalTo("test-feature"));
    }

}
