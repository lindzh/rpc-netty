package com.linda.framework.rpc.netty.generic;


import com.linda.framework.rpc.netty.HelloRpcService;
import com.linda.framework.rpc.netty.HelloRpcServiceImpl;
import com.linda.framework.rpc.netty.RpcNettyAcceptor;
import com.linda.framework.rpc.server.SimpleRpcServer;

public class GenericServerTest {

	public static void main(String[] args) {
		SimpleRpcServer server = new SimpleRpcServer();
		server.setAcceptor(new RpcNettyAcceptor());
		server.setHost("0.0.0.0");
		server.setPort(4445);
		server.register(HelloRpcService.class, new HelloRpcServiceImpl());
		server.startService();
		System.out.println("server startup");
	}
	
}
