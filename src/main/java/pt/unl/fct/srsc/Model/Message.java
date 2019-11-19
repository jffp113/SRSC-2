package pt.unl.fct.srsc.Model;

import lombok.Data;
import pt.unl.fct.srsc.Utils.DATE;
import pt.unl.fct.srsc.Utils.JSON;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

@Entity
@Data
@Table(name="messages")
public class Message {

    public static final String FORMAT_DATE = "yyyy/MM/dd HH:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="valueFrom", nullable = false)
    private Long from;

    @Column(name="valueTo", nullable = false)
    private Long to;

    @NotNull
    private String messageFrom;

    @NotNull
    private String messageTo;

    private String sendDate;

    private String receivedDate;

    private String receiveSignature;

    public Message(){ }

    public Message(Long from, Long to, String messageFrom, String messageTo) {
        this.from = from;
        this.to = to;
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
        this.receivedDate = null;
    }

    public Long getId() { return id; }

    public Long getFrom() { return from; }

    public Long getTo() { return to; }

    public String getSign() { return receiveSignature; }

    public String getParameters(){ return from + to + messageFrom + messageTo; }

    public void setReceivedDate(){ this.receivedDate = actualDate(); }

    public void setSendDate(){ this.sendDate = actualDate(); }

    public void setReceiveSignature(String receiveSignature) { this.receiveSignature = receiveSignature; }

    @Override
    public String toString() { return JSON.convert(this); }

    private String actualDate(){ return DATE.actual(FORMAT_DATE); }
}
