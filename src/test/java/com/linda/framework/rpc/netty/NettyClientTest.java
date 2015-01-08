package com.linda.framework.rpc.netty;

import com.linda.framework.rpc.client.SimpleRpcClient;

public class NettyClientTest {
	
	public static void main(String[] args) throws InterruptedException {
		SimpleRpcClient client = new SimpleRpcClient();
		client.setHost("127.0.0.1");
		client.setPort(5555);
		client.setConnectorClass(RpcNettyConnector.class);
		LoginRpcService loginRpcService = client.register(LoginRpcService.class);
		client.startService();
		Thread.currentThread().sleep(1000);
		boolean login = loginRpcService.login("143243", "534543");
		System.out.println("login:"+login);
		client.stopService();
	}

}
