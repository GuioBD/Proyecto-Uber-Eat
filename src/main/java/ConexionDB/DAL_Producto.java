package ConexionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Clases.Socio;


public class DAL_Producto {
// #region Attributes

    private Connection dbCon;
    private Socio producto;

    public DAL_Producto(Connection _dbCon) {
        this.dbCon = _dbCon;
        
    }

    public List<Socio> queryToDataBase() {
        List<Socio> productos = new ArrayList<Socio>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = this.dbCon.prepareCall("{CALL productos_Consultar_SP(?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            cstmt.setString("In_idProducto",null);
            
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
                this.producto = new Socio();
                this.producto.setIdProducto(rs.getString("idProducto"));
                this.producto.setNombreNegocio(rs.getString("NombreNegocio"));
                this.producto.setNombreProducto(rs.getString("nombreProducto"));
                this.producto.setCosto(rs.getString("costo"));
       
                productos.add(this.producto);
                
            }
            //connection.close();          
        } catch (SQLException e) {
            System.out.println("Failed to get the collection of records: " + e.toString());
            return null;
        }
        return productos;
        
        
    }
    
    public int insertToDataBase(Socio producto) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = this.dbCon.prepareCall("{CALL productos_Insertar_SP(?,?,?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            cstmt.setString("In_idProducto",producto.getIdProducto());
            cstmt.setString("In_NombreNegocio",producto.getNombreNegocio());
            cstmt.setString("In_nombreProducto",producto.getNombreProducto());
            cstmt.setString("In_costo",producto.getCosto());

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
        
    public int deleteToDataBase(String idProducto) {
            CallableStatement cstmt = null;
            ResultSet rs = null;
            try {
                cstmt = this.dbCon.prepareCall("{CALL productos_Delete_SP(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                cstmt.setString("In_idProducto",idProducto);
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