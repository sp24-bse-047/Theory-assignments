import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private ServerMessageApp messagingApp;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.messagingApp = new ServerMessageApp();
    }

    public void start() {
        System.out.println("Server started");
        try (Socket socket = serverSocket.accept(); Scanner scanner = new Scanner(System.in)) {
            System.out.println("Client connected");

            while (true) {
                System.out.println("\nServer Menu:");
                System.out.println("1. Start Chat");
                System.out.println("2. Display All Messages");
                System.out.println("3. Delete Message by ID");
                System.out.println("4. Find Message by Substring");
                System.out.println("5. Display Seen Messages");
                System.out.println("6. Sort Messages");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        messagingApp.startChat(socket, scanner);
                        break;
                    case 2:
                        messagingApp.displayAllMessages();
                        break;
                    case 3:
                        System.out.print("Enter message ID to delete: ");
                        String id = scanner.nextLine();
                        messagingApp.deleteMessageById(id);
                        break;
                    case 4:
                        System.out.print("Enter substring to search for: ");
                        String substring = scanner.nextLine();
                        messagingApp.findMessageBySubstring(substring);
                        break;
                    case 5:
                        messagingApp.displaySeenMessages();
                        break;
                    case 6:
                        messagingApp.sortMessage();
                    case 7:
                        System.out.println("Shutting down server.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(15000);
            Server server = new Server(serverSocket);
            server.start();
        } catch (IOException e) {
            System.out.println("Failed to start server: " + e.getMessage());
        }
    }
}
