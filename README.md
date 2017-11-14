
## Test de funcionalidad con Selenium

La entrega de aplicaciones debe acompañarse de un conjunto de **tests de funcionalidad** que comprueben los procesos de negocio "vitales" de la aplicación: aquellos siempre tienen que funcionar y que bajo ningún concepto pueden fallar. 

Estos tests se ejecutarán de manera automática y continua en los servidores de monitorización con la ayuda de Selenium, con un doble objetivo:

1. Conocer en cualquier momento si la aplicación está funcionando correctamente, y poder anticiparse antes que los usuarios reciban errores,

2. Obtener métricas de funcionamiento y tiempos de respuesta, que ayuden a detectar una degradación del servicio.


Este proyecto incluye una pequeña aplicación de demostración para ilustrar cómo deben ser estos tests. Necesitará  que `mvn` esté configurado para incluir los repositorios [Nexus de la CARM](http://nexus.carm.es).


### servidor-local

Instalación de Selenium WebDrive en local, para poder iniciar el servicio en su equipo, y así comprobar el correcto funcionamiento del test, antes de llevarlo a los servidores de monitorización.


El primer paso será descargar todas las dependencias del servidor Selenium-Standalone en su equipo:


```bash
cd servidor-local
mvn package
```

Encontrará todas las dependencias en el directorio `target/dependency/` y los drivers en `src/main/resources/selenium_standalone_*`


#### Ejecución en linux

Para equipos de desarrollo Linux, abra un terminal en el directorio donde compiló el proyecto y ejecute:

```bash
java -jar target/dependency/selenium-server-standalone-3.7.1.j -role hub
```

Luego desde otra terminal, ejecute:


```bash
java -jar target/dependency/selenium-server-standalone-3.7.1.jar -role webdriver -hub http://127.0.0.1:4444/grid/register -port 5555 -browser browserName=firefox,platform=LINUX,maxInstances=2
```


### test-ejemplo

Código fuente de la aplicación de prueba que implementa un test de funcionalidad, listo para poder ejecutarse de manera automática.

Para compilar y generar el fichero `.jar` ejecute:

```bash
cd test-ejemplo
mvn package
```


