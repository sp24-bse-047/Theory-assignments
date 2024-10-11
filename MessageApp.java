import java.util.Scanner;
public class MessageApp{
    Message message[];
    String contacts[];
    int messageCount;
    int contactCount;
    MessageApp(){
        message=new Message[1000];
        messageCount=0;
        contacts=new String[500];
        contactCount=0;
    }


    private boolean contactExists(String number) {
        for(int i=0;i <contactCount; i++) {
            if(contacts[i].equals(number)) {
                return true;
            }
        }
        return false;
    }
    public void sendMessage(){
        Scanner input = new Scanner(System.in);
       if(messageCount<message.length) {
           System.out.println("Enter sender phone number:");
           String sender = input.nextLine();
           System.out.println("Enter receiver phone number:");
           String receiver = input.nextLine();
           System.out.println("Enter Message content:");
           String content = input.nextLine();

           if(!contactExists(sender)) {
               contacts[contactCount++]=sender;
           }
           if(!contactExists(receiver)) {
               contacts[contactCount++]=receiver;
           }



           message[messageCount++] = new Message(sender, receiver, content, true, false);
           System.out.println("Message sent successfully");
       }
       else
       {
           System.out.println("Storage full");
       }

    }
    public void sortMessagesByTimeStamp(Message[]tempMessages) {
        for (int i = 0; i < messageCount; i++) {
            for (int j = 0; j < messageCount; j++) {
                if (j + 1 < messageCount && tempMessages[j].getTimestamp().isBefore(tempMessages[j + 1].getTimestamp())) {
                    // Swap if out of order
                    Message temp = tempMessages[j];
                    tempMessages[j] = tempMessages[j + 1];
                    tempMessages[j + 1] = temp;
                }
            }
        }
    }

    public void addContact() {
        if(contactCount>=contacts.length) {
            System.out.println("Contact list is full!");
            return;
        }

        Scanner input=new Scanner(System.in);
        System.out.println("Enter contact phone number:");
        String contactNumber=input.nextLine();

        if (contactExists(contactNumber)) {
            System.out.println("This contact already exists in your list.");
        } else {
            contacts[contactCount++] = contactNumber;
            System.out.println("Contact added successfully.");
        }
    }

    public void displayContacts() {
        if (contactCount==0) {
            System.out.println("No contacts available.");
            return;
        }

        System.out.println("Contacts list:");
        for (int i=0;i<contactCount;i++) {
            System.out.println((i+1) + ". " + contacts[i]);
        }
    }


    public void displayAllMessages() {
        Message[] tempMessages = new Message[messageCount];
        for (int i = 0; i <messageCount; i++) {
            tempMessages[i] = message[i];

        }

        sortMessagesByTimeStamp(tempMessages);
        for (int i = 0; i < tempMessages.length; i++) {
            if (tempMessages[i] != null) {
                System.out.println("Message Id:" + tempMessages[i].getMessageId());
                System.out.println("Sender Phone Number:" + tempMessages[i].getSender());
                System.out.println("Receiver Phone Number:" + tempMessages[i].getReceiver());
                System.out.println("Message content:" + tempMessages[i].getContent());
                System.out.println("Sent status:" + tempMessages[i].getStatus());
                System.out.println("Seen Status:" + tempMessages[i].isSeen());
                System.out.println(tempMessages[i].getTimestamp());
                System.out.println(" ");
                System.out.println("-------------------------------------------------------------------");
                }


          }
    }
        public void displaySpecificMessages(){
            boolean found=false;
            Message []tempMessages = new Message[messageCount];
            Scanner input=new Scanner(System.in);
            System.out.println("Enter Sender Phone Number:");
            String sender= input.nextLine();
            System.out.println("Enter Receiver Phone Number:");
            String receiver= input.nextLine();
            for(int i=0;i<messageCount;i++){
                tempMessages[i]=message[i];
            }
            sortMessagesByTimeStamp(tempMessages);
            for(int i=0;i< tempMessages.length;i++){
            if(tempMessages[i].getSender().equals(sender)&&tempMessages[i].getReceiver().equals(receiver)){
                System.out.println("Message Id:"+tempMessages[i].getMessageId());
                System.out.println("Sender Phone Number:"+tempMessages[i].getSender());
                System.out.println("Receiver Phone Number:"+tempMessages[i].getReceiver());
                System.out.println("Message content:"+tempMessages[i].getContent());
                System.out.println("Sent status:"+tempMessages[i].getStatus());
                System.out.println("Seen Status:"+tempMessages[i].isSeen());
                System.out.println(tempMessages[i].getTimestamp());
                System.out.println(" ");
                System.out.println("-------------------------------------------------------------------");
                found=true;
             }

            }
            if(found!=true){
                System.out.println("-------------------------------------------------------------------");
                System.out.println("no chat found");
                System.out.println("-------------------------------------------------------------------");
            }
    }

