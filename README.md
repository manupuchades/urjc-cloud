`URJC - Máster Cloud Apps`
# Modulo III : Aplicaciones nativas en la nube

## Aplicaciones nativas de la nube

* Práctica 1: Microservicios

El objetivo de esta práctica consiste en ampliar una saga implementada en una aplicación
existente. Habría que añadir un nuevo servicio a la aplicación y añadir una nueva
transacción a la saga.

La aplicación se basa en la [app con colas Kafka y Máquina de estados](https://github.com/MasterCloudApps/3.4.Aplicaciones-nativas-de-la-nube/tree/master/microservicios/sagas-statemachine).

--- 

### Objetivos:   
1.  Añadir un servicio de Delivery en el que se verifique si hay capacidad de delivery del producto para una ciudad concreta y se genere un “delivery-id”. Añadir al pedido una ciudad de entrega. El servicio de delivery deberá generar internamente una ruta (que será simulada con un String random) que se guardará en la BBDD.   
  Será necesario implementar la API REST para este nuevo servicio:
    * Delivery: Crear ciudades con número de envíos. Consulta de la ruta de los envíos
2. Se deberá incluir un API Gateway basado en Spring Cloud Gateway que permita
obtener, con una única petición, información del pedido e información del producto
en un pedido concreto, también deberá llevar información de la ruta.
---
[Code URJC - Máster Cloud Apps](https://www.codeurjc.es/mastercloudapps/) : Desarrollo y despliegue de aplicaciones en la nube  
Curso 2020/2021
