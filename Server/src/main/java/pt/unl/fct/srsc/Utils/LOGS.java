package pt.unl.fct.srsc.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LOGS {

    private Logger LOG;

    public LOGS(Class<?> clazz){
        LOG = LoggerFactory.getLogger(clazz);
    }

    public void warn(String string, Object ...f){
        String warn = String.format(string, f);
        LOG.warn(warn);
    }

    public void info(String string, Object... f){
        LOG.info(String.format(string, f));
    }

}
