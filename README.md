`URJC - Máster Cloud Apps`

# Modulo III : Aplicaciones nativas en la nube

### Aplicaciones nativas en la nube

* Práctica 2:
    - Monolito a microservicios

El objetivo de esta práctica consiste en simular un proceso de transformación de un
monolito a microservicios aplicando algunas de las técnicas vistas en clase.

Para ello se va a dividir el monolito : The Bookshelf. Este monolito gestiona libros, comentarios y
usuarios.

El objetivo es extraer el servicio de gestión de usuarios del monolito. Para ello se seguirán
las siguientes dos estrategias:
* Strangler Fig: De forma que las peticiones sobre usuarios que antes llegaban al
monolito ahora lleguen al nuevo microservicio.
* Branch by abstraction: De forma que aquella información de usuarios que se
necesite por otras áreas de la aplicación y que podía obtenerse directamente ahora
se tendrá que obtener haciendo uso del microservicio.

Se facilitan dos despliegues diferentes con el mismo monolito (configurado con spring profile):
● monolith: Toda la funcionalidad seguirá estando en el monolito.
● microservices: La gestión de usuarios deberá realizarse en un servicio externo.

---

Para ello:

Arrancamos minikube en virtualbox:

`$ minikube start --cni=cilium --memory=6144 --cpus=4 --driver=virtualbox --no-vtx-check`

Permitimos el tráfico de entrada (necesario en versiones previas a v1.21 de kubernetes)
`$ kubectl label namespace kube-system kubernetes.io/metadata.name=kube-system`

Habilitamos ingress:   
`$ minikube addons enable ingress`

Recuperamos la ip:   
`$ minikube ip`

Redirigimos las llamadas (en windows):   
Abrir con permisos de admin el fichero : `C:\Windows\System32\drivers\etc\hosts `

Añadimos la siguiente linea
`XXX.XXX.XX.XXX the-bookshelf`

Arrancamos la aplicación en modo microservicios o monolito   
`$ kubectl apply -f k8s/microservices/`

`$ kubectl apply -f k8s/monolith/`

> Applicación disponible en http://the-bookshelf/

Para detener la aplicación:
 `$ kubectl delete -f k8s/monolith/`  

---

Para el desarrollo en local:

* Arranque de la aplicación:

```sh
docker-compose -f docker-compose-dev.yml up
mvn spring-boot:run
```

* API Root URL

```sh
http://localhost:8080/api/v1/<resource>
```

* API Docs URL

```sh
http://localhost:8080/api-docs
http://localhost:8080/api-docs.yaml
```

* API Swagger URL

```sh
http://localhost:8080/swagger-ui.html
```

---
[Code URJC - Máster Cloud Apps](https://www.codeurjc.es/mastercloudapps/) : Desarrollo y despliegue de aplicaciones en
la nube  
Curso 2020/2021
