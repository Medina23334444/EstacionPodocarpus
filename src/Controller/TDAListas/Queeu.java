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
public class Queeu<E> {

    private QueueOperation<E> queue;

    public Queeu(Integer top) {
        this.queue = new QueueOperation<>(top);
    }

    public Integer getTop() {
        return this.queue.getSize();

    }

    public void queue(E data) throws LLenoExpetion {
        queue.queue(data);
    }

    public void clear() {
        this.queue.clear();
    }

    public void print() {

        System.out.println("Pila");
        System.out.println(queue.print());
        System.out.println("!!!!!!!!!!!!!");
    }

    public E dequee() throws VacioExpection {
        return queue.dequeue();
    }

    public static void main(String[] args) {
        Queeu<Integer> pila = new Queeu<>(10);
        try {
            pila.queue(1);
            pila.queue(12);
            pila.queue(10);
            pila.print();
            pila.print();
            pila.print();
        } catch (Exception ignored) {
        }
    }
}
