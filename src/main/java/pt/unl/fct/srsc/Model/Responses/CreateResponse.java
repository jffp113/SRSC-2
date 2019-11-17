package pt.unl.fct.srsc.Model.Responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class CreateResponse extends Response {

    private String uuid;

    public CreateResponse(String uuid) {
        super(ResponseType.CREATE);
        this.uuid = uuid;
    }

    public static ResponseEntity<CreateResponse> get(String uuid){
        return new ResponseEntity(new CreateResponse(uuid), HttpStatus.OK);
    }

    public String getUuid() {
        return uuid;
    }
}
