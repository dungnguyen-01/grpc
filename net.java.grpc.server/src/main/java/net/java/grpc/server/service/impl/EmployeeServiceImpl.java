package net.java.grpc.server.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.java.grpc.server.dao.EmployeeDao;
import net.java.grpc.server.entity.Employee;
import net.java.grpc.server.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao dao;

	@Override
	public void create(Employee employee) {
		dao.save(employee);
		
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Optional<Employee> finById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
	
	
}
