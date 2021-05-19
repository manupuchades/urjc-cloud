#################################################
# Imagen base para el contenedor de la aplicación
#################################################
FROM openjdk:11-jre-slim

# Define el directorio de trabajo donde se encuentra el JAR
WORKDIR /

# Copia el JAR del contenedor de compilación
COPY target/bookshelf-monolith.jar /opt/

# Indica el puerto que expone el contenedor
EXPOSE 3000

# Comando que se ejecuta al hacer docker run
CMD [ "java", "-jar", "/opt/bookshelf-monolith.jar" ]