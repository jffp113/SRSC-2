package pt.unl.fct.srsc.cliente.Cliente.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HASH {
    public static final String alg = "SHA-256";

    public static String digestToString(String input) {
        return B64.encode(HASH.digestToByteArray(input.getBytes()));
    }

    public static String digestToString(byte[] input){
        return B64.encode(HASH.digestToByteArray(input));
    }

    public static byte[] digestToByteArray(String input){
        return HASH.digestToByteArray(input.getBytes());
    }

    public static byte[] digestToByteArray(byte[] input){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(alg);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return digest.digest(input);
    }

}
