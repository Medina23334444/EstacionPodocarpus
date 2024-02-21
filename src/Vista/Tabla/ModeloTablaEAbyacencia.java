package Vista.Tabla;

import Modelo.Estacion;
import controller.grafo.GrafoEtiquetadoNoDirigido;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaEAbyacencia extends AbstractTableModel {

    private GrafoEtiquetadoNoDirigido<Estacion> grafo;

    @Override
    public int getRowCount() {
        return getGrafo().nroVertices();
    }

    @Override
    public int getColumnCount() {
        return getGrafo().nroVertices() + 1;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        if (columna == 0) {
            return getGrafo().obtenerEt(fila + 1).toString();
        } else {
            try {
                Estacion origen = getGrafo().obtenerEt(fila + 1);
                Estacion destino = getGrafo().obtenerEt(columna);
                if (getGrafo().ExisteArista(origen, destino)) {
                    return getGrafo().pesoArista(fila + 1, columna).toString();
                } else {
                    return "--";
                }
            } catch (Exception e) {
                System.out.println(e);
                return "0000000";
            }
        }
    }

    public String getColumnName(int column) {
        if (column == 0) {
            return "Estaciones";
        } else {
            String valor = "";
            valor = getGrafo().obtenerEt(column).toString();
            return valor;
        }

    }

    /**
     * @return the grafo
     */
    public GrafoEtiquetadoNoDirigido<Estacion> getGrafo() {
        return grafo;
    }

    /**
     * @param grafo the grafo to set
     */
    public void setGrafo(GrafoEtiquetadoNoDirigido<Estacion> grafo) {
        this.grafo = grafo;
    }

}