        public void viewMessage() {
            Scanner input = new Scanner(System.in);
            boolean found=false;
            Message[] tempMessages = new Message[messageCount];
            System.out.println("Enter Sender Phone Number:");
            String sender = input.nextLine();
            System.out.println("Enter Receiver Phone Number:");
            String receiver = input.nextLine();
            for (int i = 0; i < messageCount; i++) {
                tempMessages[i] = message[i];
            }
           sortMessagesByTimeStamp(tempMessages);
            for (int i = 0; i < tempMessages.length; i++) {
                if (tempMessages[i].getSender().equals(sender) && tempMessages[i].getReceiver().equals(receiver)) {
                     tempMessages[i].setSeen(true);
                    System.out.println("Message Id:" + tempMessages[i].getMessageId());
                    System.out.println("Sender Phone Number:" + tempMessages[i].getSender());
                    System.out.println("Receiver Phone Number:" + tempMessages[i].getReceiver());
                    System.out.println("Message content:" + tempMessages[i].getContent());
                    System.out.println("Sent status:" + tempMessages[i].getStatus());
                    System.out.println("Seen Status:" + tempMessages[i].isSeen());
                    System.out.println(tempMessages[i].getTimestamp());
                    System.out.println(" ");
                    System.out.println("-------------------------------------------------------------------");
                    found=true;
                }


            }
          if(found!=true){
              System.out.println("-------------------------------------------------------------------");
              System.out.println("no chat found");
              System.out.println("-------------------------------------------------------------------");
          }
    }



         public void deleteMessage(){
             boolean found=false;
             Scanner input=new Scanner(System.in);

             System.out.println("Enter Message Id:");
             String id= input.nextLine();
             for (int i = 0; i < messageCount; i++) {
                 if(message[i].getMessageId().equals(id)){
                     for(int j=i;j<messageCount-1;j++) {
                         message[j] = message[j + 1];
                     }
                    message[messageCount-1]=null;
                     messageCount--;
                     found=true;
                     System.out.println("Message deleted");
                     break;

                 }
             }

            if(found!=true){
                System.out.println("Message not found");

            }

         }

        public void setFalse(){
            boolean found=false;
            Scanner input=new Scanner(System.in);
            System.out.println("Enter Message Id:");
            String id= input.nextLine();
        for(int i=0;i<messageCount;i++){
            if(message[i].getMessageId().equals(id)){
                message[i].setStatus(false);
                found=true;
            }


        }
        if(found!=true){
            System.out.println("Message not found");

        }



        }

public void displayNotSent(){
        boolean found=false;
    Message[] tempMessages = new Message[messageCount];
    for (int i = 0; i < messageCount; i++) {
        tempMessages[i] = message[i];
    }

    sortMessagesByTimeStamp(tempMessages);
        for(int i = 0; i < tempMessages.length; i++){
            if(tempMessages[i].getStatus()==false){
                System.out.println("Message Id:" + tempMessages[i].getMessageId());
                System.out.println("Sender Phone Number:" + tempMessages[i].getSender());
                System.out.println("Receiver Phone Number:" + tempMessages[i].getReceiver());
                System.out.println("Message content:" + tempMessages[i].getContent());
                System.out.println("Sent status:" + tempMessages[i].getStatus());
                System.out.println("Seen Status:" + tempMessages[i].isSeen());
                System.out.println(tempMessages[i].getTimestamp());
                System.out.println(" ");
                System.out.println("-------------------------------------------------------------------");
                found=true;
            }




        }
            if(found!=true){
                System.out.println("Message not found");


            }
    }


}



















