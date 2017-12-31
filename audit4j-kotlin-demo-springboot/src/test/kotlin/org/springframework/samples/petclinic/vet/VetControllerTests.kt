package org.springframework.samples.petclinic.vet

import org.hamcrest.xml.HasXPath.hasXPath;
import org.mockito.BDDMockito.given;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.vet.Specialty;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetController;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Test class for the {@link VetController}
 */
@RunWith(SpringRunner::class)
@WebMvcTest(VetController::class)
class VetControllerTests {

    @Autowired
    lateinit private var mockMvc : MockMvc
	
    @MockBean
    lateinit private var vets: VetRepository
	
	
	
    @Before
    fun setup() {
        var james = Vet();
        james.firstName ="James"
        james.lastName="Carter"
        james.id =1
        var helen = Vet()
        helen.firstName="Helen"
        helen.lastName="Leary"
        helen.id=2;
        var radiology = Specialty();
        radiology.id=1;
        radiology.name="radiology";
        helen.addSpecialty(radiology);
        given(this.vets.findAll()).willReturn(Lists.newArrayList(james, helen));
    }

    @Test
    fun testShowVetListHtml() {
        mockMvc.perform(get("/vets.html"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("vets"))
            .andExpect(view().name("vets/vetList"));
    }

    @Test
    fun testShowResourcesVetList() {
        var actions = mockMvc.perform(get("/vets.json").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        actions.andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.vetList[0].id").value(1));
    }

    @Test
    fun testShowVetListXml() {
        mockMvc.perform(get("/vets.xml").accept(MediaType.APPLICATION_XML))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
            .andExpect(content().node(hasXPath("/vets/vetList[id=1]/id")));
    }

}
