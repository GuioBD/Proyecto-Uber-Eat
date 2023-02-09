package proyectoPF;

import ConexionDB.*;
import Clases.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import Super.superFunciones;
import java.util.Scanner;
import java.util.stream.Stream;
/**
 Nombre Autores:
 *  Erick Saul Melendez Juarez
 *  Giomara Dublan Arreola
 * 
Fecha última actualización:
    *8/02/2023
 
Nombre de la Clase : Menu 
Metodos que se incluyen :
*     initialize():Metodo para hacer el llamado de todas las funciones y un menu de opciones
*     callALLUsuarios() : Metodo para hacer el llamado dela lista Usuarios desde la DataBase
*     callALLNegocios() : Metodo para hacer el llamado dela lista Negocios desde la DataBase
*     callALLUbicaciones() : Metodo para hacer el llamado dela lista Ubicaiones desde la DataBase
*     callALLProductos() : Metodo para hacer el llamado dela lista Productos desde la DataBase
*     callALLVentas() : Metodo para hacer el llamado dela lista Ventas desde la DataBase
*     removeAProducto(String idProducto) : Metodo para eliminar un Producto en la DataBase
*       requiere los argumentos:
*           idProducto: identificador del Producto
*     removeAUbicacion(String idUbicaion) : Metodo para eliminar una Ubicacion en la DataBase
*       requiere los argumentos:
*           idUbicacion: identificador de la Ubicacion
 */
public class Menu {
    private Connection dbCon;
    DbUtil conexion;
    public List<Socio> usuarios = null;
    public List<Socio> negocios = null;
    public List<Socio> ubicaciones = null;
    public List<Socio> productos = null;
    public List<Socio> ventas = null;

    //public Practica a1=null;
    public Menu(){}
    
