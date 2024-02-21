/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.grafo;

import Controller.TDAListas.LinkedList;
import java.lang.reflect.Array;
import java.util.HashMap;

/**
 *
 * @author Usuario
 */
public class GrafoEtiquetado<E> extends GrafoDirigido {

    private E etiqueta[];

    protected HashMap<E, Integer> dicvertices;
    private Class<E> clazz;

    public GrafoEtiquetado(Integer nroVertices, Class<E> clazz) {
        super(nroVertices);
        this.clazz = clazz;
        etiqueta = (E[]) Array.newInstance(clazz, nroVertices + 1);
        dicvertices = new HashMap<>(nroVertices);
    }

    public String getEtiquetaAlineada() {
        StringBuilder etiquetaString = new StringBuilder();
        for (int i = 1; i < etiqueta.length; i++) {
            etiquetaString.append(etiqueta[i]).append(",");
        }
        return etiquetaString.toString();
    }

    public void insertarAristaE(E o, E d, Double peso) throws Exception {
        if (estaEtiquetado()) {
            System.out.println(peso);
            insertar(obtenerCodigo(o), obtenerCodigo(d), peso);
        }
    }

    public void insertarAristaE(E o, E d) throws Exception {
        if (estaEtiquetado()) {
            insertar(obtenerCodigo(o), obtenerCodigo(d), Double.NaN);
        }

    }

    public Boolean estaEtiquetado() {
        Boolean band = true;
        for (int i = 1; i < etiqueta.length; i++) {
            E dato = etiqueta[i];
            if (dato == null) {
                band = false;
                break;
            }

        }
        return band;
    }

    public LinkedList<Abyacencia> abyacentes(E o) {
        return abyacentes(obtenerCodigo(o));
    }

    public void etiquetarVertice(Integer vertice, E dato) {
        try {
            getEtiqueta()[vertice] = dato;
            dicvertices.put(dato, vertice);
        } catch (Exception e) {
            System.out.println(e + "weee");
        }

    }

    public Boolean ExisteArista(E o, E d) throws Exception {
        if (estaEtiquetado()) {
            return existeArista(obtenerCodigo(o), obtenerCodigo(d));
        } else {
            throw new EtiquetaException();
        }
    }

    public Integer obtenerCodigo(E etiqueta) {

        return dicvertices.get(etiqueta);
    }

    public E obtenerEt(Integer et) {

        return getEtiqueta()[et];
    }

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder("GRAFOS Etiquetados\n");
        try {
            for (int i = 1; i <= nroVertices(); i++) {
                grafo.append("Vertice ").append(obtenerEt(i)).append("\n");
                if (!abyacentes(i).isEmpty()) {
                    Abyacencia[] lista = abyacentes(i).toArray();
                    for (int j = 0; j < lista.length; j++) {
                        Abyacencia a = lista[j];
                        grafo.append("Adyacente ").append(obtenerEt(a.getDestino()))
                                .append(" Peso: ").append(a.getPeso()).append("\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("excepcion GEDirigido: " + e.getMessage());
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return grafo.toString();
    }

    public static void main(String[] args) {
        try {
            // Crear un grafo etiquetado con etiquetas de tipo String
            GrafoEtiquetado<String> grafo = new GrafoEtiquetado<>(5, String.class);

            // Etiquetar algunos vértices
            grafo.etiquetarVertice(1, "A");
            grafo.etiquetarVertice(2, "B");
            grafo.etiquetarVertice(3, "C");
            grafo.etiquetarVertice(4, "D");
            grafo.etiquetarVertice(5, "E");

            // Insertar aristas etiquetadas
            grafo.insertarAristaE("A", "B", 2.0);
            grafo.insertarAristaE("B", "C", 1.5);
            grafo.insertarAristaE("C", "D", 3.0);
            grafo.insertarAristaE("D", "E", 2.5);
            grafo.insertarAristaE("A", "E", 4.0);
            // Imprimir el grafo
            System.out.println(grafo.pesoArista(1, 2));
        } catch (Exception e) {
            System.out.println(e + "eeee");
        }
    }

    /**
     * @return the etiqueta
     */
    public E[] getEtiqueta() {
        return etiqueta;
    }

    public Integer sizeEtiqueta() {
        return etiqueta.length;
    }

}
