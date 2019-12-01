package pt.unl.fct.srsc.cliente.Cliente.Services;

import pt.unl.fct.srsc.cliente.Cliente.Model.Message;
import pt.unl.fct.srsc.cliente.Cliente.Model.User;

import java.util.List;

public interface Client {
    Long createUser(String uuid, String secdata);
    User listUser(Long id);
    List<User> list();
    List<Message> newMessages(Long id);
    List<Message> all(Long id);
    String send(Long to, String message) throws Exception;
    Message recv(Long id, Long mid);
    void receipt(Long mid) throws Exception;
    String status(Long id, Long mid);

}
