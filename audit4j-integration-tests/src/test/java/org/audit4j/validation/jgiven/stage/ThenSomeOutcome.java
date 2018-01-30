package org.audit4j.validation.jgiven.stage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.audit4j.validation.jgiven.enumeration.Version;
import org.audit4j.validation.jgiven.util.TesterUtil;
import org.junit.contrib.java.lang.system.SystemOutRule;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.Hidden;
import com.tngtech.jgiven.annotation.Quoted;

public class ThenSomeOutcome extends Stage<ThenSomeOutcome> {


	public ThenSomeOutcome the_console_log_contains_the_expected_output_for_event_builder(@Hidden SystemOutRule systemOutRule) {
		assertTrue(systemOutRule.getLog().contains(TesterUtil.expectedOutputEV));
		return self();	
		
	}
	
	public ThenSomeOutcome the_console_log_contains_the_expected_output_for_audit_event(@Hidden SystemOutRule systemOutRule) {
		assertTrue(systemOutRule.getLog().contains(TesterUtil.expectedOutputAE));
		return self();	
		
	}

	
	public ThenSomeOutcome the_console_log_does_not_contains_messages_for_event_builder(@Hidden SystemOutRule systemOutRule) {
		assertFalse(systemOutRule.getLog().contains(TesterUtil.expectedOutputEV));
		

		return self();	
		
	}

	public ThenSomeOutcome the_console_log_contains_the_audit4j_logo_with_version_$(@Hidden SystemOutRule systemOutRule,@Quoted Version version) {
		assertTrue(systemOutRule.getLog(),systemOutRule.getLog().contains("  / ___ \\ |_| | (_| | | |_|__   _| |\r\n"));
		assertTrue(systemOutRule.getLog(),systemOutRule.getLog().contains(version.toString()));
		return self();	
	}

	public ThenSomeOutcome the_file_log_contains_$_line(int nbLines, @Hidden String path) throws IOException {
		
		assertEquals(nbLines, getTempFileContent(path).size());
		
		return self();
	}


	private List<String> getTempFileContent(String filePath) throws IOException  {
		
		List<String> lines = new ArrayList<>();
			
		try {
			BufferedReader br = new BufferedReader(
					new FileReader(filePath));

			String line = br.readLine();	
			while (line != null) {
				lines.add(line);
				line = br.readLine();

			}
			br.close();
		} catch (FileNotFoundException e) {
			//no file found 
			return lines;
		} 
		
		return lines;
	}

	private boolean contains(List<String> lines, String linePart) {
		
		for(String line : lines) {
			if(line.contains(linePart)) return true;
		}
		return false;
	}
	
	public ThenSomeOutcome the_file_log_contains_the_expected_output_for_event_builder(@Hidden String filePath) throws IOException {
		List<String> lines = getTempFileContent(filePath);
		assertTrue(lines.toString(),contains(lines,TesterUtil.expectedOutputEV));
		return self();
	}
	
	public ThenSomeOutcome the_file_log_contains_the_expected_output_for_audit_event(@Hidden String filePath) throws IOException {
		List<String> lines = getTempFileContent(filePath);
		assertTrue(lines.toString(),contains(lines,TesterUtil.expectedOutputAE));
		return self();
	}


}