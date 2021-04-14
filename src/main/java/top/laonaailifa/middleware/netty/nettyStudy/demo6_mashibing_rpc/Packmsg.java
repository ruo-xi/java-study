package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc;

public class Packmsg {
    Header header;
    Content content;
    public Packmsg(Header header, Content content) {
        this.header = header;
        this.content = content;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
