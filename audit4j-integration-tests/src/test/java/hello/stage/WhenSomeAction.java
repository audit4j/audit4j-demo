package hello.stage;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.Hidden;
import com.tngtech.jgiven.annotation.Quoted;

import hello.GreetingController;
import hello.data.User;

public class WhenSomeAction extends Stage<WhenSomeAction> {

	public void the_method_$_annotated_with_audited_is_called_with_the_arguments_$_$_$(
			@Quoted String methodName, @Quoted User arg0, 
			@Quoted int val, 
			@Quoted List array,
			@Hidden GreetingController greetingController) {
		
		User[] ints = new User[2];
		ints[0]=new User();
		ints[1]=new User();
		
		greetingController.foo1(ints, new BigDecimal(1));
		
	}

}
