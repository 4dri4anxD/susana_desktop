package configuracion;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xmlManagment {//clase para hacer operaciones sobre un xml

    public xmlManagment() {//constructor necesarios

    }

    public void modificar(int rango) throws SAXException { //modificar el rango (la cantidad de componentes que se cargan por interfaz)
        //variable para saber si se encontro la etiqueta del rango
        int a = 0;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        }
        try {
            //abre el documento xml
            Document doc = builder.parse(new File("config.xml"));
            doc.getDocumentElement().normalize();
            //obtiene la informacion bajo la etiqueta "rango"
            NodeList nList = doc.getElementsByTagName("rango");
            for (int temp = 0; temp < nList.getLength(); temp++) {//recorre la lista de elementos
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    //encuentra el contenido
                    Element eElement = (Element) nNode;
                    //y lo cambia por lo que este en la variable rango
                    eElement.setTextContent("" + rango);
                    //a=1 indica que se modifico el rango
                    a = 1;
                }
            }
            if (a == 0) {//si no se modifico el rango, quiere decir que la etiqueta "rango" no existe
                //asi que se obtienen los elementos dentro de la etiqueta raiz
                nList = doc.getElementsByTagName("root");
                for (int temp = 0; temp < nList.getLength(); temp++) {//se recorre la lista de elementos
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        //se crea la etiqueta rango
                        Element elemento1 = doc.createElement("rango");
                        //y se le asigna el valor de la variable rango
                        elemento1.setTextContent("" + rango);
                        //luego se agrega la etiqueta rango a la etiqueta raiz
                        eElement.appendChild(elemento1);
                        //a=1 indica que se relizo con exito esta operacion
                        a = 1;
                    }
                }

            }
            //se da formato y se guardan cambios en el documen to xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("config.xml"));
            transformer.transform(source, result);
        } catch (java.io.FileNotFoundException e) {//si el documentos config.xml no existe, se llama a una funcion que lo crea
            crear(rango);
            //a=1 indica que se creo el archivo xml
            a = 1;
        } catch (IOException ex) {
            Logger.getLogger(xmlManagment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(xmlManagment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(xmlManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (a == 0) {//si por alguna razon sigue sin crearse el documento xml (en caso de que no existiera) o modificarse el rango se crea aqui llamando a la funcion crear
            crear(rango);
        }

    }

    public int leer() {//funcion encargada de obtener el valor del rango (la cantidad de componentes que se cargan por interfaz) de un xml
        //se declara por defecto en 25 elementos
        int rango = 25;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        }
        try {
            //se trata de abrir el documento config.xml
            Document doc = builder.parse(new File("config.xml"));

            doc.getDocumentElement().normalize();
            //se obtiene el valor relacionado a la etiqueta rango
            NodeList nList = doc.getElementsByTagName("rango");

            for (int temp = 0; temp < nList.getLength(); temp++) {//se recorre la lista de valores obtenidos (aunque siempre va a ser 1 solo elemento en la lista)
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //se obtiene el valor de rango y se convierte a entero
                    rango = Integer.parseInt(eElement.getTextContent());

                }
            }
        } catch (Exception ex) {// si no existe el documento config.xml se crea, con el valor por defecto de rango que es 25
            crear(rango);
        }
        //se retorna el valor del rango
        return rango;
    }

    private void crear(int rango) {//funcion para crear el documento que guarda el valor del rango (la cantidad de componentes que se cargan por interfaz)
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            //se crea un nuevo documento
            Document doc = docBuilder.newDocument();
            Element rootElement;
            //se crea la etiqueta raiz
            rootElement = doc.createElement("root");
            //se adhiere al documento xml
            doc.appendChild(rootElement);
            //se crea la etiqueta rango
            Element elemento1 = doc.createElement("rango");
            //se le asigna el valor de la variable rango
            elemento1.setTextContent("" + rango);
            //se adhiere a la etiqueta raiz
            rootElement.appendChild(elemento1);
            //se da formato y se guardan cambios
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("config.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    private void crearBd(String ruta) {//se crea el archivo xml que contendra la ruta donde se almacenara la ruta donde se guardaran las copias de la base de datos
        //el archivo donde se guardan los valores de rango e idioma es distinto del archivo donde se almacena la ruta de la copia de seguridad de la bd
        try {
            //se crea el documento
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            //se crea el nodo raiz 
            Element rootElement = doc.createElement("root");
            //se adhiere al documento
            doc.appendChild(rootElement);
            //se crea el nodo ruta
            Element elemento1 = doc.createElement("ruta");
            //se le asigna el valor de la variable ruta
            elemento1.setTextContent("" + ruta);
            //se adhiere al nodo raiz
            rootElement.appendChild(elemento1);

            //se da formato y guardan cambio
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("rutaBD.xml"));//nombre que se le da al archivo
            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

    public String leerBd() {//funcion que lee la ruta donde se guardara la copia de la base de datos
        //variable que contendra la ruta
        String rango = "";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        }
        try {
            //se abre el archivo
            Document doc = builder.parse(new File("rutaBD.xml"));

            doc.getDocumentElement().normalize();
            //se obtienen los valores relacionados al nodo ruta
            NodeList nList = doc.getElementsByTagName("ruta");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //se obtiene el contenido del nodo ruta
                    rango = eElement.getTextContent();

                }
            }

        } catch (Exception ex) {// si no existe el documento se llama a una funcion que lo crea, con una ruta en blanco
            crearBd(rango);
        }
        //se retorna la ruta
        return rango;

    }

    public void modificarBd(String ruta) throws SAXException {//funcion que modifica la ruta donde se guardan las copias de la base de datos
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        }
        try {
            //se abre el documento de la ruta
            Document doc = builder.parse(new File("rutaBD.xml"));

            doc.getDocumentElement().normalize();
            //se obtiene la informacion que contiene el nodo ruta
            NodeList nList = doc.getElementsByTagName("ruta");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //se obtiene el contenido del nodo
                    eElement.setTextContent("" + ruta);
                }
            }
            //se cierra y guardan cambios en el archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("rutaBD.xml"));
            transformer.transform(source, result);
        } catch (java.io.FileNotFoundException e) {//si no se encuentra el archivo llama a una funcion que lo crea
            crearBd(ruta);
        } catch (IOException ex) {
            Logger.getLogger(xmlManagment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(xmlManagment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(xmlManagment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modificarId(String rango) throws SAXException {//funcion que sirve para guardar el idioma en el que se muestra la interfaz
        //esta informacion se guarda en el mismo archivo que el rango
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        }
        int a = 0;
        try {
            //se abre el documento xml
            Document doc = builder.parse(new File("config.xml"));
            doc.getDocumentElement().normalize();
            //se obtiene la informacion relacionada al nodo idioma
            NodeList nList = doc.getElementsByTagName("idioma");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //se modifica el contenido del nodo
                    eElement.setTextContent("" + rango);
                }
                //a=1 indica que se modifico con exito
                a = 1;
            }
            if (a == 0) {//si no se modifico el contenido, a lo mejor el nodo idioma no existe
                //obtiene la informacion relacionada al nodo raiz
                nList = doc.getElementsByTagName("root");

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        //asi que crea el nodo
                        Element elemento1 = doc.createElement("idioma");
                        //le asigna el valor
                        elemento1.setTextContent("" + rango);
                        //y lo adhiere al nodo raiz
                        eElement.appendChild(elemento1);
                        //a=1 indica que se creo con exito el nodo y que se le asigno el valor
                        a = 1;
                    }
                }

            }
            //se cierra y guardan cambios en el documento
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("config.xml"));
            transformer.transform(source, result);
        } catch (java.io.FileNotFoundException e) {//si el archivo xml no existe se crea con la funcion crearId
            crearId(rango);
            //a=1 indica que se creo con exito
            a = 1;
        } catch (IOException ex) {
            Logger.getLogger(xmlManagment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(xmlManagment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(xmlManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (a == 0) {//si sigue sin crearse el archivo, lo manda a crear con la funcion  crearId
            crearId(rango);
        }
    }

    public String leerId() {//funcion para obtener el idioma en el que se mostrara la interfaz
        //por defecto esta en espaniol
        String rango = "Español";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        }
        try {
            //abre el archivo config.mxl
            Document doc = builder.parse(new File("config.xml"));

            doc.getDocumentElement().normalize();
            //obtiene informacion relacionada al nodo idioma
            NodeList nList = doc.getElementsByTagName("idioma");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //obtiene el contenido del nodo
                    rango = eElement.getTextContent();

                }
            }

        } catch (Exception ex) {// si no se abrio el documento es porque probablemente no existe, asi que lo manda a crear con crearId
            crearId(rango);
        }
        //retorna el idioma
        return rango;
    }

    private void crearId(String rango) {//funcion para crear el archivo xml que guardara el idioma en el que se debe mostrar la interfaz
        try {
            //crea un nuevo documento xml
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement;
            //crea un nodo raiz
            rootElement = doc.createElement("root");
            //lo adhiere al documento
            doc.appendChild(rootElement);
            //crea el nodo idioma
            Element elemento1 = doc.createElement("idioma");
            //le asigna el valor del idioma
            elemento1.setTextContent("" + rango);
            //lo adhiere al nodo raiz
            rootElement.appendChild(elemento1);
            //guarda el documento
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("config.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
