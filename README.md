![image](https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark/assets/89321404/e6819262-179b-4bef-892f-ce09e7f4cdf7)# arepTaller5-dockerSpark

# Descripción 
para este laboratorio, se creara una imagen con algunas confoguraciones para virtualización con docker, para esto debemos subir la imagen a docker hub
![image](https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark/assets/89321404/ad01f6f6-1a46-4859-88c8-fb0de28379b6)

para descargar la imagen se debe usar docker pull sebastiancepeda/arep-dockerspark:latest


1.clonar el repositorio (https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark.git) y abrirlo en su IDE de preferencia 

2. una vez dentro del proyecto, con el comando "java -cp "target/classes;target/dependency/*" com.taller5.SparkWebServer" podemos correr el server o con el mismo IDE

3. Ponemos en el browser locahost:4567/hello y nos retornara un hello world
4. para crear instancias usaremos el comando "docker run -d -p 34000:4567 --name firstdockercontainer dockersparkprimer", en la siguiente imagen vemos 3 instancias
  ![image](https://github.com/Sebasian-Cepeda/arepTaller5-dockerSpark/assets/89321404/1b26795a-020b-481b-b9d1-7f60a9f9f619)




---
## AUTOR
Juan Sebastian Cepeda saray
