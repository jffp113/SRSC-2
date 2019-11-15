package pt.unl.fct.srsc.Model;

import pt.unl.fct.srsc.Utils.JSON;

import java.text.DateFormat;
import java.util.Date;

public class Message {

    private String from;
    private String to;
    private String message;
    private String sendDate;
    private String receivedDate;
    private boolean wasRead;

    public Message(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.sendDate = DateFormat.getDateInstance().format(new Date());
        this.receivedDate = "";
        this.wasRead = false;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public boolean isWasRead() {
        return wasRead;
    }

    public void read(){
        wasRead = true;
    }

    @Override
    public String toString() {
        return JSON.encode(this);
    }
}
