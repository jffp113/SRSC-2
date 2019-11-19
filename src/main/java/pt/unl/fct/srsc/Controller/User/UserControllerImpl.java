package pt.unl.fct.srsc.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Responses.Result;
import pt.unl.fct.srsc.Model.User;
import pt.unl.fct.srsc.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static pt.unl.fct.srsc.Responses.Result.error;
import static pt.unl.fct.srsc.Responses.Result.result;

@RestController
public class UserControllerImpl implements UserController {

    public static final String CREATE_USER = "Create user: ";
    public static final String LIST_USER = "List user: ";
    public static final String LIST_ALL_USERS = "List all users: ";

    public static final String USER_ALREADY_EXISTS = "User[%s] already exist.";
    public static final String USER_DON_T_EXISTS = "User[%s] don't exist.";

    private Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Result<Long>> createUser(User user) {
        String uuid = user.getUuid();
        if(alreadyExists(uuid)){
            LOG.warn(String.format(CREATE_USER + USER_ALREADY_EXISTS, uuid));
            return error(HttpStatus.CONFLICT);
        }
        userRepository.save(user);
        LOG.info(CREATE_USER + user.toString());
        return result(user.getId());
    }

    @Override
    public ResponseEntity<Result<User>> listUser(Long id) {
        User user = userRepository.getUserById(id);
        if(user == null){
            LOG.warn(String.format(USER_DON_T_EXISTS, id));
            return error(HttpStatus.NOT_FOUND);
        }
        LOG.info(LIST_USER + user.toString());
        return result(user);
    }

    @Override
    public ResponseEntity<Result<List<User>>> listAllUsers() {
        LOG.info(LIST_ALL_USERS);
        List<User> list = userRepository.findAll();
        return result(list);
    }

    //Auxiliary Methods ---------------------------------------

    private boolean alreadyExists(String id){
        return userRepository.getUserByUuid(id) != null;
    }

}
