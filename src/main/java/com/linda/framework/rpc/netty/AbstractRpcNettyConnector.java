package com.linda.framework.rpc.netty;

import com.linda.framework.rpc.net.AbstractRpcConnector;
import com.linda.framework.rpc.net.AbstractRpcWriter;

public class AbstractRpcNettyConnector extends AbstractRpcConnector{

	public AbstractRpcNettyConnector(AbstractRpcWriter rpcWriter) {
		super(rpcWriter);
	}

	@Override
	public void startService() {
		
	}

	@Override
	public void stopService() {
		
	}

	@Override
	public void handleNetException(Exception e) {
		
	}

}
