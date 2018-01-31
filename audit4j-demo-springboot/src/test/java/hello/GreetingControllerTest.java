package hello;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingControllerTest {
	
	@Autowired
	private GreetingController greetingController;

	@Test
	public void test() {
		//greetingController.hello();
		greetingController.foo1("e1","e2");
		greetingController.foo2("f1","f2");
	}

}
