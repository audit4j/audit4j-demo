package org.audt4j.demo.spring.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.audit4j.core.handler.ConsoleAuditHandler;
import org.audit4j.core.handler.Handler;
import org.audit4j.core.handler.file.FileAuditHandler;
import org.audit4j.core.layout.SimpleLayout;
import org.audit4j.handler.db.DatabaseAuditHandler;
import org.audit4j.integration.spring.AuditAspect;
import org.audit4j.integration.spring.SpringAudit4jConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AuditConfig {

    @Bean
    public AuditAspect auditAspect() {
        AuditAspect auditAspect = new AuditAspect();
        return auditAspect;
    }

    // If you want to load configurations from file
    // (resources/audit4j.conf.yaml),
    // comment below method
    @Bean
    public DatabaseAuditHandler databaseHandler() {
        DatabaseAuditHandler dbHandler = new DatabaseAuditHandler();
        dbHandler.setEmbedded("true");
        return dbHandler;
    }
    
    private Map<String,String> getProperties() {
    	Map<String,String> properties = new HashMap<String,String>();
    	
    	properties.put("log.file.location", ".");
    	
    	return properties; 
    }

    // If you want to load configurations from file
    // (resources/audit4j.conf.yaml),
    // comment below method
    @Bean
    public SpringAudit4jConfig springAudit4jConfig() {
        SpringAudit4jConfig springAudit4jConfig = new SpringAudit4jConfig();
        springAudit4jConfig.setLayout(new SimpleLayout());
        List<Handler> handlers = new ArrayList<Handler>();
        handlers.add(new ConsoleAuditHandler());
        handlers.add(new FileAuditHandler());
        handlers.add(databaseHandler());
        
        springAudit4jConfig.setHandlers(handlers);
        springAudit4jConfig.setMetaData(new AuditMetaData());
        
        springAudit4jConfig.setProperties(getProperties());
        
        return springAudit4jConfig;
    }

}
