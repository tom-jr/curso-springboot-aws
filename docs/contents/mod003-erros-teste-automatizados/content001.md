## Rodando PostgreSQL no Docker

Entramos no site do hub do Docker para baixar a imagem do PostgreSQL
Na pagina das imagens do Postgres tem informações de como  configurar

~~~ bash
$ docker run -p 5432:5432 --name beerdb -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=beerstore -d postgres:11.15-alpine
~~~

**docker run**: comando para rodar imagem
**-p 5432:5432**: mapeando a porta da imagem docker com a porta da maquina física
**--name**: atribuindo nome a imagem docker
**-e VARIABLE_NAME=value**: atribuindo valores as variáveis de ambiente do PostgreSQL
**-d** : rodar em background


~~~ bash
$ docker ps # listar as imagens status
$ docker exec -it image_name psql -U user_name -d dabase_name # entrar no banco
~~~
Para realizar teste da porta, caso fechar normalmente, a conexão esta funcionando
~~~ bash
$ telnet localhost 5432
~~~


