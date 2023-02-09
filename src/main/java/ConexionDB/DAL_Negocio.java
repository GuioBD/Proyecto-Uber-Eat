/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Clases.Socio;


public class DAL_Negocio {
// #region Attributes

    private Connection dbCon;
    private Socio negocio;

    public DAL_Negocio(Connection _dbCon) {
        this.dbCon = _dbCon;
        
    }

    public List<Socio> queryToDataBase() {
        List<Socio> negocios = new ArrayList<Socio>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = this.dbCon.prepareCall("{CALL negocios_Consultar_SP(?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            cstmt.setString("In_nombreNegocio",null);
            
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
                this.negocio = new Socio();
                this.negocio.setIdUsuario(rs.getString("idUsuario"));
                this.negocio.setNombreNegocio(rs.getString("nombreNegocio"));
                this.negocio.setNumeroUbicaciones(rs.getString("numeroUbicaciones"));
       
                negocios.add(this.negocio);
                
            }
            //connection.close();          
        } catch (SQLException e) {
            System.out.println("Failed to get the collection of records: " + e.toString());
            return null;
        }
        return negocios;
        
        
    }
    
    
	public int insertToDataBase(Socio negocio) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = this.dbCon.prepareCall("{CALL negocios_Insertar_SP(?,?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            cstmt.setString("In_idUsuario",negocio.getIdUsuario());
            cstmt.setString("In_nombreNegocio",negocio.getNombreNegocio());
            cstmt.setString("In_numeroUbicaciones",negocio.getNumeroUbicaciones());
            
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