package pt.unl.fct.srsc.Results;

public class OkResult<T> implements Result<T> {

    public final T result;

    OkResult(T result) {
        this.result = result;
    }

    @Override
    public ErrorCode error() {
        return ErrorCode.OK;
    }

}

