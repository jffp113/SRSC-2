package pt.unl.fct.srsc.Controller.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Controller.User.UserControllerImpl;
import pt.unl.fct.srsc.Model.Message;
import pt.unl.fct.srsc.Repository.MessageBoxRepository;
import pt.unl.fct.srsc.Repository.UserRepository;
import pt.unl.fct.srsc.Utils.LOGS;

import java.util.List;

import static pt.unl.fct.srsc.Controller.User.UserControllerImpl.USER;

@RestController
public class MessageBoxControllerImpl implements MessageBoxController {

    private static final String LIST_NEW_MESSAGES = "List user new messages: ";
    private static final String LIST_ALL_MESSAGES = "List user all messages: ";
    private static final String SEND_MESSAGE = "Send message: ";
    private static final String RECEIVE_MESSAGE = "Receive message: ";
    private static final String RECEIPT_MESSAGE = "Receipt message: ";
    private static final String MESSAGE_STATUS = "Status: ";

    private static final String SIGNATURE_NOT_ACCEPTABLE = "Signature not acceptable.";
    private static final String TO_DONT_MATCH_WITH_MESSAGE = "To[id=%s] don't match with Message[mid=%s].";
    private static final String MESSAGE = "MESSAGE[mid=%s] ";
    private static final String SIGNATURE = "SIGNATURE[ %s ] ";

    @Autowired
    private MessageBoxRepository messageBoxRepository;
    @Autowired
    private UserRepository userRepository;

    private LOGS LOG = new LOGS(MessageBoxControllerImpl.class);

    @Override
    public ResponseEntity<List<Message>> listUserNewMessages(Long id) {
        if(dontExist(id)) {
            LOG.warn(LIST_NEW_MESSAGES + UserControllerImpl.DONT_EXIST);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        LOG.info(LIST_NEW_MESSAGES + USER, id);
        return ResponseEntity.ok(messageBoxRepository.getAllByToAndSignatureNull(id));
    }

    @Override
    public ResponseEntity<List<Message>> listAllUserMessages(Long id) {
        if(dontExist(id)) {
            LOG.warn(LIST_ALL_MESSAGES + UserControllerImpl.DONT_EXIST+"");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        LOG.info(LIST_ALL_MESSAGES + USER , id);
        return ResponseEntity.ok(messageBoxRepository.getAllByTo(id));
    }

    @Override
    public ResponseEntity<Long[]> sendMessageToUser(Message message) {
        Long from = message.getFrom();
        Long to = message.getTo();

        if(dontExist(from)) {
            LOG.warn(SEND_MESSAGE + UserControllerImpl.DONT_EXIST);
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(dontExist(to)) {
            LOG.warn(SEND_MESSAGE + UserControllerImpl.DONT_EXIST);
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        message.setSendDate();
        messageBoxRepository.save(message);

        LOG.info(SEND_MESSAGE + USER + "-> " + USER, from, to);
        return ResponseEntity.ok(new Long[]{from, to});
    }

    @Override
    public ResponseEntity<Message> receiveMessage(Long id, Long mid) {
        Message message = messageBoxRepository.getMessageById(mid);

        if(dontExist(id)) {
            LOG.warn(RECEIVE_MESSAGE + UserControllerImpl.DONT_EXIST);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(dontExist(message)) {
            LOG.warn(RECEIVE_MESSAGE + UserControllerImpl.DONT_EXIST);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(!message.getTo().equals(id)) {
            LOG.warn(RECEIVE_MESSAGE + SIGNATURE_NOT_ACCEPTABLE);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        LOG.info(String.format(RECEIVE_MESSAGE + USER + ", " + MESSAGE, id, mid));
        return ResponseEntity.ok(message);
    }

    @Override
    public ResponseEntity<Void> receiptMessage(Long id, Long mid, String b64Sign) {
        Message message = messageBoxRepository.getMessageByIdAndTo(mid, id);

        if(dontExist(message)) {
            LOG.warn(RECEIPT_MESSAGE + UserControllerImpl.DONT_EXIST);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        message.setSignature(b64Sign);
        message.setReceivedDate();

        if(!signCorrect(message)) {
            LOG.warn(RECEIPT_MESSAGE + SIGNATURE_NOT_ACCEPTABLE);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        messageBoxRepository.save(message);
        LOG.info(RECEIPT_MESSAGE + USER + ", " +MESSAGE + ", " + SIGNATURE, id, mid, b64Sign);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Message> messageStatus(Long id, Long mid) {
        Message message = messageBoxRepository.getMessageByIdAndTo(mid, id);

        if(dontExist(message)) {
            LOG.warn(STATUS + UserControllerImpl.DONT_EXIST+"");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        LOG.info(MESSAGE_STATUS + USER + ", " +MESSAGE, id, mid);
        return ResponseEntity.ok(message);
    }

    //Auxiliary Methods ---------------------------------------

    private boolean dontExist(Long id){
        return !userRepository.existsById(id);
    }

    private <T> boolean dontExist(T m){
        return m == null;
    }

    private boolean signCorrect(Message message) {
        return message.getSignature() == message.getSignature(); //TODO check SIGNATURE
    }

}
