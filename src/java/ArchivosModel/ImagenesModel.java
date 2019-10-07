
package ArchivosModel;

import ArchivosBeans.ImagenesBeans;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class ImagenesModel {
    
    Conexion con;

    public ImagenesModel(Conexion con) {
        this.con = con;
    }
    
    
    public boolean saveImagenes(ImagenesBeans ib){
        String sql = "INSERT INTO imagenes VALUES(?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = con.getConection().prepareStatement(sql);
            ps.setInt(1, ib.getId());
            ps.setString(2, ib.getImagen1());
            ps.setString(3, ib.getImagen2());
            ps.setString(4, ib.getImagen3());
            ps.setString(5, ib.getImagen4());
            ps.setString(6, ib.getImagen5());
            
            ps.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<ImagenesBeans> getImagenes(){
        
        String sql = "SELECT * FROM imagenes";
        
        try {
            PreparedStatement ps = con.getConection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            List<ImagenesBeans> imagenes = new LinkedList<>();
            
            ImagenesBeans ib;
            
            while (rs.next()) {
                ib = new ImagenesBeans();
                ib.setId(rs.getInt("id"));
                ib.setImagen1(rs.getString("imagen1"));
                ib.setImagen2(rs.getString("imagen2"));
                ib.setImagen3(rs.getString("imagen3"));
                ib.setImagen4(rs.getString("imagen4"));
                ib.setImagen5(rs.getString("imagen5"));
                
                imagenes.add(ib);
            }
            
            return imagenes;
            
        } catch (Exception e) {
            return null;
        }
        
    }
    
}
