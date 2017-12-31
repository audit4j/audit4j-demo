package org.springframework.samples.petclinic.owner

import java.util.ArrayList
import java.util.Collections
import java.util.HashSet

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.Digits

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.beans.support.MutableSortDefinition
import org.springframework.beans.support.PropertyComparator
import org.springframework.core.style.ToStringCreator
import org.springframework.samples.petclinic.model.Person

@Entity
@Table(name = "owners")
public class Owner : Person() {
    @Column(name = "address")
    @NotEmpty
    var address = ""

    @Column(name = "city")
    @NotEmpty
    var city =""

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    var telephone =""

	@OneToMany(cascade = arrayOf(CascadeType.ALL), mappedBy = "owner")
	var pets: MutableSet<Pet> = HashSet()


    fun getPets(): List<Pet> = pets.sortedWith(compareBy({ it.name }))


    fun addPet(pet: Pet) {
        if (pet.isNew) {
            pets.add(pet)
        }
        pet.owner = this
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    fun getPet(name: String): Pet? = getPet(name, false)

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
     fun getPet(name: String, ignoreNew: Boolean): Pet? {
        val lname = name.toLowerCase()
        for (pet in pets) {
            if (!ignoreNew || !pet.isNew) {
                val compName = pet.name?.toLowerCase()
                if (compName == lname) {
                    return pet
                }
            }
        }
        return null
    }

    override
    fun toString(): String  {
        return ToStringCreator(this)
            .append("id", this.id)
            .append("new", this.isNew)
            .append("lastName", this.lastName)
            .append("firstName", this.firstName)
            .append("address", this.address)
            .append("city", this.city)
            .append("telephone", this.telephone)
            .toString();
    }
}

