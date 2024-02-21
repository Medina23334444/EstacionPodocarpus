/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Estacion;

import Controller.EstacionPodocarpus.DataAccessObject;
import Controller.TDAListas.LinkedList;
import Modelo.Estacion;
import controller.grafo.GrafoEtiquetadoNoDirigido;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Usuario
 */
public class ControllerEstacionPodocarpus extends DataAccessObject<Estacion> {

    private Estacion estacion = new Estacion();
    private LinkedList<Estacion> lista = new LinkedList<>();
    private GrafoEtiquetadoNoDirigido<Estacion> grafoEstacion;

    public ControllerEstacionPodocarpus() {
        super(Estacion.class);
    }

    public Estacion getEstacion() {

        if (estacion == null) {
            estacion = new Estacion();
        }
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public Boolean saved() {
        estacion.setId(generarId());
        return save(estacion);
    }

    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer length = listAll().getSize() + 1;
        Integer pos = Integer.toString(length).length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(length.toString());
        return code.toString();
    }

    public LinkedList<Estacion> getLista() {
        if (lista.isEmpty()) {
            lista = listAll();
        }
        return lista;

    }

    public Integer buscarIndex(Integer id) {
        Integer index = -1;
        if (listAll().isEmpty()) {
            Estacion[] estaciones = listAll().toArray();
            for (int i = 0; i < estaciones.length; i++) {
                if (id.intValue() == estaciones[i].getId().intValue()) {
                    index = 1;
                    break;
                }
            }
        }
        return index;

    }

    public Boolean update1(Integer i) {
        return update(estacion, buscarIndex(estacion.getId()));
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(LinkedList<Estacion> lista) {
        this.lista = lista;
    }

    public void guardarGrafo() throws Exception {
        if (grafoEstacion != null) {
            getXstream().alias(grafoEstacion.getClass().getName(), GrafoEtiquetadoNoDirigido.class);
            this.getXstream().toXML(grafoEstacion, new FileWriter("data/grafo.json"));

        } else {
            new NullPointerException("Grafo vacio");
        }

    }

    public void cargarGrafo() throws Exception {
        grafoEstacion = (GrafoEtiquetadoNoDirigido<Estacion>) this.getXstream().
                fromXML(new FileReader("data/grafo.json"));
        lista.clear();
        for (int i = 1; i < grafoEstacion.nroVertices(); i++) {
            lista.add(grafoEstacion.obtenerEt(i));
        }
    }

    /**
     * @return the grafoEstacion
     */
    public GrafoEtiquetadoNoDirigido<Estacion> getGrafoEscuela() throws Exception {
        if (grafoEstacion == null) {
            LinkedList<Estacion> lista1 = getLista();
            Integer size = lista1.getSize();
            if (size > 0) {
                grafoEstacion = new GrafoEtiquetadoNoDirigido<>(size, Estacion.class);
                for (int i = 0; i < size; i++) {
                    grafoEstacion.etiquetarVertice((i + 1), lista1.get(i));

                }
            }
        }

        return grafoEstacion;
    }

    /**
     * @param grafoEstacion the grafoEstacion to set
     */
    public void setGrafoEscuela(GrafoEtiquetadoNoDirigido<Estacion> grafoEstacion) {
        this.grafoEstacion = grafoEstacion;
    }

}
