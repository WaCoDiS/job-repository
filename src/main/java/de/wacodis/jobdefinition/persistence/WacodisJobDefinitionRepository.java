/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobdefinition.persistence;

import de.wacodis.api.model.WacodisJobDefinition;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
public interface WacodisJobDefinitionRepository extends CassandraRepository<WacodisJobDefinition, UUID> {

}
