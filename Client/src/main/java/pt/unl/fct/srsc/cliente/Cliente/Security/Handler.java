package pt.unl.fct.srsc.cliente.Cliente.Security;

import org.springframework.stereotype.Service;

@Service
public interface Handler {
    byte[] handle() throws Exception;
}
