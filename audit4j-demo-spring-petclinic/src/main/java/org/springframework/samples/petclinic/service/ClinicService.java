/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.audit4j.core.annotation.Audit;
import org.audit4j.core.annotation.AuditField;
import org.audit4j.core.annotation.SelectionType;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;


/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 */
public interface ClinicService {

    Collection<PetType> findPetTypes() throws DataAccessException;

    @Audit(selection=SelectionType.MARKED)
    Owner findOwnerById(@AuditField(field = "id") int id) throws DataAccessException;

    @Audit(selection=SelectionType.MARKED)
    Pet findPetById(@AuditField(field = "id")int id) throws DataAccessException;

    @Audit(selection=SelectionType.MARKED)
    void savePet(@AuditField(field = "pet") Pet pet) throws DataAccessException;

    @Audit(selection=SelectionType.MARKED)
    void saveVisit(@AuditField(field = "visit") Visit visit) throws DataAccessException;

    @Audit(selection=SelectionType.MARKED)
    Collection<Vet> findVets() throws DataAccessException;

    @Audit(selection=SelectionType.MARKED)
    void saveOwner(@AuditField(field = "owener") Owner owner) throws DataAccessException;

    @Audit(selection=SelectionType.MARKED)
    Collection<Owner> findOwnerByLastName(@AuditField(field = "lastName") String lastName) throws DataAccessException;

}
