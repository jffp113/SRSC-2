Directorias: 

	#Client -> Código fonte do cliente
	|--	CA
		|--caroot.crt
		|--catrustedcert.jks
		|--test.jks
	|--	Client-files
		|--certchain.crt
		|--leaf.jks
	|--	src
		|--	main
			|--	java
				|--	pt/unl/fct/srsc/cliente/Cliente
					|--	Components ...
					|--	Handlers ...
					|--	Model ...
					|--	Security ...
					|--	Services ...
					|--	Utils ...
					|--	MAIN
				|--	resources
				|--	application.properties


	#Server -> Código fonte do servidor
	|--	Server-files
		|--certchain.crt
		|--test.jks
	|--	src
		|--	main
			|--	java
				|--	pt/unl/fct/srsc
					|--	Controller ...
					|--	Model ...
					|--	Repository ...
					|--	Utils ...
					|--	MAIN
				|--	resources
					|--	application.properties


	#Developers -> Ficheiros
	|--	keys.jks
	|--	publickey.cer
	|--	README.md


	#Package -> Cliente + Servidor (jars signs)
	|--	Client
		|--	CA
			|--caroot.crt
			|--catrustedcert.jks
			|--test.jks
		|--	Client-files
			|--certchain.crt
			|--leaf.jks
		|--	Client.jar
		|--	Client[Signed].jar
	|--	Server-files
		|--	Server-files
			|--certchain.crt
			|--test.jks
		|--	Server.jar
		|--	Server[Signed].jar
	|--	README.md -> TESTAR

