package Clases;

import java.util.Scanner;
import Interfaces.Objeto;
import ConexionDB.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 Nombre Autores:
 *  Erick Saul Melendez Juarez
 * Giomara Dublan Arreola
 * 
Fecha última actualización:
    *8/02/2023

Nombre de la Clase : Usuario
Metodos que se incluyen :
*  Socio crearLista(): Metodo para crear una lista y solicitar datos
*  mostrarLista( List<Socio> usuarios) : Metodo para mostrar una lista
*       requiere los argumentos:
*       usuarios: lista de Objetos
*  long contar() : Metodo para encontrar la longitud de la lista de Objetos
*  addUsuario(Socio usuario) : Metodo para registrar un Usuario
*       requiere los argumentos:
*       usuario:Usuario a registrar
* 
* 
*  
 */
public class Usuario implements Objeto{
    private Connection dbCon;
    DbUtil conexion;
    public List<Socio> usuarios = null;
    public int n=5;

    @Override
    public Socio crearLista() {
        dbCon = DbUtil.getInstance().getConnection();
        //String idUsuario;
        String nombre;
        String funcion;
        //String password;
        String tipoNegocio;
        String codigoPostal;
        String telefono;
        Scanner in=new Scanner(System.in);
        System.out.println("ingresa el nombre del usuario:");
        nombre=in.nextLine();
        System.out.println("ingresa que funcion que empleas:");
        funcion=in.nextLine();
        System.out.println("ingresa el tipo de Negocio:");
        tipoNegocio=in.nextLine();
        System.out.println("ingresa el codigo Postal:");
        codigoPostal=in.nextLine();
        System.out.println("ingresa un numero de telefono:");
        telefono=in.nextLine();
        Socio s=new Socio();
        s.setNombre(nombre);
        s.setFuncion(funcion);
        s.setTipoNegocio(tipoNegocio);
        s.setCodigoPostal(codigoPostal);
        s.setTelefono(telefono);
        s.setPassword(Generator.generatePassword());
        s.setIdUsuario(Generator.generateId(tipoNegocio, codigoPostal,telefono,this.contar()+1));
        this.addUsuario(s);
        return s;
    }
    
     
    
    @Override
    public void mostrarLista( List<Socio> usuarios){
        System.out.println("idUsuario       nombre          funcion   tipoNegocio  codigoPostal    telefono   contraseña" );
        usuarios.stream().map(s -> {
            System.out.print(s.getIdUsuario() + "    ");
            return s;
        }).map(s -> {
            System.out.print(s.getNombre() + "    ");
            return s;
        }).map(s -> {
            System.out.print(s.getFuncion() + "    ");
            return s;
        }).map(s -> {
            System.out.print(s.getTipoNegocio() + "    ");
            return s;
        }).map(s -> {
            System.out.print(s.getCodigoPostal() + "    ");
            return s;
        }).map(s -> {
            System.out.print(s.getTelefono() + "    ");
            return s;
        }).map(s -> {
            System.out.print(s.getPassword() + "    ");
            return s;            
        }).forEachOrdered(_item -> {
            System.out.println();
        });
        
    }
    
    public long contar(){
        long x=this.callALLUsuarios().stream().count();
        return x;
    }
    
    public void addUsuario(Socio usuario){
        
        BLL_Usuario bll_usuario = new BLL_Usuario(dbCon);
        bll_usuario.insertToDataBase(usuario);
    }
    public List<Socio> callALLUsuarios() {
       usuarios = new ArrayList<>();
       BLL_Usuario bll_usuario = new BLL_Usuario(dbCon);
       usuarios = bll_usuario.queryToDataBase();
       // showElementsByList();
       return usuarios;
       
    }
    
}
