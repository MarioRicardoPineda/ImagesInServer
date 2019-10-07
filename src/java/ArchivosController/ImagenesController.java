package ArchivosController;

import ArchivosBeans.ImagenesBeans;
import ArchivosModel.Conexion;
import ArchivosModel.ImagenesModel;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "ImagenesController", urlPatterns = {"/imagenes"})
public class ImagenesController extends HttpServlet {

    Conexion con = new Conexion();
    ImagenesModel im = new ImagenesModel(con);
    ImagenesBeans ib = new ImagenesBeans();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // CREO EL DIRECTORIO, DONDE GUARDARE LAS IMAGENES
        File directorio = new File("F:\\documentos\\NetBeansProjects\\archivosEnServidor\\web\\imagesServer");
        directorio.mkdir();

        // EMPIEZO CREANDO UNA FABRICA QUE ES PARTE DE LA LIBRERIA COMMONS
        FileItemFactory factory = new DiskFileItemFactory();

        // LA CLASE RECIBE COMO PARAMETRO UNA FABRICA CREADA
        // ESTA CLASE POSEE UN METODO PARA RECIBIR EL REQUEST COMO PARAMETRO
        ServletFileUpload upload = new ServletFileUpload(factory);

        // CREO UNA LISTA DONDE ALMACENARE LAS IMAGENES
        List<String> images = new ArrayList<>();

        try {

            // CREO UNA LISTA QUE ALMACENARA LA PETICION REALIZADA
            List items = upload.parseRequest(request);

            // RECORRO DICHOS VALORES
            for (int i = 0; i < items.size(); i++) {
                // CREO UN ITEM PARA GUARDAR TODOS LOS DATOS DE LA IMAGEN,
                // Y LUEO ACCEDER A CADA UNO DE SUS DATOS. EN ESTE CASO EL NOMBRE DE LA IMAGEN
                FileItem file = (FileItem) items.get(i);

                // CREO UNA RUTA DONDE QUIERO GUARDAR LAS IMAGENES
                File imagenes = new File(
                        "F:\\documentos\\NetBeansProjects\\archivosEnServidor\\web\\imagesServer\\" + file.getName()
                );

                //GUARDO CADA UNO DE LOS NOSMBRE DE LAS IMAGENES EN LA RUTA DESCRITA,
                // ANTERIORMENTE CON EL METODO WRITE DEL FILEITEM
                file.write(imagenes);

                // GUARDO CADA IMAGEN CON LA RUTA QUE SE GUARDARA EN BASE DE DATOS PARA LUEGO
                // REALIZAR CONSULTAS DONDE LA RUTA ESPECIFICADA SE LA CORRECTA AL IMPRIMIR LA IMAGEN
                images.add("imagesServer/" + file.getName());
            }

        } catch (Exception e) {
            System.out.println("Hubo algo: " + e);
        }

        ib.setImagen1(images.get(0));
        ib.setImagen2(images.get(1));
        ib.setImagen3(images.get(2));
        ib.setImagen4(images.get(3));
        ib.setImagen5(images.get(4));

        boolean res = im.saveImagenes(ib);

        if (res) {
            System.out.println("Se han gaurdado las imagenes");
        } else {
            System.out.println("No se han guardado");
        }
        
        response.sendRedirect("index.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        List<ImagenesBeans> imagenes = im.getImagenes();
        
        Gson convertJson = new Gson();
        
        String jsonConvertido = convertJson.toJson(imagenes);
        
        out.print(jsonConvertido);
        
    }

}
