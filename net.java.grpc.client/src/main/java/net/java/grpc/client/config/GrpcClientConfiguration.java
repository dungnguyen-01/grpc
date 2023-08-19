package net.java.grpc.client.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.netty.NettyChannelBuilder;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelConfigurer;
import net.devh.boot.grpc.client.inject.StubTransformer;

@Configuration
public class GrpcClientConfiguration {
	
	@Bean
	GrpcChannelConfigurer keepAliveClientConfigurer() {
	    return (channelBuilder, name) -> {
	        if (channelBuilder instanceof NettyChannelBuilder) {
	            ((NettyChannelBuilder) channelBuilder)
	            		.usePlaintext()
	                    .keepAliveTime(30, TimeUnit.SECONDS)
	                    .keepAliveTimeout(5, TimeUnit.SECONDS);
	        }
	    };
	}
	
	@Bean
	StubTransformer call() {
	    return (name, stub) -> {
	    	return stub;
	    };
	}
}
