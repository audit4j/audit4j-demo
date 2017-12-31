package org.springframework.samples.petclinic.system

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Test class for {@link CrashController}
 *
 * @author Colin But
 */
@RunWith(SpringRunner::class)
// Waiting https://github.com/spring-projects/spring-boot/issues/5574
@Ignore
@WebMvcTest(controllers = arrayOf(CrashController::class))
class CrashControllerTests {

    @Autowired
    lateinit private var mockMvc: MockMvc

    @Test
    fun testTriggerException() {
        mockMvc.perform(get("/oups")).andExpect(view().name("exception"))
                .andExpect(model().attributeExists("exception"))
                .andExpect(forwardedUrl("exception")).andExpect(status().isOk())
    }
}