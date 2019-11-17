package pt.unl.fct.srsc.Controller.User;

import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Model.User;
import pt.unl.fct.srsc.Repository.UserRepository;
import pt.unl.fct.srsc.Results.Result;

import java.util.List;

import static pt.unl.fct.srsc.Results.Result.ErrorCode.NOT_IMPLEMENTED;
import static pt.unl.fct.srsc.Results.Result.error;
import static pt.unl.fct.srsc.Results.Result.ok;

@RestController
public class UserControllerImpl implements UserController {

    private final UserRepository userRepository;

    public UserControllerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Result<Long> createUser(User user) {

        String userUuid = user.getUuid();
        if(isNullValue(userUuid) || isNullValue(user.getSecdata()))
            return error(Result.ErrorCode.INTERNAL_ERROR);

        return ok(userRepository.save(user).getId());
    }

    @Override
    public Result<User> listUser(Long userId) {
        return error(NOT_IMPLEMENTED);        //TODO
    }

    @Override
    public Result<List<User>> listAllUsers() {
        return error(NOT_IMPLEMENTED);        //TODO
    }

    // AUXILIARY METHODS --------------------------------------------

    private boolean isNullValue (Object value){
        return value.equals(null);
    }
}
