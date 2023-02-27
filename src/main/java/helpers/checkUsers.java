package helpers;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import obtenerDatos.users;

public class checkUsers {

    public checkUsers() {
    }

    public void comprobarExistencia(DatabaseReference con, String user, String idioma, JFrame context) {
        con.child("usuarios").orderByChild("user").equalTo(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot user : snapshot.getChildren()) {
                        users log = user.getValue(users.class);
                        try {
                            if (!log.getPass().equals(new info().getPass())) {
                                System.exit(0);
                                if (idioma.equals("english")) {
                                    JOptionPane.showMessageDialog(context, "Current session has expired");
                                } else {
                                    JOptionPane.showMessageDialog(context, "La sesion ha caducado");
                                }
                            }
                        } catch (Exception e) {
                            System.exit(0);
                            if (idioma.equals("english")) {
                                JOptionPane.showMessageDialog(context, "Current session has expired");
                            } else {
                                JOptionPane.showMessageDialog(context, "La sesion ha caducado");
                            }
                        }
                    }
                } else {
                    //Avisar y salir
                    System.exit(0);
                    if (idioma.equals("english")) {
                        JOptionPane.showMessageDialog(context, "Current session has expired due to user removal from database");
                    } else {
                        JOptionPane.showMessageDialog(context, "La sesion ha caducado debido a que el usuario ha sido eliminado de la base de datos");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

}
