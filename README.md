`URJC - Máster Cloud Apps`
# Modulo III : Aplicaciones nativas en la nube

## Contenedores y Orquestadores
* Práctica 4: Helm

El objetivo de esta práctica consiste en desarrollar una modificación en el planner de la
anterior y crear un chart del mismo.

### planner 

Se debe implementar un cambio básico en el planner. Para realizar este cambio, se utilizará una herramienta de desarrollo en la que el servicio
planner se pueda ejecutar en modo depuración (con un punto de ruptura en el código que
implementa la lógica de negocio) y el resto de servicios se ejecuten en el cluster
Kubernetes. La herramienta puede ser Okteto o VSCode.

Se deberá grabar un vídeo en el que se pueda ver cómo salta el punto de ruptura cuando
se usa el server desde el cluster Kubernetes.
La imagen modificada deberá publicarse en DockerHub y usarse en el chart.

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
`XXX.XXX.XX.XXX cluster-ip`

Arrancamos la aplicación   
`$ kubectl apply -f .`

> Applicación disponible en http://cluster-ip/

Modificamos el manifest del planner para poder utilizar Bridge to Kubernetes:

Añadimos el tag latest a la imagen de docker y asociamos el siguiente servicio al deployment:

    apiVersion: v1
    kind: Service
    metadata:
    name: planner-deploy
    labels:
        app: planner
    spec:
    ports:
    - port: 8888
    selector:
        app: planner
    type: ClusterIP

Aplicamos los cambios   
`$ kubectl apply -f .`



* Otros:
 
 Para compilar la aplicación grpc:   
 
    mvn compile

---
[Code URJC - Máster Cloud Apps](https://www.codeurjc.es/mastercloudapps/) : Desarrollo y despliegue de aplicaciones en la nube  
Curso 2020/2021
