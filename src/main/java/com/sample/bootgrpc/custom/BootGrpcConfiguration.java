package com.sample.bootgrpc.custom;

import java.util.concurrent.ExecutorService;

import javax.annotation.PreDestroy;

import org.lognet.springboot.grpc.GRpcServerBuilderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.grpc.ServerBuilder;

@Component
public class BootGrpcConfiguration extends GRpcServerBuilderConfigurer{
	
	@Value("${grpc.threadPoolSize}")
	String threadPoolSize;
	
	@Autowired
	ExecutorService threadPoolExecutor;
	
	@Override
	public void configure(ServerBuilder<?> serverBuilder) {
		System.out.println("Inside configuration");
		serverBuilder.executor(threadPoolExecutor);
		//int numOfThreads = Integer.parseInt(threadPoolSize);
		//threadPoolExecutor = Executors.newFixedThreadPool(numOfThreads);
		//serverBuilder.executor(threadPoolExecutor);
		//can plan for termination of pool in advance
		//threadPoolExecutor.awaitTermination(timeout, unit)
	}
	
	@PreDestroy
	public void destroy() {
		//we can shutdown our custom executor here also.
		System.out.println("Inside Pre destroy");
	}
	
}
