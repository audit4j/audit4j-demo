package hello.data;

public class User {
	
	private String lastName;
	private String firtName;
	private Address address;
	private Double val =1.0;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirtName() {
		return firtName;
	}

	public void setFirtName(String firtName) {
		this.firtName = firtName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [lastName=" + lastName + ", firtName=" + firtName + ", address=" + address + ", val=" + val + "]";
	}


	
	

}
