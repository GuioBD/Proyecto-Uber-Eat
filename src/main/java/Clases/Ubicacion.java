package Clases;
import Interfaces.Objeto;
import ConexionDB.*;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Ubicacion implements Objeto{
    private Connection dbCon;
    DbUtil conexion;
    @Override
    public Socio crearLista(){
        dbCon = DbUtil.getInstance().getConnection();
        Scanner in=new Scanner(System.in);
        String nombreNegocio;
        String idUbicacion;
        String direccion;
        String ciudad;
        String piso;
        System.out.println("ingresa el nombre del Negocio:");
        nombreNegocio=in.nextLine();
        System.out.println("ingresa el Id de la ubicacion:");
        idUbicacion=in.nextLine();
        System.out.println("ingresa la direccion:");
        direccion=in.nextLine();
        System.out.println("ingresa la ciudad:");
        ciudad=in.nextLine();
        System.out.println("ingresa el numero de piso o oficina:");
        piso=in.nextLine();
        Socio s=new Socio();
        s.setNombreNegocio(nombreNegocio);
        s.setIdUbicacion(idUbicacion);
        s.setDireccion(direccion);
        s.setCiudad(ciudad);
        s.setPiso(piso);
        this.addUbicacion(s);
        return s; 
    }
    @Override
    public void mostrarLista( List<Socio> ubicaciones){
        System.out.println("idUbicacion   nombreNegocio       direccion          ciudad      piso" );
        ubicaciones.stream().map(s -> {
            System.out.print(s.getIdUbicacion()+ "          ");
            return s;
        }).map(s -> {
            System.out.print(s.getNombreNegocio()+ "        ");
            return s;
        }).map(s -> {
            System.out.print(s.getDireccion()+ "    ");
            return s;
        }).map(s -> {
            System.out.print(s.getCiudad()+ "    ");
            return s;
        }).map(s -> {
            System.out.print(s.getPiso()+ "    ");
            return s;
        }).forEachOrdered(_item -> {
            System.out.println();
        });
    }
    
    public void addUbicacion(Socio ubicacion){
        
        BLL_Ubicacion bll_ubicacion = new BLL_Ubicacion(dbCon);
        bll_ubicacion.insertToDataBase(ubicacion);
    }
}
