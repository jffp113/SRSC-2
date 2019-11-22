package pt.unl.fct.srsc.Responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface Result<T> {

    T value();

    static <T> ResponseEntity<Result<T>> result(T result) {
        return ResponseEntity.ok(new OkResult<>(result));
    }

    static <T> ResponseEntity<Result<T>> result() {
        return ResponseEntity.ok(new OkResult<>((T)HttpStatus.OK));
    }

    static <T> ResponseEntity<Result<T>> error(HttpStatus status, String error) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResult<>(error));
    }
}


