package conexion;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import seguridad.encriptado;

public class conectar {

    private DatabaseReference db;

    public conectar() throws FileNotFoundException, IOException {

        //se lee el archivo con las credenciales encriptado
        //requiredfile1.txt buena  DoNotDelete.txt prueba
        FileInputStream serviceAccount =  new FileInputStream("DoNotDelete.txt"); //new FileInputStream("requiredfile1.txt");
        //  = new FileInputStream("susaneitor.json"); por si se ocupa leer directo del json

        //Se transforma lo leido en un inputstreamreader, todas estas conversiones se hacen para desencriptar el contenido del archivo
        InputStreamReader inputStreamReader = new InputStreamReader(serviceAccount);
        //ahora en un stream
        Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
        //ahora en string, pero contiene el string encriptado
        String streamToString = streamOfString.collect(Collectors.joining());

        try {
            //se desencripta el contenido del String
            streamToString = new encriptado().decrypt(streamToString);
        } catch (Exception ex) {
            System.out.println("Exx: " + ex);
        }
        //se convierte a inputStream pq se requiere en esa forma para establecer conexion
        InputStream stream = new ByteArrayInputStream(streamToString.getBytes(StandardCharsets.UTF_8));
        //https://susana-7c1f5-default-rtdb.firebaseio.com prueba
        //https://susana-22a74-default-rtdb.firebaseio.com buena
        //se pasan parametros para la conexion
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(stream))//serviceAccount
                .setDatabaseUrl("https://susana-7c1f5-default-rtdb.firebaseio.com")
                .build();

        //se obtiene la conexion
        FirebaseApp.initializeApp(options);
        db = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getCon() {//retorna la conexion a la bd
        return db;
    }
    
    //codigo para encriptar una nueva conexion (NO BORRAR)
    /* FileInputStream serviceAccount1 = new FileInputStream("doc.json");
        InputStreamReader inputStreamReader1 = new InputStreamReader(serviceAccount1);
        //ahora en un stream
        Stream<String> streamOfString1 = new BufferedReader(inputStreamReader1).lines();
        //ahora en string, pero contiene el string encriptado
        String streamToString1 = streamOfString1.collect(Collectors.joining());

        try {
            //se desencripta el contenido del String
            streamToString1 = new encriptado().encrypt(streamToString1);
            //escribir archivo txt
            //  Scanner sc = new Scanner(System.in);
            String ruta = "";
            String nombre = "requiredfile1.txt";
            File archivo = new File(ruta + nombre);

            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            //System.out.println("¿Qué compraste?");
            //desc=sc.nextLine();
            //bw.write(""+desc);
            //bw.newLine();

            // System.out.println("¿Cuánto gastaste?");
            //  n = sc.nextInt();
            bw.write(streamToString1);
            bw.flush();
            bw.close();

        } catch (Exception ex) {
            System.out.println("Exx: " + ex);
        } */

}
