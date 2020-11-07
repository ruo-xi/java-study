package top.laonaailifa.middleware.netty.nettyStudy.demo5_lbRpc.client.netty;


import top.laonaailifa.middleware.netty.nettyStudy.demo5_lbRpc.client.service.TestService;

public class ClientSocketNetty {
    public static void main(String[] args) {
        Object o = ClientRpcProxy.create(TestService.class);
        if (o instanceof TestService){
            System.out.println(((TestService) o).ListById(0));
        }
    }
}
