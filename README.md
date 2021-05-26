`URJC - Máster Cloud Apps`
# Modulo III : Aplicaciones nativas en la nube

### Computación en la nube
* Práctica 3: Serverless
  
El objetivo de esta práctica consiste en implementar una API REST con las tecnologías
serverless ofrecidas por AWS. En concreto, se utilizarán las siguientes:
* API Gateway
* Lambda
* DynamoDB
* SAM

La aplicación deberá ofrecer una funcionalidad inspirada en la de la práctica 2 de la
asignatura “Tecnologías de servicios de Internet”. En concreto, ofrecerá una API REST que
permitirá gestionar libros y revisiones de libros:

* El foro está formado por 2 entidades:
  * Libros. Cada entrada contiene los siguientes campos: título, resumen, autor,
editorial y año de publicación. Cada libro tendrá una lista de comentarios.
  * Comentarios. Usuario, texto y puntuación.
  * Usuarios. Nombre y correo.
* La aplicación ofrecerá los siguientes endpoints REST:
  * Operaciones genéricas:
    * Creación, consulta, modificación y borrado de libros, comentarios y
usuarios. En estas operaciones se gestionará únicamente el recurso
(sin ningún tipo de relación).
○ Operaciones especiales:
    * Obtener el listado de libros en el que se muestre únicamente el título
y el id.
    * Obtener un único libro con los comentarios asociados. En los
comentarios deberá incluirse el nombre del usuario.
    * Se deberá poder obtener los comentarios de un usuarios concreto. En
este caso los comentarios deberán incluir el id del libro al que
comentan.

  * Restricciones:
    * No se podrán borrar usuarios con comentarios.
      Para simplificar la implementación de la versión serverless, se ofrece en el enunciado la
      práctica implementada con Node, Express y MongoDB. Además, también se ofrece una
      colección Postman para verificar que la implementación serverless es correcta
      funcionalmente.
---
* Construir la aplicación:
```bash
$ npm install
$ sam build
```


* Despliegue en local:
```bash
$ sam local start-api
```

* Despliegue de la aplicacion:   

```bash
$ sam deploy --guided
```

* Borrado de la aplicacion:   
```bash
$ aws cloudformation delete-stack --stack-name sam-app --region region
```

---
[Code URJC - Máster Cloud Apps](https://www.codeurjc.es/mastercloudapps/) : Desarrollo y despliegue de aplicaciones en la nube  
Curso 2020/2021
