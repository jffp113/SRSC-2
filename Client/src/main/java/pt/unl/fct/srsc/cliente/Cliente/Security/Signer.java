package pt.unl.fct.srsc.cliente.Cliente.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.unl.fct.srsc.cliente.Cliente.Utils.B64;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Service
public class Signer {

    private static final String SIGN_ALGORITHM = "SHA512withRSA";

    private Signature signature;

    @Autowired
    private CertificateUtil certificateUtil;


    public Signer() {
        try {
            signature = Signature.getInstance(SIGN_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String doSign(String contentToSign) throws SignatureException, InvalidKeyException {
        signature.initSign(certificateUtil.getPersonalPrivateKey());
        signature.update(contentToSign.getBytes());
        return B64.encode(signature.sign());
    }

    public boolean verifySignature(String message, String b64Sign,  byte[] key) {
        try {
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(key));
            signature.initVerify(publicKey);
            signature.update(message.getBytes());
            return signature.verify(B64.decode(b64Sign));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
