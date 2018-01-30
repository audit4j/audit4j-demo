package org.audit4j.validation.jgiven.stage;

import org.audit4j.core.AuditManager;
import org.audit4j.validation.jgiven.util.TesterUtil;

import com.tngtech.jgiven.Stage;

public class WhenSomeAction extends Stage<WhenSomeAction> {


	public WhenSomeAction a_message_is_sent_to_the_audit_manager_with_event_builder() {
		
		TesterUtil.sendMessageWithEventBuilder();
		
		return self();
		
	}

	public WhenSomeAction audit_manager_is_stopped() {
		AuditManager.shutdown();
		return self();
		
	}

	public WhenSomeAction a_message_is_sent_to_the_audit_manager_with_audit_event() {
		TesterUtil.sendMessageWithAuditEvent();	
		return self();
	}

	public WhenSomeAction audit_manager_is_disabled() {
		AuditManager.disable();
		return self();
	}


}