package org.springframework.samples.petclinic.owner

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 * Instructs Spring MVC on how to parse and print elements of type 'PetType'. Starting from Spring 3.0, Formatters have
 * come as an improvement in comparison to legacy PropertyEditors. See the following links for more details: - The
 * Spring ref doc: http://static.springsource.org/spring/docs/current/spring-framework-reference/html/validation.html#format-Formatter-SPI
 * - A nice blog entry from Gordon Dickens: http://gordondickens.com/wordpress/2010/09/30/using-spring-3-0-custom-type-converter/
 * <p/>
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @author Michael Isvy
 */
@Component
class PetTypeFormatter(val pets: PetRepository) : Formatter<PetType> {


	override fun print(petType: PetType, locale: Locale): String
                = petType.name ?: ""


    override fun parse(text: String, locale: Locale): PetType {
        val findPetTypes = this.pets.findPetTypes()
        return findPetTypes.find { it.name == text } ?:
                    throw ParseException("type not found: " + text, 0)
	}

}