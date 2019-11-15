package pt.unl.fct.srsc.Utils;

import com.google.gson.Gson;

final public class JSON {
    private static final Gson gson = new Gson();

    public static final String encode(Object obj) {
        return gson.toJson(obj);
    }

}
