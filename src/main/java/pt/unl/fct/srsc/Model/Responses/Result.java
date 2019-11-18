package pt.unl.fct.srsc.Model.Responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface Result<E> {

    static <T> ResponseEntity<Result<T>> ok(T result) {
        return ResponseEntity.ok(new OkResult<>(result));
    }

    //TODO: Apagar se não for usado
    static <T> ResponseEntity error(HttpStatus status, String msg) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResult<>(msg));
    }
}


