package pt.unl.fct.srsc.ClientPackage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.LinkedList;
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
