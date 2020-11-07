package top.laonaailifa.middleware.netty.nettyStudy.demo2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new FixedLengthFrameDecoder(10));
        pipeline.addLast(new LineBasedFrameDecoder(1024,true,true));
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024));
        /**
         * 1. lengthFieldOffset 长度字段的偏差
         * 2. lengthFieldLength 长度字段占用的字节数
         * 3. lengthAdjustment 添加到长度字段的补偿值
         * 4. initialBytesToStrip //从解码帧中第一次去除的字节数
         */
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeline.addLast(new FixedLengthFrameDecoder(10));
        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new ServerHandler());
    }
}
