Quando configuramos uma porta com o ***-p*** ela fica disponível para o nosso
host e não para outros containers.
No caso se eu ter uma aplicação java rodando com os profiles da conexão com 
banco de dados apontando para a porta 5432 e tenho um container rodando o banco de
dados mapeado como ***-p 5432:5432***. Mesmo assim a aplicação java não vai conseguir se conectar
ao container do banco de dados. Para isso precisaremos criar uma ***rede docker*** 

Para listar os networks existentes:
~~~ bash
$ sudo docker network ls
~~~
Para criar uma nova network:

~~~ bash
$ sudo docker network create beer-network
~~~

Comando para adicionar um container a uma network

~~~ bash
$ sudo docker network connect beer-network beerdb
~~~

estamos conectando o container chamado beerDB no network beer-network

Agora quando formos subir o container com imagem da aplicação, precisamos informa 
a rede que esta o banco de dados:

~~~ bash
docker run -p 8080:8080 --rm --network beer-network hibicode/beerstore:01
~~~

Mesmo assim a aplicação ainda não funcionara
porque não estão mais se comunicando como antes
a aplicação apontava a url de banco de dados para
***spring.datasource.url=jdbc:postgresql://localhost:5432/beerstore***
mas dentro da rede container, eles se comunicam pelo seus nomes
No caso é preciso modificar a URL para
spring.datasource.url=jdbc:postgresql://**beerdb**:5432/beerstore

Mas para não modificar na no profiles direto, modificamos no Dockerfile a chamada de execução do jar

~~~ bash
 #onde era assim
CMD java -jar /app/app.jar

 #agora fica assim
CMD java -jar /app/app.jar $APP_OPTIONS
~~~

Isso vai dar a liberdade de quando for criarmos o container possamos receber o options
que sera a informação da url do banco de dados da forma que precisa para rodar
no network docker.

Lembrando de sempre que alterar o dockerfile rodar o build novamente

o build sera rodado adicionando modificando a versão

~~~ bash
docker build -t tomjr/beerstore:0.2 .
~~~

Depois de conferir se criou a imagem de acordo com a versão, criaremos o container

~~~ bash
docker run -p 8080:8080 --rm --network beer-network -e APP_OPTIONS='--spring.datasource.url=jdbc:postgresql://beerdb:5432/beerstore' tomjr/beerstore:0.2
~~~

-e passar variáveis de ambiente (environments)



