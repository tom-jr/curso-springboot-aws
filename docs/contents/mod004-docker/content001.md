# Docker - Primeira Imagem

Para criação da primeira Imagem iremos utilizar o **dockerfile**
DockerFile é um arquivo onde colocamos um script de comandos docker
para executar todo um ciclo que desejamos.


**nginx**:

servidor Web para servir arquivos html e etc

Exemplo de imagem docker

comandos usados no dockerfile de exemplo:
- FROM nome_imagem 
  a imagem docker sera essa declarada pela keyword from. No hub.doc.com podemos
  encontrar as imagens e selecionar qual gostaríamos de criar

- RUN comando para executar comandos dentro da imagem

Apos o script feito nos faremos o build da imagem pelo Dockerfile

na pasta que esta o dockerfile nos rodamos o comando

~~~ bash
$ sudo docker build -t ubuntu-nginx .
~~~

-t  :informa o nome que estamos atribuindo a imagem que sera gerada.
.      :que o arquivo docker esta no mesmo diretório que executa o comando


Apos os comando rodar o comando podemos verificar se foi criado a imagem com:
~~~ bash
$ sudo docker image ls
~~~


**Criar um container a partir da imagem**

~~~ bash
$ docker run -d -p 8000:80 ubuntu-nginx /usr/sbin/nginx -g "daemon off;"
~~~

- ***-d*** background
- ***-p*** comunicação das portas porta:8000 do container estará representada pela porta 80 do host.
- ***/usr/sbin/nginx -g "daemon off;"*** comando para inciar o nginx


Agora vamos criar uma adicionar arquivos na criação da imagem
No exemplo dará sequencia a adição de um arquivo html

Usaremos o comando no Dockerfile **ADD** e o nome do arquivo

vamos parar o container com 
para o container por nome o id
~~~ bash
$ sudo docker stop {name|id}
~~~

adicionando o arquivo
esse path é um padrão do servidor
~~~ bash
$ ADD index.html /var/www/html/index.html
~~~

 apos isso é necessário fazer build novamente a aplicação

 ~~~ bash
$ sudo docker build -t ubuntu-nginx .
 ~~~
 então rodar novamente a imagem


Ultimo passo da aula é abstrair mais o comando para criar o container.
vamos abstrair esse trecho ***/usr/sbin/nginx -g "daemon off;"*** adicionando-o
como um comando do na imagem pelo dockerfile

Usaremos o:
**RUN** echo "daemon off;" >> /etc/nginx/nginx.conf

**CMD** service nginx start

Apos feito build e run com o comando
~~~ bash
sudo docker run -d -p 8000:80 ubuntu-nginx
~~~
 
**Conclusão**

Criamos uma imagem com o dockerfile configurando um servidor web que pode
ser acessado pela porta do nosso host, pois esta vinculado com o host do container.
adicionamos arquivos no inicio na configuração do servidor, abstraímos comando,
buildamos imagens e criamos containers a partir delas.

