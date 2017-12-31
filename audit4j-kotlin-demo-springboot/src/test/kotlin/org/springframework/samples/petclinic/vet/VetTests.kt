package org.springframework.samples.petclinic.vet

import org.junit.Test;

import org.springframework.util.SerializationUtils;

import org.assertj.core.api.Assertions.assertThat;

class VetTests {

    @Test
    fun testSerialization() {
        var vet = Vet();
        vet.firstName="Zaphod"
        vet.lastName= "Beeblebrox"
        vet.id= 123
        var other = SerializationUtils
                .deserialize(SerializationUtils.serialize(vet)) as Vet
        assertThat(other.firstName).isEqualTo(vet.firstName)
        assertThat(other.lastName).isEqualTo(vet.lastName)
        assertThat(other.id).isEqualTo(vet.id!!)
    }

}