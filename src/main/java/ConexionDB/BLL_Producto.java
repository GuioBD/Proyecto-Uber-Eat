package ConexionDB;
import Clases.Socio;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BLL_Producto {
    private Connection dbCon;
    
    public BLL_Producto(Connection _dbCon){
         this.dbCon = _dbCon;
    }
    
    public List<Socio> queryToDataBase() {
        List<Socio> productos = null;
        try{
            productos = new ArrayList<Socio>();
            DAL_Producto dal_producto = new DAL_Producto(dbCon);
            productos= dal_producto.queryToDataBase();
        }
        catch(Exception ex){
            System.out.println("Failed to get the collection of records: " + ex.toString());
            return null;
        }
        return productos;
    }
    
  
    
    public void deleteToDataBase(String idProducto) {
      
        try {
           
            DAL_Producto dal_producto = new DAL_Producto(dbCon);
            dal_producto.deleteToDataBase(idProducto);
            System.out.println();  
        } catch (Exception e) {
            System.out.println("se borro el elemento");
        }
    }
    public void insertToDataBase(Socio producto) {
      
        try {
            DAL_Producto dal_producto = new DAL_Producto(dbCon);
            dal_producto.insertToDataBase(producto);
            System.out.println();  
        } catch (Exception e) {
            System.out.println("se agrego el elemento");
        }
    }
    
   
   
}