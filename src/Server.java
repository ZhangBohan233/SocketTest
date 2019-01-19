import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;

public class Server {

    public static final int PORT = 3456;

    private ServerSocket serverSocket;

    public Server() throws IOException {

        serverSocket = new ServerSocket(PORT, 50, InetAddress.getLocalHost());
//        serverSocket.bind(new InetSocketAddress("127.0.0.1", PORT));
        System.out.println("Listening on ip " + serverSocket.getInetAddress().getHostAddress());
        Socket client = serverSocket.accept();
        System.out.println("Connected to client " + client.getInetAddress().getHostAddress());

        OutputStream outputStream = client.getOutputStream();
        InputStream inputStream = client.getInputStream();

        outputStream.write("hello".getBytes());
        outputStream.flush();

        Thread receive = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] buffer = new byte[1024];
                    int read;
                    while ((read = inputStream.read(buffer)) != -1) {
                        System.out.println(new String(buffer, 0, read));
                    }
                } catch (IOException e) {
                    //
                }
            }
        });
        receive.start();

        Scanner sc = new Scanner(System.in);
        String line;
        while (!(line = sc.nextLine()).equals("exit")) {
            outputStream.write(line.getBytes());
        }
        sc.close();
        outputStream.close();
        client.close();

//        while (client.isConnected()) {
//
//        }
    }
}
