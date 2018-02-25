package hello.data;

public class Address {
	
	private String city;
	private String postCode;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@Override
	public String toString() {
		return "Address [city=" + city + ", postCode=" + postCode + "]";
	}

}
