package com.linda.framework.rpc.netty.oio;

import com.linda.framework.rpc.net.AbstractRpcWriter;
import com.linda.framework.rpc.netty.AbstractRpcNettyConnector;

public class RpcNettyOioConnector extends AbstractRpcNettyConnector{

	public RpcNettyOioConnector(AbstractRpcWriter rpcWriter) {
		super(rpcWriter);
	}

}
