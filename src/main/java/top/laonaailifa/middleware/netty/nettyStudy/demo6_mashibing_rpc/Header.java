package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc;

import java.io.Serializable;

public class Header implements Serializable {
    /**
     * 1. flag
     * 2. UUID: requestID
     * 3. DATA_LENGTH
     */
    int flag;
    long requestID;
    long dataLen;

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public void setDataLen(long dataLen) {
        this.dataLen = dataLen;
    }

    public int getFlag() {
        return flag;
    }

    public long getRequestID() {
        return requestID;
    }

    public long getDataLen() {
        return dataLen;
    }
}
