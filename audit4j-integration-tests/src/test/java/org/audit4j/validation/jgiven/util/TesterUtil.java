package org.audit4j.validation.jgiven.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.audit4j.core.AuditManager;
import org.audit4j.core.dto.AuditEvent;
import org.audit4j.core.dto.EventBuilder;
import org.audit4j.core.dto.Field;

public class TesterUtil {

	public final static String expectedOutputEV ="Init Actor|Default Origin|myMethodEV==>myParam1Name java.lang.String:sfd,myParam2Name java.lang.String:sdfdsf,";
	public final static String expectedOutputAE ="Init Actor|Default Origin|myMethodAE==>myParam1Name java.lang.String:sfd,myParam2Name java.lang.String:sdfdsf,";

	
	private static final String ACTOR = "Init Actor";
	
	public static void sendMessageWithEventBuilder() {
        AuditManager.getInstance().audit(
                new EventBuilder().addActor(ACTOR).addAction("myMethodEV").addField("myParam1Name", "sfd")
                        .addField("myParam2Name", "sdfdsf").build());		
	}
	
	public static void sendMessageWithAuditEvent() {
        AuditManager.getInstance().audit(
        		new AuditEvent(ACTOR, "myMethodAE", new Field("myParam1Name", "sfd"),
        	              new Field("myParam2Name", "sdfdsf")));		
	}
	
	public static String getAuditFileName() {
		Date date = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String postFix = dt.format(date);
		
		return "Audit_Log-"+postFix+".audit";		
	}
}
