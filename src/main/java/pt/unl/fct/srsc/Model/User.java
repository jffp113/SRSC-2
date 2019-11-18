package pt.unl.fct.srsc.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NonNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
