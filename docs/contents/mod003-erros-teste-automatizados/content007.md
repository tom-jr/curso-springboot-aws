# TDD

TDD teste Drive Development(Desenvolvimento Orientado a Teste)
No TDD primeiro criamos o teste e Depois implementamos a solução
Nisso utilizamos o fluxo de 

|Test FAil|--->|Test Passes| --->|Refactor|

Onde primeiro o teste esta em falha, Então fazemos o mesmo passar,
e depois refatoramos o código. Assim criando todas as situações de teste
neste ciclo.
![](/docs/assets/img/testes_001.png)



No testes tem um conceito chamado ***babySteps*** que se trata de dar passos bem pequeno na 
implementação das soluções dos teste. Assim deixando o mais simples possível a implementação.
Lembrar que esse método pode deixar o desenvolvimento do projeto bem lento. Porém bem solido

## Implementando Primeira class de Teste BeerServiceTeste

No projeto de teste criaremos raiz do projeto a class de teste de um service
então criaremos o dir /service/BeerServiceTest.Java
Essa class representa a class de service do projeto.

Todo método de teste do Spring deve ser anotado com **@Test**. Os nomes podem ser sem CamelCase.

no método should_deny_creation_of_beer_that_early_exists usamos um método do JUnit chamado ***assertThrows***
para dizer ao compilador que esse teste so estará correto se voltar com o lançamento de erro da class
**BeerAlreadyExistsException** que criamos
~~~ java

  @Test
    public void should_deny_creation_of_beer_that_early_exixts(){
     BeerService beerService =  new BeerService();

        Exception exception = assertThrows(BeerAlreadyExistsException.class, ()->{
            
            Beer newBeer = new Beer();
            newBeer.setName("Heineken");
            newBeer.setType(BeerType.LARGE);
            newBeer.setVolume(new BigDecimal("355"));

            beerService.save(newBeer);

        });
    }
}

~~~

Com o babySteps fizemos apenas com que o método save lançasse direto a exception. Assim continuamos
o fluxo de TDD. Onde a priori tínhamos um erro que não era lançado. E agora esta sendo lançado.
Neste método foi requerido apenas que lançasse o erro. Mas o método **save** também salva a entidade.
Então vamos para o proximo método de teste. Onde precisamos que a entidade seja salva. 

## Teste de salvar entidade beer

Para este teste iremos utilizar uma dependência nova.
Usaremos o **HamCrest** para fazer os asserts. Porque? O prof não da explicação. >:(

adicionamos no build então.

## Mockito

O Mockito sera utilizado para simular o uso do banco de dados. Vou deixar o código fonte aqui
mas sugiro procurar uma fonte mais atualizada

~~~java

public class BeerServiceTest {

    

    @Mock
    BeerRepository beerMock;
    @Test
    public void should_deny_creation_of_beer_that_early_exixts() {
        MockitoAnnotations.initMocks(this);
         BeerService beerService = new BeerService(beerMock);


        Exception exception = assertThrows(BeerAlreadyExistsException.class, () -> {



            Beer beerFromPersistence = new Beer();
            beerFromPersistence.setId(10L);
            beerFromPersistence.setName("Heineken");
            beerFromPersistence.setType(BeerType.LARGE);
            beerFromPersistence.setVolume(new BigDecimal("355"));
    
            Mockito.when(beerMock.findByNameAndType("Heineken", BeerType.LARGE)).thenReturn(Optional.of(beerFromPersistence));





            Beer newBeer = new Beer();
            newBeer.setName("Heineken");
            newBeer.setType(BeerType.LARGE);
            newBeer.setVolume(new BigDecimal("355"));

            beerService.save(newBeer);

        });
    }

    @Test
    public void shold_create_bew_beer() {
        MockitoAnnotations.initMocks(this);
        BeerService beerService = new BeerService(beerMock);

        Beer beerFromPersistence = new Beer();
        beerFromPersistence.setId(10L);
        beerFromPersistence.setName("Heineken");
        beerFromPersistence.setType(BeerType.LARGE);
        beerFromPersistence.setVolume(new BigDecimal("355"));


        
        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LARGE);
        newBeer.setVolume(new BigDecimal("355"));
        
        Mockito.when(beerMock.save(newBeer)).thenReturn(beerFromPersistence);

        Beer beerSaved = beerService.save(newBeer);
        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LARGE));

    }
}

~~~