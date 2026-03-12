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
public class AccessDeniedException extends RuntimeException implements ExceptionWithErrorResponse {
    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_CODE = "access_denied";

    private final String message;
    private final String code;
    private final HttpStatus httpStatus;
    private final int status;
    private final String url;

    public AccessDeniedException(String detailMessage, String url) {
        this.message = detailMessage;
        this.code = DEFAULT_CODE;
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.status = httpStatus.value();
        this.url = url;
    }
}

