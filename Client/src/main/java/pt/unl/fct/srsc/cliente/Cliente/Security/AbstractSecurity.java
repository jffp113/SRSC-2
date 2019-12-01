package pt.unl.fct.srsc.cliente.Cliente.Security;

import org.springframework.stereotype.Service;

@Service
public class AbstractSecurity {

    protected byte[] handleException(Handler handler){
        try {
            return handler.handle();
        } catch ( Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
