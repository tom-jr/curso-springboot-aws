 Criar uma dockerfile para a nossa Aplicação
Para A nossa aplicação podemos utilizar uma imagem ubuntu
que ja venha com o java JDK adicionado.

~~~ bash
FROM openjdk:8u171-jdk-alpine3.8
~~~

Vamos informar um maintainer
que é o profissional que mantém o projeto. Informamos um email

~~~ bash
LABEL maintainer="tomjavastdy@gmail.com"
~~~

Vamos informar a linguagem/ Código de caracteres
~~~ bash
ENV LANG C.UTF-8
~~~

Vamos atualizar o alpine

~~~ bash
RUN apk add --update bash
~~~

Adicionar o build de Nossa aplicação na imagem
Caso ainda não existir build da aplicação basta rodar o 
comando
~~~ bash
$ ./gradlew build
~~~
Agora pegamos o jar gerado no diretório build/libs e adicionamos na imagem


~~~ bash
ADD build/libs/beerstore-0.0.1-SNAPSHOT.jar /app/app.jar
~~~

Então rodamos o comando

~~~ bash
CMD java -jar /app/app.jar
~~~
A configuração do docker file esta pronta. Agora vamos para o build

~~~ bash
docker build -t tomjr/beerstore:0.1 .
~~~

-t nome da imagem  no antes da barra '/' adicionamos o dockerId da nossa
conta docker hub para que possamos publicar nosso build lá.
'.' para identificar que o dockerfile esta no diretório atual


Apos rodar o comando podemos verificar se criou a imagem com o comando 

~~~ bash
sudo docker image ls
~~~

Comando para rodar a aplicação

~~~ bash
docker run -p 8080:8080 --rm --network beer-network tomjr/beerstore:0.1
~~~

 -p   : pareamento das portas do container com o do host
 --rm para apagar vestígios se der erro no processo.



