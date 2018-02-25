package hello;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.audit4j.core.annotation.Audit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.data.User;


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
	public void foo1(User[] d1, BigDecimal d2) {
		// TODO Auto-generated method stub
		
	}
	

    

}
