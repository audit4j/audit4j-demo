package org.springframework.samples.petclinic.owner;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

interface PetRepository : Repository<Pet, Int> {

    /**
     * Retrieve all {@link PetType}s from the data store.
     * @return a Collection of {@link PetType}s.
     */
    @Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
    @Transactional(readOnly = true)
    fun findPetTypes() :  List<PetType>

    /**
     * Retrieve a {@link Pet} from the data store by id.
     * @param id the id to search for
     * @return the {@link Pet} if found
     */
    @Transactional(readOnly = true)
    fun findById(id : Int) : Pet

    /**
     * Save a {@link Pet} to the data store, either inserting or updating it.
     * @param pet the {@link Pet} to save
     */
    fun save(pet : Pet);

}