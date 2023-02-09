package ConexionDB;
import Clases.Socio;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BLL_Usuario {
    private Connection dbCon;
    
    public BLL_Usuario(Connection _dbCon){
         this.dbCon = _dbCon;
    }
    
    public List<Socio> queryToDataBase() {
        List<Socio> usuarios = null;
        try{
            usuarios = new ArrayList<Socio>();
            DAL_Usuario dal_usuario = new DAL_Usuario(dbCon);
            usuarios= dal_usuario.queryToDataBase();
        }
        catch(Exception ex){
            System.out.println("Failed to get the collection of records: " + ex.toString());
            return null;
        }
        return usuarios;
    }
    
    public void insertToDataBase(Socio usuario) {
      
        try {
            DAL_Usuario dal_usuario = new DAL_Usuario(dbCon);
            dal_usuario.insertToDataBase(usuario);
            System.out.println();  
        } catch (Exception e) {
            System.out.println("se agrego el elemento");
        }
    }
    
   
   
}