    public void initialize(){
        dbCon = DbUtil.getInstance().getConnection();
        Scanner in=new Scanner(System.in);
        int n=0;
        int op1,op2;
        String idU;
        while(n==0){
            System.out.println("----Listas---");
            System.out.println("1)Usuarios");
            System.out.println("2)Negocios");
            System.out.println("3)Ubicaciones");
            System.out.println("4)Productos");
            System.out.println("5)Ventas");
            System.out.print("A cual lista deseas acceder:-->>>");
            op1=in.nextInt();
            switch(op1){
                case 1 : System.out.println("***Accediste a la lista de Usuarios***");
                         System.out.println("1)Cosultar Usuarios");
                         System.out.println("2)Agregar Usuarios");
                         System.out.println("3)Filtrar Usuarios de acuerdo al tipo de Negocio");
                         System.out.println("4)Filtrar Usuarios de acuerdo a su funcion");
                         System.out.println("5)Ordenar los usuarios de acuerdo al Nombre");
                         System.out.println("6)Mostrar en  Mayusculas los Nombres de los usuarios ");
                         System.out.println("7)Mostrar los primeros n usuarios");
                         System.out.println("8)Mostrar la cantidad de usuarios registrados");
                         System.out.print("Que deseas hacer:");
                         op2=in.nextInt();
                         switch(op2){
                             case 1: superFunciones.mostrarLista(this.callALLUsuarios(),new Usuario());
                                     break;
                             case 2: System.out.print("Cuantos usuarios vas a registrar:");
                                     int nUsuarios=in.nextInt();
                                     superFunciones.crearLista(nUsuarios, new Usuario());
                                     break;
                             case 3: System.out.println("Que ususarios deseas consultar:");
                                     System.out.println("1)los que tienen restaurante");
                                     System.out.println("2)los que tienen panaderia");
                                     System.out.println("3)los que tienen super");
                                     System.out.println("4)los que tienen licoreria");
                                     int filtro=in.nextInt();
                                     if(filtro==1){
                                         List<Socio> lista=superFunciones.filtrar(this.callALLUsuarios(),new filtro1());
                                         superFunciones.mostrarLista(lista,new Usuario());
                                     }else if(filtro==2){
                                         List<Socio> lista=superFunciones.filtrar(this.callALLUsuarios(),new filtro2());
                                         superFunciones.mostrarLista(lista,new Usuario());
                                     }else if(filtro==3){
                                         List<Socio> lista=superFunciones.filtrar(this.callALLUsuarios(),new filtro3());
                                         superFunciones.mostrarLista(lista,new Usuario());
                                     }else if(filtro==4){
                                         List<Socio> lista=superFunciones.filtrar(this.callALLUsuarios(),new filtro4());
                                         superFunciones.mostrarLista(lista,new Usuario());
                                     }else{
                                         System.out.println("revisa tu entrada");
                                     }
                                     break;
                             case 4: System.out.print("Ingresa la funcion:");
                                     String funcion=in.next();
                                 this.callALLUsuarios().stream()
                                    .filter(p->p.getFuncion().equals(funcion))
                                     .forEach(p -> System.out.println(p.getIdUsuario()+ " - " + p.getNombre() + " - " 
                                                + p.getFuncion() + " - " +p.getTipoNegocio()+ " - " +p.getCodigoPostal()+ " - " 
                                                +p.getTelefono()+ " - " + p.getPassword()  ));
                                     break;
                             case 5: this.callALLUsuarios().stream()
                                     .sorted((p1,p2) ->p1.getNombre().compareTo(p2.getNombre()))
                                     .forEach(p -> System.out.println(p.getNombre()));
                                     break;
                             case 6: this.callALLUsuarios().stream()
                                        .map(p -> {
                                                p.setNombre(p.getNombre().toUpperCase());
                                                return p;
                                                })
                                        .forEach(p -> System.out.println(p.getNombre()));
                                      break;
                             case 7:  System.out.print("Cuantos usuarios deseas consultar:");
                                      int nUsers=in.nextInt();
                                      Stream<Socio> stream = this.callALLUsuarios().stream();
                                      stream.limit(nUsers)
                                        .forEach(p -> System.out.println(p.getIdUsuario()+ " - " + p.getNombre() + " - " 
                                                + p.getFuncion() + " - " +p.getTipoNegocio()+ " - " +p.getCodigoPostal()+ " - " 
                                                +p.getTelefono()+ " - " + p.getPassword()   ));  
                                      break;
                             case 8:  System.out.println("Hay "+this.callALLUsuarios().stream().count() +"  usuarios  registrados");       
                                      break;
                                    
                         }
                         break;
                case 2: System.out.println("***Accediste a la lista de Negocios***");
                        System.out.println("1)Cosultar Negocios");
                        System.out.println("2)Agregar Negocios");
                        System.out.println("3)Filtrar Negocios de acuerdo al numero de Ubicaciones");
                        System.out.println("4)Ordenar los Negocios de acuerdo al numero de Ubicaciones");
                        System.out.println("5)Mostrar en  Mayusculas los Nombres de los Negocios ");
                        System.out.println("6)Mostrar los primeros n Negocios");
                        System.out.println("7)Mostrar la cantidad de Negocios registrados");
                        System.out.print("Que deseas hacer:");
                        op2=in.nextInt();    
                        switch(op2){
                             case 1: superFunciones.mostrarLista(this.callALLNegocios(),new Negocio());
                                     break;
                             case 2: System.out.print("Cuantos usuarios vas a registrar:");
                                     int nNegocios=in.nextInt();
                                     superFunciones.crearLista(nNegocios, new Negocio());
                                     break;
                             case 3: System.out.print("Ingresa el numero de ubicaciones que debe tener el negocio:");
                                     int nUbicaciones=in.nextInt();
                                     this.callALLNegocios().stream()
                                        .filter(p->p.getNumeroUbicaciones().equals(Integer.toString(nUbicaciones)))
                                        .forEach(p -> System.out.println(p.getIdUsuario() + " - " +p.getNombreNegocio()+ " - " +p.getNumeroUbicaciones()));
                                     break;
                             case 4: this.callALLNegocios().stream()
                                        .sorted((p1,p2) -> Integer.parseInt(p1.getNumeroUbicaciones()) - Integer.parseInt(p2.getNumeroUbicaciones()))      
                                        .forEach(p -> System.out.println(p.getIdUsuario() + " - " +p.getNombreNegocio()+ " - " +p.getNumeroUbicaciones()));
                                     break;
                             case 5: this.callALLNegocios().stream()
                                        .map(p -> {
                                                p.setNombreNegocio(p.getNombreNegocio().toUpperCase());
                                                return p;
                                                })
                                        .forEach(p -> System.out.println(p.getNombreNegocio()));
                                      break;
                             case 6:  System.out.print("Cuantos Negocios deseas consultar:");
                                      int nNeg=in.nextInt();
                                      Stream<Socio> stream = this.callALLNegocios().stream();
                                      stream.limit(nNeg)
                                        .forEach(p -> System.out.println(p.getIdUsuario() + " - " +p.getNombreNegocio()+ " - " +p.getNumeroUbicaciones()));  
                                      break;
                             case 7: System.out.println("Hay "+this.callALLNegocios().stream().count() +" negocios  registrados");        
                                      break;
                        }
                        break;
                case 3: System.out.println("***Accediste a la lista de Ubicaciones***");
                        System.out.println("1)Cosultar Ubicaciones");
                        System.out.println("2)Agregar Ubicacion");
                        System.out.println("3)Eliminar Ubicacion");
                        System.out.println("4)Mostrar la cantidad de Ubicaciones registradas");
                        System.out.print("Que deseas hacer:");
                        op2=in.nextInt();
                        switch(op2){
                             case 1: superFunciones.mostrarLista(this.callALLUbicaciones(),new Ubicacion());
                                     break;
                             case 2: System.out.print("Cuantos usuarios vas a registrar:");
                                     int nUbicaciones=in.nextInt();
                                     superFunciones.crearLista(nUbicaciones, new Ubicacion());
                                     break;
                             case 3: System.out.print("Que ubicacion deseas eliminar: "); 
                                     idU=in.next();
                                     this.removeAUbicacion(idU);
                                     break;
                             case 4: System.out.println("Hay "+this.callALLUbicaciones().stream().count() +" Ubicaciones registradas");        
                                     break;
                        }
                        break;
                 case 4: System.out.println("***Accediste a la lista de Productos***");
                        System.out.println("1)Cosultar Productos");
                        System.out.println("2)Agregar Productos");
                        System.out.println("3)Eliminar Producto");
                        System.out.println("4)Mostrar la cantidad de Poductos registrados");
                        System.out.print("Que deseas hacer:");
                        op2=in.nextInt();
                        switch(op2){
                             case 1: superFunciones.mostrarLista(this.callALLProductos(),new Producto());
                                     break;
                             case 2: System.out.print("Cuantos Productos vas a registrar:");
                                     int nProductos=in.nextInt();
                                     superFunciones.crearLista(nProductos, new Producto());
                                     break;
                             case 3: System.out.print("ingresa la clave del Producto que  deseas eliminar: "); 
                                     String idP=in.next();
                                     this.removeAProducto(idP);
                                     break;   
                             case 4: System.out.println("Hay "+this.callALLProductos().stream().count() +" Productos  registrados");        
                                     break;
                        }
                        break;
                 case 5: System.out.println("***Accediste a la lista de Ventas***");
                        System.out.println("1)Cosultar Ventas");
                        System.out.println("2)Agregar Ventas");
                        System.out.println("3)Mostrar la cantidad de Ventas registradas");
                        System.out.print("Que deseas hacer:");
                        op2=in.nextInt();
                        switch(op2){
                             case 1: superFunciones.mostrarLista(this.callALLVentas(),new Venta());
                                     break;
                             case 2: System.out.print("Cuantas ventas vas a registrar:");
                                     int nVentas=in.nextInt();
                                     superFunciones.crearLista(nVentas, new Venta());
                                     break;
                             case 3: System.out.println("Hay "+this.callALLVentas().stream().count() +" ventas  registradas");   
                                     break;
                        }       
                }
            }
      
        
      
    }
  public void filtrar(){
       this.callALLUsuarios().stream()
        .filter(p->p.getFuncion().equals("cocinero"))
      .forEach(p -> System.out.println(p.getNombre() + " - " + p.getFuncion()));
      
  }
    
