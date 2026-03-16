package com.mc.icmc.error.exception;

import com.mc.icmc.error.record.ExceptionWithErrorResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author Azaiez Abdessalem
 */
@Getter
@ToString
public class ApiKeyNotFoundException extends RuntimeException implements ExceptionWithErrorResponse {
    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_CODE = "api_key_not_found";

    private final String message;
    private final String code;
    private final HttpStatus httpStatus;
    private final int status;
    private final String url;

    public ApiKeyNotFoundException(String detailMessage, String url) {
        this.message = detailMessage;
        this.code = DEFAULT_CODE;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.status = httpStatus.value();
        this.url = url;
    }
}

