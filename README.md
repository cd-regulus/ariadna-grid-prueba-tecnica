# ariadna-grid-prueba-tecnica
Prueba técnica de Ariadna Grid

Modo ejecución: Ejecutar el archivo "Main.java" de la carpeta src\main\java\ariadna\grid\pruebatecnica como una Java Application.

Test:  Ejecutar el archivo "EventServiceTest.java" de la carpeta src\test\java\ariadna\grid\pruebatecnica como Coverage As > JUnit Test.


Descripcion: 

Una de las partes más importantes en el producto de Ariadna es trabajar con grandes cantidades de eventos, que, simplificando un poco, tienen un origen, un timestamp y un valor.
El proyecto consiste en implementar una aplicación Java básica para trabajar con esos eventos. Para ello puedes asumir las siguientes estructuras:

Fuente (id, nombre)
Evento (id, fuente_id, timestamp, valor).

La API que hay que implementar es:
Buscar eventos que contengan un nombre de fuente.
Buscar eventos entre fechas (fecha inicio., fecha fin.)
Buscar eventos dentro de un rango de valores (valor min., valor máx.)
En las respuestas de la API, deberá visualizarse el objeto fuente dentro de cada evento.

Las búsquedas por timestamp y rango de valor deberían ser lo más eficientes que sea posible (dentro de lo razonable, es solo una prueba).

Instrucciones

Entregar la prueba 1 día después de recibir la especificación.
Usa JAVA con la versión que te parezca (Preferiblemente igual o superior a la 8) pero intentando no usar demasiadas librerías externas. Es un proyecto simple que no necesita de frameworks complicados. Nada de bases de datos, Servicios web o similares, almacena todos los datos en memoria.
Aunque sea una prueba sencilla nos gustaría que la realización fuera lo más profesional posible.
Haz el proyecto como harías un desarrollo en el trabajo normalmente, usa las herramientas que consideres necesario para ello.
Para ayudar en el proceso de desarrollo y TESTING habrá que implementar una carga de fuentes y eventos desde archivo. Incorpora en la entrega los archivos con las fuentes y los eventos que hayas usado en el desarrollo. Esta carga, deberá contener al menos 1000 eventos divididos en 6 ficheros, y realizar la carga de forma paralela para agilizar los tiempos de carga. Para las fuentes, se podrá utilizar el mismo criterio, pero no es necesario que existan tantas como eventos.
Incluye un MAIN para la ejecución del programa e incluye tests unitarios asociados (al menos que cargue los archivos de prueba e invoque a las funciones de la API con varios parámetros distintos).
