package pt.unl.fct.srsc.Responses;

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

