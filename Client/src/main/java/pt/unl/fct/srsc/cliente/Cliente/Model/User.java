package pt.unl.fct.srsc.cliente.Cliente.Model;

public class User {

    private Long id;

    /**
     * Unique identifier
     * Suggested to be digest of user public key
     */
    private String uuid;

    /**
     * Secure Related Data Public key
     */
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
        return String.format("\n" +
                "id=%s \n" +
                "   uuid=%s \n" +
                "   secdata=%s \n", id, uuid, secdata);
    }
}
