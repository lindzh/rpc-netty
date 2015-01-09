package com.linda.framework.rpc.netty;

import org.apache.log4j.Logger;

import com.linda.framework.rpc.client.SimpleClientRemoteExecutor;
import com.linda.framework.rpc.client.SimpleClientRemoteProxy;
import com.linda.framework.rpc.net.AbstractRpcConnector;
import com.linda.framework.rpc.nio.RpcNioConnector;

public class RpcTestNioClient {

	private static Logger logger = 	Logger.getLogger(RpcTestNioClient.class);
	
	public static void main(String[] args) {
		
		String host = "127.0.0.1";
		int port = 4332;
		AbstractRpcConnector connector = new RpcNioConnector(null);
		connector.setHost(host);
		connector.setPort(port);
		
		SimpleClientRemoteExecutor executor = new SimpleClientRemoteExecutor(connector);
		
		SimpleClientRemoteProxy proxy = new SimpleClientRemoteProxy();
		
		proxy.setRemoteExecutor(executor);
		
		proxy.startService();
		
		LoginRpcService loginService = proxy.registerRemote(LoginRpcService.class);
		
		logger.info("start client");
		
		boolean login = loginService.login("linda", "123456");
		
		logger.info("login result:"+login);
	}
	
}
