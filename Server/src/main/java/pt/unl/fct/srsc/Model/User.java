package pt.unl.fct.srsc.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

import pt.unl.fct.srsc.Utils.JSON;

import java.time.temporal.ValueRange;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Unique identifier
     * Suggested to be digest of user public key
     */
    @NotNull
    private String uuid;

    /**
     * Secure Related Data Public key
     */
    @NotNull
    @Lob
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
