/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class Estacion {

    private Integer id;
    private String nombre;
    private String decripcion;
    private String foto1;
    private String foto2;
    private String foto3;
    private String foto4;
    private double longitud;
    private double lalitud;
    private String acceso;
    private Integer Capacidad;


    public Integer getId() {
        return id;
    }

   
    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getNombre() {
        return nombre;
    }

  
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    public String getDecripcion() {
        return decripcion;
    }

    
    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }

    
    public String getFoto1() {
        return foto1;
    }

    
    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

   
    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

   
    public String getFoto3() {
        return foto3;
    }

    
    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

  
    public String getFoto4() {
        return foto4;
    }

    
    public void setFoto4(String foto4) {
        this.foto4 = foto4;
    }

    
    public double getLongitud() {
        return longitud;
    }

   
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    
    public double getLalitud() {
        return lalitud;
    }


    public void setLalitud(double lalitud) {
        this.lalitud = lalitud;
    }


    public String getAcceso() {
        return acceso;
    }


    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

 
    public Integer getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(Integer Capacidad) {
        this.Capacidad = Capacidad;
    }

    @Override
    public String toString() {
        return nombre;
                
                //super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    

}
