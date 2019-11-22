package fct.unl.pt.SRSC2Client.ClientPackage;

import java.security.PrivateKey;
import java.util.LinkedList;
import java.util.List;

public class ClientImpl implements Client {

    private String BASE;
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

    public ClientImpl(){
    }

    public String createUser() {
        //TODO
        return null;
    }

    public String listUser(String uuid) {
        //TODO
        return uuid;
    }

    public List<String> list() {
        //TODO
        List<String> list = new LinkedList<>();
        return list;
    }

    public List<String> newMessages(String id) {
        //TODO
        List<String> list = new LinkedList<>();
        return list;
    }

    public List<String> all(String id) {
        //TODO
        List<String> list = new LinkedList<>();
        return list;
    }

    public String send(String to, String message) {
        //TODO
        return message;
    }

    public String recv(String id, String mid) {
        //TODO
        return mid;
    }

    public void receipt(String mid) {
        //TODO
    }

    public String status(String mid) {
        //TODO
        return mid;
    }

    private String createURL(String model, String method){
        return BASE + model + method;
    }

}
