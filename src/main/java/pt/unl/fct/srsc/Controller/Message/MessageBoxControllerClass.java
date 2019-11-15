package pt.unl.fct.srsc.Controller.Message;

import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Utils.Response.ErrorCodes;
import pt.unl.fct.srsc.Utils.Response.Response;

@RestController
public class MessageBoxControllerClass implements MessageBoxController {

    MessageBoxControllerClass(){
        //TODO Instancia para Base de dados
    }

    @Override
    public String listUserNewMessages(String id) {
        return Response.error(ErrorCodes.NOT_IMPLEMENTED);
    }

    @Override
    public String listAllUserMessages(String id) {
        return Response.error(ErrorCodes.NOT_IMPLEMENTED);
    }

    @Override
    public String sendMessageToUser(String id) {
        return Response.error(ErrorCodes.NOT_IMPLEMENTED);
    }

    @Override
    public String receiveMessage(String id, String messageId) {
        return Response.error(ErrorCodes.NOT_IMPLEMENTED);
    }

    @Override
    public String receiptMessage(String id, String messageId) {
        return Response.error(ErrorCodes.NOT_IMPLEMENTED);
    }

    @Override
    public String messageStatus(String id, String messageId) {
        return Response.error(ErrorCodes.NOT_IMPLEMENTED);
    }
}
