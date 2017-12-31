package org.springframework.samples.petclinic

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.audit4j.core.MetaData;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

class AuditMetaData : MetaData {

	    override fun getOrigin() : String {
	        /*try {
	            return ((ServletRequest)((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()).getRemoteAddr();
	        }catch(Exception e){
	            e.printStackTrace();
	        }*/
	        return "unidentified";
	    }
	    
	override fun getActor() = "anonymous";
	      
}
