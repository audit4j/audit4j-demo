package org.springframework.samples.petclinic

import java.util.ArrayList;
import java.util.HashMap;

import org.audit4j.core.dto.AuditEvent;
import org.audit4j.core.handler.ConsoleAuditHandler;
import org.audit4j.core.handler.Handler;
import org.audit4j.core.handler.file.FileAuditHandler;
import org.audit4j.core.layout.CustomizableLayout;
import org.audit4j.core.layout.Layout;
import org.audit4j.core.layout.SimpleLayout;
//import org.audit4j.handler.db.DatabaseAuditHandler;
import org.audit4j.integration.spring.AuditAspect;
import org.audit4j.integration.spring.SpringAudit4jConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAspectJAutoProxy
//@Profile("production")
open class AuditConfig {

    @Bean
    open fun auditAspect() = AuditAspect();
        
    // If you want to load configurations from file
    // (resources/audit4j.conf.yaml),
    // comment below method
    /*@Bean
    public DatabaseAuditHandler databaseHandler() {
        DatabaseAuditHandler dbHandler = new DatabaseAuditHandler();
        dbHandler.setEmbedded("true");
        return dbHandler;
    }*/
    
    
    private fun getProperties() : Map<String,String> {
    	var properties = HashMap<String,String>();
    	properties.put("log.file.location", ".");
    	return properties; 
    }
    
    @Bean
    open fun fileAuditHandler() = FileAuditHandler();
    	

    // If you want t o load configurations from file
    // (resources/audit4j.conf.yaml),
    // comment below method
    @Bean
    open fun springAudit4jConfig() : SpringAudit4jConfig {
        var springAudit4jConfig = SpringAudit4jConfig();
        springAudit4jConfig.setLayout(SimpleLayout());
        var handlers : MutableList<Handler<AuditEvent>> = mutableListOf();
        handlers.add(ConsoleAuditHandler());

        //handlers.add(databaseHandler());
        handlers.add(fileAuditHandler());
        springAudit4jConfig.setHandlers(handlers);
        springAudit4jConfig.setMetaData(AuditMetaData());
        springAudit4jConfig.setProperties(getProperties());

        springAudit4jConfig.setLayout(getLayout());
        
        return springAudit4jConfig;
    }
    
    private fun getLayout() : Layout {
    	
    	var layout = CustomizableLayout();
    	layout.setTemplate("${'$'}{eventDate}|${'$'}{uuid}|actor=${'$'}{actor}|${'$'}{action}|origin=${'$'}{origin} => ${'$'}{foreach fields field}${'$'}{field.name} ${'$'}{field.type}:${'$'}{field.value}, ${'$'}{end}");
    	return layout;
    }
}
