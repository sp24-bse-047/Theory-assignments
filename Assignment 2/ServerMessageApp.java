import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ServerMessageApp {
    private ArrayList<Message> messages;
    private DataOutputStream outputStream;
    private boolean connected;
    private boolean allMessagesSeen;

    public ServerMessageApp() {
        this.messages = new ArrayList<>();
        this.allMessagesSeen = false;  // Initially, all messages are unseen
    }

    // Start chat with a connected client
    // Inside ServerMessageApp class
    public void startChat(Socket socket, Scanner scanner) {
        try (DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

            this.outputStream = outputStream;
            connected = true;

            // Separate thread to handle incoming messages from the client
            Thread receiveThread = new Thread(() -> {
                try {
                    while (connected) {
                        String clientMessage = inputStream.readUTF();
                        if (clientMessage.equalsIgnoreCase("EXIT")) {
                            System.out.println("Client disconnected.");
                            connected = false;
                            break;
                        }
                        System.out.println("CLIENT: " + clientMessage);
                        messages.add(new Message(clientMessage, LocalDateTime.now()));
                    }
                } catch (IOException e) {
                    System.out.println("Connection lost: " + e.getMessage());
                    connected = false;
                }
            });

            receiveThread.start();

            // Handle outgoing messages from the server
            while (connected) {
                System.out.println("Enter message to send (type 'EXIT' to end chat): ");
                String serverMessage = scanner.nextLine();
                if (serverMessage.equalsIgnoreCase("EXIT")) {
                    connected = false;
                    outputStream.writeUTF("EXIT");
                    break;
                }
                outputStream.writeUTF(serverMessage);
                messages.add(new Message(serverMessage, LocalDateTime.now()));
            }

            receiveThread.join();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error in chat: " + e.getMessage());
        }
    }


    // Display all messages
    public void displayAllMessages() {
        if (messages.isEmpty()) {
            System.out.println("No messages to display.");
        } else {
            for (Message message : messages) {
                System.out.println(message);
            }
            allMessagesSeen = true;
            for (Message message : messages) {
                message.setSeen(true);
            }
        }
    }

    // Delete a message by ID
    public void deleteMessageById(String id) {
        boolean found = false;
        for (Message message : messages) {
            if (message.getId().equals(id)) {
                messages.remove(message);
                System.out.println("Message with ID " + id + " deleted.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No message found with ID: " + id);
        }
    }

    // Find messages by substring (case-insensitive)
    public void findMessageBySubstring(String substring) {
        boolean found = false;
        for (Message message : messages) {
            if (message.getMessage().toLowerCase().contains(substring.toLowerCase())) {
                System.out.println(message);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found containing: \"" + substring + "\".");
        }
    }

    // Display only seen messages
    public void displaySeenMessages() {
        if (!allMessagesSeen) {
            System.out.println("All messages are marked as unseen.");
            return;
        }

        boolean seenMessagesExist = false;
        for (Message message : messages) {
            if (message.isSeen()) {
                System.out.println(message);
                seenMessagesExist = true;
            }
        }

        if (!seenMessagesExist) {
            System.out.println("No seen messages to display.");
        }
    }

   public void sortMessage(){
        Collections.sort(messages);
        for (Message message : messages) {
            System.out.println(message);
        }
    }
}
