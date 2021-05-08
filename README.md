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

## Ejecución

* Arranque de mysql y kafka
`
docker-compose -f docker-compose-mysql.yaml up
`

* Parada de mysql y Kafka
`
docker-compose -f docker-compose-mysql.yaml down
`

* Terminal en gateway-service
`
mvn spring-boot:run
`

* Terminal en order-service
`
mvn spring-boot:run
`

* Terminal en inventory-service
`
mvn spring-boot:run
`

* Terminal en customer-service
`
mvn spring-boot:run
`

### API endpoints

Se adjunta "collection" en la carpeta postman 

POST customer (name + creditLimit)
GET customer (id)

POST product (name + reference + stockQuantity)
PUT product (id + name + reference + stockQuantity), en este caso se añade o resta el stockQuantity al ya persistido en base de datos.
GET product (id)

GET state order (id) devuelve el estado del pedido
GET order completo /orders/{orderId}/products/{productId}
POST order (orderTotal, customerId, productName, productReference, quantity), en este caso se solicita la ejecución de la saga del pedido.

Varios escenarios posibles:

# orderState    rejectionReason    comentario
  APPROVED                             el pedido cumple con todos los requisitos del sistema
  REJECTED        SOLD_OUT             el pedido se ha solicitado con un producto que no existe en el inventario o que la cantidad solicitada excede la que hay en el stock.
  REJECTED        UNKNOWN_CUSTOMER     el pedido se ha solicitado por un cliente que no existe en el sistema.   
  REJECTED        INSUFFICIENT_CREDIT  el pedido se ha solicitado por un total que excede el credito limite del cliente 

---
[Code URJC - Máster Cloud Apps](https://www.codeurjc.es/mastercloudapps/) : Desarrollo y despliegue de aplicaciones en la nube  
Curso 2020/2021
