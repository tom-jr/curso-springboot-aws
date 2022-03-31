# Configurando Spring Data JPA

- Informamos as dependências do spring-started-jpa e do PostgreSQL no arquivo de ***build.gradle***
no seu método de dependências
~~~ groover
dependencies {
	implementation  'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.postgresql:postgresql'
}
~~~

- Configuramos o application,properties do projeto como os dados de acesso ao banco de dados

~~~ properties
spring.datasource.url=jdbc:postgresql://localhost:5432/beerstore
spring.datasource.username=admin
spring.datasource.password=admin

spring.jpa.database-platform = org.hibernate.dialect.PostgreSQL10Dialect
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
~~~



Seguindo esses passos e lembrando de verificar se a imagem docker do Postgres esta de pé.