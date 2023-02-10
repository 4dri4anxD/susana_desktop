
package seguridad;

import static com.google.api.client.util.Base64.decodeBase64;
import static com.google.api.client.util.Base64.encodeBase64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class encriptado {
    //clase que sirve para encriptar y desencriptar las contrasenas de los usuarios

    // Definición del tipo de algoritmo a utilizar (AES, DES, RSA)
    private final static String alg = "AES";
    // Definición del modo de cifrado a utilizar
    private final static String cI = "AES/CBC/PKCS5Padding";
    private final static String key = "HOIZ4MZpIsI!r7Zt"; //llave para desencriptar  92AE31A79FEEB2A3   HOIZ4MZpIsI!r7Zt
    private final static String iv = "HQyEeVsXi#ku^Hyd"; //variable para desencriptar 0123456789ABCDEF   HQyEeVsXi#ku^Hyd

    public static String encrypt(String cleartext) throws Exception {//funcion que encripta un texto
        Cipher cipher = Cipher.getInstance(cI);//crea un objeto con el modo de cifrado
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);//crea un objeto y establece la llave para encriptar (la que esta en la clase info en la package configuraciones y el algoritmo de cifrado
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());//crea un objeto y le asigna los bytes de la variable iv de la clase info en el package configuraciones
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);//establece los parametros para el modo de cifrado
        byte[] encrypted = cipher.doFinal(cleartext.getBytes());//obtiene los bytes del texto ya encriptado
        return new String(encodeBase64(encrypted));//le anade un nuevo cifrado base64 y lo convierte a string para retornarlo como cadena
    }
    public static String decrypt(String encrypted) throws Exception {//funcion para desencriptar un texto
        Cipher cipher = Cipher.getInstance(cI);//crea un objeto con el modo de cifrado
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);//crea un objeto y establece la llave para encriptar (la que esta en la clase info en la package configuraciones y el algoritmo de cifrado
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());//crea un objeto y le asigna los bytes de la variable iv de la clase info en el package configuraciones
        byte[] enc = decodeBase64(encrypted);//desencripta el algoritmo base64 y obtiene el resultado en un array de bytes
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);//establece los parametros para el modo de cifrado
        byte[] decrypted = cipher.doFinal(enc);//obtiene los bytes del texto ya desencriptado
        return new String(decrypted);//convierte los bytes en string para devolver una cadena de caracteres
    }
}
