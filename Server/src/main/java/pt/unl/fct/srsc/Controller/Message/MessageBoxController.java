package pt.unl.fct.srsc.Controller.Message;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.unl.fct.srsc.Model.Message;
import pt.unl.fct.srsc.Responses.Result;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = MessageBoxController.MESSAGES_BASE_URL)
public interface MessageBoxController {

    String MESSAGES_BASE_URL = "/messages";

    String NEW = "/new";
    String ALL = "/all";
    String SEND = "/send";
    String RECV = "/recv";
    String RECEIPT = "/receipt";
    String STATUS = "/status";


    /**
     * @Description
     * Sent by a client in order to list all
     * new messages in a user’s message box.
     *
     * @param
     * id: The id field contains an
     * integer identifying the user owning
     * the target message box.
     *
     * @return
     * The server reply is a message
     * containing in a result field an array
     * of mes- sage identifiers (strings).
     * These should be used as given to have
     * access to messages’ contents.
     */
    @GetMapping(
            value = NEW + "/{id}",
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Result<List<Message>>>listUserNewMessages(
            @PathVariable( "id" ) Long id);

    /**
     * @Description
     * Sent by a client in order to list all
     * messages in a user’s message box.
     *
     * @param
     * id: The remaining fields of the request
     * are similar to the previous one (NEW).
     *
     * @return
     * The server reply is similar to the previous
     * one (NEW), but the result an array containing
     * two arrays: one with the identifiers of the
     * received messages, and another with the identifiers
     * of the sent messages.
     */
    @GetMapping(
            value = ALL + "/{id}",
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Result<List<Message>>> listAllUserMessages(
            @PathVariable( "id" ) Long id);

    /**
     * @Description
     * Sent by a client in order to send
     * a message to a user’s message box.
     *
     * @param
     * message: The src and dst field contain
     * the identifiers of the sender and receiver
     * identifiers, respectively. The msg field contains
     * the encrypted and signed message to be delivered to
     * the target message box; the server will not validate
     * the message. The copy field contains a replica of the
     * message to be stored in the receipt box of the sender.
     * It should be encrypted in a way that only the sender can
     * access its contents, which will be crucial to validate
     * receipts.
     *
     * @return
     * The server reply is a message containing the identifiers
     * of both the message sent and the receipt, stored in a vector
     * of strings.
     */
    @PostMapping(
            value = SEND,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Result<Long>> sendMessageToUser(
            @RequestBody Message message);

    /**
     * @Description
     * Sent by a client in order to receive
     * a message from a user’s message box.
     *
     * @param
     * id: The id contains the identifier of the message box.
     *
     * @param
     * mid: The msg contains the identifier of the message
     * to fetch.
     *
     * @return
     * The server will reply with the identifier of the message
     * sender and the message contents.
     */
    @GetMapping(
            value = RECV + "/{id}/{mid}",
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Result<Message>> receiveMessage(
            @PathVariable( "id" ) Long id,
            @PathVariable( "mid" ) Long mid);

    /**
     * @Description
     * Receipt message sent by a client after
     * receiving and validating a message from
     * a message box.
     *
     * @param
     * id: The id contains the identifier of the
     * message box of the receipt sender.
     *
     * @param
     * mid: The msg contains the identifier of message
     * for which a receipt is being sent.
     *
     * @param
     * b64Sign: The receipt field contains a signature
     * over the plaintext message received, calculated with the same
     * credentials that the user uses to authenticate mes- sages to
     * other users. Its contents will be stored next to the copy of
     * the messages sent by a user, with a extension indicating the
     * receipt reception date.
     *
     *
     * @return
     * The server will not reply to this message, nor will it validate
     * its correctness.
     */
    @PutMapping(
            value = RECEIPT + "/{id}/{mid}",
            consumes = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Result<Void>> receiptMessage(
            @PathVariable( "id" ) Long id,
            @PathVariable( "mid" ) Long mid,
            @RequestBody String b64Sign);

    /**
     * @Description
     * Sent by a client for checking the reception
     * status of a sent message (i.e. if it has or
     * not a receipt and if it is valid).
     *
     * @param
     * id: The id contains the identifier of the receipt box.
     *
     * @param
     * mid: The msg contains the identifier of sent message for
     * which receipts are going.
     *
     * @return
     * The server will reply with an object containing the sent
     * message and a vector of receipt objects, each containing
     * the receipt data (when it was received by the server),
     * the identification of the receipt sender and the receipt
     * itself.
     */
    @GetMapping(
            value = STATUS + "/{id}/{mid}",
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Result<Message>> messageStatus(
            @PathVariable( "id" ) Long id,
            @PathVariable( "mid" ) Long mid);

}
