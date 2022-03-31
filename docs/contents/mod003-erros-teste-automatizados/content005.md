# Error

Criar uma classe para representar a mensagem de error   
Ela se chamara ErrorResponse

O final é por que depois que os valores forem definidos
não queremos que os mesmos mudem

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
sera usado para converter os atributos em JSON

@RequiredArgsConstructor
criar um construtor para que pare o erro das classes


~~~ java
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private final int statusCode;

    private final List<ApiError> errors;

    // constructor
    static ErrorResponse of(HttpStatus status, List<ApiError> errors) {
        return new ErrorResponse(status.value(), errors);
    }

    static ErrorResponse of(HttpStatus status, ApiError error) {
        return new ErrorResponse(status.value(), Collections.singletonList(error));
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @RequiredArgsConstructor
    static class ApiError {
        private final String code;
        private final String message;
    }



    @Data
public class BeerDTO {

    private Long id;
    
    @NotBlank(message = "beer-1")
    private String name;

    @NotNull(message = "beer-2")
    private BeerType type;

    @NotNull(message = "beer-3")
    @DecimalMin("0")
    private BigDecimal volume;
}

}

~~~

Criar uma properties em resources de ***api-errors.properties***

~~~ properties
beer-1=Name is mandatory and cannot be blank
~~~

Criar uma class com Nome de ApiErrorConfig