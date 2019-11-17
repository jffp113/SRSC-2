package pt.unl.fct.srsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.unl.fct.srsc.Model.RMessage;

public interface RBoxRepository extends JpaRepository<RMessage, Long> {
    //TODO
}
