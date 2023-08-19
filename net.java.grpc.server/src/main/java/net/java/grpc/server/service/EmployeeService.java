package net.java.grpc.server.service;

import java.util.List;
import java.util.Optional;

import net.java.grpc.server.entity.Employee;

public interface EmployeeService {

	void create(Employee employee);

	List<Employee> findAll();
	
	Optional<Employee> finById(int id);

}
