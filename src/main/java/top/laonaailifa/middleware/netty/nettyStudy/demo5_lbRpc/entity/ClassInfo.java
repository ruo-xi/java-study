package top.laonaailifa.middleware.netty.nettyStudy.demo5_lbRpc.entity;

import java.io.Serializable;

public class ClassInfo implements Serializable {
    private String ClassName;
    private String MethodName;
    private Object[] args;
    private Class[] clazzTyoe;

    public ClassInfo() {
    }

    public ClassInfo(String className, String methodName, Object[] args, Class[] clazzTyoe) {
        ClassName = className;
        MethodName = methodName;
        this.args = args;
        this.clazzTyoe = clazzTyoe;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getMethodName() {
        return MethodName;
    }

    public void setMethodName(String methodName) {
        MethodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class[] getClazzTyoe() {
        return clazzTyoe;
    }

    public void setClazzTyoe(Class[] clazzTyoe) {
        this.clazzTyoe = clazzTyoe;
    }
}
