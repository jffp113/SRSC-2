package pt.unl.fct.srsc.Controller.Message;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Model.Exceptions.NotImplementedException;
import pt.unl.fct.srsc.Model.Responses.Response;

@RestController
public class MessageBoxControllerClass implements MessageBoxController {

    @Override
    public ResponseEntity<Response> listUserNewMessages(String id) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<Response> listAllUserMessages(String id) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<Response> sendMessageToUser(String id) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<Response> receiveMessage(String id, String messageId) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<Response> receiptMessage(String id, String messageId) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<Response> messageStatus(String id, String messageId) {
        throw new NotImplementedException();
    }
}
