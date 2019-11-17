package pt.unl.fct.srsc.Controller.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Model.Responses.CreateResponse;
import pt.unl.fct.srsc.Model.Responses.Response;

@RestController
public interface UserController {

    /**
     * Invocation used to create a
     * message box for the user in the server
     * @param id
     * @return userId
     */
    @RequestMapping(
            value = "/create/{id}",
            method = RequestMethod.POST,
            produces = "application/json")
    ResponseEntity<CreateResponse>  createUser(@PathVariable( "id" ) String id);


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
