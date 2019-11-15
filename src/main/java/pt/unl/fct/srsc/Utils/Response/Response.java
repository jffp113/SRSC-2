package pt.unl.fct.srsc.Utils.Response;

import pt.unl.fct.srsc.Utils.JSON;

public class Response {

    public static String ok(Object data){
        return "{\"result\":" + JSON.encode(data) + "}";
    }

    public static String error(ErrorCodes errorCode){
        return "{\"error\":\"" + errorCode + "\"}";
    }
}
