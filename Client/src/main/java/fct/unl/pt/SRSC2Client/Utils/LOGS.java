package fct.unl.pt.SRSC2Client.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LOGS {

    private Logger LOG;

    public LOGS(Class<?> clazz){
        LOG = LoggerFactory.getLogger(clazz);
    }

    public String warn(String string, Object ...f){
        String warn = String.format(string, f);
        LOG.warn(warn);
        return warn;
    }

    public void info(String string, Object... f){
        LOG.info(String.format(string, f));
    }

}
