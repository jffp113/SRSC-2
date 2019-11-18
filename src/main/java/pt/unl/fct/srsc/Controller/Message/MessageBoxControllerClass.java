package pt.unl.fct.srsc.Controller.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Model.Exceptions.NotImplementedException;
import pt.unl.fct.srsc.Model.Exceptions.ResourceNotFoundException;
import pt.unl.fct.srsc.Model.Message;
import pt.unl.fct.srsc.Model.Responses.Result;
import pt.unl.fct.srsc.Repository.MessageBoxRepository;
import pt.unl.fct.srsc.Repository.UserRepository;

import java.util.List;

import static pt.unl.fct.srsc.Model.Responses.Result.ok;

@RestController
public class MessageBoxControllerClass implements MessageBoxController {

    @Autowired
    private MessageBoxRepository messageBoxRepository;
    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<Result<List<Message>>> listUserNewMessages(Long id) {
        isUserInTheSystem(id);
        return ok(messageBoxRepository.getAllByToAndReceiveSignatureNull(id));
    }


    @Override
    public ResponseEntity<Result<List<Message>>> listAllUserMessages(Long id) {
        isUserInTheSystem(id);
        return ok(messageBoxRepository.getAllByTo(id));
    }

    @Override
    public ResponseEntity<Result<Long>> sendMessageToUser(Message message) {
        isUserInTheSystem(message.getFrom());
        isUserInTheSystem(message.getTo());
        //TODO check SIGNATURE
        messageBoxRepository.save(message);

        return ok(message.getId());
    }

    @Override
    public ResponseEntity<Result<Message>> receiveMessage(Long id, String messageId) {
        Message message = messageBoxRepository.getMessageById(id);

        if(message == null || !message.getTo().equals(id)){
            throw new ResourceNotFoundException("No message to that user");
        }

        return ok(message);
    }

    @Override
    public void receiptMessage(Long id, String uuid,String signatureBase64) {
        Message message = messageBoxRepository.getMessageByIdAndTo(id,uuid);

        if(message == null){
            throw new ResourceNotFoundException("There's no message matching.");
        }

        message.setReceiveSignature(signatureBase64);
        //TODO verify ?

        messageBoxRepository.save(message);
    }

    @Override
    public ResponseEntity<Result<Message>> messageStatus(Long id, String uuid) {
        Message message = messageBoxRepository.getMessageByIdAndTo(id,uuid);

        if(message == null){
            throw new ResourceNotFoundException("There's no message matching.");
        }

        return ok(message);
    }

    private void isUserInTheSystem(Long id) {
        if(!userRepository.existsById(id))
            throw new ResourceNotFoundException(String.format("User with id %d does not exist."));
    }
}
