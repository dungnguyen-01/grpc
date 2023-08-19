package net.java.grpc.server.service.impl;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import net.java.grpc.server.HelloRequest;
import net.java.grpc.server.HelloResponse;
import net.java.grpc.server.MyGrpcServiceGrpc;


@GrpcService
public class MyGrpcServiceImpl extends MyGrpcServiceGrpc.MyGrpcServiceImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String name = request.getName();
        String greeting = "Hello, " + name + "!";
        HelloResponse response = HelloResponse.newBuilder().setMessage(greeting).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
}
