package net.java.grpc.server.grpc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import net.java.grpc.server.EmployeeEntity;
import net.java.grpc.server.EmployeeRequest;
import net.java.grpc.server.EmployeeServiceGrpc.EmployeeServiceImplBase;
import net.java.grpc.server.Empty;
import net.java.grpc.server.FindEmployeeRequest;
import net.java.grpc.server.MessageResponse;
import net.java.grpc.server.ResponseEmployeeEntity;
import net.java.grpc.server.entity.Employee;
import net.java.grpc.server.service.EmployeeService;

@GrpcService
public class EmployeeGrpcService extends EmployeeServiceImplBase {

	@Autowired
	private EmployeeService employeeService;
//	private final EmployeeService employeeService;
	
	public EmployeeGrpcService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@Override
		public void create(EmployeeRequest request, StreamObserver<MessageResponse> responseObserver) {
			try {
				Employee employee = new Employee();
				
				
				employee.setName(request.getName());
				employee.setPhone(request.getPhone());
				employee.setAddress(request.getAddress());
				employee.setJobTitle(request.getJobTitle());
				employee.setSalary(request.getSalary());
				
				employeeService.create(employee);
				
				MessageResponse response = MessageResponse.newBuilder()
											.setStatus(HttpStatus.OK.value())
											.setMessage("Create success").build();
				
				responseObserver.onNext(response);
				responseObserver.onCompleted();
				
				
			} catch (Exception e) {
				String messageError = "Create Faild!";
				Status status = Status.INTERNAL.withDescription(messageError);
				responseObserver.onError(status.asRuntimeException());
			}
		}	
	
	@Override
	public void findAllEmployee(Empty request, StreamObserver<EmployeeEntity> responseObserver) {
	    try {
	        List<Employee> employees = employeeService.findAll();

	        for (Employee employee : employees) {
	            EmployeeEntity entity = EmployeeEntity.newBuilder()
	            		.setId(employee.getId())
	                    .setName(employee.getName())
	                    .setPhone(employee.getPhone())
	                    .setAddress(employee.getAddress())
	                    .setJobTitle(employee.getJobTitle())
	                    .setSalary(employee.getSalary())
	                    .build();
	            
	            // Send each EmployeeEntity to the client using responseObserver
	            responseObserver.onNext(entity);
	        }
	        
	        // Indicate that the stream is complete
	        responseObserver.onCompleted();
	    } catch (Exception e) {
	        String messageError = "Error occurred while fetching employees.";
	        Status status = Status.INTERNAL.withDescription(messageError);
	        responseObserver.onError(status.asRuntimeException());
	    }
	}
	
	
	@Override
	public void findEmployeeById(FindEmployeeRequest request, StreamObserver<ResponseEmployeeEntity> responseObserver) {
		
		try {
			Employee employee = employeeService.finById(request.getEmployeeId()).orElse(null);
			
			if (employee == null) {
				String messageError = "Find employee not found!";
				Status status = Status.NOT_FOUND.withDescription(messageError);
				
				ResponseEmployeeEntity entity = ResponseEmployeeEntity.newBuilder()
													.setEmployeeEntity(EmployeeEntity.newBuilder())
													.setStatus(HttpStatus.NOT_FOUND.value())
													.setMessage(status.toString()).build();
				
				responseObserver.onNext(entity);
				responseObserver.onCompleted();
				return;
			
			}
			
			EmployeeEntity employeeEntity = EmployeeEntity.newBuilder()
													.setId(employee.getId())
										            .setName(employee.getName())
										            .setPhone(employee.getPhone())
										            .setAddress(employee.getAddress())
										            .setJobTitle(employee.getJobTitle())
										            .setSalary(employee.getSalary())
										            .build();
			
			ResponseEmployeeEntity entity = ResponseEmployeeEntity.newBuilder().setEmployeeEntity(employeeEntity).setStatus(0).setMessage("Success").build();
			
	
			responseObserver.onNext(entity);
			responseObserver.onCompleted();
			
			
			
		} catch (Exception e) {
			String messageError = "Find employee Faild!";
			Status status = Status.INTERNAL.withDescription(messageError);
			responseObserver.onError(status.asRuntimeException());
		}
	}
	
	
	
	
}
