package pt.unl.fct.srsc.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.util.Date;

@Entity
@Data
@Table(name="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="valueFrom")
    private Long from;

    @NotNull
    @Column(name="valueTo")
    private Long to;

    @NotNull
    private String messageFrom;

    @NotNull
    private String messageTo;

    private String sendDate;
    private String receivedDate;

    private String receiveSignature;

    public Message(){

    }

    public Message(Long from, Long to, String messageFrom, String messageTo) {
        this.from = from;
        this.to = to;
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
        this.sendDate = DateFormat.getDateInstance().format(new Date());
        this.receivedDate = null;
    }

    public Long getId() {
        return id;
    }

    public Long getFrom() {
        return from;
    }

    public Long getTo() {
        return to;
    }


    public void setReceiveSignature(String receiveSignature) {
        this.receiveSignature = receiveSignature;
    }
}
