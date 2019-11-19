package pt.unl.fct.srsc.ClientPackage;

import java.util.List;
import java.util.Scanner;

public class Client {

    private static String BASE;
    private static String SPACE = "    ";

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
        String comm = in. nextLine().toLowerCase().trim();

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
            comm = in. nextLine().toLowerCase().trim();
        }
        System.exit(0);
    }

    private static void help() {
        //TODO
        System.out.println("LIST USER:          listuser");
        System.out.println("LIST ALL USERS:     listall");
        System.out.println("LIST NEW MESSAGES:  new");
        System.out.println("LIST ALL MESSAGES:  all");
        System.out.println("SEND MESSAGES:      send");
        System.out.println("RECEIVE MESSAGES:   recv");
        System.out.println("RECEIPT MESSAGES:   receipt");
        System.out.println("MESSAGES STATUS:    status");
        System.out.println("EXIT:               exit");
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
        System.out.print("  > USER UUID: ");
        String uuid = in.nextLine();
        System.out.println(SPACE + "USER: " + client.listUser(uuid));
    }

    private static void list(ClientImpl client, Scanner in) {
        //TODO
        List<String> list =  client.list();
        for (String a : list)
            System.out.println(SPACE + a);
    }

    private static void newMessages(ClientImpl client, Scanner in) {
        //TODO
        System.out.print("  > USER ID: ");
        String id = in.nextLine();

        List<String> list =  client.newMessages(id);
        if(list.isEmpty()){
            System.out.println(SPACE + "NO NEW MESSAGE FOR USER: " + id);
        }else
            for (String a : list)
                System.out.println(SPACE + a);
    }

    private static void all(ClientImpl client, Scanner in) {
        //TODO
        System.out.print("  > USER ID: ");
        String id = in.nextLine();

        List<String> list =  client.all(id);
        if(list.isEmpty()){
            System.out.println(SPACE + "NO MESSAGE FROM USER BOX: " + id);
        }else
            for (String a : list)
                System.out.println(SPACE  + a);
    }

    private static void send(ClientImpl client, Scanner in) {
        //TODO
        System.out.print("  > USER TO: ");
        String to = in.nextLine();
        System.out.print("  > MESSAGE: ");
        String message = in.nextLine();
        client.send(to, message);
        System.out.println(SPACE + "MESSAGE SENDED.");
    }

    private static void recv(ClientImpl client, Scanner in) {
        //TODO
        System.out.print("  > USER ID: ");
        String id = in.nextLine();
        System.out.print("  > MESSAGE ID: ");
        String mid = in.nextLine();
        String message = client.recv(id, mid);
        System.out.println(SPACE + "MESSAGE: " + message);
    }

    private static void receipt(ClientImpl client, Scanner in) {
        //TODO
        System.out.print("  > MESSAGE ID: ");
        String mid = in.nextLine();
        client.receipt(mid);
        System.out.println(SPACE + "MESSAGE RECEIPT.");
    }

    private static void status(ClientImpl client, Scanner in) {
        //TODO
        System.out.print("  > MESSAGE ID: ");
        String mid = in.nextLine();
        String status = client.status(mid);
        System.out.println(SPACE + status);
    }

}
