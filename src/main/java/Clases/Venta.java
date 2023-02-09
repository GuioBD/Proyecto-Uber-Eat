package Clases;
import Interfaces.Objeto;
import ConexionDB.*;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Venta implements Objeto{
    
    private Connection dbCon;
    DbUtil conexion;
    @Override
    public Socio crearLista(){
        dbCon = DbUtil.getInstance().getConnection();
        Scanner in=new Scanner(System.in);
        String idProducto;
        String cantidad;
        String tarifa;
        String descuento;
        System.out.println("ingresa la clave del Producto:");
        idProducto=in.nextLine();
        System.out.println("ingresa la cantidad que se vendio:");
        cantidad=in.nextLine();
        System.out.println("ingresa la tarifa :");
        tarifa=in.nextLine();
        if(null == tarifa){
            descuento="ninguna";
        }else switch (tarifa) {
            case "Tarifa 1":
            case "tarifa 1":
            case "tarifa1":    
            case "Tarifa1":
                descuento="30%";
                break;
            case "tarifa 2":
            case "Tarifa 2":
            case "tarifa2":
            case "Tarifa2":
                descuento="15%";
                break;
            case "tarifa 3":
            case "Tarifa 3":
            case "tarifa3":
            case "Tarifa3":
                descuento="15%";
                break;
            default:
                descuento="ninguna";
                break;
        }
        Socio s=new Socio();
        s.setIdProducto(idProducto);
        s.setCantidad(cantidad);
        s.setTarifa(tarifa);
        s.setDescuento(descuento);
        this.addVenta(s);
        return s; 
    }
    @Override
    public void mostrarLista( List<Socio> ventas){
        System.out.println("cantidad   idProducto   tarifa     descuento" );
        ventas.stream().map(s -> {
            System.out.print("    " + s.getCantidad()+ "      ");
            return s;
        }).map(s -> {
            System.out.print(s.getIdProducto()+ "      ");
            return s;
        }).map(s -> {
            System.out.print(s.getTarifa()+ "     ");
            return s;
        }).map(s -> {
            System.out.print(s.getDescuento()+ "    ");
            return s;
        }).forEachOrdered(_item -> {
            System.out.println();
        });
    }
    
    public void addVenta(Socio venta){
        
        BLL_Venta bll_venta = new BLL_Venta(dbCon);
        bll_venta.insertToDataBase(venta);
    }
}
