package pt.unl.fct.srsc.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Model.Responses.Result;
import pt.unl.fct.srsc.Model.User;
import pt.unl.fct.srsc.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static pt.unl.fct.srsc.Model.Responses.Result.error;
import static pt.unl.fct.srsc.Model.Responses.Result.ok;

@RestController
public class UserControllerClass implements UserController {

    public static final String USER_ALREADY_EXISTS = "User with uudi=%s already exists.";
    public static final String USER_DON_T_EXISTS = "User with id=%s don't exists.";

    private Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Result<Long>> createUser(User user) {
        LOG.info(String.format("Creating user with uudi %s.", user.getUuid()));
        String uuid = user.getUuid();
        if(alreadyExists(uuid)){
            LOG.info(String.format(USER_ALREADY_EXISTS, uuid));
            return error(HttpStatus.CONFLICT, String.format(USER_ALREADY_EXISTS, uuid));
        }
        userRepository.save(user);
        LOG.info("Created: "  + user.toString());
        return ok(user.getId());
    }

    @Override
    public ResponseEntity<Result<User>> listUser(Long id) {
        LOG.info(String.format("List user with id %s", id));
        User user = userRepository.getUserById(id);
        if(user == null){
            LOG.info(String.format(USER_DON_T_EXISTS, id));
            return error(HttpStatus.NOT_FOUND, String.format(USER_DON_T_EXISTS, id));
        }
        LOG.info("List user: "  + user.toString());
        return ok(user);
    }

    @Override
    public ResponseEntity<Result<List<User>>> listAllUsers() {
        LOG.info("List all users");
        List<User> list = userRepository.findAll();
        return ok(list);
    }

    //Auxiliary Methods ------------------------------------------------

    private boolean alreadyExists(String id){
        return userRepository.getUserByUuid(id) != null;
    }

}
