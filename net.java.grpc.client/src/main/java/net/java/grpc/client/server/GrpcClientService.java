package net.java.grpc.client.server;

import org.springframework.stereotype.Service;

import io.grpc.Channel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.java.grpc.server.HelloRequest;
import net.java.grpc.server.HelloResponse;
import net.java.grpc.server.MyGrpcServiceGrpc;
import net.java.grpc.server.MyGrpcServiceGrpc.MyGrpcServiceBlockingStub;

@Service
public class GrpcClientService {

    @GrpcClient("net_java_grpc_server") // Replace with the gRPC server name
    private Channel serverChannel;

    public String sendMessage(String name) {
    	
//    	ManagedChannel channel = ManagedChannelBuilder
//    	        .forAddress("127.0.0.1", 9191)
//    	        .usePlaintext()
//    	        .build();
    	
        MyGrpcServiceBlockingStub blockingStub = MyGrpcServiceGrpc.newBlockingStub(serverChannel);
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloResponse response = blockingStub.sayHello(request);
        return response.getMessage();
    }
}
