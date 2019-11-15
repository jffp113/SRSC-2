package pt.unl.fct.srsc.Controller.Message;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageBoxControllerClass implements MessageBoxController {

    @Override
    public String listUserNewMessages(String id) {
        return null;
    }

    @Override
    public String listAllUserMessages(String id) {
        return null;
    }

    @Override
    public String sendMessageToUser(String id) {
        return null;
    }

    @Override
    public String receiveMessage(String id, String messageId) {
        return null;
    }

    @Override
    public String receiptMessage(String id, String messageId) {
        return null;
    }

    @Override
    public String messageStatus(String id, String messageId) {
        return null;
    }
}
