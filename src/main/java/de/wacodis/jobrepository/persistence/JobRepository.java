/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobrepository.persistence;

import de.wacodis.api.model.Job;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
public interface JobRepository extends CrudRepository<Job, UUID> {

}
