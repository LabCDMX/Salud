MÉDICO EN TU CASA
========================


Descripción de la aplicación
----------------------------


Esta es una aplicación de formularios, los cuales sustituyen la papeleria convencional de las brigadas del programa de salud social
"Médico en tu casa", cuenta con 3 tipos de Roles, Promotora social, Medico, Enfermera, los cuales son elementos indispensables de una brigada,
al terminar el llenado de datos, estos son enviados a una base de datos en la cual es posible imprimir los datos en formato de expediente.




Funcionamiento
--------------

La aplicación se conecta a un Backend para enviar los resultados de cada uno de los pacientes , identificandolos por un ID interno para poder dar seguimiento en visitas posteriores.

El BackEnd provee a la aplicación de preguntas y posibles respuestas para cada pregunta. 

El proyecto esta dividido en Fragmentos los cuales reciclan las vistas para evitar el uso de vistas repetitivas, el target para la 
aplicación es con buildTools 24, para correr en tablets con Android 4.4, al usar Android 4.4 por convencion global se tiene que usar 
una configuracion especial para el TSL, sin esta configuracion ningun endPoint dentro de la app podra consumirse.

Cuando se instala la aplicacion en una tablet nueva se tiene que descomentar el codigo getPostales();, la cual se encuentra ubicada en 
MainActivity, al cargar la app tarda alrededor de 3 min en cargar, debido a que son 98000 codigos postales, el proceso tarda, esto solo se tiene que
hacer la primera vez para llenar la BD, despues se tiene que volver a comentar el metodo para evitar que se descargue de nuevo. 


Base de datos
-------------

La base de datos en SQL Liste se encuentra constituida por 5 tablas:
    *Seguimiento
    *Pacientes
    *SinExpediente
    *Visitas
    *PacienteSincronizacion

En la tabla de seguimiento se guardan los pacientes que tienen una historia clinica.
En la tabla de pacientes se guardan los pacientes que completaron datos generales.
En la tabla de sinExpediente se guardan los pacientes que completaron la historia clinica por primera vez.
En la tabla de visitas se guardan las visitas con historia clinica que se han hecho al paciente.
En la tabla de pacientesSincronizacion se guardan los clientes que completaron datos generales para poder sincronizarlos posteriormente.

Problemas Actuales 
-------------
Las capacidades tecnicas de la tablet utilizada son limitadas, y el sistema SQL Lite esta teniendo problemas de responsividad.

Como se estructuró incialmente que por pantalla se arma el fragment por pregunta, lo cual consume demasiados recursos.

Un fragment por pregunta, empezaba a alentar cuando teniamos tantos fragments vivos , porque la sobrecargabamos

Se propone solo ciertos módulos de preguntas dentro la app hacerlos sincronizables considerando un mejor rendimiento. 


TODO
--------------
Normalización y estandarización de base de datos SQL lite en Android
~Sincronización Completa de Datos del Paciente~
Actualización de vista de Visitas 
Sincronización de preguntas hacia la tablet
Sincronización de pacientes hacia la tablet