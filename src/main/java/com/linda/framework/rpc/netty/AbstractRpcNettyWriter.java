package com.linda.framework.rpc.netty;

import com.linda.framework.rpc.net.AbstractRpcConnector;
import com.linda.framework.rpc.net.AbstractRpcWriter;

public class AbstractRpcNettyWriter extends AbstractRpcWriter{

	@Override
	public boolean doSend(AbstractRpcConnector connector) {
		return false;
	}

}
