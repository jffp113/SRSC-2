package pt.unl.fct.srsc.Model.Responses;

public interface Result<E> {

    enum ErrorCode{ OK, CONFLICT, NOT_FOUND, INTERNAL_ERROR, NULL_NOT_ALLOWED, NOT_IMPLEMENTED };

    ErrorCode error();

    static <T> Result<T> ok(T result) {
        return new OkResult<>(result);
    }

    static <T> ErrorResult<T> error(ErrorCode error) {
        return new ErrorResult<>(error);
    }
}


