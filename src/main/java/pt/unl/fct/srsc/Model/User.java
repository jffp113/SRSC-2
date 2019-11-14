package pt.unl.fct.srsc.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

    /**
     * Unique identifier
     * Suggested to be digest of user public key
     */
   private String uudi;

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

    public User() {

    }
   public User(String uudi) {
       this.uudi = uudi;
       this.mbox = uudi + "mbox";
       this.rbox = uudi + "rbox";
   }

   @Override
   public String toString() {
       return "User{" + uudi + "}";
   }
}
