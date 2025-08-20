# Proyecto

Base y esquema de proyecto sgd

## Activar entorno
Cómo activar un entorno

Por defecto, Spring carga application.properties y luego fusiona el application-{profile}.properties del perfil activo.

Para indicar el perfil activo, agrega en tu application.properties:
spring.profiles.active=dev

O bien, al correr el JAR/WAR, especificas:
java -jar sgd-backend.jar --spring.profiles.active=prod

## Configuración de JWT para implementar seguridad por token
Configuración con Copilot el 20/08/05

## Proyecto requiere instalar Maven
En Windows: 
Agregar Maven al PATH
Abrí el Panel de Control → Sistema → Configuración avanzada del sistema.

En la pestaña "Opciones avanzadas", hacé clic en Variables de entorno.

En "Variables del sistema", buscá Path y hacé clic en Editar.

Agregá una nueva entrada con la ruta del paso anterior: C:\Program Files\Apache\Maven\bin

Aceptá todo y cerrá. -copilot

## Configuraciones adicionales
Si es la version mvnd es una version mejorada

Añadir al path de variables de sistema JAVA_HOME apuntando a jdk17 para que funcione mvnd