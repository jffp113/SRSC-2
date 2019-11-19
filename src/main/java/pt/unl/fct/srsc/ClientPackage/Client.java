package pt.unl.fct.srsc.ClientPackage;

import java.util.Scanner;

public class Client {

    private static String BASE;

    public static void main(String[] args) {

        if(args.length == 0)
            BASE = "http://localhost:8080";
        else
            BASE = args[0];

        ClientImpl client = new ClientImpl(BASE);
        Scanner in = new Scanner(System.in);

        //Criar user,
        //Caso já exista continuar
        createUser(client, in);
        System.out.println();

        System.out.print("Any input for help option.");
        System.out.println();
        System.out.print("> ");
        String comm = in. nextLine().toLowerCase();

        while(!comm.equals("exit")){
            switch (comm){
                case "listuser":
                    listUser(client, in);
                    break;
                case "listall":
                    list(client, in);
                    break;
                case "new":
                    newMessages(client, in);
                    break;
                case "all":
                    all(client, in);
                    break;
                case "send":
                    send(client, in);
                    break;
                case "recv":
                    recv(client, in);
                    break;
                case "receipt":
                    receipt(client, in);
                    break;
                case "status":
                    status(client, in);
                    break;
                default: help();
            }
            System.out.println();
            System.out.print("> ");
            comm = in. nextLine().toLowerCase();
        }
        System.exit(0);
    }

    private static void help() {
        //TODO
        System.out.println("LIST USER:          listuser <userID>");
        System.out.println("LIST ALL USERS:     listall");
        System.out.println("LIST NEW MESSAGES:  new <userID>");
        System.out.println("LIST ALL MESSAGES:  all <userID>");
        System.out.println("SEND MESSAGES:      send");
        System.out.println("RECEIVE MESSAGES:   recv <userID> <msgID>");
        System.out.println("RECEIPT MESSAGES:   receipt <userID> <msgID>");
        System.out.println("MESSAGES STATUS:    status <userID> <msgID>");
    }

    //API Methods --------------------------------------------

    private static void createUser(ClientImpl client, Scanner in) {
        //TODO
        System.out.println("Trying create new user...");
        String response = client.createUser();
        if(response.equals("error"))
            System.out.println("You already exists in the System");
        else
            System.out.println("New user created");
    }

    private static void listUser(ClientImpl client, Scanner in) {
        //TODO
        System.out.println("List user:");
        client.listUser();
    }

    private static void list(ClientImpl client, Scanner in) {
        //TODO
        System.out.println("List all users:");
        client.list();
    }

    private static void newMessages(ClientImpl client, Scanner in) {
        //TODO
        System.out.println("Get new messages:");
        client.newMessages();
    }

    private static void all(ClientImpl client, Scanner in) {
        //TODO
        System.out.println("Get all messages:");
        client.all();
    }

    private static void send(ClientImpl client, Scanner in) {
        //TODO
        System.out.println("Send message:");
        client.send();
    }

    private static void recv(ClientImpl client, Scanner in) {
        //TODO
        System.out.println("Receive message:");
        client.recv();
    }

    private static void receipt(ClientImpl client, Scanner in) {
        //TODO
        System.out.println("Receipt message:");
        client.receipt();
    }

    private static void status(ClientImpl client, Scanner in) {
        //TODO
        System.out.println("Status:");
        client.status();
    }

}
