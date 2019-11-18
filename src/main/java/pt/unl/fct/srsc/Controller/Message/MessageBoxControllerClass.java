package pt.unl.fct.srsc.Controller.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Controller.User.UserControllerClass;
import pt.unl.fct.srsc.Model.Message;
import pt.unl.fct.srsc.Responses.Result;
import pt.unl.fct.srsc.Repository.MessageBoxRepository;
import pt.unl.fct.srsc.Repository.UserRepository;

import java.util.List;

import static pt.unl.fct.srsc.Responses.Result.error;
import static pt.unl.fct.srsc.Responses.Result.result;

@RestController
public class MessageBoxControllerClass implements MessageBoxController {

    public static final String LIST_NEW_MESSAGES = "List new messages: ";
    public static final String LIST_ALL_MESSAGES = "List all messages: ";
    public static final String SEND_MESSAGE = "Send message: ";
    public static final String RECEIVE_MESSAGE = "Receive message: ";
    public static final String RECEIPT_MESSAGE = "Receipt message: ";
    public static final String MESSAGE_STATUS = "Status: ";

    public static final String SIGNATURE_NOT_ACCEPTABLE = "Signature not acceptable.";
    public static final String MESSAGE_DONT_EXIST = "Message[%s] don't exist.";
    public static final String USER = " User[%s]";

    private Logger LOG = LoggerFactory.getLogger(MessageBoxController.class);

    @Autowired
    private MessageBoxRepository messageBoxRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Result<List<Message>>> listUserNewMessages(Long id) {
        if(!exists(id))
            return error(HttpStatus.NOT_FOUND);
        LOG.info(String.format(LIST_NEW_MESSAGES + USER, id));
        return result(messageBoxRepository.getAllByToAndReceiveSignatureNull(id));
    }

    @Override
    public ResponseEntity<Result<List<Message>>> listAllUserMessages(Long id) {
        if(!exists(id))
            return error(HttpStatus.NOT_FOUND);
        LOG.info(String.format(LIST_ALL_MESSAGES + USER, id));
        return result(messageBoxRepository.getAllByTo(id));
    }

    @Override
    public ResponseEntity<Result<Long>> sendMessageToUser(Message message) {
        Long from = message.getFrom();
        Long to = message.getTo();

        if(!exists(from) || !exists(to))
            return error(HttpStatus.NOT_FOUND);

        if(!signCorrect(message))
            return error(HttpStatus.NOT_ACCEPTABLE);

        message.setSendDate();
        messageBoxRepository.save(message);

        LOG.info(String.format(SEND_MESSAGE + " From[%s] -> To[%s]", from, to));
        return result(message.getId());
    }

    @Override
    public ResponseEntity<Result<Message>> receiveMessage(Long id, Long mid) {
        Message message = messageBoxRepository.getMessageById(mid);

        if(isNull(message))
            return error(HttpStatus.NOT_FOUND);

        if(!message.getTo().equals(id)) {
            LOG.warn(RECEIVE_MESSAGE + " To[%s] don't match with Message[%s].", id, mid);
            return error(HttpStatus.NOT_FOUND);
        }
        LOG.info(String.format(RECEIVE_MESSAGE + " User[%s], Message[%s]", id, mid));
        return result(message);
    }

    @Override
    public ResponseEntity<Result<Void>> receiptMessage(Long id, Long mid, String b64Sign) {
        Message message = messageBoxRepository.getMessageByIdAndTo(mid, id);

        if(isNull(message))
            return error(HttpStatus.NOT_FOUND);

        message.setReceiveSignature(b64Sign);
        message.setReceivedDate();
        //TODO verify ?

        messageBoxRepository.save(message);
        LOG.info(String.format(RECEIPT_MESSAGE + " User[%s], Message[%s], Signature[%s]", id, mid, b64Sign));
        return result();
    }

    @Override
    public ResponseEntity<Result<Message>> messageStatus(Long id, Long mid) {
        Message message = messageBoxRepository.getMessageByIdAndTo(mid, id);

        if(isNull(message))
            return error(HttpStatus.NOT_FOUND);

        LOG.info(String.format(MESSAGE_STATUS + " User[%s], Message[%s]", id, mid));
        return result(message);
    }

    //Auxiliary Methods ---------------------------------------

    private boolean exists(Long id){
        if(userRepository.existsById(id))
            return true;
        LOG.warn(String.format(UserControllerClass.USER_DON_T_EXISTS, id));
        return false;
    }

    private boolean isNull(Message m){
        if(m == null){
            LOG.warn(MESSAGE_STATUS + MESSAGE_DONT_EXIST, m.getId());
            return true;
        }
        return false;
    }

    private boolean signCorrect(Message message) {
        //TODO check SIGNATURE
        if(true)
            return true;
        LOG.warn(SIGNATURE_NOT_ACCEPTABLE);
        return false;
    }

}
