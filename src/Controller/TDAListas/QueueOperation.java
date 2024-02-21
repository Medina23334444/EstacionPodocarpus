/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.TDAListas;

import Controller.TDAListas.Expection.LLenoExpetion;
import Controller.TDAListas.Expection.VacioExpection;


/**
 *
 * @author Usuario
 */
public class QueueOperation<E> extends LinkedList<E> {

    private Integer top;

    public QueueOperation(Integer top) {
        this.top = top;
    }

    public Boolean varify() {
        return getSize() <= getTop();

    }

    public void queue(E data) throws LLenoExpetion {
        if (varify()) {
            addLast(data);
        } else {
            throw new LLenoExpetion("Cola llena");

        }
    }

    public E dequeue() throws VacioExpection {
        if (varify()) {
            throw new VacioExpection("Cola Vacia");
        } else {
            return deleteFirst();

        }
    }

    /**
     * @return the top
     */
    public Integer getTop() {
        return top;
    }

    /**
     * @param top the top to set
     */
    public void setTop(Integer top) {
        this.top = top;
    }

}
