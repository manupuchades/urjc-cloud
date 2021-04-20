# Práctica 3

El manifiesto `global-np.yaml` contine las network policies:
 * Deny All
 * Una que permite el acceso por DNS a los pods con la etiqueta `dns-policy: enabled`.

En cada manifiesto están definidos el Deployment, Service, PVC y los network policies de cada servicio.

# Minikube

Debe tener los siguientes servicios:
 * Cilium (no funciona con Docker, se ha comprobado que funciona con Virtualbox)
 * Ingress
 * El cluster debe estar vinculado al dominio `cluster-ip` (de acuerdo a la configuración del ingress)

Además, para que funcione la network policy para el ingress, si la versión es menor a la 1.21, se debe etiquetar el namespace kube-system con `kubernetes.io/metadata.name=kube-system`

Esta configuración se consigue con los siguientes comandos:

```bash
$ minikube start --cni=cilium --memory=4096 --driver=virtualbox
$ minikube addons enable ingress
$ minikube ip
$ sudo vim /etc/hosts #anañidr la dns cluster-ip con la ip obtenida en el comando anterior, pej: '192.168.99.104 cluster-ip'
$ kubectl label namespace kube-system kubernetes.io/metadata.name=kube-system
```
# Pruebas de funcionamiento

* Servidor: http://cluster-ip/
* Servicio de topografía: http://cluster-ip/toposervice/api/topographicdetails/jaca
