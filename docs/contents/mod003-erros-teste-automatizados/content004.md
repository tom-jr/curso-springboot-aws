# Validação

Vamos adicionar a dependência do spring-started-validation no build.gradle
 e usar as anotações nas classes que desejamos que dispare a validação.

- Vamos usar as seguintes annotations de validação
- **@NotNull**: não aceita valores null no atributo
- **@NotBlank**: não aceita valores em branco no atributo(casos array de caracteres)
- **@DecimalMin("n")**: não ira aceita valores menores que n


 ~~~ java
 public class BeerDTO {

    private Long id;
    
    @NotBlank
    private String name;

    @NotNull
    private BeerType type;

    @NotNull
    @DecimalMin("0")
    private BigDecimal volume;
}

 ~~~

 Para que as validações funcionem é preciso que no ***RequestBody*** que recebe o corpo
 da requisição adicione a annotation ***@Valid***