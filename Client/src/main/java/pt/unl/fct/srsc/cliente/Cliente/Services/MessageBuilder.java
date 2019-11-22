package pt.unl.fct.srsc.cliente.Cliente.Services;

import org.springframework.stereotype.Service;

@Service
public class MessageBuilder {

    //Injectar criptografia
    //Injectar outras cenas


    public MessageBuilder() {

    }

    public String build(){
        return "{message}k||{k}kubreceptor ||AssinaturaComkPRIVDoSender";
    }
}
