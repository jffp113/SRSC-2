package pt.unl.fct.srsc.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.unl.fct.srsc.Model.Message;
import pt.unl.fct.srsc.Model.User;

import java.util.List;

@Repository
public interface MessageBoxRepository extends JpaRepository<Message, Long> {

    List<Message> getAllByToAndReceiveSignatureNull(Long id);
    List<Message> getAllByTo(Long id);
    Message getMessageById(Long id);
    Message getMessageByIdAndTo(Long message,String uuid);
    Message getMessageByIdAndFrom(Long message,String uuid);
}
