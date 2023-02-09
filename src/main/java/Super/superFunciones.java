package Super;

import Clases.Socio;
import java.util.ArrayList;
import java.util.List;
import Interfaces.*;
/**
 Nombre Autores:
 *  Erick Saul Melendez Juarez
 *  Giomara Dublan Arreola
 * 
Fecha última actualización:
    *8/02/2023
 
Nombre de la Clase : superFunciones 
Metodos que se incluyen :
*     crearLista(int n, Objeto s):Metodo para crear una lista y agregar elementos a la DataBase
*     requiere los argumentos:
*           n: numero de elemntos a crear y registrar
*           s: objeto que se va a crear y registrar
*     filtrar(List<Socio> objetos,Predicado ev): Metodo para filtrar objetos de acuerdo a una condicion
*           objetos: lista de objetos inicial
*           ev condicion que se evalua para filtrar
*     mostrarLista(List<Socio> socios,Objeto s) : Metodo para mostrar en pantalla una Lista
*           socios: lista que se va mostrar
*           s: objeto para definir el tipo de lista
* 
 */
public class superFunciones {
     public static List<Socio> crearLista(int n, Objeto s) {
        List<Socio> socios = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            socios.add(s.crearLista());
        }
        return socios;
    }
    
public static List<Socio> filtrar(List<Socio> objetos,Predicado ev){
        List<Socio> socios = new ArrayList<>();
        for(Socio s:objetos){
            if (ev.eval(s.getTipoNegocio())){
                socios.add(s);
            }
        }
        return socios;
    }     
      public static void mostrarLista(List<Socio> socios,Objeto s){
          s.mostrarLista(socios);
      }
      
}
