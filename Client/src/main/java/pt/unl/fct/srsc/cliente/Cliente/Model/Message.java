package pt.unl.fct.srsc.cliente.Cliente.Model;


public class Message {

    private Long id;

    private Long from;

    private Long to;

    private String messageFrom;

    private String messageTo;

    private String sendDate;

    private String receivedDate;

    private String signature;

    public Long getId() {
        return id;
    }

    public Long getFrom() {
        return from;
    }

    public Long getTo() {
        return to;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public String getMessageTo() {
        return messageTo;
    }

    public String getSendDate() {
        return sendDate;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public String getSignature() {
        return signature;
    }

    public Message(){ }

    public Message(Long from, Long to, String messageFrom, String messageTo) {
        this.from = from;
        this.to = to;
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
        this.receivedDate = null;
    }

}
