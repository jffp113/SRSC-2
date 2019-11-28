package pt.unl.fct.srsc.cliente.Cliente.Utils;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;

public final class SSLUtil{

    private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[]{
            new X509TrustManager() {
                private Set<X509Certificate> trustCertificastes;
                private KeyStore truststore;

                public java.security.cert.X509Certificate[] getAcceptedIssuers(){
                    return null;
                }
                public void checkClientTrusted( X509Certificate[] certs, String authType ){performeValidation(certs);}
                public void checkServerTrusted( X509Certificate[] certs, String authType ){performeValidation(certs);}

                private void performeValidation(X509Certificate[] certs){
                    try {
                        loadKeyStoreAndTrustCertificates();
                        verify("leaf",certs);
                    } catch (KeyStoreException e) {
                        e.printStackTrace();
                    }
                }

                public void loadKeyStoreAndTrustCertificates() throws KeyStoreException {
                    KeyStore keystore = null;
                    try {
                        keystore = KeyStore.getInstance(KeyStore.getDefaultType());
                        keystore.load(new FileInputStream("./CA/catrustedcert.jks"), "chageit".toCharArray());
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    this.truststore = keystore;
                    trustCertificastes = new HashSet<>();
                    trustCertificastes.add((X509Certificate) truststore.getCertificate("ca"));

                }

                private boolean verify(String userName,X509Certificate[] certs){

                    X509Certificate CARoot = certs[0];
                    X509Certificate personal = certs[1];

                    //Verify Chain Date Validity
                    for(Certificate cert : certs){
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
            }
        };

    public  static void changeDefaultCertificateValidation() throws NoSuchAlgorithmException, KeyManagementException {
        // Install the all-trusting trust manager
        final SSLContext sc = SSLContext.getInstance("SSL");
        sc.init( null, UNQUESTIONING_TRUST_MANAGER, null );
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        //Return true to accept certificates that come from different named machines
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }

    private SSLUtil(){
        throw new UnsupportedOperationException( "Do not instantiate libraries.");
    }
}
