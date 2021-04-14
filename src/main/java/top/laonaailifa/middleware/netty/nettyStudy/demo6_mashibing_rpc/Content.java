package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc;

import java.io.Serializable;

public class Content implements Serializable {
    String name;
    String methodName;
    Class<?>[] parameterTypes;
    Object[] args;
    String res;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String getName() {
        return name;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }
}
