




#Developers (Gerar as chaves e a keystore para assinar os jars): password:password
	keytool -genkey -alias myKey -keyalg RSA -keystore keys.jks -keysize 2048
	keytool -export -alias myKey -keystore keys.jks -file publickey.cer

#Assinar .jars
	jarsigner -keystore keys.jks -storepass password -keypass password --signedjar Client[Signed].jar Client.jar myKey
	jarsigner -keystore keys.jks -storepass password -keypass password --signedjar Server[Signed].jar Server.jar myKey