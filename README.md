`URJC - Máster Cloud Apps`
# Modulo III : Aplicaciones nativas en la nube

## Escalabilidad y tolerancia a fallos
* Práctica 1: Helm

El objetivo es crear una aplicación escalable y tolerante a fallos en Kubernetes.

### web-gatos 

Se usará Hazelcast para mantener la sesión sincronizada entre las réplicas.

Se deberán ejecutar pruebas de carga con Artillery en varios escenarios diferentes y se compararán los resultados obtenidos. El objetivo es que se pueda ver cómo el uso de replicación y service Mesh de Istio reducen el número de errores obtenidos por los usuarios en caso de fallo.

Escenarios
* Escenario 1: Un único pod en el deployment. No caos testing.
* Escenario 2: Un único pod en el deployment. Caos testing con
chaos-pod-monkey.
* Escenario 3: Dos pods en el deployment. Caos testing con
chaos-pod-monkey.
 * Escenario 4: Dos pods en el deployment. Uso de Gateway de Istio
para aceptar peticiones. Caos testing con chaos-pod-monkey.

Para ello:

Modificamos la aplicacion de gatos:
* Añadimos hazelcast y spring session al pom
* Creamos una nueva imagen de la aplicacion   
`mvn package -DskipTests`

* Modificamos el webapp.yaml para que apunte a la nueva imagen de la aplicación

Arrancamos la aplicación con minikube en virtualbox:

`$ minikube start --cni=cilium --memory=6144 --cpus=4 --driver=virtualbox --no-vtx-check`   
`$ kubectl apply -f https://raw.githubusercontent.com/hazelcast/hazelcast-kubernetes/master/rbac.yaml`   
`$ kubectl apply -f k8s/webapp.yaml`   
`$ minikube service webapp`


Lanzamos los tests en local para verificar que la sesión es compartida:
`mvn test -Dweburl=http://192.168.99.103:32110`


## Escalado

Para escalar la aplicación basta con pedir a Kubernetes más réplicas del pod:

`
$ kubectl scale deployments/webapp --replicas=2
`

---
[Code URJC - Máster Cloud Apps](https://www.codeurjc.es/mastercloudapps/) : Desarrollo y despliegue de aplicaciones en la nube  
Curso 2020/2021
