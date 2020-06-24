# practicaGM 
QUE ES EL CODIGO FUENTE <br>
este codigo tiene como proposito es un servidor web con cuatro servicios, realizado en JAVA con la libreria HttpServer, el cual trata de eumlar un servicio de mesaje:<br>
      http://.../credencial PUT <br>
       http://.../credencial POST / GET  <br>
        http://.../credencial  <br>
<br>
REQUISITOS Y FORMA  DE USO:
      + tener intalado la maquina virutal de jaba (jre 1.8)<br>
      + fue creado con la JDK 8 (jdk 1.8) y Netbeans 8.1<br>
      + el archivo Jar y sus dependencias estan en el archivo dist.zip <br>
            + para poder utilizar este ejecuble dirijace a la consola y ubiquese en la carpeta descompimida, luego ejecute java -jar server_GM.jar y el servidor iniciara a corrrer.<br><br>
            
PRUEBAS DE LOS SERVICIOS  
     los servicios han sido probados con un cliente Rest instaldo en un navegador web, y se realizan de la siguiente manera.<br> 
PARA : <br>
 http://.../credencial PUT <br> , para esta solicitud se espera en el body un json, con las etiquetas "key" y "shared_secret" como se nuestra en la imagen, si esta no existe guarda en meroria los valores y envia una respuesta de 204  y si la etiqueta "key" contiene un valor ya guardado devuelve el valor de 403. 
 <br>
 <br>
 
 ![Alt text](Lightshot/crR.png?raw=true "request ")
 
 <br>
 <br>
 
 ![Alt text](Lightshot/crRR2.png?raw=true "request ")

<br>
 <br>
 
 PARA : <br>
 http://.../message POTS<br> , para esta solicitud se espera en el body un json, con las etiquetas "msg" y "tags" como se nuestra en la imagen,  esta  guarda en meroria los valores y envia una respuesta de 200 y  el valor del id asignado y con el cual fue guardado y el cual se puede utilizar para recuperarlo . 
 <br>
 <br>
 ![Alt text](Lightshot/msgRR.png?raw=true "request ")
 
 <br>
 <br>
 
  PARA : <br>
 http://.../message GET<br> , para esta solicitud no se espera en el body , el id se espera en la URL como se nuestra en la imagen,  este  toma el valro y envia una respuesta de 200 y  la informacion que contiene ese mesanje.  si ingresa un id que no existe envia un json vacio. 
 <br>
 <br>
 
 ![Alt text](Lightshot/mssidRR.png?raw=true "request ")
 
 <br>
 <br>
 
   PARA : <br>
 http://.../messages GET<br> , para esta solicitud no se espera en el body , el un valor referente a "tag"  en la URL como se nuestra en la imagen,  este  toma el valro y envia una respuesta de 200 y todos los mesanjes con este tag. si ingresa un tag que no existe envia un json vacio. 
 <br>
 <br>
 ![Alt text](Lightshot/msgTg.png?raw=true "request")
 
 <br>
 <br>
 
   LOS HEADERS PARA  MASSAGE(POST /GET), MESSAGE : <br>
       Este server tiene un guard para el acceso a estas , por lo que esta peticiones tiene que llevar los tres tag "x-route", "x-key","x-signature", los cuales son utilizados para reaizar una verificacion de no corrupcion con HMAC-SHA256, la cual se realiza con la union de los paramametros del body clave/valor mas el valor de x-route que lleva  para los POST  y  el paramametro URI valor mas el valor de x-route que lleva  para los GET, orneados lexicograficamente y utilizando la shared_secred  asociado al valor de "x-key" y x-signature lleva el string resultante del sifrado para poder realizar la verificacion.
       ejemplos : <BR>
  <BR>
      msg/caldito;post /message;tags/caldito2
      Result:[EC50E29A3E388367129245C38EB17CBC1D66B74BB80728ECAA3B31083766AB06]
    <BR><BR>
      1;get /message
      Result:[A6F579DE39169A3CF4578F9ADDE76DA875E959E41F1CB0D5D8A820B20433422C]
<BR><BR>
      caldito2;get /messages
      Result:[20441B051EC8E9AF218042EB5B929E7524BE63DD9673FF50121CE09341DC9F60]

 <br>
 <br>
       ![Alt text](Lightshot/msg.png?raw=true "request ")
 
 <br>
 <br>
 
 OTRAS RESPUESTAS :   <br>   
       404  SI SE ENVIA UNA RUA QUE NO EXISTE <br>
       500  SI PASA ALGUN ERROR INTERNO  <br>
       400  SI NO LLEVAR ALGUN HEADER  O  PARAMETRO FALTANTE<br>
 
 <br><strong>NOTA<strong> se realizaron esta puebras durante el desarrollo y no tiene una interfaz propia para poder ralizarlas, ya que por falta de tiempo no pude comcluirla.<strong><br>
     

      
      
      
      
            
      
      
