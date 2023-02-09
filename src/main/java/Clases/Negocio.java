/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Objeto;
import ConexionDB.*;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Negocio implements Objeto{
    private Connection dbCon;
    DbUtil conexion;
    @Override
    public Socio crearLista(){
        dbCon = DbUtil.getInstance().getConnection();
        Scanner in=new Scanner(System.in);
        String idUsuario;
        String nombreNegocio;
        String numeroUbicaciones;
        System.out.println("ingresa el Id del usuario:");
        idUsuario=in.nextLine();
         System.out.println("ingresa el nombre del Negocio:");
        nombreNegocio=in.nextLine();
        System.out.println("ingresa el numero de ubicaciones:");
        numeroUbicaciones=in.nextLine();
        Socio s=new Socio();
        s.setIdUsuario(idUsuario);
        s.setNombreNegocio(nombreNegocio);
        s.setNumeroUbicaciones(numeroUbicaciones);
        this.addNegocio(s);
        return s; 
    }
    
   
    
    @Override
    public void mostrarLista( List<Socio> negocios){
        System.out.println("idUsuario  nombreNegocio  numeroUbicaciones " );
        negocios.stream().map(s -> {
            System.out.print(s.getIdUsuario()+ "    ");
            return s;
        }).map(s -> {
            System.out.print(s.getNombreNegocio()+ "        ");
            return s;
        }).map(s -> {
            System.out.print(s.getNumeroUbicaciones()+ "    ");
            return s;
        }).forEachOrdered(_item -> {
            System.out.println();
        });
    }
    public void addNegocio(Socio negocio){
        
        BLL_Negocio bll_negocio = new BLL_Negocio(dbCon);
        bll_negocio.insertToDataBase(negocio);
    }
}
