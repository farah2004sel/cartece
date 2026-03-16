package com.mc.icmc.error.exception;

import com.mc.icmc.error.record.ExceptionWithErrorResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception levée lorsqu'un type de concours n'est pas trouvé.
 *
 * @author Ghazi
 */
@Getter
@ToString
public class TypeNotFoundException extends RuntimeException implements ExceptionWithErrorResponse {

    private static final String DEFAULT_MESSAGE = "Concours type not found";
    private static final String DEFAULT_CODE = "concours_type_not_found";

    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;
    private final String code;
    private final HttpStatus httpStatus;
    private final int status;
    private final String url;

    public TypeNotFoundException() {
        this.message = DEFAULT_MESSAGE;
        this.code = DEFAULT_CODE;
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.status = httpStatus.value();
        this.url = "";
    }

    public TypeNotFoundException(UUID uuid) {
        this.message = String.format("Concours type not found with UUID: %s", uuid);
        this.code = DEFAULT_CODE;
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.status = httpStatus.value();
        this.url = "";
    }
}
