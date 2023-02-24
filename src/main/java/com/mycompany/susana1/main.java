package com.mycompany.susana1;

import alerts.alert;
import interfaces.login;

//revisar linea 137 de la clase tblUsersActividades
//Cambair resolucion de imagenes
//Hacer prueba general, especial enfasis en el apartado de trabjar en la orden 66666 pq el checklistTT me da de progreso 0


//quitar textos y agregarlos al jsonxdd
//ver si en los checkListTF,C,S,T se puede pintar de color la celda del JCheckBox
//busquedas en plantillas indiferentes de minusculas o mayusculas
//Ver si funciona bien en ingles
//estilizar alertas
//Comporbar que el usuario existe en la bd al cambiar de interfaz
//Ajustar a pantalla completa las ventanas en laptops (ya mas o menos)
//Poner la nueva ventana del tamaño de la anterior
//Revisar estilizacion de ventanas, iconos y todo esp
//Cambiar textos por lo que hay en strings
//librerias para abrir el archivo json y encriptarlo (que?xd)
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
