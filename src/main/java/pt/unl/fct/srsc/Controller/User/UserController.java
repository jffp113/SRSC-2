package pt.unl.fct.srsc.Controller.User;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserController {

    /**
     * Message type used to create a
     * message box for the user in the server
     * @param id
     * @return userId
     */
    @RequestMapping(
            value = "/create/{id}",
            method = RequestMethod.POST,
            produces = "application/json")
    String createUser(@PathVariable( "id" ) String id);


    /**
     * Sent by a client in order to list users
     * with a message box in the server
     * @param id
     * @return list of users
     */
    @RequestMapping(
            value = "/list/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    String listUserMessageBox(@PathVariable( "id" ) String id);

}
