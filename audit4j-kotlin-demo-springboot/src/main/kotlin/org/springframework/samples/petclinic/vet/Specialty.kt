package org.springframework.samples.petclinic.vet

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

/**
 * Models a {@link Vet Vet's} specialty (for example, dentistry).
 *
 * @author Juergen Hoeller
 */
@Entity
@Table(name = "specialties")
class Specialty : NamedEntity(), Serializable {

}
