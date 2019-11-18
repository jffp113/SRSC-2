package pt.unl.fct.srsc.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.unl.fct.srsc.Model.Message;

import java.util.List;

@Repository
public interface MessageBoxRepository extends JpaRepository<Message, Long> {
    List<Message> getAllByToAndReceiveSignatureNull(Long id);
    List<Message> getAllByTo(Long id);
    Message getMessageById(Long mid);
    Message getMessageByIdAndTo(Long mid, Long id);
    Message getMessageByIdAndFrom(Long mid, Long id);
}
