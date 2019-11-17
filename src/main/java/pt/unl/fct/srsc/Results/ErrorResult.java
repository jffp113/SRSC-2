package pt.unl.fct.srsc.Results;

public class ErrorResult<T> implements Result<T> {

    public final ErrorCode error;

    ErrorResult(ErrorCode error) {
        this.error = error;
    }

    @Override
    public ErrorCode error() {
        return error;
    }

}