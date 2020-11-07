package top.laonaailifa.middleware.netty.nettyStudy.demo1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerLnitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpServerCode",new HttpServerCodec());
        pipeline.addLast("testServerHandler",new TestServerHandler());
    }
}
