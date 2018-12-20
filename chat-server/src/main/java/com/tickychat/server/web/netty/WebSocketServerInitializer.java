package com.tickychat.server.web.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Angus
 * @date 2018/12/13
 */
@Component
public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private ChatHandler chatHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // websocket 基于 http 协议，需要 http 编解码器
        pipeline.addLast(new HttpServerCodec());
        // ChunkedWriteHandler 提供对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 对 HttpMessage 进行聚合，聚合成 HttpRequest 或 HttpResponse
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        // websocket 服务器处理协议，用于指定给客户端连接访问的路由，以及处理一些繁杂的事务
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 自定义 Handler，用于消息处理
        pipeline.addLast(chatHandler);
    }
}
