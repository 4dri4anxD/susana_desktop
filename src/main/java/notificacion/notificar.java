
package notificacion;

import org.json.simple.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class notificar {//clase para mandar notificaciones a los celulares cuando se modifique o agregue una nueva orden o trabajo

    public notificar() {//constructor
    }
    public void notificaT1(String titulo, String contenido, String topic) {//notifica cuando la aplicacion esta en uso
        //notificas trabajos u ordenes
        HttpClient httpclient = new DefaultHttpClient();
        String URL = "https://fcm.googleapis.com/fcm/send";
        HttpPost httppost = new HttpPost(URL);
        JSONObject json = new JSONObject();
        try {
            json.put("to", "/topics/" + topic);
            JSONObject notificacion = new JSONObject();
            notificacion.put("titulo", titulo);
            notificacion.put("contenido", contenido);
            notificacion.put("tipo", 0);
            notificacion.put("chat", "");
            json.put("data", notificacion);
            httppost.setEntity(new StringEntity(json
                    .toString(), "UTF-8"));
            httppost.setHeader("content-type", "application/json");
            httppost.setHeader("authorization", "key=AAAA7xGD1O8:APA91bEdR2zvSGKf4c1CUuI5cw3KCH8K4Nwzj7iZNgeUILyoPyMRjHGeP_UbJLZA2qPyaMrfI4WkGQdKc3GuPgOxaZ15tAA8W0lxw1XRPtmC1QNMstOWXedZ45j9x5Wk8xmujPP6XNfE");
            HttpResponse resp = httpclient.execute(httppost);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
   

      public void notificaT(String titulo, String contenido, String topic) {//notifica cuando se agrega una orden/trabajo y no se esta en la aplicacion
        
        HttpClient httpclient = new DefaultHttpClient();
        String URL = "https://fcm.googleapis.com/fcm/send";
        HttpPost httppost = new HttpPost(URL);
        JSONObject json = new JSONObject();
        try {
          //  json.put("to", "/topics/" + topic);
            json.put("to",topic);
            JSONObject notificacion = new JSONObject();
            notificacion.put("title", titulo);
            notificacion.put("body", contenido);
            json.put("notification", notificacion);
            httppost.setEntity(new StringEntity(json
                    .toString(), "UTF-8"));
            //key=AAAA7xGD1O8:APA91bEdR2zvSGKf4c1CUuI5cw3KCH8K4Nwzj7iZNgeUILyoPyMRjHGeP_UbJLZA2qPyaMrfI4WkGQdKc3GuPgOxaZ15tAA8W0lxw1XRPtmC1QNMstOWXedZ45j9x5Wk8xmujPP6XNfE
            httppost.setHeader("content-type", "application/json");
            httppost.setHeader("authorization", "key=AAAAJfdqkR0:APA91bG9LNfOYIWCJ7DkqwchE00vnLxpFaD3YhqUFj4Fwgvy6nIhq3hgIzYEKp9VFFdRSDW2ZfouVplTm4cln5r2LS_Ogu178ygva0GKXiZGO6D5Ugwpj_Ve8EB0egypzHapOuEQgitg");
            HttpResponse resp = httpclient.execute(httppost);
            notificaT1(titulo, contenido, topic);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    } 
    
    public void notificaE1(String titulo, String contenido, String topic, int tipo, String chat){//notifica cuando se esta en la aplicacion
        //notifica cuando se envia un mensaje por chat
        HttpClient httpclient = new DefaultHttpClient();
        String URL = "https://fcm.googleapis.com/fcm/send";
        HttpPost httppost = new HttpPost(URL);
        JSONObject json=new JSONObject();
        try{
            json.put("to",topic);
            JSONObject notificacion=new JSONObject();
            notificacion.put("titulo",titulo);
            notificacion.put("contenido",contenido);
            notificacion.put("tipo",tipo);
            notificacion.put("chat",chat);
            json.put("data",notificacion);
            httppost.setEntity(new StringEntity(json
                    .toString(), "UTF-8"));
            httppost.setHeader("content-type", "application/json");
            httppost.setHeader("authorization", "key=AAAA7xGD1O8:APA91bEdR2zvSGKf4c1CUuI5cw3KCH8K4Nwzj7iZNgeUILyoPyMRjHGeP_UbJLZA2qPyaMrfI4WkGQdKc3GuPgOxaZ15tAA8W0lxw1XRPtmC1QNMstOWXedZ45j9x5Wk8xmujPP6XNfE");
            HttpResponse resp = httpclient.execute(httppost);
            notificaT1(titulo, contenido, topic);
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    public void notificaE(String titulo, String contenido, String topic, int tipo, String chat) {//notifica cuando no se esta en la aplicacion
        //notifica cuando se manda un mansaje por chat
        HttpClient httpclient = new DefaultHttpClient();
        String URL = "https://fcm.googleapis.com/fcm/send";
        HttpPost httppost = new HttpPost(URL);
        JSONObject json = new JSONObject();
        try {
            json.put("to", topic);
            JSONObject notificacion = new JSONObject();
            notificacion.put("title", titulo);
            notificacion.put("body", contenido);
            json.put("notification", notificacion);
            httppost.setEntity(new StringEntity(json
                    .toString(), "UTF-8"));
            httppost.setHeader("content-type", "application/json");
            httppost.setHeader("authorization", "key=AAAA7xGD1O8:APA91bEdR2zvSGKf4c1CUuI5cw3KCH8K4Nwzj7iZNgeUILyoPyMRjHGeP_UbJLZA2qPyaMrfI4WkGQdKc3GuPgOxaZ15tAA8W0lxw1XRPtmC1QNMstOWXedZ45j9x5Wk8xmujPP6XNfE");
            HttpResponse resp = httpclient.execute(httppost);
            notificaT1(titulo, contenido, topic);
            notificaE1(titulo, contenido, topic, tipo, chat);
        } catch (Exception e) {
           System.out.println("Error: " + e);
        }
    }
}
