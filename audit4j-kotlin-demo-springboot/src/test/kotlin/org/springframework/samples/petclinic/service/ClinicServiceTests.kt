package org.springframework.samples.petclinic.system

import org.assertj.core.api.Assertions.assertThat

import java.util.Date

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.samples.petclinic.owner.PetType;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration test of the Service and the Repository layer.
 * <p>
 * ClinicServiceSpringDataJpaTests subclasses benefit from the following services provided by the Spring
 * TestContext Framework: </p> <ul> <li><strong>Spring IoC container caching</strong> which spares us unnecessary set up
 * time between test execution.</li> <li><strong>Dependency Injection</strong> of test fixture instances, meaning that
 * we don't need to perform application context lookups. See the use of {@link Autowired @Autowired} on the <code>{@link
 * ClinicServiceTests#clinicService clinicService}</code> instance variable, which uses autowiring <em>by
 * type</em>. <li><strong>Transaction management</strong>, meaning each test method is executed in its own transaction,
 * which is automatically rolled back by default. Thus, even if tests insert or otherwise change database state, there
 * is no need for a teardown or cleanup script. <li> An {@link org.springframework.context.ApplicationContext
 * ApplicationContext} is also inherited and can be used for explicit bean lookup if necessary. </li> </ul>
 *
 * @author Ken Krebs
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Dave Syer
 */

@RunWith(SpringRunner::class)
@DataJpaTest(includeFilters = arrayOf(ComponentScan.Filter(Service::class)))
open class ClinicServiceTests {

    @Autowired
    lateinit protected var ownersRepository : OwnerRepository

    @Autowired
    lateinit private var petsRepository : PetRepository

    @Autowired
    lateinit private var visitsRepository : VisitRepository

    @Autowired
    lateinit private var vetsRepository : VetRepository

    @Test
    fun shouldFindOwnersByLastName() {
        var owners = ownersRepository.findByLastName("Davis")
        assertThat(owners.size).isEqualTo(2);

        owners = ownersRepository.findByLastName("Daviss")
        assertThat(owners.isEmpty()).isTrue();
    }

    @Test
    fun shouldFindSingleOwnerWithPet() {
        val owner = ownersRepository.findById(1);
        assertThat(owner.lastName).startsWith("Franklin");
        assertThat(owner.pets.size).isEqualTo(1);
        assertThat(owner.getPets().get(0).type).isNotNull();
        assertThat(owner.getPets().get(0).type!!.name).isEqualTo("cat");
    }

    @Test
    @Transactional
    fun shouldInsertOwner() {
        var owners = ownersRepository.findByLastName("Schultz");
        val found = owners.size;

        var owner = Owner();
        owner.firstName ="Sam";
        owner.lastName="Schultz";
        owner.address="4, Evans Street";
        owner.city="Wollongong";
        owner.telephone="4444444444";
        ownersRepository.save(owner);
        assertThat(owner.id!!).isNotEqualTo(0);

        owners = ownersRepository.findByLastName("Schultz");
        assertThat(owners.size).isEqualTo(found + 1);
    }

    @Test
    @Transactional
    fun shouldUpdateOwner() {
        var owner = ownersRepository.findById(1)
        val oldLastName = owner.lastName
        val newLastName = oldLastName + "X"

        owner.lastName=newLastName;
        ownersRepository.save(owner);

        // retrieving new name from database
        owner = ownersRepository.findById(1);
        assertThat(owner.lastName).isEqualTo(newLastName);
    }

    @Test
    fun shouldFindPetWithCorrectId() {
        val pet7 = petsRepository.findById(7);
        assertThat(pet7.name).startsWith("Samantha");
        assertThat(pet7.owner!!.firstName).isEqualTo("Jean");

    }

    @Test
    fun shouldFindAllPetTypes() {
        val petTypes = petsRepository.findPetTypes();

        /*val petType1 = EntityUtils.getById(petTypes, PetType.class, 1);
        assertThat(petType1.getName()).isEqualTo("cat");
        PetType petType4 = EntityUtils.getById(petTypes, PetType.class, 4);
        assertThat(petType4.getName()).isEqualTo("snake");*/
    }

    @Test
    @Transactional
    fun shouldInsertPetIntoDatabaseAndGenerateId() {
        val owner6 = ownersRepository.findById(6);
        val found = owner6.pets.size;

        var pet = Pet();
        pet.name="bowser";
        val types = petsRepository.findPetTypes();
        /*pet.type= EntityUtils.getById(types, PetType.class, 2));
        pet.setBirthDate(new Date());
        owner6.addPet(pet);
        assertThat(owner6.getPets().size()).isEqualTo(found + 1);

        this.pets.save(pet);
        this.owners.save(owner6);

        owner6 = this.owners.findById(6);
        assertThat(owner6.getPets().size()).isEqualTo(found + 1);
        // checks that id has been generated
        assertThat(pet.getId()).isNotNull();*/
    }

    @Test
    @Transactional
    fun shouldUpdatePetName() {
        var pet7 = petsRepository.findById(7);
        val oldName = pet7.name

        val newName = oldName + "X";
        pet7.name= newName;
        petsRepository.save(pet7);

        pet7 = petsRepository.findById(7);
        assertThat(pet7.name).isEqualTo(newName);
    }

    @Test
    fun shouldFindVets() {
        val vets = vetsRepository.findAll();

        /*val vet = EntityUtils.getById(vets, Vet.class, 3);
        assertThat(vet.getLastName()).isEqualTo("Douglas");
        assertThat(vet.getNrOfSpecialties()).isEqualTo(2);
        assertThat(vet.getSpecialties().get(0).getName()).isEqualTo("dentistry");
        assertThat(vet.getSpecialties().get(1).getName()).isEqualTo("surgery");*/
    }

    @Test
    @Transactional
    fun shouldAddNewVisitForPet() {
        var pet7 = petsRepository.findById(7);
        val found = pet7.visits.size;
        var visit = Visit();
        pet7.addVisit(visit);
        visit.description ="test"
        visitsRepository.save(visit);
        petsRepository.save(pet7);

        pet7 = petsRepository.findById(7);
        assertThat(pet7.visits.size).isEqualTo(found + 1);
        assertThat(visit.id).isNotNull();
    }

    @Test
    fun shouldFindVisitsByPetId() {
        val visits = visitsRepository.findByPetId(7);
        assertThat(visits.size).isEqualTo(2);
        val visit = visits.get(0)
        assertThat(visit.date).isNotNull();
        assertThat(visit.petId).isEqualTo(7);
    }

}