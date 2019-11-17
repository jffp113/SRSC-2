package pt.unl.fct.srsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.unl.fct.srsc.Model.MMessage;

@Repository
public interface MBoxRepository extends JpaRepository<MMessage, Long> {
        //TODO
}
