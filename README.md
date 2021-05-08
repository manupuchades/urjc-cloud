`URJC - Máster Cloud Apps`
# Modulo III : Aplicaciones nativas en la nube

## Escalabilidad y tolerancia a fallos
* Práctica 1: Escalabilidad y Tolerancia a fallos

El objetivo es crear una aplicación escalable y tolerante a fallos en Kubernetes.

### Web-gatos 

Partimos de la aplicacion: [ejem4-webgatos2-scalable](https://github.com/MasterCloudApps/3.3.Escalabilidad-y-tolerancia-a-fallos/tree/master/tema3-k8s/ejem4-webgatos2-scalable)   

En lugar de MySQL, se usará Hazelcast para mantener la sesión sincronizada entre las réplicas.

Se deberán ejecutar pruebas de carga con Artillery en varios escenarios diferentes y se compararán los resultados obtenidos. El objetivo es que se pueda ver cómo el uso de replicación y service Mesh de Istio reducen el número de errores obtenidos por los usuarios en caso de fallo.

Escenarios
* Escenario 1: Un único pod en el deployment. No caos testing.
* Escenario 2: Un único pod en el deployment. Caos testing con
chaos-pod-monkey.
* Escenario 3: Dos pods en el deployment. Caos testing con
chaos-pod-monkey.
* Escenario 4: Dos pods en el deployment. Uso de Gateway de Istio
para aceptar peticiones. Caos testing con chaos-pod-monkey.

---

### Hazelcast para mantener la sesión sincronizada entre las réplicas:

Modificamos la aplicacion de gatos:
* Añadimos hazelcast, hazelcast-kubernetes y spring session al pom
```
	<dependency>
		<groupId>com.hazelcast</groupId>
		<artifactId>hazelcast</artifactId>
	</dependency>
	<dependency>
		<groupId>com.hazelcast</groupId>
		<artifactId>hazelcast-kubernetes</artifactId>
		<version>2.2.2</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.session</groupId>
		<artifactId>spring-session-hazelcast</artifactId>
	</dependency>
```
* Se modifica la ruta de la imagen a publicar por jib en el pom:
```
    <image>urjcmpuchades/kittens-as-a-service</image>
```
* Se incluye el nuevo fichero de propiedades hazelcast.yaml en src/resouces.
* Creamos una nueva imagen de la aplicacion:
```
$ mvn package -DskipTests
```
* Modificamos el webapp.yaml para que apunte a la nueva imagen de la aplicación


* Arrancamos la aplicación con minikube en virtualbox:
```
$ minikube start --cni=cilium --memory=6144 --cpus=4 --driver=virtualbox --no-vtx-check
``` 
* Se añaden los recursos necesarios para Hazelcast:
```
`$ kubectl apply -f https://raw.githubusercontent.com/hazelcast/hazelcast-kubernetes/master/rbac.yaml
```
* Arrancamos la aplicacion:
```
$ kubectl apply -f k8s/webapp.yaml
```
* Obtenemos la URL del servicio desplegado:
```
$ minikube service webapp
```
```
|-----------|--------|-------------|-----------------------------|
| NAMESPACE |  NAME  | TARGET PORT |             URL             |
|-----------|--------|-------------|-----------------------------|
| default   | webapp | webapp/8080 | http://192.168.99.137:32342 |
|-----------|--------|-------------|-----------------------------|
```

* Lanzamos los tests en local para verificar que la sesión es compartida:   
```
$ mvn test -Dweburl=http://192.168.99.103:32110
```

---
## Testeo de la aplicación:

> Escenario 1: Un único pod en el deployment. No caos testing.
  
Reducimos el numero de réplicas del pod:   
```
$ kubectl scale deployments/webapp --replicas=1
```

Ejecutamos el escenario de carga:   
```
$ artillery run --output load-test/resultados-test-1.json artillery/load.yaml
```

> Escenario 2: Un único pod en el deployment. Caos testing con
chaos-pod-monkey. 

Lanzamos el chaos monkey:   
```
$ ./k8s-pod-chaos-monkey/chaos.sh 
```

> Escenario 3: Dos pods en el deployment. Caos testing con
chaos-pod-monkey. 

Aumentamos el numero de réplicas del pod:   
``` 
$ kubectl scale deployments/webapp --replicas=2 
```

Lanzamos el chaos monkey:   
```
$ ./k8s-pod-chaos-monkey/chaos.sh 
```

 > Escenario 4: Dos pods en el deployment. Uso de Gateway de Istio
para aceptar peticiones. Caos testing con chaos-pod-monkey.

---
[Code URJC - Máster Cloud Apps](https://www.codeurjc.es/mastercloudapps/) : Desarrollo y despliegue de aplicaciones en la nube  
Curso 2020/2021
