## Push em imagem docker
Fazer login no docker hub pelo host local
~~~ bash
sudo docker login --username username --password 'password'
~~~

A forma mais segura é criando um documento txt com a senha do dockerHub
e então passar por stdin
~~~ bash
cat .arquive_pass.txt | docker login --username username --password-stdin
~~~

Login feito, podemos fazer push das imagens  que temos.

para a lista de imagem:
~~~ bash
sudo docker imagem ls
~~~

Para realizar o push

~~~ bash
docker push imageName:tag
~~~


indo no site do docker hub na area de repositories
poderá ver a image adicionada.

caso deletar a imagem e tentar criar um container a partir dela, o docker vai fazer o download
dela .