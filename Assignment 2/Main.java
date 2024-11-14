import java.util.Scanner;

public class Main {
    private static int choice = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter server ip address");
        String ip = sc.nextLine();

        // Create MessagingApp instance
        MessagingApp messagingApp = new MessagingApp();

        // Pass the instance to Client
        Client client = new Client(ip, 15000, messagingApp);

        while (choice != 0) {
            System.out.println("Enter 1 to start chat ");
            System.out.println("Enter 2 to Display All Messages ");
           System.out.println("Enter 3 to Delete Messages by id");
           System.out.println("Enter 4 to Find message by substring ");
           System.out.println("Enter 5 to Display Sorted Messages ");
           System.out.println("Enter 6 to Display Seen Messages");
            System.out.println("Enter 0 to Exit ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    client.Start(sc);
                    // Check if the chat has ended
                    if (!client.isConnected()) {
                        System.out.println("Chat ended. Returning to main menu.");
                    }
                    break;
                case 2:
                    messagingApp.displayAllMessages();
                    break;
                case 3:
                    messagingApp.deleteMessageById(sc);
                    break;
                case 4:
                messagingApp.findMessageBySubstring(sc);
                break;
                case 5:
                    messagingApp.sortedMessgaes();
                    break;
                    case 6:
                        messagingApp.displaySeenMessages();
                    case 0:

                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}
