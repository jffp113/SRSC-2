package pt.unl.fct.srsc.cliente.Cliente.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pt.unl.fct.srsc.cliente.Cliente.Handlers.RestTemplateResponseErrorHandler;
import pt.unl.fct.srsc.cliente.Cliente.Model.Message;
import pt.unl.fct.srsc.cliente.Cliente.Model.User;
import pt.unl.fct.srsc.cliente.Cliente.Security.AssymetricEncription;
import pt.unl.fct.srsc.cliente.Cliente.Security.CertificateUtil;
import pt.unl.fct.srsc.cliente.Cliente.Utils.B64;
import pt.unl.fct.srsc.cliente.Cliente.Utils.HASH;


import javax.annotation.PostConstruct;
import java.io.File;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
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

    private String uuid;
    private Long myId;
    private String secdata;

    private RestTemplate restTemplate;

    @Autowired
    private MessageDisassembler disassembler;

    @Autowired
    private CertificateUtil cert;

    @Autowired
    private MessageBuilder mBuilder;



    @Autowired
    public ClientImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    @PostConstruct
    public void init() {
        uuid = HASH.digestToString(cert.getPersonalCertificate().getPublicKey().getEncoded());
        secdata = B64.encode(cert.getPersonalCertificate().getPublicKey().getEncoded());
        myId = this.createUser(uuid, secdata);
    }

    public Long createUser(String uuid, String secdata){
        User u = new User(uuid, secdata);
        ResponseEntity<Long> response  =
                restTemplate.postForEntity(createURL(USERS, CREATE), u, Long.class);
        return response.getBody();
    }

    public User listUser(Long id) {
        ResponseEntity<User> response  =
                restTemplate.getForEntity(createURL(USERS, LIST_ID,id),User.class);

        return response.getBody();
    }

    public List<User> list() {
        ResponseEntity<User[]> response  =
                restTemplate.getForEntity(createURL(USERS, LIST),User[].class);

        return Arrays.asList(response.getBody());
    }

    public List<Message> newMessages(Long id) {
        ResponseEntity<Message[]> response  =
                restTemplate.getForEntity(createURL(MESSAGES, NEW, id),Message[].class);
        return Arrays.asList(response.getBody());
    }

    public List<Message> all(Long id) {
//        URL url = createURL(MESSAGES, ALL);
        ResponseEntity<Message[]> response  =
                restTemplate.getForEntity(createURL(MESSAGES, ALL, id), Message[].class);
        return Arrays.asList(response.getBody());
    }

    public List<Long> send(Long to, String message) throws Exception {
        String messageFrom = mBuilder.build(message, getUserPublicKey(to));
        String messageTo = mBuilder.build(message, B64.decode(secdata));
        Message m = new Message(myId, to, messageFrom, messageTo);

        ResponseEntity<Long[]> response  =
                restTemplate.postForEntity(createURL(MESSAGES, SEND, myId, to), m, Long[].class);
        return Arrays.asList(response.getBody());
    }

    public Message recv(Long id, Long mid) {
        ResponseEntity<Message> response  =
                restTemplate.getForEntity(createURL(MESSAGES, RECV, id, mid), Message.class);
        return response.getBody();
    }

    public boolean receipt(Long mid) {
        ResponseEntity<Void> response  =
                restTemplate.getForEntity(createURL(MESSAGES, SEND, mid), Void.class);
        return response.getStatusCodeValue() == 200;
    }

    public Message status(Long mid) {
        ResponseEntity<Message> response  =
                restTemplate.getForEntity(createURL(MESSAGES, RECV, mid), Message.class);
        return response.getBody();
    }

    /* Auxiliary Methods ------------------------------------------------------------------- */

    private <T> String createURL(String model, String method, T... f){
        return String.format(BASE + model + method, f);
    }

    private byte[] getUserPublicKey(Long to) {
        User userTo = listUser(to);
        return B64.decode(userTo.getSecdata());
    }

}
