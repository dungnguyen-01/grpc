package net.java.grpc.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.java.grpc.server.entity.Employee;

public interface EmployeeDao  extends JpaRepository<Employee, Integer>{

}
