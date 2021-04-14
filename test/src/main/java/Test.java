import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
