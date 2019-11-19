#Criar um par de chaves RSA de 2048 bits e guardar numa keystore na entry hj

keytool -genkey -alias client -keyalg RSA -keystore keyStore.jks -keysize 2048 -storepass password


#Gerar um certificado da chave publica a partir da anterior keystore 
#e exporta-lo para um ficheiro Cert.cer


keytool -export -alias client -keystore keyStore.jks -file Cert.cer


#Gerar um certificado a partir da anterior keystore e exporta-lo para
#um ficheiro Cert.cer mas em formarto BASE64

keytool -exportcert -alias client -keystore keyStore.jks -file CertB64.cer -rfc


#Verificar o certificado Cert.cer que tem a chave publica

keytool -printcert -file Cert.cer

#Importar o certificado para uma trusted store

keytool -import -file Cert.cer -alias client -keystore trustedstore.jks -storepass password

#Ver agora o conteudo da keystore trustedstore

keytool -list -v -keystore trustedstore.jks

