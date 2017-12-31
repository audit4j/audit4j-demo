package org.springframework.samples.petclinic.visit

import org.springframework.dao.DataAccessException
import org.springframework.data.repository.Repository
import org.springframework.samples.petclinic.model.BaseEntity


interface VisitRepository : Repository<Visit, Int> {

    /**
     * Save a <code>Visit</code> to the data store, either inserting or updating it.
     *
     * @param visit the <code>Visit</code> to save
     * @see BaseEntity#isNew
     */
    fun save(visit: Visit)

    fun findByPetId(petId: Int) : List<Visit>

}