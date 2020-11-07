package top.laonaailifa.middleware.netty.bioStudy.bio_rediscli;

public class Resp {
    public static final String star="*";

    public static final String StringLength="$";

    public static final String line="\r\n";

    public static enum command{
        SET, GET, INCR
    }

}
