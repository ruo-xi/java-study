package top.laonaailifa.middleware.netty.nettyStudy.demo5_lbRpc.server.service;

import java.util.List;

public interface TestService {
    List<String> listAll();

    String ListById(Integer id);
}
