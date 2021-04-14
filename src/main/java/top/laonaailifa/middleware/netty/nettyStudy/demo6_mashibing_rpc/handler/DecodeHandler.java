package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.Content;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.Header;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.Packmsg;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class DecodeHandler extends ByteToMessageDecoder {
    static final int HEADER_LENGTH = 140;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        while (buf.readableBytes() >= DecodeHandler.HEADER_LENGTH) {
            if (buf.readableBytes() >= DecodeHandler.HEADER_LENGTH) {
                byte[] bytes = new byte[DecodeHandler.HEADER_LENGTH];
                buf.getBytes(buf.readerIndex(), bytes);
                ObjectInputStream headerReader = new ObjectInputStream(new ByteArrayInputStream(bytes));
                Header header = (Header) headerReader.readObject();

                if (buf.readableBytes() >= header.getDataLen() + DecodeHandler.HEADER_LENGTH) {
                    buf.readBytes(DecodeHandler.HEADER_LENGTH);
                    byte[] data = new byte[(int) header.getDataLen()];
                    buf.readBytes(data);
                    ObjectInputStream dataReader = new ObjectInputStream(new ByteArrayInputStream(data));
                    Content content = (Content) dataReader.readObject();
                    out.add(new Packmsg(header, content));
                } else {
                    break;
                }
            }
        }
    }
}
