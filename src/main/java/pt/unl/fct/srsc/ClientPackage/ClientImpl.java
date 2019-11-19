package pt.unl.fct.srsc.ClientPackage;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClientImpl {

    private static String BASE;
    private static String USERS = "/users";
    private static String MESSAGES = "/messages";

    private static String CREATE = "/create";
    private static String LIST = "/list";
    private static String LIST_ID = "/list/%s";
    private static String NEW = "/new/%s";
    private static String ALL = "/all/%s";
    private static String SEND = "/send";
    private static String RECV = "/recv/%s/%s";
    private static String RECEIPT = "/receipt/%s/%s";
    private static String STATUS = "/status/%s/%s";

    private PrivateKey privateKey;
    private String uuid;

    public ClientImpl(String baseUrl) {
        this.BASE = baseUrl;
        this.privateKey = null;
        this.uuid = null;
    }

    public String createUser() {
        //TODO
        URL url = createURL(USERS, CREATE);
        return "error";
    }

    public String listUser(String uuid) {
        //TODO
        URL url = createURL(USERS, LIST_ID);
        return uuid;
    }

    public List<String> list() {
        //TODO
        List<String> list = new LinkedList<>();
        URL url = createURL(USERS, LIST);
        list.add("User1");
        list.add("User2");
        list.add("User3");
        return list;
    }

    public List<String> newMessages(String id) {
        //TODO
        URL url = createURL(MESSAGES, NEW);
        List<String> list = new LinkedList<>();
        list.add(id + ": NewMessage1");
        list.add(id + ": NewMessage2");
        list.add(id + ": NewMessage3");
        return list;
    }

    public List<String> all(String id) {
        //TODO
        URL url = createURL(MESSAGES, ALL);
        List<String> list = new LinkedList<>();
        list.add(id + ": AllMessage1");
        list.add(id + ": AllMessage2");
        list.add(id + ": AllMessage3");
        return list;
    }

    public String send(String to, String message) {
        //TODO
        URL url = createURL(MESSAGES, SEND);
        return message;
    }

    public String recv(String id, String mid) {
        //TODO
        URL url = createURL(MESSAGES, RECV);
        return mid;
    }

    public void receipt(String mid) {
        //TODO
        URL url = createURL(MESSAGES, RECEIPT);
    }

    public String status(String mid) {

        //TODO
        URL url = createURL(MESSAGES, STATUS);
        return mid;
    }

    private static URL createURL(String model, String method){
        try {
            return new URL(BASE + model + method);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
