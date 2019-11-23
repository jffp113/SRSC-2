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

    private String parameters;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
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

    @Override
    public String toString() {
        return String.format("\n" +
                        "id=%s \n" +
                        "   from=%s \n" +
                        "   to=%s \n" +
                        "   messageFrom=%s \n" +
                        "   messageTo=%s \n" +
                        "   sendDate=%s \n"+
                        "   receivedDate=%s \n" +
                        "   signature=%s \n" +
                        "   parameters=%s \n",
                id, from, to, messageFrom, messageTo, sendDate, receivedDate, signature, parameters);
    }

}
