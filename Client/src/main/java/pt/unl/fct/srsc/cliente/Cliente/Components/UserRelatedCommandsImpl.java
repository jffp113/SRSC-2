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
    public String listUser(
            @ShellOption() Long id)
    {
        return client.listUser(id).toString();
    }

    @ShellMethod("List all users in a repository")
    public List<User> listAll()
    {
        return client.list();
    }

    @ShellMethod("List the new user messages")
    public String news(
            @ShellOption() Long id)
    {
        List<Message> list = client.newMessages(id);
        if(list.isEmpty()){
            System.out.println("No new Messages.");
            return list.toString();
        }else
            return list.toString();
    }

    @ShellMethod("List the new user messages")
    public String all(
            @ShellOption() Long id)
    {
        List<Message> list = client.all(id);
        if(list.isEmpty()){
            System.out.println("No Messages.");
            return list.toString();
        }else
            return list.toString();
    }

    @ShellMethod("Send new message to user")
    public String send(
            @ShellOption() Long to,
            @ShellOption() String message)
    {
        try {
            return client.send(to, message).toString();
        } catch (Exception e) {
            return "User not found.";
        }
    }

    @ShellMethod("Receive user message")
    public String recv(
            @ShellOption() Long id,
            @ShellOption() Long mid)
    {
        return client.recv(id, mid).toString();
    }

    @ShellMethod("Receipt user message")
    public String receipt(
            @ShellOption() Long mid)
    {
        try {
            client.receipt(mid);
        } catch (Exception e) {
            e.printStackTrace();
           return "Not receipted";
        }
        return "Receipted";
    }

    @ShellMethod("Message status")
    public String status(
            @ShellOption() Long id,
            @ShellOption() Long mid)
    {
        return client.status(id, mid).toString();
    }


}
