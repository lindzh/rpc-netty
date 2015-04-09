package com.linda.framework.rpc.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.linda.framework.rpc.RpcObject;
import com.linda.framework.rpc.exception.RpcException;
import com.linda.framework.rpc.utils.RpcUtils;


/**
 * 
 * @author linda
 * RPC Object encoder
 * @See RpcUtils
 *
 */
public class RpcNettyEncoder extends MessageToByteEncoder<RpcObject>{

	@Override
	protected void encode(ChannelHandlerContext ctx, RpcObject msg, ByteBuf out)
			throws Exception {
		out.writeInt(msg.getType().getType());
		out.writeLong(msg.getThreadId());
		out.writeInt(msg.getIndex());
		out.writeInt(msg.getLength());
		if(msg.getLength()>0){
			if(msg.getLength()>RpcUtils.MEM_1M){
				throw new RpcException("rpc data too long "+ msg.getLength());
			}
			out.writeBytes(msg.getData());
		}
	}
	
}
