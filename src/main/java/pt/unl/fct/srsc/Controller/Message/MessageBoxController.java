package pt.unl.fct.srsc.Controller.Message;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    String listUserNewMessages(@PathVariable( "id" ) String id);

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
    String listAllUserMessages(@PathVariable( "id" ) String id);

    /**
     * Sent by a client in order to send
     * a message to a user’s message box
     * @param id
     * @return //TODO
     */
    @RequestMapping(
            value = "/send/{sourceid}",
            method = RequestMethod.GET,
            consumes = "application/json",
            produces = "application/json")
    String sendMessageToUser(@PathVariable( "sourceid" ) String id);

    /**
     * Sent by a client in order to receive
     * a message from a user’s message box
     * @param id
     * @return //TODO
     */
    @RequestMapping(
            value = "/recv/{id}/{mid}",
            method = RequestMethod.GET,
            consumes = "application/json",
            produces = "application/json")
    String receiveMessage(@PathVariable( "id" ) String id,
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
            method = RequestMethod.GET,
            consumes = "application/json",
            produces = "application/json")
    String receiptMessage(@PathVariable( "id" ) String id,
                          @PathVariable( "mid" ) String messageId);

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
    String messageStatus(@PathVariable( "id" ) String id,
                          @PathVariable( "mid" ) String messageId);

}
