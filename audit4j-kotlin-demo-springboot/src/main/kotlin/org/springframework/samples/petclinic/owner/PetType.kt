package org.springframework.samples.petclinic.owner


import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

@Entity
@Table(name = "types")
open class PetType : NamedEntity()