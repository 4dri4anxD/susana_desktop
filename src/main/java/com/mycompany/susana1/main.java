package com.mycompany.susana1;

import interfaces.login;
//cambiar el llamado a clases como info de new info() a una variable solamente, igual con disenos
//El info width y height no estan funcionando, lo pone maximizado (ya funciona)


//Generar nueva aplicacion movil (build)

//Cambiar resolucion de imagenes (inkscape, filters->color->brilliance->inverted apply, ver que resolucion se necesita)

//quitar textos y agregarlos al jsonxdd

//Poner la nueva ventana del tamaño de la anterior

//En vistaCompletarOrden, en checkListTC y todos, ver tamano de la barra lateral derecha cuando un admin entra a ver alguna actividad
//se esconden los botones y cambia el ancho de ese panel

//librerias para abrir el archivo json y encriptarlo


/* 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import seguridad.encriptado;*/
public class main {

    public static void main(String[] args) {

        new login(null).setVisible(true);
        
        //Si por alguna razon se pierde el archivo con las credenciales de la base de datos:
        //el siguiente codigo es para encriptar el contenido del archivo json
        /*  FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream("susaneitor.json");
            InputStreamReader inputStreamReader = new InputStreamReader(serviceAccount);
            Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
            String streamToString = streamOfString.collect(Collectors.joining());
            String texto=encriptado.encrypt(streamToString);
            
            String ruta = "requiredfile.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.close();
           
        } catch (Exception ex) {
            System.out.println("Ex: "+ex);
        }  */
    }

}
