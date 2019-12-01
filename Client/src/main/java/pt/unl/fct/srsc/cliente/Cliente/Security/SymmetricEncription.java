package pt.unl.fct.srsc.cliente.Cliente.Security;

import org.springframework.stereotype.Service;
import pt.unl.fct.srsc.cliente.Cliente.Security.IV.*;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;

@Service
public class SymmetricEncription extends AbstractSecurity{

    private Cipher c;

    private IVMessageBuilder ivSpec;

    private static final String ALG = "AES";
    private static final String MODE = "GCM";
    private static final String PADDING = "PKCS5Padding";


    public SymmetricEncription() throws Exception {
        c = Cipher.getInstance(ALG + "/" + MODE + "/" + PADDING);
        ivSpec = getIV(MODE, c.getBlockSize());
    }

    public byte[] encrypt(byte[] input, Key ks){
        return handleException(()->{
            ivSpec = getIV(MODE, c.getBlockSize());
            c.init(Cipher.ENCRYPT_MODE, ks, ivSpec.getSpec());
            return ivSpec.buildMessageWithIV(c.doFinal(input));
        });
    }

    public byte[] encrypt(String input,Key ks){
        return handleException(()->{
            ivSpec = getIV(MODE, c.getBlockSize());
            c.init(Cipher.ENCRYPT_MODE, ks, ivSpec.getSpec());
            return ivSpec.buildMessageWithIV(c.doFinal(input.getBytes()));
        });
    }


    public byte[] decrypt(byte [] input, Key key){
        return handleException(()->{
            IVPair messageAndIV = ivSpec.unbuildMessageWithIV(input);
            c.init(Cipher.DECRYPT_MODE, key, messageAndIV.getAlg());
            return c.doFinal(messageAndIV.getMessage());
        });
    }

    public byte[] decrypt(String input, Key key){
        return handleException(()->{
            IVPair messageAndIV = ivSpec.unbuildMessageWithIV(input.getBytes());
            c.init(Cipher.DECRYPT_MODE, key, messageAndIV.getAlg());
            return c.doFinal(messageAndIV.getMessage());
        });
    }

    public IVMessageBuilder getIV(String mode, int blockSize) {
        IVMessageBuilder parameterSpec;

        if(mode.equalsIgnoreCase("GCM"))
            parameterSpec = new IVGCMBuilder(new GCMParameterSpec(128,generateIV(blockSize)));
        else if(mode.equalsIgnoreCase("ECB"))
            return new IVEmptyBuilder();
        else
            parameterSpec = new IVGeneralBuilder(new IvParameterSpec(generateIV(blockSize)));
        return parameterSpec;
    }

    private byte[] generateIV(int blockSize){
        SecureRandom randomSecureRandom = new SecureRandom();
        byte[] iv = new byte[blockSize];
        randomSecureRandom.nextBytes(iv);
        return iv;
    }


}
