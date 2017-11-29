import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class Spoker {


    /**
     * The main method.
     * Usage: $java -jar SSLPoker.jar <host> <port>
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        String host;
        Integer port;
        Integer cipherEnable = 0;
        if (args.length != 2) {
            System.out.println("Usage: " + Spoker.class.getName() + " <host> <port>");
            //System.exit(1);
            host= "www.google.com";
            port = 443;
            cipherEnable = 1;

        }
        else{
            host = args[0];
            port= Integer.parseInt(args[1]);
            cipherEnable= Integer.parseInt(args[2]);
        }
        try {

            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket =
                    (SSLSocket) sslsocketfactory.createSocket(host, port);
            /*preferred cipher*/
            String pickedCipher[] ={"TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA"};
            /*setting our cipher only if cipherEnable is 1*/
            if(cipherEnable == 1)
            sslsocket.setEnabledCipherSuites(pickedCipher);

            /*register a callback for handshaking completion event*/
            sslsocket.addHandshakeCompletedListener(handshakeCompletedEvent -> {
                System.out.println("Handshaking completed\nCipherSuite:"+handshakeCompletedEvent.getCipherSuite());
            });

            sslsocket.startHandshake();

/*            OutputStream out = sslsocket.getOutputStream();
            PrintWriter pOut= new PrintWriter(out,false);
            pOut.print("GET / HTTP/1.0");
            pOut.flush();

            InputStream in = sslsocket.getInputStream();
            InputStreamReader inr = new InputStreamReader(in);
            BufferedReader br= new BufferedReader(inr);
            String inputLine;
            while ((inputLine = br.readLine()) != null)
                System.out.println(inputLine);*/

            /*another way of printing ciphersuite being used by server and cleint*/
            System.out.println("Session Ciphersuite is:"+sslsocket.getSession().getCipherSuite());
            System.out.println("Successfully connected");
            sslsocket.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

