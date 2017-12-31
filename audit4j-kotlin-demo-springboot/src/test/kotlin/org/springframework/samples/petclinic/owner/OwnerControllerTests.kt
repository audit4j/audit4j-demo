package org.springframework.samples.petclinic.owner



import org.hamcrest.Matchers.hasProperty
import org.hamcrest.Matchers
import org.mockito.BDDMockito.given;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

import org.assertj.core.util.Lists
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.samples.petclinic.owner.Owner
import org.springframework.samples.petclinic.owner.OwnerController
import org.springframework.samples.petclinic.owner.OwnerRepository
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc


/**
 * Test class for {@link OwnerController}
 *
 * @author Colin But
 */
@RunWith(SpringRunner::class)
@WebMvcTest(OwnerController::class)
class OwnerControllerTests {

	companion object {

        private val TEST_OWNER_ID = 1
	}

    @MockBean
    lateinit private var owners: OwnerRepository

    lateinit private var george: Owner


    @Autowired
    lateinit private var mockMvc : MockMvc


    @Before
    fun setup() {
        george = Owner()
        george.id=TEST_OWNER_ID;
        george.firstName="George";
        george.lastName="Franklin";
        george.address="110 W. Liberty St.";
        george.city="Madison";
        george.telephone="6085551023";
        given(this.owners.findById(TEST_OWNER_ID)).willReturn(george);
    }

    @Test
    fun testInitCreationForm() {
        mockMvc.perform(get("/owners/new"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    fun testProcessCreationFormSuccess() {
        mockMvc.perform(post("/owners/new")
            .param("firstName", "Joe")
            .param("lastName", "Bloggs")
            .param("address", "123 Caramel Street")
            .param("city", "London")
            .param("telephone", "01316761638")
        )
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    fun testProcessCreationFormHasErrors() {
        mockMvc.perform(post("/owners/new")
            .param("firstName", "Joe")
            .param("lastName", "Bloggs")
            .param("city", "London")
        )
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeHasErrors("owner"))
            .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("owner", "address"))
            .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("owner", "telephone"))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    fun testInitFindForm() {
        mockMvc.perform(get("/owners/find"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
            .andExpect(view().name("owners/findOwners"));
    }

    @Test
    fun testProcessFindFormSuccess() {
        given(this.owners.findByLastName("")).willReturn(Lists.newArrayList(george, Owner()));
        mockMvc.perform(get("/owners"))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/ownersList"));
    }

    @Test
    fun testProcessFindFormByLastName() {
        given(this.owners.findByLastName(george.lastName)).willReturn(Lists.newArrayList(george));
        mockMvc.perform(get("/owners")
            .param("lastName", "Franklin")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/" + TEST_OWNER_ID));
    }

    @Test
    fun testProcessFindFormNoOwnersFound() {
        mockMvc.perform(get("/owners")
            .param("lastName", "Unknown Surname")
        )
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("owner", "lastName"))
            .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrorCode("owner", "lastName", "notFound"))
            .andExpect(view().name("owners/findOwners"));
    }

    @Test
    fun testInitUpdateOwnerForm() {
        mockMvc.perform(get("/owners/{ownerId}/edit", TEST_OWNER_ID))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
            //.andExpect(MockMvcResultMatchers.model().attribute("owner", hasProperty("lastName", Matchers.`is`("Franklin"))))
            //.andExpect(MockMvcResultMatchers.model().attribute("owner", hasProperty("firstName", Matchers.`is`("George"))))
            //.andExpect(MockMvcResultMatchers.model().attribute("owner", hasProperty("address", Matchers.`is`("110 W. Liberty St."))))
            //.andExpect(MockMvcResultMatchers.model().attribute("owner", hasProperty("city", Matchers.`is`("Madison"))))
            //.andExpect(MockMvcResultMatchers.model().attribute("owner", hasProperty("telephone", Matchers.`is`("6085551023"))))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    fun testProcessUpdateOwnerFormSuccess() {
        mockMvc.perform(post("/owners/{ownerId}/edit", TEST_OWNER_ID)
            .param("firstName", "Joe")
            .param("lastName", "Bloggs")
            .param("address", "123 Caramel Street")
            .param("city", "London")
            .param("telephone", "01616291589")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    fun testProcessUpdateOwnerFormHasErrors() {
        mockMvc.perform(post("/owners/{ownerId}/edit", TEST_OWNER_ID)
            .param("firstName", "Joe")
            .param("lastName", "Bloggs")
            .param("city", "London")
        )
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeHasErrors("owner"))
            .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("owner", "address"))
            .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("owner", "telephone"))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"))
    }

    @Test
    fun testShowOwner() {
        mockMvc.perform(get("/owners/{ownerId}", TEST_OWNER_ID))
            .andExpect(status().isOk())
            //.andExpect(MockMvcResultMatchers.model().attribute("owner", hasProperty("lastName", Matchers.`is`("Franklin"))))
            //.andExpect(MockMvcResultMatchers.model().attribute("owner", hasProperty("firstName", Matchers.`is`("George"))))
            //.andExpect(MockMvcResultMatchers.model().attribute("owner", hasProperty("address", Matchers.`is`("110 W. Liberty St."))))
            //.andExpect(MockMvcResultMatchers.model().attribute("owner", hasProperty("city", Matchers.`is`("Madison"))))
            //.andExpect(MockMvcResultMatchers.model().attribute("owner", hasProperty("telephone", Matchers.`is`("6085551023"))))
            .andExpect(view().name("owners/ownerDetails"));
    }

}