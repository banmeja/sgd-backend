# Proyecto

Base y esquema de proyecto sgd

## Activar entorno
Cómo activar un entorno

Por defecto, Spring carga application.properties y luego fusiona el application-{profile}.properties del perfil activo.

Para indicar el perfil activo, agrega en tu application.properties:
spring.profiles.active=dev

O bien, al correr el JAR/WAR, especificas:
java -jar sgd-backend.jar --spring.profiles.active=prod