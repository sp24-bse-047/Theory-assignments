import java.util.Scanner;

import java.util.Scanner;
public class Main{
    static int choice=-1;
    public static void main(String []args){
        MessageApp m1=new MessageApp();
        while(choice!=0){
            Scanner input=new Scanner(System.in);
            System.out.println("..................................................................");
            System.out.println("                   VWELCOME TO WHATSAPP");
            System.out.println("..................................................................");
            System.out.println("Enter 1 to Send Messagge");
            System.out.println("Enter 2 to Display All Messagge");
            System.out.println("Enter 3 to Display Messages from Specific User");
            System.out.println("Enter 4 to See Messages");
            System.out.println("Enter 5 to Delete Message");
            System.out.println("Enter 6 to Set Message Status to not Sent");
            System.out.println("Enter 7 to See Unsent Messages");
            System.out.println("Enter 8 to Add Contact");
            System.out.println("Enter 9 to Display All Contacts");
            System.out.println("Enter 0 to Exit");
            System.out.println("..................................................................");
            choice=input.nextInt();
            switch (choice) {
                case 1:
                    m1.sendMessage();
                    break;
                case 2:
                    m1.displayAllMessages();
                    break;
                case 3:
                    m1.displaySpecificMessages();
                    break;
                case 4:
                    m1.viewMessage();
                    break;
                case 5:
                    m1.deleteMessage();
                    break;
                case 6:
                    m1.setFalse();
                    break;
                case 7:
                    m1.displayNotSent();
                    break;
                case 8:
                    m1.addContact();
                    break;
                case 9:
                    m1.displayContacts();
                    break;
                case 0:
                    System.out.println("Terminating program....");
                break;
                default:
                    System.out.println("Invalid choice");



            }
        }


    }







}








