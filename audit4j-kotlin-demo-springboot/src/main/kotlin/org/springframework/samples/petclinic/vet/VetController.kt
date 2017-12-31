package org.springframework.samples.petclinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
class VetController(val vetRepository: VetRepository) {


	@GetMapping("/vets.html")
    fun showHtmlVetList(model: MutableMap<String, Any>): String {
        val vets = Vets(vetRepository.findAll())
        model.put("vets", vets)
        return "vets/vetList"
	 }

    @GetMapping(path = arrayOf("vets.json", "vets.xml"), produces = arrayOf("application/json"))
    @ResponseBody
    fun showJsonVetList(): Vets =
            // Here we are returning an object of type 'Vets' rather than a collection of Vet
            // objects so it is simpler for Json/Object mapping
            Vets(vetRepository.findAll())

}