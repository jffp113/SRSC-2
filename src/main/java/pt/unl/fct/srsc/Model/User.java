package pt.unl.fct.srsc.Model;

import com.fasterxml.jackson.databind.util.JSONPObject;

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
   private String uid;

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
   public User(String uid) {
       this.uid = uid;
       this.mbox = uid + "mbox";
       this.rbox = uid + "rbox";
   }

   @Override
   public String toString() {
       return "User{" + uid + "}";
   }
}
