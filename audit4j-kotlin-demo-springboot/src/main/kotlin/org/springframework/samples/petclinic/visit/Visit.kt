package org.springframework.samples.petclinic.visit

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;

/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 * @author Dave Syer
 */
@Entity
@Table(name = "visits")
class Visit : BaseEntity() {

    /**
     * Holds value of property date.
     */
    @Column(name = "visit_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var date: Date = Date()

    /**
     * Holds value of property description.
     */
    @NotEmpty
    @Column(name = "description")
    var description: String? = null


    /**
     * Holds value of property owner.
     */
    @Column(name = "pet_id")
    var petId: Int? = null
}