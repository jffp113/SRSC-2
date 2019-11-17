package pt.unl.fct.srsc.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Model.Exceptions.NotImplementedException;
import pt.unl.fct.srsc.Model.Exceptions.ResourceAlreadyExistsException;
import pt.unl.fct.srsc.Model.Responses.CreateResponse;
import pt.unl.fct.srsc.Model.Responses.Response;
import pt.unl.fct.srsc.Model.User;
import pt.unl.fct.srsc.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class UserControllerClass implements UserController {
    private Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<CreateResponse> createUser(User user) {
        LOG.info(String.format("Creating user with uudi %s",user.getUuid()));

        if(alreadyExists(user.getUuid())){
            LOG.info(String.format("User with uudi=%s already exists",user.getUuid()));
            throw new ResourceAlreadyExistsException("User already exist with uudi: " + user.getUuid());
        }
        userRepository.save(user);

        LOG.info("Created: "  + user.toString());

       return CreateResponse.get(user.getUuid());
    }

    private boolean alreadyExists(String uudi){
        return userRepository.getByUuid(uudi) != null;
    }

    @Override
    public ResponseEntity<Response> listUserMessageBox(String id) {
        throw new NotImplementedException();
    }

}
