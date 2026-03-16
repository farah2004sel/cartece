package com.mc.icmc.error.record;

public record FieldError(
        String entityName,
        String fieldName,
        String message,
        String code
) {
}
