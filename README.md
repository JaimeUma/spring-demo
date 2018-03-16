# Spring boot simple demo

La demo usa:
* Maven (gestor de dependencias)
* Base de datos empotrada H2
* Thymeleaf (para las plantillas del frontend)
* JPA (para conseguir mayor nivel de abstracción sobre la base de datos)
* Spring security para la gestión de la autenticación

## Instalar

1. Instalar eclipse 4.7
2. Help->Marketplace e instalar "spring tools"
3. Para facilitar la implementación del frontend se aconseja instalar "eclipse web developer" (http://www.eclipse.org/webtools/)
4. Desde eclipse importar el proyecto. File->Import->Project y seleccionar "Import maven project"


## Ejecutar

1. Arrancar servidor (Botón derecho en el proyecto y Run as->spring boot app) desde eclipse
2. Acceder a la dirección "localhost:3029" desde el navegador

## Ayuda

* Model (modelo) : carpeta src/main/java/com/example/demo/model
* Templates (vista): carpeta src/main/resources/templates
* Controllers (controlador): carpeta src/main/java/com/example/demo/controller
* Main: clase DemoApplication en la carpeta src/main/java
* Para ver el contenido de la base de datos y gestionarla acceder a la dirección localhost:3029/console
* Parámetros de configuración: fichero application.properties en la carpeta /src/main/resources


## Ejecutar tests (JUnit y Mockito)

1. Botón derecho en el proyecto y Run as -> JUnit test
