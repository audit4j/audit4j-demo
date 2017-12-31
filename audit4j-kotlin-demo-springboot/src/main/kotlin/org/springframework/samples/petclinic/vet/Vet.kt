package org.springframework.samples.petclinic.vet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.samples.petclinic.model.Person;

/**
 * Simple JavaBean domain object representing a veterinarian.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Arjen Poutsma
 */
@Entity
@Table(name = "vets")
class Vet : Person() {
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties", joinColumns = arrayOf(JoinColumn(name = "vet_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "specialty_id")))
    var specialties: MutableSet<Specialty> = HashSet()


    @XmlElement
    fun getSpecialties(): List<Specialty> =
            specialties.sortedWith(compareBy { it.name })

    fun getNrOfSpecialties(): Int =
            specialties.size


    fun addSpecialty(specialty: Specialty) =
            specialties.add(specialty)

}