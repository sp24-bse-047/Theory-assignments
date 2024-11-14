import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MessagingApp {
    private boolean connected;
    private DataOutputStream outputStream;
    private ArrayList<Message> messages = new ArrayList<>();
    private Message message;

    public void startChat(Socket socket, Scanner scanner) {
        try (DataInputStream inputStream = new DataInputStream(socket.getInputStream())) {
            // Initialize output stream
            outputStream = new DataOutputStream(socket.getOutputStream());
            connected = true;

            // Start a separate thread to listen for incoming messages from the server
            Thread thread = new Thread(() -> {
                try {
                    while (connected) {
                        String input = inputStream.readUTF(); // Read message from server
                        System.out.println("SERVER: " + input);
                        messages.add(new Message(input, LocalDateTime.now()));

                        if (input.equalsIgnoreCase("EXIT")) {
                            connected = false;
                            System.out.println("SERVER EXIT");
                            break;
                        }
                    }
                } catch (IOException e) {
                    if (connected) {
                        System.out.println("Connection lost: " + e.getMessage());
                    }
                    connected = false;
                }
            });

            thread.start(); // Start the thread for reading incoming messages

            // Main loop to send messages from the client side
            String input;
            while (connected) {
                System.out.println("Enter message (type 'EXIT' to quit): ");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("EXIT")) {
                    connected = false;
                    outputStream.writeUTF("EXIT");  // Notify server about exit
                    System.out.println("You have exited the chat.");
                    break;
                }

                try {
                    outputStream.writeUTF(input); // Send the message
                    messages.add(new Message(input, LocalDateTime.now())); // Store the message
                } catch (IOException e) {
                    System.out.println("Failed to send message: " + e.getMessage());
                }
            }

            // Wait for the thread to finish reading incoming messages
            try {
                thread.join(); // Ensure the thread finishes
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error initializing streams or socket: " + e.getMessage());
        } finally {
            try {
                // Close output stream and socket
                if (outputStream != null) {
                    outputStream.close();
                }

                // Close socket after everything is done
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Failed to close resources: " + e.getMessage());
            }
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void displayAllMessages() {
        if (messages.isEmpty()) {
            System.out.println("No messages to display.");
        } else {
            for (Message message : messages) {
                System.out.println(message);
                message.setSeen(true);
            }
        }
    }
    // Method to delete a message by its id
    public void deleteMessageById(Scanner scanner) {
        System.out.println("Enter the ID of the message to delete:");
        String messageId = scanner.nextLine();  // Get the message ID input from the user

        // Iterate through the list of messages
        boolean messageFound = false;
        for (Message message : messages) {
            // If message id matches, remove the message and return true
            if (message.getId().equals(messageId)) {
                messages.remove(message);
                System.out.println("Message with ID " + messageId + " has been deleted.");
                messageFound = true;
                break;  // Exit the loop once the message is found and deleted
            }
        }

        // If no message was found with the given id
        if (!messageFound) {
            System.out.println("Message with ID " + messageId + " not found.");
        }
    }
    public void findMessageBySubstring(Scanner scanner) {
        System.out.println("Enter substring to search for:");
        String substring = scanner.nextLine();  // Get the substring input from the user

        boolean messageFound = false;
        for (Message message : messages) {
            // Check if the message contains the substring
            if (message.getMessage().toLowerCase().contains(substring.toLowerCase())) {
                System.out.println("Found Message: " + message);
                messageFound = true;
            }
        }

        // If no message was found with the substring
        if (!messageFound) {
            System.out.println("No messages found containing the substring \"" + substring + "\".");
        }
    }

    public void sortedMessgaes(){
        Collections.sort(messages);
        for (Message message : messages) {
            System.out.println(message);
        }


    }
    public void displaySeenMessages() {
        if (messages.isEmpty()) {
            System.out.println("No messages to display.");
        } else {
            boolean seenMessagesExist = false;
            for (Message msg : messages) {
                if (msg.isSeen()) {
                    System.out.println(msg);
                    seenMessagesExist = true;
                }
            }

            if (!seenMessagesExist) {
                System.out.println("No seen messages to display.");
            }
        }
    }


}
