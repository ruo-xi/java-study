package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.Packmsg;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.client.ResponseMappingCallback;

public class ClientResponseHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packmsg packmsg = (Packmsg) msg;
        ResponseMappingCallback.runCallBack(packmsg);
//        System.out.println(packmsg.getContent().getRes());
    }
}
