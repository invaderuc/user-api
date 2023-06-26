# user-api
API related to Escalab Java Fundamentals course

<!-- GETTING STARTED -->
## Bienvenido

A continuación revisaremos los pasos necesarios para ejecutar de forma correcta el proyecto de curso
"Java Fundamentals course"
### Prerrequisitos
1. Java
2. Postman
3. Navegador
4. Docker
5. git
6. DBWAVER u otro gestor de Database
7. Archivo init.sql
8. Archivo docker-compose.yml
9. Archivo User-API.postman_collection.json
10. Intellij IDEA


### Instalación

A continuación se detalla el paso a paso para un instalación exitosa


1. Clonar the repo.
   ```sh
   git clone https://github.com/invaderuc/user-api.git
   ```
2. Descomprimir Archivo.zip.
3. Revisar que tengamos el puerto 8080 y 3306 libres.
4. En la carpeta donde Archivo.zip fue descomprimido, ejecutar docker con el siguiente comando
   ```sh
   docker compose up
   ```
5. Hacer correr proyecto en nuestro IDE favorito.
6. Importar User-API.postman_collection.json en nuestro postman para probar nuestros servicios.
7. Probar servivios
8. Swagger disponible en /doc/swagger-ui/index.html solo al estar corriendo el proyecto.

Cualquier duda estaré pendiente del correo.

PD: En caso de tener problemas para visualizar la DB habilitar allowPublicKeyRetrieval 

<p align="right">(<a href="#readme-top">back to top</a>)</p>
