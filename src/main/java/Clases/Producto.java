package Clases;
import Interfaces.Objeto;
import ConexionDB.*;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Producto implements Objeto{
    private Connection dbCon;
    DbUtil conexion;
    @Override
    public Socio crearLista(){
        dbCon = DbUtil.getInstance().getConnection();
        Scanner in=new Scanner(System.in);
        String nombreNegocio;
        String nombreProducto;
        String costo;
        String idProducto;
        System.out.println("ingresa el nombre del Negocio:");
        nombreNegocio=in.nextLine();
        System.out.println("ingresa el nombre del Producto:");
        nombreProducto=in.nextLine();
        System.out.println("ingresa el costo:");
        costo=in.nextLine();
        System.out.println("ingresa la clave del Producto:");
        idProducto=in.nextLine();
        Socio s=new Socio();
        s.setNombreNegocio(nombreNegocio);
        s.setNombreProducto(nombreProducto);
        s.setCosto(costo);
        s.setIdProducto(idProducto);
        this.addProducto(s);
        return s; 
    }
    @Override
    public void mostrarLista( List<Socio> productos){
        System.out.println("nombreNegocio   nombreProducto   Costo     IdProucto" );
        productos.stream().map(s -> {
            System.out.print(s.getNombreNegocio()+ "         ");
            return s;
        }).map(s -> {
            System.out.print(s.getNombreProducto()+ "       ");
            return s;
        }).map(s -> {
            System.out.print(s.getCosto()+ "    ");
            return s;
        }).map(s -> {
            System.out.print(s.getIdProducto()+ "    ");
            return s;
        }).forEachOrdered(_item -> {
            System.out.println();
        });
    }
    public void addProducto(Socio producto){
        
        BLL_Producto bll_producto = new BLL_Producto(dbCon);
        bll_producto.insertToDataBase(producto);
    }
}
