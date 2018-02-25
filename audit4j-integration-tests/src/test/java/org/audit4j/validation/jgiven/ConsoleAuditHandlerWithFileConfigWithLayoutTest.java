package org.audit4j.validation.jgiven;

import java.util.Arrays;

import org.audit4j.validation.jgiven.enumeration.Version;
import org.audit4j.validation.jgiven.stage.GivenSomeState;
import org.audit4j.validation.jgiven.stage.ThenSomeOutcome;
import org.audit4j.validation.jgiven.stage.WhenSomeAction;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import com.tngtech.jgiven.junit.ScenarioTest;

public class ConsoleAuditHandlerWithFileConfigWithLayoutTest 
	extends ScenarioTest<GivenSomeState, WhenSomeAction, ThenSomeOutcome> {
	
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	
	private static final String FILE_NAME = "audit4j-console-layout.conf.yml";
    
    @Test
    @Ignore // does not work in version 2.5.0
    public void console_audit_handler_with_file_configuration_receiving_a_message_with_event_builder() {
    	
        given().audit4j_starting_with_the_configuration_defined_in_the_file_$_for_$(FILE_NAME,Arrays.asList("ConsoleAuditHandler"));
        when().a_message_is_sent_to_the_audit_manager_with_event_builder()
        .and().audit_manager_is_stopped();
        then().the_console_log_contains_the_audit4j_logo_with_version_$(systemOutRule, Version.V250);
        //.and().the_console_log_contains_the_expected_output_for_event_builder(systemOutRule);
    }
	
   /* @Test
    public void console_audit_handler_with_file_configuration_receiving_a_message_with_audit_event() {
    
        given().audit4j_starting_with_the_configuration_defined_in_the_file_$_for_$(FILE_NAME,Arrays.asList("ConsoleAuditHandler"));
        when().a_message_is_sent_to_the_audit_manager_with_audit_event()
        .and().audit_manager_is_stopped();
        then().the_console_log_contains_the_audit4j_logo_with_version_$(systemOutRule, Version.V250)
        .and().the_console_log_contains_the_expected_output_for_audit_event(systemOutRule);
    }

	
	@Test
	public void console_audit_handler_with_file_configuration_receiving_a_message_with_event_builder_during_disable_state() {
		
        given().audit4j_starting_with_the_configuration_defined_in_the_file_$_for_$(FILE_NAME,Arrays.asList("ConsoleAuditHandler"));
        when().audit_manager_is_disabled()
        .and().a_message_is_sent_to_the_audit_manager_with_event_builder()
        .and().audit_manager_is_stopped();
        then().the_console_log_contains_the_audit4j_logo_with_version_$(systemOutRule, Version.V250)
        .but().the_console_log_does_not_contains_messages_for_event_builder(systemOutRule);
		
	}*/

}
