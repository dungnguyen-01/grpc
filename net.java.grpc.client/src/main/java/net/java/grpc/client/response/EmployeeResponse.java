package net.java.grpc.client.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.java.grpc.client.entity.Employee;
import net.java.grpc.server.EmployeeEntity;

public class EmployeeResponse {
	

	private int id;
	
	private String name;
	
	private String phone;
	
	private String address;
	
	@JsonProperty("job_title")
	private String jobTitle;
	
	private double salary;
	
	public EmployeeResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeResponse(Employee entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.phone = entity.getPhone();
		this.address = entity.getAddress();
		this.jobTitle = entity.getJobTitle();
		this.salary = entity.getSalary();
		
	}
	
	public EmployeeResponse(EmployeeEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.phone = entity.getPhone();
		this.address = entity.getAddress();
		this.jobTitle = entity.getJobTitle();
		this.salary = entity.getSalary();
		
	}
	
	
	
	public List<EmployeeResponse> mapToList(List<Employee> entities) {
		return entities.stream().map(EmployeeResponse::new).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
