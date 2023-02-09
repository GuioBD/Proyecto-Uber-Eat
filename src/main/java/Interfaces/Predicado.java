/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 Nombre Autores:
 *  Erick Saul Melendez Juarez
 *  Giomara Dublan Arreola
 * 
Fecha última actualización:
    *8/02/2023

Nombre de la interfaz : Predicado 
Metodos que se incluyen : 
*   eval(String atributo) : metodo abstracto que avalua si dos cadenas son iguales
*       requiere los argumentos:
*       atributo: cadena que va a evaluar con otra 
*       
 */
public interface Predicado {
    boolean eval(String atributo);
}
