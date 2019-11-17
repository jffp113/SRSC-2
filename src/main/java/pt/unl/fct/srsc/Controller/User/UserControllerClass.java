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
    public ResponseEntity<CreateResponse> createUser(String uuid) {
        LOG.info("Creating user with uudi %s",uuid);

        if(alreadyExists(uuid)){
            LOG.info("User with uudi=%s already exists",uuid);
            throw new ResourceAlreadyExistsException("User with Id already Exists");
        }
        userRepository.save(new User(uuid));

       return CreateResponse.get(uuid);
    }

    private boolean alreadyExists(String uudi){
        return userRepository.getByUuid(uudi) != null;
    }

    @Override
    public ResponseEntity<Response> listUserMessageBox(String id) {
        throw new NotImplementedException();
    }

}
