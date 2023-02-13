/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerDatos;

public class checkCommentsC {//Comentarios de cada actividad del child checkTC
    private  String comentario;


    public checkCommentsC() {
        //Es obligatorio incluir constructor por defecto
    }

    public checkCommentsC(String comentario)
    {
        this.comentario=comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
