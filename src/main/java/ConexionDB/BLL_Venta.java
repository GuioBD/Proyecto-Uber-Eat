package ConexionDB;
import Clases.Socio;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BLL_Venta {
    private Connection dbCon;
    
    public BLL_Venta(Connection _dbCon){
         this.dbCon = _dbCon;
    }
    
    public List<Socio> queryToDataBase() {
        List<Socio> ventas = null;
        try{
            ventas = new ArrayList<Socio>();
            DAL_Venta dal_venta = new DAL_Venta(dbCon);
            ventas= dal_venta.queryToDataBase();
        }
        catch(Exception ex){
            System.out.println("Failed to get the collection of records: " + ex.toString());
            return null;
        }
        return ventas;
    }
    
    public void insertToDataBase(Socio venta) {
      
        try {
            DAL_Venta dal_venta = new DAL_Venta(dbCon);
            dal_venta.insertToDataBase(venta);
            System.out.println();  
        } catch (Exception e) {
            System.out.println("se agrego el elemento");
        }
    }
    
   
   
}