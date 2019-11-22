package pt.unl.fct.srsc.cliente.Cliente.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;
import java.security.cert.*;
import java.util.*;

@Component
public class CertificateChain extends Certificate {
    private static final String PERM_CERTIFICATE = "-----BEGIN CERTIFICATE-----\n%s\n-----END CERTIFICATE-----\n";

    @Value("${security.truststore}")
    public String CERT_KEY_CATRUSTEDCERT_JKS;
    @Value("${security.truststore.password}")
    private String STRONG_PASSWORD;

    private List<Certificate> certChain;
    private Set<X509Certificate> trustCertificastes;
    private KeyStore truststore;

    public CertificateChain(List<Certificate> certChain) throws KeyStoreException {
        super("CertificateChain");
        this.certChain = certChain;

    }

    @PostConstruct
    public void init() throws KeyStoreException {
        loadKeyStoreAndTrustCertificates();
    }

    public void loadKeyStoreAndTrustCertificates() throws KeyStoreException {
        KeyStore keystore = null;
        try {
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(new FileInputStream(CERT_KEY_CATRUSTEDCERT_JKS), STRONG_PASSWORD.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.truststore = keystore;
        trustCertificastes = new HashSet<>();
        trustCertificastes.add((X509Certificate) truststore.getCertificate("ca"));

    }

    public boolean verify(String userName){
        X509Certificate CARoot = (X509Certificate)certChain.get(0);
        X509Certificate personal = (X509Certificate)certChain.get(1);

        //Verify Chain Date Validity
        for(Certificate cert : certChain){
            try {
                ((X509Certificate)cert).checkValidity();
            } catch (CertificateExpiredException e) {
                return false;
            } catch (CertificateNotYetValidException e) {
                return false;
            }
        }

        //Verify if CA is Known
        if(!trustCertificastes.contains(CARoot))
            return false;

        //Verify if client CA was signed by CARoot
        try {
            personal.verify(CARoot.getPublicKey());
        } catch (Exception e) {
            return false;
        }

        if(!(personal.getSubjectDN().getName().equals("CN=Leaf") ||
                personal.getSubjectDN().getName().equals("CN="+userName)))
            return false;

        return true;
    }

    @Override
    public byte[] getEncoded() throws CertificateEncodingException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        for(Certificate cert : certChain){
            try {
                dataStream.write(cert.getEncoded());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return byteStream.toByteArray();
    }

    public String serializaToPem() throws Exception{
        StringBuilder bd = new StringBuilder();

        for(Certificate cer : this.certChain){
            bd.append(String.format(PERM_CERTIFICATE, Base64.getEncoder().encodeToString(cer.getEncoded())));
        }
        return bd.toString();
    }

    @Override
    public void verify(PublicKey key){
        throw new NotImplementedException();
    }

    @Override
    public void verify(PublicKey key, String sigProvider) {
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        return certChain.get(0).toString()+certChain.get(1).toString();
    }

    @Override
    public PublicKey getPublicKey() {
        return this.certChain.get(1).getPublicKey();
    }

}
