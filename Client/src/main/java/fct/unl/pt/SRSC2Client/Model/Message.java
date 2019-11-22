package fct.unl.pt.SRSC2Client.Model;

import fct.unl.pt.SRSC2Client.Utils.DATE;
import fct.unl.pt.SRSC2Client.Utils.JSON;

public class Message {

    public static final String FORMAT_DATE = "yyyy/MM/dd HH:mm:ss";

    private Long id;

    private Long from;

    private Long to;

    private String messageFrom;

    private String messageTo;

    private String sendDate;

    private String receivedDate;

    private String signature;

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

    public String getSignature() { return signature; }

    public String getParameters(){ return from + to + messageFrom + messageTo; }

    public void setReceivedDate(){ this.receivedDate = actualDate(); }

    public void setSendDate(){ this.sendDate = actualDate(); }

    public void setSignature(String signature) { this.signature = signature; }

    @Override
    public String toString() { return JSON.convert(this); }

    private String actualDate(){ return DATE.actual(FORMAT_DATE); }
}
