import java.io.DataInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String ipAddress;
    private int port;
    private MessagingApp messagingApp; // Add messagingApp as a field

    public Client(String ipAddress, int port, MessagingApp messagingApp) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.messagingApp = messagingApp;
    }

    public void Start(Scanner scanner) {
        try {
            System.out.println("Connecting to " + ipAddress + ":" + port);
            Socket socket = new Socket(ipAddress, port);
            System.out.println("Connected to " + ipAddress + ":" + port);
            messagingApp.startChat(socket, scanner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return messagingApp.isConnected();
    }
}
