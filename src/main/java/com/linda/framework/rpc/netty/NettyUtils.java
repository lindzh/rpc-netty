package com.linda.framework.rpc.netty;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import com.linda.framework.rpc.netty.codec.RpcNettyDecoder;
import com.linda.framework.rpc.netty.codec.RpcNettyEncoder;
import com.linda.framework.rpc.netty.handler.NettyRpcInBoundHandler;

public class NettyUtils {
	
	public static ServerBootstrap buildServerBootStrap(EventLoopGroup group){
		ServerBootstrap boot = new ServerBootstrap();
		NettyUtils.buildAbstractBootStrap(boot, group);
		return boot;
	}
	
	public static Bootstrap buildBootStrap(EventLoopGroup group){
		Bootstrap boot = new Bootstrap();
		NettyUtils.buildAbstractBootStrap(boot, group);
		return boot;
	}
	
	public static void buildAbstractBootStrap(AbstractBootstrap boot,EventLoopGroup group){
		if(group == null){
			group = new NioEventLoopGroup(20);
		}
		if(group instanceof NioEventLoopGroup){
			if(boot instanceof ServerBootstrap){
				boot.channel(NioServerSocketChannel.class);
			}else{
				boot.channel(NioSocketChannel.class);
			}
			
		}else if(group instanceof OioEventLoopGroup){
			if(boot instanceof ServerBootstrap){
				boot.channel(OioServerSocketChannel.class);
			}else{
				boot.channel(OioSocketChannel.class);
			}
		}
		boot.group(group);
		ChannelInitializer handler = new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast(new LoggingHandler());
				pipeline.addLast(new RpcNettyDecoder());
				pipeline.addLast(new RpcNettyEncoder());
				pipeline.addLast(new NettyRpcInBoundHandler());
			}
		};
		if(boot instanceof ServerBootstrap){
			((ServerBootstrap) boot).childHandler(handler);
		}else{
			boot.handler(handler);
		}
	}
}
