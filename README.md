`URJC - Máster Cloud Apps`
# Modulo III : Aplicaciones nativas en la nube

## Contenedores y Orquestadores
* Práctica 3: Kubernetes avanzado

### EoloPlanner

El objetivo de esta práctica consiste en mejorar diversos aspectos de la aplicación kubernetes de la practica 2.

Este proyecto es una aplicación distribuida formada por diferentes servicios que se comunican entre sí usando API REST, gRPC y RabbitMQ. La aplicación ofrece un interfaz web que se comunica con el servidor con API REST y WebSockets. 

> Applicación disponible en http://cluster-ip/

Para ello:

Habilitamos ingress:   
`$ minikube addons enable ingress`

Recuperamos la ip:   
`$ minikube ip`

Redirigimos las llamadas:    
`$ sudo vim /etc/hosts XXX.XXX.XX.XXX cluster-ip`

> los datos se encontrarán persistidos bajo la carpeta /data/
---
[Code URJC - Máster Cloud Apps](https://www.codeurjc.es/mastercloudapps/) : Desarrollo y despliegue de aplicaciones en la nube  
Curso 2020/2021
