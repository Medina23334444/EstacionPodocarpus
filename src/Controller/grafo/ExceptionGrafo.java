/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package controller.grafo;

/**
 *
 * @author Usuario
 */
public class ExceptionGrafo extends Exception {

    /**
     * Creates a new instance of <code>ExceptionGrafo</code> without detail
     * message.
     */
    public ExceptionGrafo() {
        super("Fuera de rango");
    }

 
    public ExceptionGrafo(String msg) {
        super(msg);
    }
}
