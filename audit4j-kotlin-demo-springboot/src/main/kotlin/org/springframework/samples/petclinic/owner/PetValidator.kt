package org.springframework.samples.petclinic.owner

import org.springframework.util.StringUtils
import org.springframework.validation.Errors
import org.springframework.validation.Validator

class PetValidator : Validator {

	
	companion object {
        const val REQUIRED = "required"
	}

    override
    fun validate(obj: Any?, errors: Errors) {
        if (obj == null) {
            return
        }
        val pet = obj as Pet
        val name = pet.name;
        // name validation
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }

        // type validation
        if (pet.isNew && pet.type == null) {
            errors.rejectValue("type", REQUIRED, REQUIRED);
        }

        // birth date validation
        if (pet.birthDate == null) {
            errors.rejectValue("birthDate", REQUIRED, REQUIRED);
        }
    }

    /**
     * This Validator validates *just* Pet instances
     */
    override fun supports(clazz: Class<*>) = Pet::class.java.isAssignableFrom(clazz)


}