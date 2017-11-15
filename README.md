
## Test de funcionalidad con Selenium

La entrega de aplicaciones debe acompañarse de un conjunto de **tests de funcionalidad** que comprueben los procesos de negocio "vitales" de la aplicación: aquellos siempre tienen que funcionar y que bajo ningún concepto pueden fallar. 

Estos tests se ejecutarán de manera automática y continua en los servidores de monitorización con la ayuda de Selenium, con un doble objetivo:

1. Conocer en cualquier momento **si la aplicación está funcionando correctamente**, y poder anticiparse antes que los usuarios reciban errores,

2. Obtener **métricas de funcionamiento y tiempos de respuesta**, que ayuden a detectar una degradación del servicio.


Este proyecto incluye una pequeña aplicación de demostración para ilustrar cómo deben ser estos tests. Necesitará  que `mvn` esté configurado para incluir los repositorios [Nexus de la CARM](http://nexus.carm.es).


Además, necesitará ejecutar *Selenium en local (HUB + NODE)*, y para ello más práctico será usar los contenedores dockers que los propios desarrolladores han realizado:

Para **ejecutar el docker de Selenium HUB**, use:

``` 
docker run  -p 4444:4444 -e HUB_PORT_4444_TCP_ADDR=localhost -e HUB_PORT_4444_TCP_PORT=4444  --name selenium-hub selenium/hub
```


Una vez iniciado el *HUB*, desde otra consola en su equipo, ejecute el **Selenium NODE para FireFox** mediante:


``` 
docker run --link selenium-hub:hub  selenium/node-firefox
```

Para más información, podrá consultar:

* La [documentación en PDF](https://carm-es.github.io/selenium/Guia_Selenium_SSI.pdf) que el departamento de sistemas ha elaborado

* La [documentación JavaDOC](https://carm-es.github.io/selenium/javadoc/index.html) del paquete SeleniumCarm.jar


### Aplicación de ejemplo

Se incluye el código fuente de la aplicación de prueba que implementa un test de funcionalidad, listo para poder ejecutarse de manera automática.

Para compilar, generar el fichero `.jar` y lanzar  la prueba contra nuestro Selenium local, ejecute:


```
git clone https://github.com/carm-es/selenium.git

cd selenium
mvn package

java -jar target/test-bicicarm-1.0.jar -seleniumServer=http://localhost:4444/wd/hub  -filesUrl=http://localhost -filesDir=/tmp/

```

Debería obtener una salida similar a la siguiente:

``` 
nov 15, 2017 10:53:11 AM org.openqa.selenium.remote.ProtocolHandshake createSession
INFORMACIÓN: Detected dialect: W3C
OK - Test Correcto
1 Carga pagina inicial  -> 2112ms 
2 Busqueda BICI  -> 3679ms 
3 Busqueda Temario en BICI  -> 355ms 
4 Abrir PDF  -> 2138ms 
 | carga=2112ms busquedaCarm=3679ms busquedaBICI=355ms abrirPdf=2138ms 
``` 


