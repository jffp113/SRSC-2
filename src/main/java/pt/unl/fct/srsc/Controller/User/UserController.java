package pt.unl.fct.srsc.Controller.User;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.unl.fct.srsc.Model.User;
import pt.unl.fct.srsc.Results.Result;

import java.util.List;

@RequestMapping(UserController.BASE_URL)
public interface UserController {

    String BASE_URL = "/users";

    String CREATE = "/create";
    String LIST = "/list";

    String PATH_USERID = "/{userId}";
    String USERID = "userId";

    /**
     * Message type used to create a
     * message box for the user in the server.
     *
     * @return  the id (an integer) given
     *          to the user.
     */
    @RequestMapping(
            value = CREATE,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Result<Long> createUser(@RequestBody User user);


    /**
     * Sent by a client in order to list a single
     * user with a message box in the server.
     *
     * @param userId
     *
     * @return All information regarding a single user.
     */
    @RequestMapping(
            value = LIST + PATH_USERID,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Result<User> listUser(@PathVariable( USERID ) Long userId);


    /**
     * Sent by a client in order to list users
     * with a message box in the server.
     *
     * @return All users data.
     */
    @RequestMapping(
            value = LIST,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Result<List<User>> listAllUsers();

}
