package com.linda.framework.rpc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.AbstractChannel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import org.apache.log4j.Logger;

import com.linda.framework.rpc.net.AbstractRpcAcceptor;

public class RpcNettyAcceptor extends AbstractRpcAcceptor {

	private EventLoopGroup eventLoopGroup;
	private int eventLoopThread = 50;
	private AbstractChannel channel;

	private Logger logger = Logger.getLogger(RpcNettyAcceptor.class);

	public EventLoopGroup getEventLoopGroup() {
		return eventLoopGroup;
	}

	public void setEventLoopGroup(EventLoopGroup eventLoopGroup) {
		this.eventLoopGroup = eventLoopGroup;
	}
	
	public int getEventLoopThread() {
		return eventLoopThread;
	}

	public void setEventLoopThread(int eventLoopThread) {
		this.eventLoopThread = eventLoopThread;
	}

	@Override
	public void startService() {
		this.startListeners();
		if(this.eventLoopGroup == null){
			eventLoopGroup = new NioEventLoopGroup(eventLoopThread);
			ServerBootstrap bootstrap = NettyUtils.buildServerBootStrap(eventLoopGroup,this);
			ChannelFuture f = bootstrap.bind(this.host, this.port);
			try {
				f.sync();
				channel = (AbstractChannel)f.channel();
			} catch (InterruptedException e) {
				logger.info("server interrupted start to exist");
				this.stopService();
			}
		}
	}

	@Override
	public void stopService() {
		if(this.channel!=null){
			this.channel.close();
		}
		if(this.eventLoopGroup!=null){
			try {
				this.eventLoopGroup.shutdownGracefully().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.stopListeners();
		this.callListeners.clear();
	}

	@Override
	public void handleNetException(Exception e) {
		logger.error("server error:"+e+"     start to stop service");
		this.stopService();
	}
}
