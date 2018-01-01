package org.springframework.samples.petclinic.system


import org.audit4j.core.annotation.Audit
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
open class WelcomeController {

	@Audit
    @GetMapping("/")
    open fun welcome() = "welcome"
}
