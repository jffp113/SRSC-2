package pt.unl.fct.srsc.Model.Responses;

public class ErrorResult<T> implements Result {

    public final T error;

    ErrorResult(T error) {
        this.error = error;
    }
}
