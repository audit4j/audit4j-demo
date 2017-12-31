package org.springframework.samples.petclinic.owner

import java.util.ArrayList
import java.util.Collections
import java.util.Date
import java.util.HashSet
import java.util.LinkedHashSet

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

import org.springframework.beans.support.MutableSortDefinition
import org.springframework.beans.support.PropertyComparator
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.samples.petclinic.model.NamedEntity
import org.springframework.samples.petclinic.visit.Visit


@Entity
@Table(name = "pets")
class Pet : NamedEntity() {

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var birthDate: Date? = null

    @ManyToOne
    @JoinColumn(name = "type_id")
    var type: PetType? = null

    @ManyToOne
    @JoinColumn(name = "owner_id")
    var owner: Owner? = null

    @OneToMany(cascade = arrayOf(CascadeType.ALL), mappedBy = "petId", fetch = FetchType.EAGER)
    var visits: MutableSet<Visit> = LinkedHashSet()


    fun getVisits(): List<Visit> =
            visits.sortedWith(compareBy { it.date })

    fun addVisit(visit: Visit) {
        visits.add(visit)
        visit.petId = this.id
    }

}

