package pt.unl.fct.srsc.cliente.Cliente.Services;

import pt.unl.fct.srsc.cliente.Cliente.Model.Message;
import pt.unl.fct.srsc.cliente.Cliente.Model.User;

import java.util.List;

public interface Client {

    //TODO Id: LONGS

    boolean isLoggedIn();
    void login(String keystore,String password,String alias,String keyPass);
    String createUser(String keystore,String password,String alias,String keyPass);
    String listUser(String id);
    List<User> list();
    List<String> newMessages(String id);
    List<String> all(String id);
    List<Long> send(String to, String message);
    Message recv(String id, String mid);
    boolean receipt(String mid);
    Message status(String mid);

}
