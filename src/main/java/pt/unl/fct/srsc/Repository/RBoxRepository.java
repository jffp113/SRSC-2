package pt.unl.fct.srsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.unl.fct.srsc.Model.RMessage;

@Repository
public interface RBoxRepository extends JpaRepository<RMessage, Long> {
    //TODO
}
