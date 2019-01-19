import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Please specify:\ns for server, c for client");
        Scanner sc = new Scanner(System.in);
        String side = sc.nextLine();
        if (side.equals("c")) {
            // client side
            System.out.println("Please enter the server IP: ");
            Client client = new Client(sc.nextLine());
        } else if (side.equals("s")) {
            // server side
            Server server = new Server();
        }
    }
}
