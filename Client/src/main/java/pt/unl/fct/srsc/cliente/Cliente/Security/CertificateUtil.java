package pt.unl.fct.srsc.cliente.Cliente.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;

@Component
public class CertificateUtil {
    private static final String PERM_CERTIFICATE = "-----BEGIN CERTIFICATE-----\n%s\n-----END CERTIFICATE-----\n";

    @Value("${security.certchain.path}")
    private String CERTCHAINPATH;

    @Value("${security.keystore.password}")
    private String PASSWORD;

    @Value("${security.keystore.path}")
    public String KEYSTORE;

    private File permCertificateFile;

    private Certificate personalCertificate;
    private PrivateKey privateKey;


    @PostConstruct
    public void init() {
        permCertificateFile = new File(CERTCHAINPATH);
        privateKey = loadPersonalPrivateKey();
        personalCertificate = loadCertificate();
    }

    private Certificate loadCertificate(){
        try {
            return certificatesFromFile(permCertificateFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private PrivateKey loadPersonalPrivateKey(){
        try {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(new FileInputStream(KEYSTORE), PASSWORD.toCharArray());
            return (PrivateKey)keystore.getKey("leaf",PASSWORD.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Certificate certificatesFromFile(File pemCertsFile) throws Exception {
        return parseCertificates(new BufferedInputStream(new FileInputStream(pemCertsFile)));
    }

    public Certificate parseCertificates(InputStream pemStream) throws Exception {
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certs = factory.generateCertificates(pemStream);
        Certificate[] toReturn = certs.toArray(new Certificate[] {});
        return new CertificateChain(Arrays.asList(toReturn));
    }


    public String getPermCertificateAsString(Certificate certificate) throws Exception {
        if(certificate instanceof CertificateChain)
            return ((CertificateChain) certificate).serializaToPem();

        return String.format(PERM_CERTIFICATE,Base64.getEncoder().encodeToString(certificate.getEncoded()));
    }

    public Certificate getPersonalCertificate(){
        return personalCertificate;
    }


    public PrivateKey getPersonalPrivateKey(){
        return privateKey;
    }

    public String getPermCertificateString() throws Exception {
        return getPermCertificateAsString(personalCertificate);
    }
}
