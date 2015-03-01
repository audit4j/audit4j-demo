package org.springframework.samples.petclinic.util;

import org.audit4j.core.MetaData;

public class AuditMetaData implements MetaData{
    
    private static final long serialVersionUID = 1L;

    @Override
    public String getActor() {
        return "annonymous";
    }

}
