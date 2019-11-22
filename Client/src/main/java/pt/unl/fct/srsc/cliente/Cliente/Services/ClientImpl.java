package pt.unl.fct.srsc.cliente.Cliente.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pt.unl.fct.srsc.cliente.Cliente.Handlers.RestTemplateResponseErrorHandler;
import pt.unl.fct.srsc.cliente.Cliente.Model.User;


import java.security.PrivateKey;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class ClientImpl implements Client {

    @Value("${client.server.url}")
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
    private String myId;
    private RestTemplate restTemplate;

    @Autowired
    public ClientImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    @Override
    public boolean isLoggedIn() {
        return false;
    }

    @Override
    public void login(String keystore, String password, String alias, String keyPass) {
        //LoadCertificate
        //Generate UUID
        //Get User ID From server
    }

    @Override
    public String createUser(String keystore, String password, String alias, String keyPass){
        User u = new User("testUUID","mysecdata");
        ResponseEntity<String> response  =
                restTemplate.postForEntity(createURL(USERS, CREATE), u, String.class);
        myId = response.getBody();
        return myId;
    }

    public String listUser(String id) {
        ResponseEntity<User> response  =
                restTemplate.getForEntity(String.format(createURL(USERS, LIST_ID),id),User.class);

        return response.getBody().toString();
    }

    public List<User> list() {
        ResponseEntity<User[]> response  =
                restTemplate.getForEntity(createURL(USERS, LIST),User[].class);

        return Arrays.asList(response.getBody());
    }

    public List<String> newMessages(String id) {
        //TODO
//        URL url = createURL(MESSAGES, NEW);
        List<String> list = new LinkedList<>();
        list.add(id + ": NewMessage1");
        list.add(id + ": NewMessage2");
        list.add(id + ": NewMessage3");
        return list;
    }

    public List<String> all(String id) {
        //TODO
//        URL url = createURL(MESSAGES, ALL);
        List<String> list = new LinkedList<>();
        list.add(id + ": AllMessage1");
        list.add(id + ": AllMessage2");
        list.add(id + ": AllMessage3");
        return list;
    }

    public String send(String to, String message) {
        //TODO
//        URL url = createURL(MESSAGES, SEND);
        return message;
    }

    public String recv(String id, String mid) {
        //TODO
//        URL url = createURL(MESSAGES, RECV);
        return mid;
    }

    public void receipt(String mid) {
        //TODO
//        URL url = createURL(MESSAGES, RECEIPT);
    }

    public String status(String mid) {

        //TODO
//        URL url = createURL(MESSAGES, STATUS);
        return mid;
    }

    private String createURL(String model, String method){
        return BASE + model + method;
    }
}
