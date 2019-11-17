package pt.unl.fct.srsc.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    /**
     * Unique identifier
     * Suggested to be digest of user public key
     */
    private String uuid;

    /**
     * User message box
     */
    private String mbox;

    /**
     * User receipt box
     */
    private String rbox;


    /**
     * Secure Related Data
     */
    private String secdata;

    public User() {}

    public User(String uuid) {
        this.uuid = uuid;
        this.mbox = uuid + "mbox";
        this.rbox = uuid + "rbox";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUid(String uid) {
        this.uuid = uid;
    }

    public String getMbox() {
        return mbox;
    }

    public void setMbox(String mbox) {
        this.mbox = mbox;
    }

    public String getRbox() {
        return rbox;
    }

    public void setRbox(String rbox) {
        this.rbox = rbox;
    }

    public String getSecdata() {
        return secdata;
    }

    public void setSecdata(String secdata) {
        this.secdata = secdata;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
