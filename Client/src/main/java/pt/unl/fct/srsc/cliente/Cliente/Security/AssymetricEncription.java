package pt.unl.fct.srsc.cliente.Cliente.Security;

import org.springframework.stereotype.Service;
import pt.unl.fct.srsc.cliente.Cliente.Utils.B64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

@Service
public class AssymetricEncription {

    private Cipher cipher;

    public AssymetricEncription(){
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encript(String content, byte[] key) throws Exception {
        PublicKey publicKey =
                KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(key));
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return B64.encode(cipher.doFinal(content.getBytes()));

    }

    public String encript(String content, Key key) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return B64.encode(cipher.doFinal(content.getBytes()));

    }

    public byte[] decript(String b64Content, PrivateKey key) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(B64.decode(b64Content));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
