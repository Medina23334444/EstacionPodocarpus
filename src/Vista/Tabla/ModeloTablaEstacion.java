package Vista.Tabla;

import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;
import Modelo.Estacion;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaEstacion extends AbstractTableModel {

    private LinkedList<Estacion> estaciones;

    @Override
    public int getRowCount() {
        return getEstaciones().getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Estacion estacion = null;
        try {
            estacion = estaciones.get(row);
        } catch (VacioExpection e) {
            throw new RuntimeException(e);
        }
        switch (col) {
            case 0:
                return (estacion != null) ? estacion.getNombre() : "";
            case 1:
                return (estacion != null) ? estacion.getLalitud() : "";
            case 2:
                return (estacion != null) ? estacion.getLongitud() : "";
            case 3:
                return (estacion != null) ? estacion.getDecripcion() : "";
            case 4:
                return (estacion != null) ? estacion.getCapacidad() : "";
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nombre";
            case 1:
                return "Lalitud";
            case 2:
                return "Longitud";
            case 3:
                return "Descripcion";
            case 4:
                return "Capacidad";
            default:
                return null;
        }
    }

    /**
     * @return the estaciones
     */
    public LinkedList<Estacion> getEstaciones() {
        return estaciones;
    }

    /**
     * @param estaciones the estaciones to set
     */
    public void setEstaciones(LinkedList<Estacion> estaciones) {
        this.estaciones = estaciones;
    }

}
