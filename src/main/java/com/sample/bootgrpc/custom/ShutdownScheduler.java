package com.sample.bootgrpc.custom;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

public class ShutdownScheduler {
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	ExecutorService threadPoolExecutor;
	
	@Scheduled(initialDelay = 30000, fixedDelay = 1000)
    public void killApp() {
		System.out.println("Inside kill App");
		
		if(threadPoolExecutor != null) {
			System.out.println("closing thread pool");
			threadPoolExecutor.shutdownNow();
			System.out.println("isShutdown = " +  threadPoolExecutor.isShutdown());
			System.out.println("isTerminated = " +  threadPoolExecutor.isTerminated());
		}
		
		((ConfigurableApplicationContext) context).close();
    }
}
