package top.laonaailifa.middleware.netty.bioStudy.masterAndSlave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int port = 9999;
        String hostName = "127.0.0.1";
        try {
            Socket c = new Socket(hostName, port);
            System.out.println("connect successfully");
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            PrintWriter out = new PrintWriter(c.getOutputStream());
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = stdin.readLine()) != null) {
                out.println(input);
                out.flush();
                if (input.equals("exit")) {
                    break;
                }
                System.out.println("server" + in.readLine());
            }
            c.close();
            System.out.println("client stop");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
