package fct.unl.pt.SRSC2Client.Model;

import fct.unl.pt.SRSC2Client.Utils.JSON;

public class User {

    private Long id;

    private String uuid;

    private String secdata;

    public User() {}

    public User(String uuid, String secdata) {
        this.uuid = uuid;
        this.secdata = secdata;
    }

    public User(Long id, String uuid, String secdata) {
        this.id = id;
        this.uuid = uuid;
        this.secdata = secdata;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getSecdata() {
        return secdata;
    }

    @Override
    public String toString() {
        return JSON.convert(this);
    }
}
