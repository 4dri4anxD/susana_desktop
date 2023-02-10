
package com.mycompany.susana1;

import interfaces.login;


//Poner alertaal cerrar una ventana
//poner alerta al regresar
//estilizar alertas
//Comporbar que el usuario existe en la bd al cambiar de interfaz
//Poner mensajes de error en la interfaz menuActivudades
//checkBox personalizado en crearTS, completar esa interfaz
//tambien hacer la de crearTT
//Ajustar a pantalla completa las ventanas
//Cambiar resolucion de imagenes
//Poner la nueva ventana del tamaño de la anterior

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
