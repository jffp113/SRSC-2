package pt.unl.fct.srsc.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Responses.Result;
import pt.unl.fct.srsc.Model.User;
import pt.unl.fct.srsc.Repository.UserRepository;
import pt.unl.fct.srsc.Utils.LOGS;

import java.util.List;

import static pt.unl.fct.srsc.Responses.Result.error;
import static pt.unl.fct.srsc.Responses.Result.result;

@RestController
public class UserControllerImpl implements UserController {

    public static final String CREATE_USER = "Create user: ";
    public static final String LIST_USER = "List user: ";
    public static final String LIST_ALL_USERS = "List all users: ";

    public static final String ALREADY_EXISTS = "already exist.";
    public static final String DONT_EXIST = "don't exist.";
    public static final String USER = "USER[id=%s] ";

    @Autowired
    private UserRepository userRepository;

    private LOGS LOG = new LOGS(UserControllerImpl.class);

    @Override
    public ResponseEntity<Result<Object>> createUser(User user) {
        User userR = userRepository.getUserByUuid(user.getUuid());
        if(exists(userR)) {
            LOG.info(CREATE_USER + USER + ALREADY_EXISTS, userR.getId());
            return result(userR.getId()); //TODO: Ver se podemos fazer assim: Se ja existir devolvemos o id
            //return error(HttpStatus.CONFLICT, LOG.warn(CREATE_USER + USER + ALREADY_EXISTS, userR.getUuid()));
        }
        userRepository.save(user);
        LOG.info(CREATE_USER  + USER, user.toString());
        return result(user.getId());
    }

    @Override
    public ResponseEntity<Result<User>> listUser(Long id) {
        User user = userRepository.getUserById(id);
        if(!exists(user))
            return error(HttpStatus.NOT_FOUND, LOG.warn(LIST_USER + USER + DONT_EXIST, id));

        LOG.info(LIST_USER + user.toString());
        return result(user);
    }

    @Override
    public ResponseEntity<Result<List<User>>> listAllUsers() {
        List<User> list = userRepository.findAll();
        LOG.info(LIST_ALL_USERS + list.toString());
        return result(list);
    }

    //Auxiliary Methods ---------------------------------------

    private User exists(String id){
        return userRepository.getUserByUuid(id);
    }

    private <T> boolean exists(T t){
        return t != null;
    }

}
