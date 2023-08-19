package net.java.grpc.server.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import net.java.grpc.server.service.impl.MyGrpcServiceImpl;

@Configuration
public class GrpcServerConfig {
    @Value("${grpc.server.port}")
    private int grpcServerPort;

    @Bean
    public Server grpcServer() throws IOException {
        return NettyServerBuilder.forPort(grpcServerPort)
                .addService(new MyGrpcServiceImpl())
                .build();
    }
    
   
}
