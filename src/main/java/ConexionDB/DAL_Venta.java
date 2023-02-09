package ConexionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Clases.Socio;


public class DAL_Venta{
// #region Attributes

    private Connection dbCon;
    private Socio venta;

    public DAL_Venta(Connection _dbCon) {
        this.dbCon = _dbCon;
        
    }

    public List<Socio> queryToDataBase() {
        List<Socio> ventas = new ArrayList<Socio>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = this.dbCon.prepareCall("{CALL ventas_Consultar_SP()}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            
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
                this.venta = new Socio();
                this.venta.setCantidad(rs.getString("cantidad"));
                this.venta.setIdProducto(rs.getString("idProducto"));
                this.venta.setTarifa(rs.getString("tarifa"));
                this.venta.setDescuento(rs.getString("descuento"));
       
                ventas.add(this.venta);
                
            }
            //connection.close();          
        } catch (SQLException e) {
            System.out.println("Failed to get the collection of records: " + e.toString());
            return null;
        }
        return ventas;
        
        
    }
    
    
	public int insertToDataBase(Socio venta) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = this.dbCon.prepareCall("{CALL ventas_Insertar_SP(?,?,?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            cstmt.setString("In_cantidad",venta.getCantidad());
            cstmt.setString("In_idProducto",venta.getIdProducto());
            cstmt.setString("In_tarifa",venta.getTarifa());
            cstmt.setString("In_descuento",venta.getDescuento());
            
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

	
}