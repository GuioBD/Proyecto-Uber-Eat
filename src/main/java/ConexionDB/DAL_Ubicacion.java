package ConexionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Clases.Socio;


public class DAL_Ubicacion {
// #region Attributes

    private Connection dbCon;
    private Socio ubicacion;

    public DAL_Ubicacion(Connection _dbCon) {
        this.dbCon = _dbCon;
        
    }

    public List<Socio> queryToDataBase() {
        List<Socio> ubicaciones = new ArrayList<Socio>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = this.dbCon.prepareCall("{CALL ubicaciones_Consultar_SP(?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            cstmt.setString("In_idUbicacion",null);
            
            boolean results = cstmt.execute();
            int rowsAffected = 0;
// Protects against lack of SET NOCOUNT in stored prodedure
            while (results || rowsAffected != -1) {
                if (results) {
                    rs = cstmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cstmt.getUpdateCount();
                }
                results = cstmt.getMoreResults();
            }
            while (rs.next()) {
                this.ubicacion = new Socio();
                this.ubicacion.setIdUbicacion(rs.getString("idUbicacion"));
                this.ubicacion.setNombreNegocio(rs.getString("nombreNegocio"));
                this.ubicacion.setDireccion(rs.getString("direccion"));
                this.ubicacion.setCiudad(rs.getString("ciudad"));
                this.ubicacion.setPiso(rs.getString("piso"));
              
                ubicaciones.add(this.ubicacion);
                
            }
            //connection.close();          
        } catch (SQLException e) {
            System.out.println("Failed to get the collection of records: " + e.toString());
            return null;
        }
        return ubicaciones;
        
        
    }
    
    public int insertToDataBase(Socio ubicacion) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = this.dbCon.prepareCall("{CALL ubicaciones_Insertar_SP(?,?,?,?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            cstmt.setString("In_idUbicacion",ubicacion.getIdUbicacion());
            cstmt.setString("In_nombreNegocio",ubicacion.getNombreNegocio());
            cstmt.setString("In_direccion",ubicacion.getDireccion());
            cstmt.setString("In_ciudad",ubicacion.getCiudad());
            cstmt.setString("In_piso",ubicacion.getPiso());
            

            boolean results = cstmt.execute();
            int rowsAffected = 0;
            // Protects against lack of SET NOCOUNT in stored prodedure
            while (results || rowsAffected != -1) {
                if (results) {
                    rs = cstmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cstmt.getUpdateCount();
                }
                results = cstmt.getMoreResults();
            }
            int aux = 0;
            if (rs.next()) {
                aux = rs.getInt(1);
            }
            return aux;

        } catch (SQLException e) {
            System.out.println("Failed to get the collection of records: " + e.toString());
            //return 0;
        }
        return 0;
    }
        
    public int deleteToDataBase(String idUbicacion) {
            CallableStatement cstmt = null;
            ResultSet rs = null;
            try {
                cstmt = this.dbCon.prepareCall("{CALL ubicaciones_Delete_SP(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                cstmt.setString("In_idUbicacion",idUbicacion);
                boolean results = cstmt.execute();
                int rowsAffected = 0;
                // Protects against lack of SET NOCOUNT in stored prodedure
                while (results || rowsAffected != -1) {
                    if (results) {
                        rs = cstmt.getResultSet();
                        break;
                    } else {
                        rowsAffected = cstmt.getUpdateCount();
                    }
                    results = cstmt.getMoreResults();
                }
                int aux = 0;
                if (rs.next()) {
                    aux = rs.getInt(1);
                }
                System.out.println("Result: " + aux);
                return aux;

            } catch (SQLException e) {
                System.out.println("Failed to get the collection of records: " + e.toString());
                //return 0;
            }
            return 0;
    }

	
}