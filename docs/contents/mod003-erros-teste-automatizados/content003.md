# Implementando Cadastro    

## Criando Entidade Beer

Em ***beerstore/model/*** Criaremos a entidade Beer para ser considerada uma entidade 
devemos adicionar as annotations referentes do Spring


- @Data: Lombok, para gerar várias estruturas de forma automática: getters,setters,constructors, etc
- @EqualsAndHashCode: Gerar equals and hashCode de forma explicita para adicionarmos apenas o id como referencia de comparação
- @Entity: mapeamento entidade x banco de dados, mapeia o class como uma entidade de persistência e gera tabela no banco de dados caso aja a configuração no ***application.properties***
- @Id identifica a chave primaria da entidade
- @SequenceGenerator configuração da sequencia de nova chave para cada entidade que for adicionada no banco


~~~ java

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Beer {
    @Id
    @EqualsAndHashCode.Include
    @SequenceGenerator(name = "beer_id_seq",sequenceName = "beer_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "beer_id_seq")

    private Long id;
    
    private String name;
    
    private BeerType type;
    
    private BigDecimal volume;
}
~~~

Em seguida criar a interface BeerRepository para extender JpaRepository para que possamos
usar vários métodos de persistência nas entidades sem necessitar a implementação dos mesmos.


~~~ java
public interface BeerRepository extends JpaRepository<Beer, Long> {

}
~~~

Feito isso, podemos criar um método para que possamos salvar uma nova entidade no banco de dados
esse método sera implementado no resource, que é nosso restController. E o método sera com a annotation
***@PostMapping*** O qual é responsável a receber requisições do Tip **POST**
