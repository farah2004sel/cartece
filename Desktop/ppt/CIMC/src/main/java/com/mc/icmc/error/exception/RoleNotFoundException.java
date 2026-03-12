package com.mc.icmc.error.exception;

import com.mc.icmc.error.record.ExceptionWithErrorResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * @author Ghazi Ben Yahya
 */
@Getter
@ToString
public class RoleNotFoundException extends RuntimeException implements ExceptionWithErrorResponse {
    private static final String DEFAULT_MESSAGE = "Role not found";
    private static final String DEFAULT_CODE = "Role_not_found";
    @Serial
    private static final long serialVersionUID = 7222354884383946415L;
    private final String message;
    private final String code;
    private final HttpStatus httpStatus;
    private final int status;
    private final String url;

    public RoleNotFoundException() {
        this.message = DEFAULT_MESSAGE;
        this.code = DEFAULT_CODE;
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.status = httpStatus.value();
        this.url = "";
    }
}