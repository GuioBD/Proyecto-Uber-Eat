/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Predicado;

/**
 *
 * @author HP
 */
public class filtro3 implements Predicado{
    @Override
    public boolean eval(String atributo) {
        return "super".equals(atributo);
    }
}

