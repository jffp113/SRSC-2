package pt.unl.fct.srsc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pt.unl.fct.srsc.ClientPackage.Client;

import java.util.List;
import java.util.Scanner;


//@SpringBootApplication
public class SpringBootConsoleApplication
  implements CommandLineRunner {


    private static String SPACE = "    ";

    @Autowired
    private Client client;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Write your command");

        Scanner in = new Scanner(System.in);
        System.out.print("> ");
        String[] comm = in. nextLine().split(" ");
        while(!comm.equals("exit")){

            invokeNextCommand(comm);
            System.out.println();
            System.out.print("> ");
            comm = in. nextLine().split(" ");
        }
    }

    public void invokeNextCommand(String command[]){
            switch (command[0]){
                case "createUser":
                    createUser(command);
                    break;
                case "listuser":
                    listUser(command);
                    break;
                case "listall":
                    list(command);
                    break;
                case "new":
                    newMessages(command);
                    break;
                case "all":
                    all(command);
                    break;
                case "send":
                    send(command);
                    break;
                case "recv":
                    recv(command);
                    break;
                case "receipt":
                    receipt(command);
                    break;
                case "status":
                    status(command);
                    break;
                default: help();
            }

    }
    private void help() {
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
    private void createUser(String[] in) {
        //TODO
        System.out.println("Trying create new user...");
        String response = client.createUser();
        if(response.equals("error"))
            System.out.println("You already exists in the System");
        else
            System.out.println("New user created");
    }

    private void listUser(String[] in) {
        //TODO
        System.out.print("  > USER UUID: ");
        String uuid = in[1];
        System.out.println(SPACE + "USER: " + client.listUser(uuid));
    }

    private void list(String[] in) {
        //TODO
        List<String> list =  client.list();
        for (String a : list)
            System.out.println(SPACE + a);
    }

    private void newMessages(String[] in) {
        //TODO
        System.out.print("  > USER ID: ");
        String id = in[1];

        List<String> list =  client.newMessages(id);
        if(list.isEmpty()){
            System.out.println(SPACE + "NO NEW MESSAGE FOR USER: " + id);
        }else
            for (String a : list)
                System.out.println(SPACE + a);
    }

    private void all(String[] in) {
        //TODO
        System.out.print("  > USER ID: ");
        String id = in[1];

        List<String> list =  client.all(id);
        if(list.isEmpty()){
            System.out.println(SPACE + "NO MESSAGE FROM USER BOX: " + id);
        }else
            for (String a : list)
                System.out.println(SPACE  + a);
    }

    private void send(String[] in) {
        //TODO
        System.out.print("  > USER TO: ");
        String to = in[1];
        System.out.print("  > MESSAGE: ");
        String message = in[2];
        client.send(to, message);
        System.out.println(SPACE + "MESSAGE SENDED.");
    }

    private void recv(String[] in) {
        //TODO
        System.out.print("  > USER ID: ");
        String id = in[1];
        System.out.print("  > MESSAGE ID: ");
        String mid = in[2];
        String message = client.recv(id, mid);
        System.out.println(SPACE + "MESSAGE: " + message);
    }

    private void receipt(String[] in) {
        //TODO
        System.out.print("  > MESSAGE ID: ");
        String mid = in[1];
        client.receipt(mid);
        System.out.println(SPACE + "MESSAGE RECEIPT.");
    }

    private void status(String[] in) {
        //TODO
        System.out.print("  > MESSAGE ID: ");
        String mid = in[1];
        String status = client.status(mid);
        System.out.println(SPACE + status);
    }


}
