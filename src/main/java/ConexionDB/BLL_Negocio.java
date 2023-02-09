/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;
import Clases.Socio;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BLL_Negocio {
    private Connection dbCon;

    public BLL_Negocio(Connection _dbCon){
         this.dbCon = _dbCon;
    }
    
    public List<Socio> queryToDataBase() {
        List<Socio> negocios = null;
        try{
            negocios = new ArrayList<Socio>();
            DAL_Negocio dal_negocio = new DAL_Negocio(dbCon);
            negocios = dal_negocio.queryToDataBase();
        }
        catch(Exception ex){
            System.out.println("Failed to get the collection of records: " + ex.toString());
            return null;
        }
        return negocios;
    }
    
 
    public void insertToDataBase(Socio negocio) {
      
        try {
            DAL_Negocio dal_negocio = new DAL_Negocio(dbCon);
            dal_negocio.insertToDataBase(negocio);
            System.out.println();  
        } catch (Exception e) {
            System.out.println("se agrego el elemento");
        }
    }
    
   
   
}
