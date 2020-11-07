package top.laonaailifa.middleware.netty.nettyStudy.demo5_lbRpc.server.service.impl;


import top.laonaailifa.middleware.netty.nettyStudy.demo5_lbRpc.server.service.TestService;

import java.util.ArrayList;
import java.util.List;

public class TestServiceImpl implements TestService {

    static ArrayList<String> list = new ArrayList<>();
    static {
        list.add("张三");
        list.add("lisi");
    }

    @Override
    public List<String> listAll() {
        return list;
    }

    @Override
    public String ListById(Integer id) {
        return list.get(id);
    }
}
