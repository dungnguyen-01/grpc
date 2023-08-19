package net.java.grpc.client.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.devh.boot.grpc.client.inject.GrpcClient;
import net.java.grpc.client.request.CUEmployeeRequest;
import net.java.grpc.client.response.BaseResponse;
import net.java.grpc.client.response.EmployeeResponse;
import net.java.grpc.server.EmployeeEntity;
import net.java.grpc.server.EmployeeRequest;
import net.java.grpc.server.EmployeeServiceGrpc;
import net.java.grpc.server.Empty;
import net.java.grpc.server.FindEmployeeRequest;
import net.java.grpc.server.MessageResponse;
import net.java.grpc.server.ResponseEmployeeEntity;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	@GrpcClient("net_java_grpc_server")
	private EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeService;

	@PostMapping("/create")
	public ResponseEntity<BaseResponse<?>> create(@RequestBody CUEmployeeRequest wrapper) {

		BaseResponse<?> response = new BaseResponse<>();

		EmployeeRequest employeeRequest = EmployeeRequest.newBuilder().setName(wrapper.getName())
				.setPhone(wrapper.getPhone()).setAddress(wrapper.getAddress()).setJobTitle(wrapper.getJobTitle())
				.setSalary(wrapper.getSalary()).build();

		MessageResponse messageResponse = employeeService.create(employeeRequest);
		

		if (messageResponse.getStatus() == 200) {
			System.out.println("Snyc successfully!");
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Snyc Faild!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	
	
	@GetMapping()
	public ResponseEntity<BaseResponse<List<EmployeeResponse>>> getAll() {
		
		BaseResponse<List<EmployeeResponse>> response = new BaseResponse<>();
		
		List<EmployeeResponse> employeeResponses = new ArrayList<>();
		
		try {
			Iterator<EmployeeEntity> entities = employeeService.findAllEmployee(Empty.newBuilder().build());
			
			while (entities.hasNext()) {
				EmployeeEntity entity = (EmployeeEntity) entities.next();
				
				EmployeeResponse employeeResponse = new EmployeeResponse();
				
				employeeResponse.setId(entity.getId());
				employeeResponse.setName(entity.getName());
				employeeResponse.setPhone(entity.getPhone());
				employeeResponse.setAddress(entity.getAddress());
				employeeResponse.setJobTitle(entity.getJobTitle());
				employeeResponse.setSalary(entity.getSalary());
				
				employeeResponses.add(employeeResponse);	
				
			}
			response.setData(employeeResponses);	
			
		} catch (Exception e) {
			// Handle any exceptions that may occur during the gRPC call
            String errorMessage = "An error occurred while fetching employees: " + e.getMessage();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessageError(errorMessage);
			
		}

        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<EmployeeResponse>> getById(@PathVariable("id") int id) {		
		BaseResponse<EmployeeResponse> response = new BaseResponse<>();
				
		try {

			ResponseEmployeeEntity employeeEntity = employeeService.findEmployeeById(FindEmployeeRequest.newBuilder().setEmployeeId(id).build());
			
			if (employeeEntity.getStatus() == HttpStatus.NOT_FOUND.value()) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setMessageError("Employee not found by id: "+id);
				return new ResponseEntity<>(response, HttpStatus.OK);
							
			}
			
			EmployeeResponse employeeResponse = new EmployeeResponse(employeeEntity.getEmployeeEntity());
				
			response.setData(employeeResponse);	
			
			
		} catch (Exception e) {
			// Handle any exceptions that may occur during the gRPC call
            String errorMessage = "An error occurred while fetching employees: " + e.getMessage();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessageError(errorMessage);
		}
	
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	
}
