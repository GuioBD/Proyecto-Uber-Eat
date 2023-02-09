package Interfaces;

import Clases.Socio;
import java.util.List;
/**
 Nombre Autores:
 *  Erick Saul Melendez Juarez
 *  Giomara Dublan Arreola
 * 
Fecha última actualización:
    *8/02/2023

Nombre de la interfaz : Objeto 
Metodos que se incluyen :
*   cerarLista: metodo abstracto que crea una lista
*   mostrarLista(List<Socio> socios) : metodo abstracto que muestra una lista
*       requiere los argumentos:
*       socios: lista de objetos 
*       
 */
public interface Objeto {
    Socio crearLista();
    void mostrarLista(List<Socio> socios);
    
}
