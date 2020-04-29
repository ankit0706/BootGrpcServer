package com.sample.bootgrpc.custom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class CustomConfig {
	 @Bean
     public ShutdownScheduler task() {
         return new ShutdownScheduler();
     }
	 
	 @Bean
	 public ExecutorService getCustomThreadPool() {
		 return Executors.newFixedThreadPool(1);
	 }
}
