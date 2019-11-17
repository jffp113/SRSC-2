package pt.unl.fct.srsc.Model.Responses;

import javax.persistence.Entity;


public abstract class Response {
    //Variables
    private ResponseType type;

    public Response(ResponseType type){
        this.type = type;
    }

    public ResponseType getType() {
        return type;
    }
}
