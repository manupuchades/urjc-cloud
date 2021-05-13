`URJC - Máster Cloud Apps`
# Modulo III : Aplicaciones nativas en la nube

### Aplicaciones nativas en la nube
* Práctica 2:
  - Monolito a microservicios
  
* Usage

```sh
docker run -p 3306:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=test -e -d mysql:latest
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
[Code URJC - Máster Cloud Apps](https://www.codeurjc.es/mastercloudapps/) : Desarrollo y despliegue de aplicaciones en la nube  
Curso 2020/2021
