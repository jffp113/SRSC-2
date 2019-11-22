package pt.unl.fct.srsc.cliente.Cliente.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.unl.fct.srsc.cliente.Cliente.Utils.Utils;

import java.security.*;

@Service
public class Signer {

    private static Signer signer = new Signer();

    private static final String SIGN_ALGORITHM = "SHA512withRSA";

    private Signature signature;

    @Autowired
    private CertificateUtil certificateUtil;


    private Signer() {
        try {
            signature = Signature.getInstance(SIGN_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String doSign(String contentToSign) throws SignatureException, InvalidKeyException {
        signature.initSign(certificateUtil.getPersonalPrivateKey());
        signature.update(contentToSign.getBytes());
        return Utils.base64Encode(signature.sign());
    }

    public boolean verifySignature(String message, String b64Sign,  PublicKey key) throws InvalidKeyException, SignatureException {
        signature.initVerify(key);
        signature.update(message.getBytes());
        return signature.verify(Utils.base64Decode(b64Sign));
    }

    public static Signer getInstace(){
        return signer;
    }

}
