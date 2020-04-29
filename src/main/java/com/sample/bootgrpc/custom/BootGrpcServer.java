package com.sample.bootgrpc.custom;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.bootgrpc.api.BootRequest;
import com.sample.bootgrpc.api.BootResponse;
import com.sample.bootgrpc.api.BootServiceGrpc.BootServiceImplBase;
import io.grpc.stub.StreamObserver;

@GRpcService(interceptors = { BootGrpcInterceptor.class })
public class BootGrpcServer extends BootServiceImplBase{
	
	private static final Logger LOGGER =
		      LoggerFactory.getLogger(BootGrpcServer.class);
	
	@Override
	public void giveBootValues(BootRequest request, StreamObserver<BootResponse> responseObserver) {
		LOGGER.info("Request received. request name = " + request.getName() + " request ");
		
		String requestedName = request.getName();
		
		BootResponse response = BootResponse.newBuilder().setRespName("Hello " + requestedName)
				.setRespId((int)(Math.random() *  100)).build();
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

}
