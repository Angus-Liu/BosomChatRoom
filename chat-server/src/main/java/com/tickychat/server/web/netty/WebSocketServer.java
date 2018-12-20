package com.tickychat.server.web.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Angus
 * @date 2018/12/13
 */
@Slf4j
@Component
public class WebSocketServer {

    private EventLoopGroup parentGroup;
    private EventLoopGroup childGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;


    @Autowired
    public WebSocketServer(WebSocketServerInitializer webSocketServerInitializer) {
        parentGroup = new NioEventLoopGroup();
        childGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap()
                .group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(webSocketServerInitializer);
        // TODO：为什么 WebSocketServerInitializer 无法以属性注入方式注入
    }

    public void start() {
        channelFuture = serverBootstrap.bind(8088);
        log.debug("Netty server start...");
    }
}
