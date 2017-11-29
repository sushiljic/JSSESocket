import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        SSLSocketFactory  sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("https://www.google.com",443);
        /*InputStream in = sslSocket.getInputStream();
        while(in.available() >0){
            System.out.println(in.read());
        }*/
        System.out.println(sslSocket.getInetAddress());
        System.out.println("connected successfully");
    }
}