  public void ordenar(){
      this.callALLNegocios().stream()
        //.sorted((p1,p2) ->p1.getNombre().compareTo(p2.getNombre()))
        .sorted((p1,p2) -> Integer.parseInt(p1.getNumeroUbicaciones()) - Integer.parseInt(p2.getNumeroUbicaciones()))      
        .forEach(p -> System.out.println(p.getNombreNegocio() + " - " + p.getNumeroUbicaciones()));
  }  
  public void convMayus(){
      this.callALLUsuarios().stream()
        .map(p -> {
        p.setNombre(p.getNombre().toUpperCase());
        //p.setIdUsuario(p.getIdUsuario().toUpperCase());
        return p;
      })
      .forEach(p -> System.out.println(p.getNombre() + " - " + p.getIdUsuario()));
   }
    
   public void limitar(){
       Stream<Socio> stream = this.callALLUsuarios().stream();
       stream.limit(3)
      .forEach(p -> System.out.println(p.getNombre() + " - " + p.getIdUsuario()));
   }
   
   public long contar(){
        long x=this.callALLUsuarios().stream().count();
        return x;
    }
   
    
    
    public List<Socio> callALLUsuarios() {
       usuarios = new ArrayList<>();
       BLL_Usuario bll_usuario = new BLL_Usuario(dbCon);
       usuarios = bll_usuario.queryToDataBase();
       // showElementsByList();
       return usuarios;
       
    }
    public List<Socio> callALLNegocios() {
       negocios = new ArrayList<>();
       BLL_Negocio bll_negocio = new BLL_Negocio(dbCon);
       negocios = bll_negocio.queryToDataBase();
       // showElementsByList();
       return negocios;
       
    }
    public List<Socio> callALLUbicaciones() {
       ubicaciones = new ArrayList<>();
       BLL_Ubicacion bll_ubicacion = new BLL_Ubicacion(dbCon);
       ubicaciones = bll_ubicacion.queryToDataBase();
       // showElementsByList();
       return ubicaciones;
       
    }
    
    public List<Socio> callALLProductos() {
       productos = new ArrayList<>();
       BLL_Producto bll_producto = new BLL_Producto(dbCon);
       productos = bll_producto.queryToDataBase();
       // showElementsByList();
       return productos;
       
    }
    public List<Socio> callALLVentas() {
       ventas = new ArrayList<>();
       BLL_Venta bll_venta = new BLL_Venta(dbCon);
       ventas = bll_venta.queryToDataBase();
       // showElementsByList();
       return ventas;
       
    }
    
    public void removeAProducto(String idProducto){
        BLL_Producto bll_producto = new BLL_Producto(dbCon);
        bll_producto.deleteToDataBase(idProducto);
    }
   public void removeAUbicacion(String idUbicacion){
        BLL_Ubicacion bll_ubicacion = new BLL_Ubicacion(dbCon);
        bll_ubicacion.deleteToDataBase(idUbicacion);
    }
    
   
    
}