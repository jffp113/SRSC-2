package fct.unl.pt.SRSC2Client;


import fct.unl.pt.SRSC2Client.ClientPackage.Client;
import fct.unl.pt.SRSC2Client.ClientPackage.ClientImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class Srsc2ClientApplication implements CommandLineRunner {

	private Client client;

	public static void main(String[] args) {
		SpringApplication.run(Srsc2ClientApplication.class, args);
	}

        @Override
        public void run(String... args) {

            client = new ClientImpl();

            createUser();

            System.out.println("Any key to help option.");

            Scanner in = new Scanner(System.in);
            System.out.print("> ");
            String[] comm = in. nextLine().split(" ");

            while(!comm[0].equalsIgnoreCase("exit")){

                invokeNextCommand(comm);
                System.out.println();
                System.out.print("> ");
                comm = in. nextLine().split(" ");
            }
            System.out.println("Good Bye!");
        }

	public void invokeNextCommand(String command[]){
		switch (command[0].toLowerCase()){
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
	private void createUser() {
		//TODO
		System.out.println("1");
	}

	private void listUser(String[] in) {
		//TODO
		System.out.println("2");
	}

	private void list(String[] in) {
		//TODO
		System.out.println("3");
	}

	private void newMessages(String[] in) {
		//TODO
		System.out.println("4");
	}

	private void all(String[] in) {
		//TODO
		System.out.println("5");
	}

	private void send(String[] in) {
		//TODO
		System.out.println("6");
	}

	private void recv(String[] in) {
		//TODO
		System.out.println("7");
	}

	private void receipt(String[] in) {
		//TODO
		System.out.println("8");
	}

	private void status(String[] in) {
		//TODO
		System.out.println("9");
	}


}
