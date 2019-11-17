package pt.unl.fct.srsc.Controller.Message;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.unl.fct.srsc.Model.Message;
import pt.unl.fct.srsc.Results.Result;

import java.util.List;

@RequestMapping(MessageController.BASE_URL)
public interface MessageController {

    String BASE_URL = "/messagebox";

    String NEW = "/new";
    String ALL = "/all";
    String SEND = "/send";
    String RECV = "/recv";
    String RECEIPT = "/receipt";
    String STATUS = "/status";

    String PATH_USERID = "/{userId}";
    String USERID = "userId";

    String PATH_MSGID = "/{msgId}";
    String MSGID = "msgId";

    /**
     * Sent by a client in order to list
     * all new messages in a user’s message box.
     *
     * @param userId
     *
     * @return  Message containing in a result
     *          field an array of mes- sage
     *          identifiers (strings).
     */
    @RequestMapping(
            value = NEW + PATH_USERID,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Result<String> listNewMessages(@PathVariable(USERID) String userId);

    /**
     * Sent by a client in order to list
     * all messages in a user’s message box.
     *
     * @param userId
     *
     * @return  Array containing two arrays:
     *          one with the identifiers of
     *          the received messages, and
     *          another with the identifiers
     *          of the sent messages.
     */
    @RequestMapping(
            value = ALL + PATH_USERID,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Result<List<List<Message>>> listAllMessages(@PathVariable(USERID) String userId);

    /**
     * Sent by a client in order to send
     * a message to a user’s message box.
     *
     * @param message
     *
     * @return  Message containing the identifiers of
     *          both the message sent and the receipt, stored
     *          in a vector of strings.
     */
    @RequestMapping(
            value = SEND,
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Result<List<String>> sendMessage(@RequestBody Message message);

    /**
     * Sent by a client in order to receive
     * a message from a user’s message box.
     *
     * @param userId
     * @param msgId
     *
     * @return  The identifier of the message
     *          sender and the message contents.
     */
    @RequestMapping(
            value = RECV + PATH_USERID + PATH_MSGID,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Result<Message> receiveMessage(@PathVariable(USERID) String userId,
                                   @PathVariable(MSGID) String msgId);

    /**
     * Receipt message sent by a client after
     * receiving and validating a message from
     * a message box.
     *
     * @param userId
     * @param msgId
     *
     * @return  The server will not reply to this
     *          message, nor will it validate its
     *          correctness.
     */
    @RequestMapping(
            value = RECEIPT + PATH_USERID + PATH_MSGID,
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Result<Void> receiptMessage(@PathVariable(USERID) String userId,
                                @PathVariable(MSGID) String msgId,
                                @RequestBody String receipt);

    /**
     * Sent by a client for checking the reception
     * status of a sent message (i.e. if it has or
     * not a receipt and if it is valid).
     *
     * @param userId
     * @param msgId
     *
     * @return  The server will reply with an object
     *          containing the sent message and a vector of
     *          receipt objects, each containing the receipt.
     */
    @RequestMapping(
            value = STATUS + PATH_USERID + PATH_MSGID,
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Result<Void> status(@PathVariable(USERID) String userId, /*TODO*/
                        @PathVariable(MSGID) String msgId);


}
