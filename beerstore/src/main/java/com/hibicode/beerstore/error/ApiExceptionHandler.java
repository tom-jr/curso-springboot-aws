package com.hibicode.beerstore.error;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hibicode.beerstore.error.ErrorResponse.ApiError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final MessageSource apiErrorMessageSource;

    private static final String NO_MESSAGE_AVAILABLE = "No message avaliable";
    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {

        Stream<ObjectError> errors = ex.getBindingResult().getAllErrors().stream();

        List<ApiError> apiErrors = errors
                .map(ObjectError::getDefaultMessage)
                .map(code -> toApiError(code, locale))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, apiErrors);
        return ResponseEntity.badRequest().body(errorResponse);

    }

    private ErrorResponse.ApiError toApiError(String code, Locale locale) {
        String message;

        try {
            message = apiErrorMessageSource.getMessage(code, null, locale);
        } catch (NoSuchMessageException ex) {
            LOG.error("Could not find any message for {} code under {} locale",code,locale);
            message = NO_MESSAGE_AVAILABLE;
        }
        return new ApiError(code, message);
    }
}
