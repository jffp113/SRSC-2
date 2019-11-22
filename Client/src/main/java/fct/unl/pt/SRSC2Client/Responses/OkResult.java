package fct.unl.pt.SRSC2Client.Responses;

public class OkResult<T> implements Result<T> {

    public final T result;

    OkResult(T result) {
        this.result = result;
    }

    @Override
    public T value() {
        return result;
    }

}

