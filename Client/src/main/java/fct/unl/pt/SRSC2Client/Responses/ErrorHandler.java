package fct.unl.pt.SRSC2Client.Responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class ErrorHandler implements ResponseErrorHandler {

    public ErrorHandler(){
        super();
    }

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR ||
                        httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            // handle SERVER_ERROR
            System.out.println("aqui");
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
            System.out.println("aqui1");
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("aqui2");
            }
        }
    }
}
