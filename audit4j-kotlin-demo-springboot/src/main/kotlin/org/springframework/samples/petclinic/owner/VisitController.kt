package org.springframework.samples.petclinic.owner

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
class VisitController(val visits: VisitRepository, val pets: PetRepository) {

    @InitBinder
    fun setAllowedFields(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    
    /**
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param petId
     * @return Pet
     */
	@ModelAttribute("visit")
    fun loadPetWithVisit(@PathVariable("petId") petId: Int, model: MutableMap<String, Any>): Visit {
        val pet = pets.findById(petId)
        model.put("pet", pet)
        val visit = Visit()
        pet.addVisit(visit)
        return visit
    }
	
	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping(value = "/owners/*/pets/{petId}/visits/new")
    fun initNewVisitForm(@PathVariable("petId") petId: Int, model: Map<String, Any>): String
            = "pets/createOrUpdateVisitForm"

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping(value = "/owners/{ownerId}/pets/{petId}/visits/new")
    fun processNewVisitForm(@Valid visit: Visit, result: BindingResult): String {
        return if (result.hasErrors()) {
            "pets/createOrUpdateVisitForm"
        } else {
            visits.save(visit)
            "redirect:/owners/{ownerId}"
        }
    }
    
}