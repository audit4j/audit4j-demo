package org.audit4j.demo;

import org.audit4j.core.annotation.Audit;
import org.audit4j.core.annotation.AuditField;

public class CardManagement {
	
	@Audit
	public String decrypt(@AuditField(field="encryptedCardNumber") 
		String encryptedCardNumber, 
		@AuditField(field="algo")
		String algo) {
		//dummy value
		return "123456790123456";
	}

}
