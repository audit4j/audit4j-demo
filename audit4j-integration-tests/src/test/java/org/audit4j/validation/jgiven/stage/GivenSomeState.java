package org.audit4j.validation.jgiven.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.audit4j.core.AuditManager;
import org.audit4j.core.Configuration;
import org.audit4j.core.DummyMetaData;
import org.audit4j.core.handler.ConsoleAuditHandler;
import org.audit4j.core.handler.Handler;
import org.audit4j.core.handler.file.FileAuditHandler;
import org.audit4j.core.layout.SimpleLayout;
import org.junit.rules.TemporaryFolder;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.Hidden;
import com.tngtech.jgiven.annotation.Quoted;

public class GivenSomeState extends Stage<GivenSomeState> {
	
	private Configuration getConfigurationForConsoleHandler() {
        Configuration conf = new Configuration();
        List<Handler> handlers = new ArrayList<Handler>();
        handlers.add(new ConsoleAuditHandler());
        conf.setHandlers(handlers);
        conf.setLayout(new SimpleLayout());
        conf.setMetaData(new DummyMetaData());
        
		return conf;
	}
	
	private Configuration getConfigurationForFileHandler(TemporaryFolder folder) {
		Configuration conf = new Configuration();
		List<Handler> handlers = new ArrayList<Handler>();

		FileAuditHandler fileAuditHandler = new FileAuditHandler();
		handlers.add(fileAuditHandler);
		conf.setHandlers(handlers);
		conf.setLayout(new SimpleLayout());
		conf.setMetaData(new DummyMetaData());

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("log.file.location", folder.getRoot().getAbsolutePath());
		conf.setProperties(properties);

		return conf;
        
	}
	
	private Configuration getConfigurationForConsoleAndFileHandler(TemporaryFolder folder) {
		Configuration conf = new Configuration();
		List<Handler> handlers = new ArrayList<Handler>();

		FileAuditHandler fileAuditHandler = new FileAuditHandler();
		handlers.add(fileAuditHandler);
        handlers.add(new ConsoleAuditHandler());
		conf.setHandlers(handlers);
		conf.setLayout(new SimpleLayout());
		conf.setMetaData(new DummyMetaData());

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("log.file.location", folder.getRoot().getAbsolutePath());
		conf.setProperties(properties);

		return conf;
        
	}


	public GivenSomeState audit4j_starting_with_a_programming_configuration_using_ConsoleAuditHandler() {
		AuditManager.startWithConfiguration(getConfigurationForConsoleHandler());
		return self();
		
	}
	
	public GivenSomeState audit4j_starting_with_a_programming_configuration_using_FileAuditHandler(@Hidden TemporaryFolder folder) {
		AuditManager.startWithConfiguration(getConfigurationForFileHandler(folder));
		return self();
	}
	
	public GivenSomeState audit4j_starting_with_a_programming_configuration_using_ConsoleAuditHandler_and_FileAuditHandler(@Hidden TemporaryFolder folder) {
		AuditManager.startWithConfiguration(getConfigurationForConsoleAndFileHandler(folder));
		return self();
	}


	public GivenSomeState audit4j_starting_with_the_configuration_defined_in_the_file_$_for_$(@Quoted String confFileName, List<String> handlerType) {
		AuditManager.startWithConfiguration("./src/test/resources/"+confFileName);
		return self();
		
	}
	




	
	
}
