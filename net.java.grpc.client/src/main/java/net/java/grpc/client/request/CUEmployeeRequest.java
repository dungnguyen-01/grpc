package net.java.grpc.client.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CUEmployeeRequest {
	
	private String name;
	
	private String phone;
	
	private String address;
	
	@JsonProperty("job_title")
	private String jobTitle;
	
	private double salary;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	

}
