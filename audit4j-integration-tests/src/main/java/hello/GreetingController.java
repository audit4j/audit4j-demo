package hello;

import org.audit4j.core.annotation.Audit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class GreetingController {
	
	static Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);
	
	@Audit
    @RequestMapping("/hello")
    public String hello() {
    	LOGGER.info("hello called");
        return "hello";
    }

	@Audit
	public void foo1(String string1, String string2) {
		// TODO Auto-generated method stub
		
	}
	

    

}
