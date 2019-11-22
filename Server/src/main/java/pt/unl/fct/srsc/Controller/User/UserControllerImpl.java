package pt.unl.fct.srsc.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Model.User;
import pt.unl.fct.srsc.Repository.UserRepository;
import pt.unl.fct.srsc.Utils.LOGS;

import java.util.List;

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
    public ResponseEntity<Long> createUser(User user) {
        User userR = userRepository.getUserByUuid(user.getUuid());
        if(exists(userR)) {
            LOG.info(CREATE_USER + USER + ALREADY_EXISTS, userR.getId());
            return ResponseEntity.ok(userR.getId()); //TODO: Ver se podemos fazer assim: Se ja existir devolvemos o id
            //return error(HttpStatus.CONFLICT, LOG.warn(CREATE_USER + USER + ALREADY_EXISTS, userR.getUuid()));
        }
        userRepository.save(user);
        LOG.info(CREATE_USER  + USER, user.toString());
        return ResponseEntity.ok(user.getId());
    }

    @Override
    public ResponseEntity<User> listUser(Long id) {
        User user = userRepository.getUserById(id);
        if(!exists(user))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        LOG.info(LIST_USER + user.toString());
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> list = userRepository.findAll();
        LOG.info(LIST_ALL_USERS + list.toString());
        return ResponseEntity.ok(list);
    }

    //Auxiliary Methods ---------------------------------------

    private <T> boolean exists(T t){
        return t != null;
    }

}
