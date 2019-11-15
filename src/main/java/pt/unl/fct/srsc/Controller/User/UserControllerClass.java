package pt.unl.fct.srsc.Controller.User;

import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Model.User;
import pt.unl.fct.srsc.Utils.Response.ErrorCodes;
import pt.unl.fct.srsc.Utils.Response.Response;

@RestController
public class UserControllerClass implements UserController {


    UserControllerClass(){
        //TODO: Instancia para base de dados
    }

    @Override
    public String createUser(String id) {
        return Response.ok(new User("12345"));
    }

    @Override
    public String listUserMessageBox(String id) {
        return Response.error(ErrorCodes.NOT_IMPLEMENTED);
    }


}
