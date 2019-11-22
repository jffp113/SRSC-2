package fct.unl.pt.SRSC2Client.Responses;

public class ErrorResult<T> implements Result {

    public final T error;

    ErrorResult(T error) {
        this.error = error;
    }

    @Override
    public T value() {
        return error;
    }

}
