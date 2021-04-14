package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.Content;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.Header;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.Packmsg;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.tool.SerDerUtil;

public class ServerRequestHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packmsg packmsg = (Packmsg) msg;
//        System.out.println("server handler : " + packmsg.getContent().getArgs()[0]);

        String ioThreadName = Thread.currentThread().getName();

        ctx.executor().parent().next().execute(() -> {
            String execThreadName = Thread.currentThread().getName();

            Content content = new Content();
            content.setRes("io thread: " + ioThreadName + " exec thread: " + execThreadName + " from args: " + packmsg.getContent().getArgs()[0]);
            byte[] msgContent = SerDerUtil.ser(content);

            Header header = new Header();
            header.setRequestID(packmsg.getHeader().getRequestID());
            header.setFlag(0x14141414);
            header.setDataLen(msgContent.length);
            byte[] msgHeader = SerDerUtil.ser(header);
            ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(msgHeader.length + msgContent.length);

            byteBuf.writeBytes(msgHeader)
                    .writeBytes(msgContent);
            ctx.writeAndFlush(byteBuf);
        });

//        // v1  exec in the io thread
//        Content content = new Content();
//        content.setRes(ioThreadName);
//
//        Header header = new Header();
//        header.setRequestID(packmsg.getHeader().getRequestID());
//        header.setFlag(0x14141414);
//        header.setDataLen();
//
//        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(Long.BYTES);
//        byteBuf.writeLong(packmsg.getHeader().getRequestID());
//        ctx.writeAndFlush(byteBuf);

//        ByteBuf buf = (ByteBuf) msg;
//        ByteBuf sendBuf = buf.copy();
//
//        while (buf.readableBytes() >= 140) {
//            System.out.println("start : " + buf.readableBytes());
//            if (buf.readableBytes() >= 140) {
//                byte[] bytes = new byte[140];
//                buf.getBytes(buf.readerIndex(), bytes);
//                ObjectInputStream headerReader = new ObjectInputStream(new ByteArrayInputStream(bytes));
//                Header header = (Header) headerReader.readObject();
//                System.out.println("server received : " + header);
//
//                if (buf.readableBytes() >= header.getDataLen()) {
//                    buf.readBytes(140);
//                    byte[] data = new byte[(int) header.getDataLen()];
//                    buf.readBytes(data);
//                    ObjectInputStream dataReader = new ObjectInputStream(new ByteArrayInputStream(data));
//                    Content content = (Content) dataReader.readObject();
//                } else {
//                    System.out.println("else : " + buf.readableBytes());
//                }
//            }
//
//        }
//
//        ChannelFuture channelFuture = ctx.writeAndFlush(sendBuf);
//        channelFuture.sync();
    }
}
