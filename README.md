`URJC - Máster Cloud Apps`
# Modulo III : Aplicaciones nativas en la nube

## Contenedores y Orquestadores
* Práctica 1: Dockerizar una aplicación

### EoloPlanner

Este proyecto es una aplicación distribuida formada por diferentes servicios que se comunican entre sí usando API REST, gRPC y RabbitMQ. La aplicación ofrece un interfaz web que se comunica con el servidor con API REST y WebSockets. 

> Cada servicio incluye un .devcontainer que habilita el desarrollo local en contenedores.

### Iniciar servicios auxiliares: MongoDB, MySQL y RabbitMQ
Los servicios auxiliares se lanzan con el siguiente comando:

```
$ docker-compose -f docker-compose-dev.yml up
```

### Construir servicios
Construye los proyectos y los publica en dockerHub.

```
$ ./build-publish-containers.sh
```

### Ejecutar servicios

Ejecuta los servicios utilizando docker.

```
$ docker-compose up
```


---
[Code URJC - Máster Cloud Apps](https://www.codeurjc.es/mastercloudapps/) : Desarrollo y despliegue de aplicaciones en la nube  
Curso 2020/2021
