# arepTaller5-dockerSpark

# Descripción 
para este laboratorio, se creara una imagen con algunas confoguraciones para virtualización con docker, para esto debemos subir la imagen a docker hub
![image](https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark/assets/89321404/ad01f6f6-1a46-4859-88c8-fb0de28379b6)

## DESARROLLADO CON
* [docker](https://www.docker.com/products/docker-desktop/) contenedorización 
* [Java version 17](https://www.oracle.com/co/java/technologies/downloads/) - Lenguaje de programación usado.
* [Maven](https://maven.apache.org/download.cgi) - Gestor de dependencias del proyecto
* [Git](https://git-scm.com/downloads) - Gestion de versiones del proyecto
* [omdbapi](https://www.omdbapi.com) - API externa para realizar consultas

1. Debemos tener la imagen del docker y para eso usaremos el siguiente comando: docker pull sebastiancepeda/arep-dockerspark:latest
2. Luego usaremos el siguiente comando para crear una instancia: docker run -d -p 34000:46000 --name firstdockercontainer sebastiancepeda/arep-dockerspark
3. Podemos buscar en la URL http://localhost:34000/hello para optener la siguiente respuesta
   ![image](https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark/assets/89321404/b55ee9bc-1ca7-4f05-859c-35648d5c7005)

Si usamos http://localhost:34000, tendremos un formulario con las diferentes operaciones
  ![image](https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark/assets/89321404/e9a1083a-1ea3-48d8-b249-9e2d3cacb293)

podemos ver que al dar click al boton correspiente de cada operación, tenderemos la respuesta abajo de cada operación.
![image](https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark/assets/89321404/b4723569-6c85-4c14-a922-dba0788f0f49)
5. Por otro lado podemos hacer las peticiones directamente en la URL, por ejemplo: 
>
>http://localhost:34000/sin/90
>![image](https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark/assets/89321404/a8fa52cd-b7a9-4109-8016-ee86c99b9291)
>http://localhost:34000/cos/90
>![image](https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark/assets/89321404/f3b5f3f9-b5dd-4594-8fee-4e720492bbc3)
>http://localhost:34000/palindrome/oso
>![image](https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark/assets/89321404/54c87d03-66e3-486b-9abc-580f190ce267)
>http://localhost:34000/magnitud/3/3
>![image](https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark/assets/89321404/c2ed8d62-83ab-41cb-b606-5efb9d02b0fd)
6. Tambien podemos probar el proyecto clonando el repositorio en el IDE de su preferencia y con ayuda de este correr la clase SparkWebServer.java y cambiar un poco la ruta pues ahora sera 4567 en lugar de 34000

# Diseño
Se tiene una clase SparkWebServer que se encarga de las peticiones por medio de spark, tambien se asigna un puerto para correr el servidor y el directorio estatico para poder leer los archivos, y se cuenta con un html en la carpeta public/static, para facilitar al usuario a realizar las peticiones GET.
---
## AUTOR
Juan Sebastian Cepeda saray
