package com.mc.icmc.error.exception;

import com.mc.icmc.error.record.ExceptionWithErrorResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * Exception thrown when trying to create a Role that already exists.
 * @author Ghazi Ben Yahya
 */
@Getter
@ToString
public class ModuleAlreadyExistsException extends RuntimeException implements ExceptionWithErrorResponse {

    private static final String DEFAULT_MESSAGE = "Module already exists";
    private static final String DEFAULT_CODE = "Module_already_exists";

    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;
    private final String code;
    private final HttpStatus httpStatus;
    private final int status;
    private final String url;

    public ModuleAlreadyExistsException() {
        this.message = DEFAULT_MESSAGE;
        this.code = DEFAULT_CODE;
        this.httpStatus = HttpStatus.CONFLICT; // 409 Conflict
        this.status = httpStatus.value();
        this.url = "";
    }

    public ModuleAlreadyExistsException(String message) {
        this.message = message;
        this.code = DEFAULT_CODE;
        this.httpStatus = HttpStatus.CONFLICT;
        this.status = httpStatus.value();
        this.url = "";
    }
}
