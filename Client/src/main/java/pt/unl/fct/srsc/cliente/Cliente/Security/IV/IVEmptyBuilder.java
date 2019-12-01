package pt.unl.fct.srsc.cliente.Cliente.Security.IV;


import java.security.spec.AlgorithmParameterSpec;

public class IVEmptyBuilder implements IVMessageBuilder {

    @Override
    public byte[] buildMessageWithIV(byte[] message) {
        return message;
    }

    @Override
    public IVPair unbuildMessageWithIV(byte[] message) {
        return new IVPair(null,message);
    }

    @Override
    public AlgorithmParameterSpec getSpec() {
        return null;
    }
}
