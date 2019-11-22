package pt.unl.fct.srsc.Controller.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Model.Message;
import pt.unl.fct.srsc.Responses.Result;
import pt.unl.fct.srsc.Repository.MessageBoxRepository;
import pt.unl.fct.srsc.Repository.UserRepository;
import pt.unl.fct.srsc.Utils.LOGS;

import java.util.List;

import static pt.unl.fct.srsc.Controller.User.UserControllerImpl.DONT_EXIST;
import static pt.unl.fct.srsc.Controller.User.UserControllerImpl.USER;
import static pt.unl.fct.srsc.Responses.Result.error;
import static pt.unl.fct.srsc.Responses.Result.result;

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
    public ResponseEntity<Result<List<Message>>> listUserNewMessages(Long id) {
        if(dontExist(id))
            return error(HttpStatus.NOT_FOUND, LOG.warn(LIST_NEW_MESSAGES + USER + DONT_EXIST, id));

        LOG.info(LIST_NEW_MESSAGES + USER, id);
        return result(messageBoxRepository.getAllByToAndSignatureNull(id));
    }

    @Override
    public ResponseEntity<Result<List<Message>>> listAllUserMessages(Long id) {
        if(dontExist(id))
            return error(HttpStatus.NOT_FOUND, LOG.warn(LIST_ALL_MESSAGES + USER + DONT_EXIST, id));

        LOG.info(LIST_ALL_MESSAGES + USER , id);
        return result(messageBoxRepository.getAllByTo(id));
    }

    @Override
    public ResponseEntity<Result<Long>> sendMessageToUser(Message message) {
        Long from = message.getFrom();
        Long to = message.getTo();

        if(dontExist(from))
            return error(HttpStatus.NOT_FOUND, LOG.warn(SEND_MESSAGE + USER + DONT_EXIST, from));

        if(dontExist(to))
            return error(HttpStatus.NOT_FOUND, LOG.warn(SEND_MESSAGE + USER + DONT_EXIST, to));

        message.setSendDate();
        messageBoxRepository.save(message);

        LOG.info(SEND_MESSAGE + USER + "-> " + USER, from, to);
        return result(message.getId());
    }

    @Override
    public ResponseEntity<Result<Message>> receiveMessage(Long id, Long mid) {
        Message message = messageBoxRepository.getMessageById(mid);

        if(dontExist(id))
            return error(HttpStatus.NOT_FOUND, LOG.warn(RECEIVE_MESSAGE + USER + DONT_EXIST, id));

        if(dontExist(message))
            return error(HttpStatus.NOT_FOUND, LOG.warn(RECEIVE_MESSAGE + MESSAGE + DONT_EXIST, mid));

        if(!message.getTo().equals(id))
            return error(HttpStatus.NOT_FOUND, LOG.warn(RECEIVE_MESSAGE + TO_DONT_MATCH_WITH_MESSAGE, id, mid));

        LOG.info(String.format(RECEIVE_MESSAGE + USER + ", " + MESSAGE, id, mid));
        return result(message);
    }

    @Override
    public ResponseEntity<Result<Void>> receiptMessage(Long id, Long mid, String b64Sign) {
        Message message = messageBoxRepository.getMessageByIdAndTo(mid, id);

        if(dontExist(message))
            return error(HttpStatus.NOT_FOUND, LOG.warn(RECEIPT_MESSAGE + MESSAGE + DONT_EXIST, mid));

        message.setSignature(b64Sign);
        message.setReceivedDate();

        if(!signCorrect(message))
            return error(HttpStatus.NOT_ACCEPTABLE, LOG.warn(RECEIPT_MESSAGE + SIGNATURE_NOT_ACCEPTABLE));

        messageBoxRepository.save(message);
        LOG.info(RECEIPT_MESSAGE + USER + ", " +MESSAGE + ", " + SIGNATURE, id, mid, b64Sign);
        return result();
    }

    @Override
    public ResponseEntity<Result<Message>> messageStatus(Long id, Long mid) {
        Message message = messageBoxRepository.getMessageByIdAndTo(mid, id);

        if(dontExist(message))
            return error(HttpStatus.NOT_FOUND, LOG.warn(MESSAGE_STATUS + MESSAGE + DONT_EXIST, mid));

        LOG.info(MESSAGE_STATUS + USER + ", " +MESSAGE, id, mid);
        return result(message);
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
