import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String side = sc.nextLine();
        if (side.equals("c")) {
            // client side
            Client client = new Client();
        } else if (side.equals("s")) {
            // server side
            Server server = new Server();
        }
    }
}
