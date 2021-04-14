package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc;

public class MyCar implements Car {
    @Override
    public String ooxx(String msg) {
        System.out.println("server get client arg: " + msg);
        return "server response: " + msg;
    }
}
