import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket client;

    private boolean running = true;

    public Client(String ip) throws IOException {
        client = new Socket();
        client.connect(new InetSocketAddress(ip, 3456));

        System.out.println("Connected to " + ip);

        InputStream inputStream = client.getInputStream();
        OutputStream outputStream = client.getOutputStream();

        Thread receive = new Thread(() -> {
            try {
                byte[] buffer = new byte[1024];
                int read;
                while ((read = inputStream.read(buffer)) != -1) {
                    String s = new String(buffer, 0, read);
                    if (s.equals("exit")) {
                        running = false;
                        return;
                    }
                    System.out.println(s);
                }
            } catch (IOException e) {
                //
            }
        });
        receive.start();

        Scanner sc = new Scanner(System.in);
        String line;
        while (!(line = sc.nextLine()).equals("exit") && running) {
            outputStream.write(line.getBytes());
        }
        outputStream.write("exit".getBytes());
        outputStream.flush();
        sc.close();
        inputStream.close();
        outputStream.close();
    }
}
