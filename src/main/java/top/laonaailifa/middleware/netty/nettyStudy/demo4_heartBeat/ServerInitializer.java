package top.laonaailifa.middleware.netty.nettyStudy.demo4_heartBeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//        pipeline.addLast(new LengthFieldPrepender(4));

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        pipeline.addLast(new IdleStateHandler(40, 50, 45));
        pipeline.addLast(new HeartHandler());
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new ChatHandler());
        pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
//        pipeline.addLast(new StringDecoder());
//        pipeline.addLast(new StringEncoder());
//        pipeline.addLast(new ServerHandler());
    }
}
