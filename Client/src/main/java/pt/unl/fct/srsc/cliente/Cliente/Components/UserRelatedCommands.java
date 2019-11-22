package pt.unl.fct.srsc.cliente.Cliente.Components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pt.unl.fct.srsc.cliente.Cliente.Services.Client;


@ShellComponent
public class UserRelatedCommands {


    private Client client;

    @Autowired
    public UserRelatedCommands(Client client) {
        this.client = client;
    }

    @ShellMethod("Login a user to execute commands on his behalf")
    public String login(
            @ShellOption() String keystore,
            @ShellOption() String password,
            @ShellOption() String alias,
            @ShellOption() String keyPass
    ){


        return "Logged In user with id %s";
    }

    @ShellMethod("Create a new user in the repository")
    public String createUser(
            @ShellOption() String keystore,
            @ShellOption() String password,
            @ShellOption() String alias,
            @ShellOption() String keyPass
    ){

        client.createUser(keystore,password,alias,keyPass);

        return null;
    }

    @ShellMethod("List a specific user in a repository")
    public String listUser(
            @ShellOption(defaultValue="") String id
    ){
        String response = "";
        if(id.isEmpty()){
            response = client.list().toString();
        }else{
            response = client.listUser(id);
        }


        return response;
    }


}
