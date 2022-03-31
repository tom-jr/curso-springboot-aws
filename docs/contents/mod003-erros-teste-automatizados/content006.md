
Criar class com nome de **ApiExceptionHandle**
Vai ter a anotação **RestControllerAdvice**  informa que a class sera um interceptador de exceptions

Precisamos que a class ApiExceptionHandle tenha acesso ao ***api-errors.properties***

Vamos fazer isso por meio do Configuration que criamos, injetando o mesmo na class.

~~~ java
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

~~~
 