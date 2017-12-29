package org.audt4j.demo.hibernate.config;

import java.util.ArrayList;
import java.util.List;

import org.audit4j.core.handler.ConsoleAuditHandler;
import org.audit4j.core.handler.Handler;
import org.audit4j.core.layout.SimpleLayout;
import org.audit4j.handler.db.DatabaseAuditHandler;
import org.audit4j.integration.spring.SpringAudit4jConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfig {

    // If you want to load configurations from file
    // (resources/audit4j.conf.yaml),
    // comment below method
    @Bean
    public DatabaseAuditHandler databaseHandler() {
        DatabaseAuditHandler dbHandler = new DatabaseAuditHandler();
        dbHandler.setEmbedded("true");
        return dbHandler;
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

        handlers.add(databaseHandler());
        springAudit4jConfig.setHandlers(handlers);
        springAudit4jConfig.setMetaData(new AuditMetaData());
        return springAudit4jConfig;
    }

}
