package pt.unl.fct.srsc.Controller.Message;

import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Model.Message;
import pt.unl.fct.srsc.Repository.MBoxRepository;
import pt.unl.fct.srsc.Results.Result;

import java.util.List;

import static pt.unl.fct.srsc.Results.Result.ErrorCode.NOT_IMPLEMENTED;
import static pt.unl.fct.srsc.Results.Result.error;

@RestController
public class MessageControllerImpl implements MessageController {

    private final MBoxRepository messageRepository;

    public MessageControllerImpl(MBoxRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Override
    public Result<String> listNewMessages(String userId) {
        return error(NOT_IMPLEMENTED);        //TODO
    }

    @Override
    public Result<List<List<Message>>> listAllMessages(String userId) {
        return error(NOT_IMPLEMENTED);        //TODO
    }

    @Override
    public Result<List<String>> sendMessage(Message message) {
        return error(NOT_IMPLEMENTED);        //TODO
    }

    @Override
    public Result<Message> receiveMessage(String userId, String msgId) {
        return error(NOT_IMPLEMENTED);        //TODO
    }

    @Override
    public Result<Void> receiptMessage(String userId, String msgId, String receipt) {
        return error(NOT_IMPLEMENTED);        //TODO
    }

    @Override
    public Result<Void> status(String userId, String msgId) {
        return error(NOT_IMPLEMENTED);        //TODO
    }
}
