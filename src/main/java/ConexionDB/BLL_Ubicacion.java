package ConexionDB;
import Clases.Socio;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BLL_Ubicacion {
    private Connection dbCon;
    
    public BLL_Ubicacion(Connection _dbCon){
         this.dbCon = _dbCon;
    }
    
    public List<Socio> queryToDataBase() {
        List<Socio> ubicaciones = null;
        try{
            ubicaciones = new ArrayList<Socio>();
            DAL_Ubicacion dal_ubicacion = new DAL_Ubicacion(dbCon);
            ubicaciones= dal_ubicacion.queryToDataBase();
        }
        catch(Exception ex){
            System.out.println("Failed to get the collection of records: " + ex.toString());
            return null;
        }
        return ubicaciones;
    }
    
  
    
    public void deleteToDataBase(String idUbicacion) {
      
        try {
           
            DAL_Ubicacion dal_ubicacion = new DAL_Ubicacion(dbCon);
            dal_ubicacion.deleteToDataBase(idUbicacion);
            System.out.println();  
        } catch (Exception e) {
            System.out.println("se borro el elemento");
        }
    }
    public void insertToDataBase(Socio ubicacion) {
      
        try {
            DAL_Ubicacion dal_ubicacion = new DAL_Ubicacion(dbCon);
            dal_ubicacion.insertToDataBase(ubicacion);
            System.out.println();  
        } catch (Exception e) {
            System.out.println("se agrego el elemento");
        }
    }
    
   
   
}