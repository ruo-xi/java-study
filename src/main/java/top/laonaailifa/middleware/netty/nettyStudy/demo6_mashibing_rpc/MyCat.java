package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc;

public class MyCat implements Cat{
    @Override
    public String xxoo(String msg) {
        System.out.println("server get client arg: " + msg);
        return "server response: " + msg;
    }
}
