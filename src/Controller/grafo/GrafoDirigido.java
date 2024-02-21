/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.grafo;

import Controller.TDAListas.LinkedList;


/**
 *
 * @author Usuario
 */
public class GrafoDirigido extends Grafo {

    private Integer nroVertices;
    private Integer nroAristas;
    private LinkedList<Abyacencia> listaAb[];

    public GrafoDirigido(Integer nroVertices) {
        this.nroVertices = nroVertices;
        nroAristas = 0;
        listaAb = new LinkedList[nroVertices + 1];
        for (int i = 1; i <= nroVertices; i++) {  
            listaAb[i] = new LinkedList<>();
        }
    }

    @Override
    public Integer nroVertices() {

        return this.nroVertices;
    }

    @Override
    public Integer nroAristas() {
        return this.nroAristas;
    }

    @Override
    public Boolean existeArista(Integer origen, Integer destino) throws Exception {
        Boolean band = false;
        if (origen.intValue() <= nroVertices.intValue() && destino.intValue() <= nroVertices.intValue()) {
            LinkedList<Abyacencia> lista = getListaAb()[origen ];
            for (int i = 0; i < lista.getSize(); i++) {
                Abyacencia aux = lista.get(i);
                if (aux.getDestino().intValue() == destino.intValue()) {
                    band = true;
                    break;
                }
            }
        } else {
            throw new ExceptionGrafo();
        }
        return band;
    }

    @Override
    public Double pesoArista(Integer origen, Integer destino) throws Exception {
        Double peso = Double.NaN;
        if (existeArista(origen, destino)) {
            LinkedList<Abyacencia> lista = getListaAb()[origen];
            for (int i = 0; i < lista.getSize(); i++) {
                Abyacencia aux = lista.get(i);
                if (aux.getDestino().intValue() == destino.intValue()) {
                    peso = aux.getPeso();
                    break;

                }
            }
        }
        return peso;
    }

    @Override
    public void insertar(Integer origen, Integer destino) throws Exception {

        insertar(origen, destino, Double.NaN);
    }

    @Override
    public void insertar(Integer origen, Integer destino, Double peso) throws Exception {
        if (origen.intValue() <= nroVertices.intValue() && destino.intValue() <= nroVertices.intValue()) {
            if (!existeArista(origen, destino)) {
                nroAristas++;
                Abyacencia aux = new Abyacencia();
                aux.setPeso(peso);
                aux.setDestino(destino);

                getListaAb()[origen].add(aux);
            }
        } else {
            throw new ExceptionGrafo();
        }
    }

    @Override
    public LinkedList<Abyacencia> abyacentes(Integer a) {
        return getListaAb()[a];
    }

    public static void main(String[] args) {

        GrafoDirigido gd = new GrafoDirigido(4);
//        System.out.println(gd.toString());
        try {
            gd.insertar(1, 2, 23.3);
            gd.insertar(4, 1,45.5);
            gd.insertar(1, 2, 45.3);
            gd.insertar(3, 2, 34.2);
            System.out.println(gd.toString());
            System.out.println(gd.pesoArista(1, 2));
        } catch (Exception e) {
        }
    }

    /**
     * @param nroAristas the nroAristas to set
     */
    public void setNroAristas(Integer nroAristas) {
        this.nroAristas = nroAristas;
    }

    /**
     * @return the listaAb
     */
    public LinkedList<Abyacencia>[] getListaAb() {
        return listaAb;
    }

}
