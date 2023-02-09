package ConexionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Clases.Socio;


public class DAL_Usuario {
// #region Attributes

    private Connection dbCon;
    private Socio usuario;

    public DAL_Usuario(Connection _dbCon) {
        this.dbCon = _dbCon;
        
    }

    public List<Socio> queryToDataBase() {
        List<Socio> usuarios = new ArrayList<Socio>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = this.dbCon.prepareCall("{CALL usuarios_Consultar_SP(?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            cstmt.setString("In_idUsuario",null);
            
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
                this.usuario = new Socio();
                this.usuario.setIdUsuario(rs.getString("idUsuario"));
                this.usuario.setNombre(rs.getString("nombre"));
                this.usuario.setFuncion(rs.getString("funcion"));
                this.usuario.setPassword(rs.getString("contraseña"));
                this.usuario.setTipoNegocio(rs.getString("tipoNegocio"));
                this.usuario.setCodigoPostal(rs.getString("codigoPostal"));
                this.usuario.setTelefono(rs.getString("telefono"));

                usuarios.add(this.usuario);
                
            }
            //connection.close();          
        } catch (SQLException e) {
            System.out.println("Failed to get the collection of records: " + e.toString());
            return null;
        }
        return usuarios;
        
        
    }
    
    
	public int insertToDataBase(Socio usuario) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = this.dbCon.prepareCall("{CALL usuarios_Insertar_SP(?,?,?,?,?,?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            cstmt.setString("In_idUsuario",usuario.getIdUsuario());
            cstmt.setString("In_nombre",usuario.getNombre());
            cstmt.setString("In_funcion",usuario.getFuncion());
            cstmt.setString("In_contraseña",usuario.getPassword());
            cstmt.setString("In_tipoNegocio",usuario.getTipoNegocio());
            cstmt.setString("In_codigoPostal",usuario.getCodigoPostal());
            cstmt.setString("In_telefono",usuario.getTelefono());
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