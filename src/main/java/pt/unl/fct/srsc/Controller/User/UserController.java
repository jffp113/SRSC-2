package pt.unl.fct.srsc.Controller.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.unl.fct.srsc.Model.Responses.CreateResponse;
import pt.unl.fct.srsc.Model.Responses.Response;
import pt.unl.fct.srsc.Model.User;

@RestController
public interface UserController {

    /**
     * Invocation used to create a
     * message box for the user in the server
     * @param user
     * @return userId
     */
    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = "application/json")
    ResponseEntity<CreateResponse>  createUser(@RequestBody User user);


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
    ResponseEntity<Response> listUserMessageBox(@PathVariable( "id" ) String id);

}
