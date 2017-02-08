package com.github.netty.http.bussiness;

import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class MsgPushHandler {
	private final ChannelGroup group;
	public MsgPushHandler(ChannelGroup group){
		this.group = group;
	}
	public void start() throws Exception{
		while(true){
			String msg = DataCenter.getMessageString();
			if(msg != null){
				group.writeAndFlush(new TextWebSocketFrame(msg));
				System.out.println("send msg:" + msg);
			}
		}
	}
}
