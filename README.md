spring.application.name=codeback

# DB Setting
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://192.168.0.13:3306/codeback
spring.datasource.username=codeback
spring.datasource.password=ohboon
spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true

# OAuth Setting

# Google
spring.security.oauth2.client.registration.google.client-name={name}
spring.security.oauth2.client.registration.google.client-id={id}
spring.security.oauth2.client.registration.google.client-secret={secret}
spring.security.oauth2.client.registration.google.redirect-uri={redirect-uri}
spring.security.oauth2.client.registration.google.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.google.scope={scope}

# Github
spring.security.oauth2.client.registration.github.client-name={name}
spring.security.oauth2.client.registration.github.client-id={id}
spring.security.oauth2.client.registration.github.client-secret={secret}
spring.security.oauth2.client.registration.github.redirect-uri={redirect-uri}
spring.security.oauth2.client.registration.github.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.github.scope={scope}

# Provider

# Google
spring.security.oauth2.client.provider.google.authorization-uri={uri}
spring.security.oauth2.client.provider.google.token-uri={uri}
spring.security.oauth2.client.provider.google.user-info-uri={uri}
spring.security.oauth2.client.provider.google.user-name-attribute=sub

# Github
spring.security.oauth2.client.provider.github.authorization-uri={uri}
spring.security.oauth2.client.provider.github.token-uri={uri}
spring.security.oauth2.client.provider.github.user-info-uri={uri}
spring.security.oauth2.client.provider.github.user-name-attribute=id

# Email Sender

spring.mail.host={host}
spring.mail.port={port}
spring.mail.username={username}
spring.mail.password={pw}
spring.mail.properties.mail.smtp.auth={auth}
spring.mail.properties.mail.smtp.starttls.enable={enable}
spring.mail.client-url={uri}


