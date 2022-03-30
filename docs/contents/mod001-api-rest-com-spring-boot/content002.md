# Primeiro EndPoint

O ***controller***(classes de controle de requisições) será representado como **resource**.
O primeiro resource será da entidade Beer.

**main/java/resource/BearRecourse.java**

Anotaremos a classe com o ***@RestController*** para o identificar ao Spring como um controllerRest
de requisições
E também adicionaremos a anotação ***@RequesMapping*** para passar seus metadados necessários
para o mapeio da classe pelo Spring. O metadado que iremos adicionar a principio é o value
que mapeia **URI** de acesso ao recurso. Por default o Spring sobe o seu servidor tomcat embutido na URI:
***localhost:8080*** . Então todo resource tem o no path de acesso do recurso esse prefixo do servidor
No caso de **BeerRecourse** o value do seu mapeamento rest é **/beers** então para que possamos ter acesso ao 
recurso na web browser devemos adicionar o URI raiz mais o value do recurso
Sendo assim para acessar os recursos do mapeamento de beers devemos fazer a requisição para a URL: localhost:8080/beers .

 Todo método dentro do resource que não for mapeado, pega o mapeio da class. Então cuidado o spring não saberá qual método
 deve chamar caso houver ambiguidades.
e todo método com mapeio com value preenchido, é mais uma membro adicionado na URI final. Ex

beers  - mapping class
/id   - mapping method class

URL final: localhost:8080/beers/id

Ex:
~~~ java
@RestController
@RequestMapping(value = "/beers")
public class BeerRecourse{

}
~~~

Vamos adicionar um método que retorna para a requisição web uma lista de beers
o método terá a annotation de ***@GetMapping*** que a identifica ao Spring como um método de 
retorno para uma requisição. 
Obs ela usa o mapping default da class. Se realizar a requisição para localhost:8080/beers
o spring chama esse método GET para retorna. Então caso aja outro método com o mesmo tipo de 
mapeio o Spring gerará um erro de ambiguidade.

~~~ java
@RestController
@RequestMapping(value = "/beers")
public class BeerRecourse{

  @GetMapping
    public List<String> listBeers(){

    return Arrays.asList("Brahma","Amstel","Heineken","Budweiser","Devassa","Antarctica","Schin");
    }
}
~~~

.
.
.
.
.
.
;