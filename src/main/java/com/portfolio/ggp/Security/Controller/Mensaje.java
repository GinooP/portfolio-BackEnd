/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.ggp.Security.Controller;

/**
 *
 * @author walte
 */
public class Mensaje {
    private String mensaje;
    
    //Constructor
    
    public Mensaje(){
    }

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    //Getter and Setter

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
