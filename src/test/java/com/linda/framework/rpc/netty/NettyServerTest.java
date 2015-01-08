package com.linda.framework.rpc.netty;

import com.linda.framework.rpc.server.SimpleRpcServer;

public class NettyServerTest {
	
	public static void main(String[] args) {
		SimpleRpcServer rpcServer = new SimpleRpcServer();
		rpcServer.setAcceptor(new RpcNettyAcceptor());
		rpcServer.setHost("127.0.0.1");
		rpcServer.setPort(5555);
		rpcServer.register(LoginRpcService.class, new LoginRpcServiceImpl());
		rpcServer.startService();
	}
}
