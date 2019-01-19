import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket client;

    private InputStream inputStream;
    private OutputStream outputStream;

    public Client() throws IOException {
        client = new Socket();
        client.connect(new InetSocketAddress("192.168.2.11", 3456));

        inputStream = client.getInputStream();
        outputStream = client.getOutputStream();

        Thread send = new Thread(() -> {
            try {
                Scanner sc = new Scanner(System.in);
                String line;
                while (!(line = sc.nextLine()).equals("exit")) {
                    outputStream.write(line.getBytes());
                }
            } catch (IOException ioe) {

            }
        });
        send.start();

        byte[] buffer = new byte[1024];
        int read;
        while ((read = inputStream.read(buffer)) != -1) {
            System.out.println(new String(buffer, 0, read));
        }

        client.close();
    }
}
