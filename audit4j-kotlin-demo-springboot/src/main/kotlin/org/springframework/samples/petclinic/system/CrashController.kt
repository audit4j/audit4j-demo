package org.springframework.samples.petclinic.system

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller used to showcase what happens when an exception is thrown
 *
 * @author Michael Isvy
 * <p/>
 * Also see how a view that resolves to "error" has been added ("error.html").
 */
@Controller
class CrashController {

    @GetMapping("/oups")
    fun triggerException() : String {
        throw RuntimeException(
                "Expected: controller used to showcase what " + "happens when an exception is thrown");
    }

}