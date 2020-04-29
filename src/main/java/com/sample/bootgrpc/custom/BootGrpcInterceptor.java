package com.sample.bootgrpc.custom;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;

public class BootGrpcInterceptor implements ServerInterceptor{
	
	@Override
	public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
			ServerCallHandler<ReqT, RespT> next) {
		String apiKey = headers.get(Constants.METADATA_KEY);
		System.out.println("apiKey = " + apiKey);
		if(Constants.API_KEY.equals(apiKey)) {
			System.out.println("Request Authenticated");
			return next.startCall(call, headers);
		}else {
			call.close(Status.UNAUTHENTICATED, headers);
		}
		return null;
	}

}
