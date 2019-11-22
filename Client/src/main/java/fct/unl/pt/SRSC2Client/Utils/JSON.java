package fct.unl.pt.SRSC2Client.Utils;

import com.google.gson.Gson;

public class JSON {

    public static <T> String convert(T obj){
        return new Gson().toJson(obj);
    }
}
