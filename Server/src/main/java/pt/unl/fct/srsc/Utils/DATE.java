package pt.unl.fct.srsc.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DATE {
    public static String actual(String format){
        return new SimpleDateFormat(format).format(new Date());
    }
}
