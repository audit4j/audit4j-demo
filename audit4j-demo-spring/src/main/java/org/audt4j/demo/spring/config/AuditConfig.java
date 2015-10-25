package org.audt4j.demo.spring.config;

import java.util.ArrayList;
import java.util.List;

import org.audit4j.core.handler.ConsoleAuditHandler;
import org.audit4j.core.handler.Handler;
import org.audit4j.core.layout.SimpleLayout;
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

    /*
     * @Bean public DatabaseAuditHandler databaseHandler(){ DatabaseAuditHandler
     * dbHandler = new DatabaseAuditHandler(); dbHandler.setEmbedded("false");
     * dbHandler.setDb_connection_type("jndi");
     * dbHandler.setDb_jndi_datasource("java:jboss/datasources/DS"); return
     * dbHandler; }
     */

    @Bean
    public SpringAudit4jConfig springAudit4jConfig() {
        SpringAudit4jConfig springAudit4jConfig = new SpringAudit4jConfig();
        springAudit4jConfig.setLayout(new SimpleLayout());
        List<Handler> handlers = new ArrayList<Handler>();
        handlers.add(new ConsoleAuditHandler());

        // handlers.add(databaseHandler());
        springAudit4jConfig.setHandlers(handlers);
        springAudit4jConfig.setMetaData(new AuditMetaData());
        return springAudit4jConfig;
    }

}
