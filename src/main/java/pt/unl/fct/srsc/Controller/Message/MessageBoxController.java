package pt.unl.fct.srsc.Controller.Message;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.unl.fct.srsc.Model.Message;
import pt.unl.fct.srsc.Model.Responses.Result;

import java.util.List;


public interface MessageBoxController {

    /**
     * Sent by a client in order to list all
     * new messages in a user’s message box
     * @param id
     * @return list of unreaded messages
     */
    @RequestMapping(
            value = "/new/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    ResponseEntity<Result<List<Message>>> listUserNewMessages(@PathVariable( "id" ) Long id);

    /**
     * Sent by a client in order to list all
     * messages in a user’s message box
     * @param id
     * @return list of all messages
     */
    @RequestMapping(
            value = "/all/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    ResponseEntity<Result<List<Message>>> listAllUserMessages(@PathVariable( "id" ) Long id);

    /**
     * Sent by a client in order to send
     * a message to a user’s message box
     * @param message
     * @return //TODO
     */
    @RequestMapping(
            value = "/send",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<Result<Long>> sendMessageToUser(@RequestBody Message message);

    /**
     * Sent by a client in order to receive
     * a message from a user’s message box
     * @return Message
     */
    @RequestMapping(
            value = "/recv/{id}/{mid}",
            method = RequestMethod.GET,
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<Result<Message>> receiveMessage(@PathVariable( "id" ) Long id,
                             @PathVariable( "mid" ) String messageId);

    /**
     * Receipt message sent by a client after
     * receiving and validating a message from
     * a message box
     * @param id
     * @return //TODO
     */
    @RequestMapping(
            value = "/receipt/{id}/{mid}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    void receiptMessage(@PathVariable( "id" ) Long id,
                          @PathVariable( "mid" ) String uuid,
                                          @RequestBody String signatureBase64);

    /**
     * Sent by a client for checking the reception
     * status of a sent message (i.e. if it has or
     * not a receipt and if it is valid):
     * @param id
     * @return //TODO
     */
    @RequestMapping(
            value = "/status/{id}/{mid}",
            method = RequestMethod.GET,
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<Result<Message>> messageStatus(@PathVariable( "id" ) Long id,
                          @PathVariable( "mid" ) String uuid);

}
