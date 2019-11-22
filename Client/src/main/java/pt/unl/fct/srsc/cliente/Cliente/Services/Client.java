package pt.unl.fct.srsc.cliente.Cliente.Services;

import org.springframework.shell.standard.ShellOption;
import pt.unl.fct.srsc.cliente.Cliente.Model.User;

import java.util.List;

public interface Client {

    boolean isLoggedIn();
    void login(String keystore,String password,String alias,String keyPass);
    String createUser(String keystore,String password,String alias,String keyPass);
    String listUser(String id);
    List<User> list();
    List<String> newMessages(String id);
    List<String> all(String id);
    String send(String to, String message);
    String recv(String id, String mid);
    void receipt(String mid);
    String status(String mid);

}
