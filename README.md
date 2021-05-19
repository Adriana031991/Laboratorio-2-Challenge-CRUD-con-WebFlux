# Laboratorio-2-Challenge-CRUD-con-WebFlux
En este reto se busca realizar un CRUD con WebFlux, trabajando los operadores mas básicos de Reactor Core. Para esto vamos a clonar el siguiente repositorio: https://github.com/Sofka-XT/workshop-reactor-core. Dentro de este repositorio encontramos las pruebas unitarias, lo que se busca es diseñar un proyecto desde cero y orientarse con el proyecto que se encuentra en el gestor de versiones (usarlo como referencia). Para eso seguir estas indicaciones:

1. Crear una clase llamanda CardController y crear una prueba con el mismo (CardControllerTest) en el grupo de pruebas unitarias del proyecto de maven.
2. Luego de esto crear un modelo de entidad llamado Card, crearle las propiedades como (title, date, number, type, code). Esta entidad debe anotada.
3. Crear los operadores Create, Read, Update, Delete, List  dentro del controlado, muy importante cada operador que diseñe debe tener su prueba unitaria, y ademas cada operador debes hacer un commit, ejemplo si estoy actualizando la tarjeta entonces voy a realizar la prueba y al finalizar la prueba voy a realizar un commit de lo que realice.
4. Aplicar arquitectura por capas (Controller - Service - Repository), se debe mokear los repositorios asi como se muestra y se hace en el ejemplo.
5. Se debe validar la tarjeta, es decir, tenemos tres tipos de tarjetas MasterCard, VISA, PRIME, para el tipo MasterCard se debe iniciar con el codigo 03, para VISA 06 y para PRIME 12, si un código de estos no corresponde al tipo entonces debe generar un error de registro.
6. Se debe filtrar las tarjetas según el tipo.
7. Tener una cobertura de código de 100%


Este desafío demuestra su conocimiento, recuerde hacer esto de forma consciente.