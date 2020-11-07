package top.laonaailifa.middleware.netty.bioStudy.bio_rediscli;

/**
 * 封装api
 * 协议层
 * 网络层
 */
public class RedisClient {

    MySocket mySocket;

    public RedisClient(String ip, int port) {
        mySocket = new MySocket(ip, port);
    }

    public String Set(String key, String value) {
        mySocket.send(commondUtil(Resp.command.SET, key.getBytes(), value.getBytes()));
        return mySocket.read();
    }

    public String get(String key) {
        mySocket.send(commondUtil(Resp.command.GET, key.getBytes()));
        return mySocket.read();
    }

    public String incr(String key) {
        mySocket.send(commondUtil(Resp.command.INCR, key.getBytes()));
        return mySocket.read();
    }

    public static String commondUtil(Resp.command command, byte[]... bytess) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Resp.star).append(1 + bytess.length ).append(Resp.line);
        stringBuilder.append(Resp.StringLength).append(command.toString().getBytes().length).append(Resp.line);
        stringBuilder.append(command.toString()).append(Resp.line);
        for (byte[] bytes : bytess) {
            stringBuilder.append(Resp.StringLength).append(bytes.length).append(Resp.line);
            stringBuilder.append(new String(bytes)).append(Resp.line);
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
//        RedisClient redisClient = new RedisClient("127.0.0.1", 6379);
//        System.out.println(redisClient.Set("yu", "123456"));
//        System.out.println(redisClient.get("yu"));
//        System.out.println(redisClient.incr("yu"));

        System.out.println("11\r11");
        System.out.println("-----------------");
        System.out.println("11\r\n11");
        System.out.println("----------------");
        System.out.println("11\n11");
        System.out.println("------------------");

    }
}
