package pt.unl.fct.srsc.ClientPackage;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivateKey;

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

    public void listUser() {
        //TODO
        URL url = createURL(USERS, LIST_ID);
    }

    public void list() {
        //TODO
        URL url = createURL(USERS, LIST);
    }

    public void newMessages() {
        //TODO
        URL url = createURL(MESSAGES, NEW);
    }

    public void all() {
        //TODO
        URL url = createURL(MESSAGES, ALL);
    }

    public void send() {
        //TODO
        URL url = createURL(MESSAGES, SEND);
    }

    public void recv() {
        //TODO
        URL url = createURL(MESSAGES, RECV);
    }

    public void receipt() {
        //TODO
        URL url = createURL(MESSAGES, RECEIPT);
    }

    public void status() {
        //TODO
        URL url = createURL(MESSAGES, STATUS);
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
