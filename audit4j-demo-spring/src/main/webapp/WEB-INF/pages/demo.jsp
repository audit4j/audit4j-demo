<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Audit4j Demo Application</title>
</head>
<body>

	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<h3>Test Without Annotation</h3>
	<p>
		Please see <a
			href="http://audit4j.org/documentation/#sending_events_call">this</a>
		documentation section for more details.
	<p>

		Example from:
		<code>org.audt4j.demo.spring.service.impl.InventoryServiceImpl</code>
	<pre>
	<code>
  @Override
  public void addItem(Item item) {
    AuditManager.getInstance().audit(
      new EventBuilder().addAction("addInventoryItem").addField("itemName", item.getName())
        .addField("itemValue", item.getValue()).build());
        
     //Method Body
  }
    </code>
	</pre>

	<h4>
		<a href="/testWithoutAnnotations">Click here to test</a>
	</h4>
	<br />


	<!-- ######################################### -->
	<h3>Test Class Annotation</h3>
	<p>
		Please see <a href="http://audit4j.org/documentation/#annotations">this</a>
		documentation section for more details.
	<p>

		Example from:
		<code>org.audt4j.demo.spring.service.impl.UserServiceImpl</code>
	<pre>
	<code>
  @Audit
  public class UserServiceImpl implements UserService {
  
    @Override
      public User getUserByuserName(String userName) {
      // Method Body
    }
    
    // Class
  }
    </code>
	</pre>

	<h4>
		<a href="/testClassAnnotation">Click here to test</a>
	</h4>
	<br />


	<!-- ######################################### -->
	<h3>Test Method Annotation</h3>
	<h4>Test Method without mark</h4>
	<p>
		Please see <a href="http://audit4j.org/documentation/#annotations">this</a>
		documentation section for more details.
	<p>

		Example from:
		<code>org.audt4j.demo.spring.service.impl.PaymentServiceImpl</code>
	<pre>
	<code>
  @Override
  @Audit(action = "checkoutPayment")
  public void checkout(Payment payment) {
	//Method Body
  }
    </code>
	</pre>

	<h4>
		<a href="/testMethodAnnotationWithoutMark">Click here to test</a>
	</h4>
	<br />


	<!-- ######################################### -->
	<h4>Test Marked method</h4>
	<p>
		Please see <a href="http://audit4j.org/documentation/#annotations">this</a>
		documentation section for more details.
		<p>

		Example from:
		<code>org.audt4j.demo.spring.service.impl.PaymentServiceImpl</code> 
	
	<pre>
	<code>
  @Audit
  public void savePayment(@AuditField(field = "paymentUserId") String userId,
          @AuditField(field = "paymentItems") List<Item> items) {
  }
    </code>
	</pre>
	
	<h4>
		<a href="/testMethodAnnotationMarked">Click here to test</a>
	</h4>
	<br />


<!-- ######################################### -->
	<h3>Test Ignore Audit Annotation</h3>
	<p>
	Please see <a href="http://audit4j.org/documentation/#annotations">this</a> documentation section for more details.
	<p>
	
	Example from: <code>org.audt4j.demo.spring.service.impl.UserServiceImpl</code> 
	
	<pre>
	<code>
  @Override
  @IgnoreAudit
  public void changePassword(String oldPassword, String newPassword) {
      // Method Body

  }
    </code>
	</pre>
	
	<h4>
		<a href="/testIgnoreAuditAnnotation">Click here to test</a>
	</h4>
	<br />



<!-- ######################################### -->	
	
	<h3>Test De-identify Annotation</h3>
		<p>
	Please see <a href="http://audit4j.org/documentation/#annotations">this</a> documentation section for more details.
	<p>
	
	Example from: <code>org.audt4j.demo.spring.service.impl.PaymentServiceImpl</code> 
	
	<pre>
	<code>
  @Override
  @Audit
  public void saveCreditCard(@AuditField(field = "cardUserName") String validName,
          @AuditField(field = "expiryDate") Date expiry,
          @AuditField(field = "cardNumber") @DeIdentify(fromRight = 4) String cardNumber) {
    //Method Body
  }
    
    </code>
	</pre>
	
	<h4>
		<a href="/testDeidentifyAnnotation">Click here to test</a>
	</h4>
	<br />
	
	
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h4 align="right">
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h4>
	</c:if>
	
	
	

</body>
</html>