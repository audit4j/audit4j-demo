package org.springframework.samples.petclinic.owner

import org.junit.Assert.assertEquals

import java.text.ParseException
import java.util.ArrayList
import java.util.Locale

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.samples.petclinic.owner.PetRepository
import org.springframework.samples.petclinic.owner.PetType
import org.springframework.samples.petclinic.owner.PetTypeFormatter

/**
 * Test class for {@link PetTypeFormatter}
 *
 * @author Colin But
 */
@RunWith(MockitoJUnitRunner::class)
class PetTypeFormatterTests {
	
    @Mock
    lateinit private var pets: PetRepository

    lateinit private var petTypeFormatter: PetTypeFormatter



    @Before
    fun setup() {
        this.petTypeFormatter = PetTypeFormatter(pets);
    }

    @Test
    fun testPrint() {
        var petType = PetType()
        petType.name="Hamster"
        val petTypeName = petTypeFormatter.print(petType, Locale.ENGLISH)
        assertEquals("Hamster", petTypeName)
    }

    @Test
    fun shouldParse()  {
        Mockito.`when`(this.pets.findPetTypes()).thenReturn(makePetTypes());
        val petType = petTypeFormatter.parse("Bird", Locale.ENGLISH);
        assertEquals("Bird", petType.name);
    }

    @Test(expected = ParseException::class)
    fun shouldThrowParseException() {
        Mockito.`when`(this.pets.findPetTypes()).thenReturn(makePetTypes());
        petTypeFormatter.parse("Fish", Locale.ENGLISH);
    }

    /**
     * Helper method to produce some sample pet types just for test purpose
     *
     * @return {@link Collection} of {@link PetType}
     */
    private fun makePetTypes(): List<PetType> {
        val petTypes = ArrayList<PetType>()
        petTypes.add(object : PetType() {
            init {
                name = "Dog"
            }
        })
        petTypes.add(object : PetType() {
            init {
                name = "Bird"
            }
        })
        return petTypes
    }


}
