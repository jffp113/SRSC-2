package pt.unl.fct.srsc.cliente.Cliente.Components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pt.unl.fct.srsc.cliente.Cliente.Model.Message;
import pt.unl.fct.srsc.cliente.Cliente.Model.User;
import pt.unl.fct.srsc.cliente.Cliente.Services.Client;

import java.util.List;

@ShellComponent
public class UserRelatedCommandsImpl {

    private Client client;

    @Autowired
    public UserRelatedCommandsImpl(Client client) {
        this.client = client;
    }

    @ShellMethod("List a specific user in a repository")
    public User listUser(
            @ShellOption() Long id)
    {
        return client.listUser(id);
    }

    @ShellMethod("List all users in a repository")
    public List<User> listAll()
    {
        return client.list();
    }

    @ShellMethod("List the new user messages")
    public List<Message> news(
            @ShellOption() Long id)
    {
        List<Message> list = client.newMessages(id);
        if(list.isEmpty()){
            System.out.println("No new Messages.");
            return list;
        }else
            return list;
    }

    @ShellMethod("List the new user messages")
    public List<Message> all(
            @ShellOption() Long id)
    {
        List<Message> list = client.all(id);
        if(list.isEmpty()){
            System.out.println("No Messages.");
            return list;
        }else
            return list;
    }

    @ShellMethod("Send new message to user")
    public List<Long> send(
            @ShellOption() Long to,
            @ShellOption() String message)
    {
        try {
            return client.send(to, message);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
