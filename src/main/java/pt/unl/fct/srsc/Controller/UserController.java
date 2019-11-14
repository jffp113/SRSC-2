package pt.unl.fct.srsc.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.srsc.Model.User;
import pt.unl.fct.srsc.Repository.UserRepository;

@RestController
public class UserController {
    private Logger LOG = LoggerFactory.getLogger(UserController.class);

    //Repositories
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/create/{id}")
    public void createUser(@PathVariable( "id" ) String uudi) {
        LOG.info("Creating User with uudi %s",uudi);

        if(alreadyExists(uudi)){
            LOG.info("User with uudi=%s already exists",uudi);
            //TODO return response
        }
        userRepository.save(new User(uudi));
        User user = userRepository.getByUudi(uudi);

        //todo return response
    }

    private boolean alreadyExists(String uudi){
        return userRepository.getByUudi(uudi) != null;
    }

}
