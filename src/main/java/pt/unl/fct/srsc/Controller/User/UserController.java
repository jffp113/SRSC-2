package pt.unl.fct.srsc.Controller.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.unl.fct.srsc.Model.Responses.Result;
import pt.unl.fct.srsc.Model.User;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = UserController.USERS_BASE_URL)
public interface UserController {

    String USERS_BASE_URL = "/users";

    String CREATE = "/create";
    String LIST = "/list";

    /**
     * Message type used to create a message
     * box for the user in the server.
     *
     * @param user : The uuid field should contain
     * a unique (and not yet known to the
     * server) user unique identifier. We
     * suggest using the digest of a user’s public
     * key certificate, extracted from his/her Citizen
     * Card.
     *
     * @return The server will respond with
     * the id (an integer) given to the user:
     */
    @RequestMapping(
            value = CREATE,
            method = POST,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Result<Long>> createUser(@RequestBody User user);


    /**
     * Sent by a client in order to list users
     * with a message box in the server.
     *
     * @param id: The id field is an optional field
     * with an integer identifying a
     * user to be listed.
     *
     * @return The server reply is a message containing
     * in a result field all information regarding a
     * single user or all users. This information corresponds
     * to the creation message, excluding the type field.
     */
    @RequestMapping(
            value = LIST + "/{id}",
            method = GET,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Result<User>> listUser(@PathVariable( "id" ) Long id);

    /**
     * Sent by a client in order to list users
     * with a message box in the server.
     *
     * @return The server reply is a message containing
     * in a result field all information regarding a
     * single user or all users. This information corresponds
     * to the creation message, excluding the type field.
     */
    @RequestMapping(
            value = LIST,
            method = GET,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Result<List<User>>> listAllUsers();

}