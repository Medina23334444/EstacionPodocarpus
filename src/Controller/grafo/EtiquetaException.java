/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package controller.grafo;

/**
 *
 * @author Usuario
 */
public class EtiquetaException extends Exception {

    /**
     * Creates a new instance of <code>ExceptionGrafo</code> without detail
     * message.
     */
    public EtiquetaException() {
        super("El grafo no esta etiquetado");
    }

 
    public EtiquetaException(String msg) {
        super(msg);
    }
}
