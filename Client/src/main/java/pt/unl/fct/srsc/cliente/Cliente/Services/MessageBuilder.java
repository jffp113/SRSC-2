package pt.unl.fct.srsc.cliente.Cliente.Services;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.unl.fct.srsc.cliente.Cliente.Security.AssymetricEncription;
import pt.unl.fct.srsc.cliente.Cliente.Security.CertificateUtil;
import pt.unl.fct.srsc.cliente.Cliente.Security.Signer;
import pt.unl.fct.srsc.cliente.Cliente.Security.SymmetricEncription;
import pt.unl.fct.srsc.cliente.Cliente.Utils.B64;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Service
public class MessageBuilder {

    @Autowired
    private AssymetricEncription assy;
    @Autowired
    private SymmetricEncription symm;
    @Autowired
    private Signer sign;
    @Autowired
    private CertificateUtil cert;


    public String generate(String message, byte[] pubKey) throws Exception {
        Key ks  = KeyGenerator.getInstance("AES").generateKey();
        String a = B64.encode(symm.encrypt(message, ks));
        String b = assy.encript(B64.encode(ks.getEncoded()), pubKey);
        String c = sign.doSign(a+b);

        return a + "|" + b + "|" + c;
    }


    public String degenerate(String messageTo, byte[] pubKey) {
        String[] m = messageTo.split("\\|");
        String a = m[0];
        String b = m[1];
        String c = m[2];
        if(sign.verifySignature(a+b, c, pubKey)){
            byte[] ks = B64.decode(assy.decript(b, cert.getPersonalPrivateKey()));
            String message = new String(symm.decrypt(B64.decode(a), new SecretKeySpec(ks, 0, ks.length, "AES")));
            return message;
        }else
            return null;
    }
}
