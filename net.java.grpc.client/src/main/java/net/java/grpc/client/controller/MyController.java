package net.java.grpc.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.java.grpc.client.server.GrpcClientService;

@RestController
public class MyController {
	
	@Autowired
    private GrpcClientService grpcClientService;


    @GetMapping("/send-greeting")
    public String sendGreeting(@RequestParam String name) {
        return grpcClientService.sendMessage(name);
    }
}
