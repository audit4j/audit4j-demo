package org.audit4j.validation.jgiven;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.audit4j.validation.jgiven.enumeration.Version;
import org.audit4j.validation.jgiven.util.TesterUtil;
import org.audit4j.validation.jgiven.stage.GivenSomeState;
import org.audit4j.validation.jgiven.stage.ThenSomeOutcome;
import org.audit4j.validation.jgiven.stage.WhenSomeAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import com.tngtech.jgiven.junit.ScenarioTest;

public class FileAuditHandlerWithFileConfigTest extends ScenarioTest<GivenSomeState, WhenSomeAction, ThenSomeOutcome> {


    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    
	private static final String FILE_NAME = "audit4j-file.conf.yml";
	
    private String getFilePath() {
    	return ".\\"+TesterUtil.getAuditFileName();
    }
    
	private void deleteTempFile() {
		File file = new File(getFilePath());
		file.delete();
	}

	@Before
	public void before() {
		deleteTempFile();
	}
	
	@After
	public void after() {
		deleteTempFile();
	}
	
	@Test
	public void file_audit_handler_with_file_configuration_receiving_a_message_with_event_builder() throws IOException {

        given().audit4j_starting_with_the_configuration_defined_in_the_file_$_for_$(FILE_NAME, Arrays.asList("FileAuditHandler"));
        when().a_message_is_sent_to_the_audit_manager_with_event_builder()
        .and().audit_manager_is_stopped();
        then().the_console_log_contains_the_audit4j_logo_with_version_$(systemOutRule, Version.V250)
        .and().the_file_log_contains_$_line(1, getFilePath())
        .and().the_file_log_contains_the_expected_output_for_event_builder(getFilePath());
        
	}
	
	@Test
	public void file_audit_handler_with_file_configuration_receiving_a_message_with_audit_event() throws IOException {
		
        given().audit4j_starting_with_the_configuration_defined_in_the_file_$_for_$(FILE_NAME, Arrays.asList("FileAuditHandler"));
        when().a_message_is_sent_to_the_audit_manager_with_audit_event()
        .and().audit_manager_is_stopped();
        then().the_console_log_contains_the_audit4j_logo_with_version_$(systemOutRule, Version.V250)
        .and().the_file_log_contains_$_line(1, getFilePath())
        .and().the_file_log_contains_the_expected_output_for_audit_event(getFilePath());
        
		
	}
	
	@Test
	public void file_audit_handler_with_file_configuration_receiving_a_message_with_event_builder_during_disable_state() throws IOException {

        given().audit4j_starting_with_the_configuration_defined_in_the_file_$_for_$(FILE_NAME, Arrays.asList("FileAuditHandler"));
        when().audit_manager_is_disabled()
        .and().a_message_is_sent_to_the_audit_manager_with_event_builder()
        .and().audit_manager_is_stopped();
        then().the_console_log_contains_the_audit4j_logo_with_version_$(systemOutRule, Version.V250)
        .and().the_file_log_contains_$_line(0, getFilePath());
        

	}
}
