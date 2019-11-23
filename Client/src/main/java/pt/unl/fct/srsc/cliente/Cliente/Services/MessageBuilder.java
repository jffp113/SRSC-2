package pt.unl.fct.srsc.cliente.Cliente.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.unl.fct.srsc.cliente.Cliente.Security.AssymetricEncription;
import pt.unl.fct.srsc.cliente.Cliente.Security.Signer;
import pt.unl.fct.srsc.cliente.Cliente.Security.SymmetricEncription;
import pt.unl.fct.srsc.cliente.Cliente.Utils.B64;
import pt.unl.fct.srsc.cliente.Cliente.Utils.HASH;

import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SignatureException;

@Service
public class MessageBuilder {

    @Autowired
    private AssymetricEncription assy;
    @Autowired
    private SymmetricEncription symm;
    @Autowired
    private Signer sign;

    private String a;
    private String b;
    private String c;


    public String build(String message, byte[] pubKey) throws Exception {
        Key ks  = KeyGenerator.getInstance("AES").generateKey();
        String a = B64.encode(symm.encrypt(message, ks));
        String b = B64.encode(assy.encript(B64.encode(ks.getEncoded()), pubKey));
        String c = B64.encode(sign.doSign(a+b));


        return a + "|" + b + "|" + c;
    }


}
