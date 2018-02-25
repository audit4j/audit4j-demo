package hello;


import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tngtech.jgiven.junit.ScenarioTest;

import hello.data.User;
import hello.stage.GivenSomeState;
import hello.stage.ThenSomeOutcome;
import hello.stage.WhenSomeAction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAuditedTest 
    extends ScenarioTest<GivenSomeState, WhenSomeAction, ThenSomeOutcome> {
	
	@Autowired
	private GreetingController greetingController;
	

	@Ignore
	@Test
	public void springbootApplicationWithAuditedMethodTest() {
		
		List<User> ints = new ArrayList<User>();
		ints.add(new User());
		ints.add(new User());
		
		given().spring_boot_application_started_with_audit4j_with_a_programming_configuration_using_ConsoleAuditHandler();
		when().the_method_$_annotated_with_audited_is_called_with_the_arguments_$_$_$("foo", new User(),1, ints, greetingController );
        then().the_file_log_contains_$_line(1, "getFilePath()");
       // .and().the_file_log_contains_the_expected_output_for_event_builder(getFilePath());

	}

}
