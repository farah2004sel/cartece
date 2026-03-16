package com.mc.icmc.error;


import com.mc.icmc.error.exception.*;
import com.mc.icmc.error.record.ErrorResponse;
import com.mc.icmc.error.record.ExceptionWithErrorResponse;
import com.mc.icmc.error.record.FieldError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ExceptionsHandler is the class that handle all the exception from the application for witch we want to send an error response to the client.
 *
 * @author Fethi Benseddik
 */
@ControllerAdvice
@Slf4j
public class ExceptionsHandler extends ResponseEntityExceptionHandler {


    /**
     * Handle the MethodArgumentNotValidException to automatically send an error response to the client.
     *
     * @param ex      the exception MethodArgumentNotValidException
     * @param headers the headers
     * @param status  the status
     * @param request the request
     * @return the error response entity
     * @author Fethi Benseddik
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
    ) {
        log.error("Method argument not valid: {}", ex.getMessage());
        List<FieldError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new FieldError(
                        fieldError.getObjectName(),
                        fieldError.getField(),
                        fieldError.getDefaultMessage(),
                        fieldError.getCode()))
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles exceptions that involve a custom error response.
     * It logs an error with the name of the exception class and its message and returns an HTTP response with a 400 Bad Request status code and a response body containing the error details.
     *
     * @param ex The exception with a custom error response
     * @return The HTTP response with a 400 Bad Request status code and a response body containing the error details
     */
    protected ResponseEntity<ErrorResponse> handleExceptionWithErrorResponse(ExceptionWithErrorResponse ex) {
        log.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                ex.getCode(),
                ex.getHttpStatus().getReasonPhrase(),
                ex.getStatus(),
                ex.getUrl(),
                Instant.now());

        // Utiliser le httpStatus de l'exception pour le code de réponse
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }






    @ExceptionHandler({
            AccessDeniedException.class,
            UnauthorizedException.class,
            BadRequestException.class
    })
    public ResponseEntity<Object> handleCustomException(ExceptionWithErrorResponse ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "error");
        body.put("code", ex.getCode());
        body.put("errors", List.of(Map.of(
                "code", ex.getStatus(),
                "description", ex.getMessage()
        )));
        return ResponseEntity.status(ex.getStatus()).body(body);

    }


    /**
     * Handles the {@link ApiKeyNotFoundException} and returns an appropriate HTTP response.
     *
     * @param ex the exception
     * @return the response entity with error details
     */
    @ExceptionHandler(ApiKeyNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleApiKeyNotFoundException(ApiKeyNotFoundException ex) {
        log.error("ApiKey not found: {}", ex.getMessage());
        return handleExceptionWithErrorResponse(ex);
    }

    /**
     * Handles the {@link RoleNotFoundException} and returns an appropriate HTTP response.
     *
     * @param ex the exception
     * @return the response entity with error details
     */
    @ExceptionHandler(RoleNotFoundException.class)
    protected ResponseEntity<ErrorResponse> RoleNotFoundException(RoleNotFoundException ex) {
        log.error("Role not found: {}", ex.getMessage());
        return handleExceptionWithErrorResponse(ex);
    }

    /**
     * Handles the {@link RoleAlreadyExistsException} and returns an appropriate HTTP response.
     *
     * @param ex the exception
     * @return the response entity with error details
     */
    @ExceptionHandler(RoleAlreadyExistsException.class)
    protected ResponseEntity<ErrorResponse> handleRoleAlreadyExistsException(RoleAlreadyExistsException ex) {
        log.error("Role already exists: {}", ex.getMessage());
        return handleExceptionWithErrorResponse(ex);
    }


    /**
     * Handles the {@link TypeNotFoundException} and returns an appropriate HTTP response.
     *
     * @param ex the exception
     * @return the response entity with error details
     */
    @ExceptionHandler(TypeNotFoundException.class)
    protected ResponseEntity<ErrorResponse> ConcoursTypeNotFoundException(TypeNotFoundException ex) {
        log.error("Type not found: {}", ex.getMessage());
        return handleExceptionWithErrorResponse(ex);
    }



    /**
     * Handles the {@link ActionNotFoundException} and returns an appropriate HTTP response.
     *
     * @param ex the exception
     * @return the response entity with error details
     */
    @ExceptionHandler(ActionNotFoundException.class)
    protected ResponseEntity<ErrorResponse> ActionNotFoundException(ActionNotFoundException ex) {
        log.error("Action not found: {}", ex.getMessage());
        return handleExceptionWithErrorResponse(ex);
    }

    /**
     * Handles the {@link ActionAlreadyExistsException} and returns an appropriate HTTP response.
     *
     * @param ex the exception
     * @return the response entity with error details
     */
    @ExceptionHandler(ActionAlreadyExistsException.class)
    protected ResponseEntity<ErrorResponse> handleActionAlreadyExistsException(ActionAlreadyExistsException ex) {
        log.error("Action already exists: {}", ex.getMessage());
        return handleExceptionWithErrorResponse(ex);
    }

    /**
     * Handles the {@link LogUserNotFoundException} and returns an appropriate HTTP response.
     *
     * @param ex the exception
     * @return the response entity with error details
     */
    @ExceptionHandler(LogUserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> LogUserNotFoundException(LogUserNotFoundException ex) {
        log.error("LogUser not found: {}", ex.getMessage());
        return handleExceptionWithErrorResponse(ex);
    }

    /**
     * Handles the {@link UserNotFoundException} and returns an appropriate HTTP response.
     *
     * @param ex the exception
     * @return the response entity with error details
     */
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> UserNotFoundException(UserNotFoundException ex) {
        log.error("User not found: {}", ex.getMessage());
        return handleExceptionWithErrorResponse(ex);
    }

    /**
     * Handles the {@link ModuleNotFoundException} and returns an appropriate HTTP response.
     *
     * @param ex the exception
     * @return the response entity with error details
     */
    @ExceptionHandler(ModuleNotFoundException.class)
    protected ResponseEntity<ErrorResponse> ModuleNotFoundException(ModuleNotFoundException ex) {
        log.error("Module not found: {}", ex.getMessage());
        return handleExceptionWithErrorResponse(ex);
    }

    /**
     * Handles the {@link ModuleAlreadyExistsException} and returns an appropriate HTTP response.
     *
     * @param ex the exception
     * @return the response entity with error details
     */
    @ExceptionHandler(ModuleAlreadyExistsException.class)
    protected ResponseEntity<ErrorResponse> handleModuleAlreadyExistsException(ModuleAlreadyExistsException ex) {
        log.error("Module already exists: {}", ex.getMessage());
        return handleExceptionWithErrorResponse(ex);
    }


}
