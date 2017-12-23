package hello;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.audit4j.core.MetaData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuditMetaData implements MetaData {

	   private static final long serialVersionUID = 7243065407615627372L;

	    @Override
	    public String getOrigin() {
	        try {
	            return ((ServletRequest)((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()).getRemoteAddr();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return "unidentified";
	    }

	    @Override
	    public String getActor() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
	            return ((UserDetails) authentication.getPrincipal()).getUsername()+ " "+getRoles();
	        }
	        return "anonymous";
	    }
	    
	    //how to have a string giving the list of roles that the user has
	    private String getRoles() {
	    	
        	List<String> roles = new ArrayList<String>();
        	for( GrantedAuthority author :SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
        		roles.add(author.toString());
        	}
        	if(roles.isEmpty()) {
        		return "no role";
        	} else
        		return  "roles=["+StringUtils.join(roles, ",")+ "]";
	    	
	    }


}
