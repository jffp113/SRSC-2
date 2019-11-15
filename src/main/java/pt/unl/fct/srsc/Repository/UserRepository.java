package pt.unl.fct.srsc.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.unl.fct.srsc.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String id);
}
