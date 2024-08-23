## CodeBack

### application.properties

#### src/main/resources/application.properties

```
spring.application.name=codeback

##### DB Setting
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver  
spring.datasource.url={url}  
spring.datasource.username={username}  
spring.datasource.password={password}  
spring.jpa.hibernate.ddl-auto=update  

###### DB Initialization Setting
spring.jpa.defer-datasource-initialization=true


###### OAuth Setting
spring.security.oauth2.client.registration.github.client-id={client-id}  
spring.security.oauth2.client.registration.github.client-secret={client-secret}  
spring.security.oauth2.client.registration.github.redirect-uri={redirect-uri}

spring.security.oauth2.client.registration.google.client-id={client-id}  
spring.security.oauth2.client.registration.google.client-secret={client-secret}  
spring.security.oauth2.client.registration.google.redirect-uri={redirect-uri}

```