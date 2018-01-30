package org.audit4j.validation.jgiven.enumeration;

public enum Version {

	V250("v2.5.0");
	
	private String name ="";
	
	Version(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
