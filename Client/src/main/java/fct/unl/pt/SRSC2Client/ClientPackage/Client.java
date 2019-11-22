package fct.unl.pt.SRSC2Client.ClientPackage;

import java.util.List;

public interface Client {

    String createUser();
    String listUser(String uuid);
    List<String> list();
    List<String> newMessages(String id);
    List<String> all(String id);
    String send(String to, String message);
    String recv(String id, String mid);
    void receipt(String mid);
    String status(String mid);

}
