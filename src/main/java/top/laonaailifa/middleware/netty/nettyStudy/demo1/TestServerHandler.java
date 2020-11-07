package top.laonaailifa.middleware.netty.nettyStudy.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class TestServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            String uri = request.uri();
            System.out.println(uri);
            ByteBuf buffer = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            DefaultFullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buffer);
            fullHttpResponse.headers()
                    .set(HttpHeaderNames.CONTENT_TYPE, "text_plain")
                    .set(HttpHeaderNames.CONTENT_LENGTH, buffer.readableBytes());
            ctx.writeAndFlush(fullHttpResponse);

        }
    }
}
