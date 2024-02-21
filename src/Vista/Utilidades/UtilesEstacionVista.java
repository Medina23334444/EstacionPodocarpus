/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Utilidades;


import Controller.Estacion.ControllerEstacionPodocarpus;
import Controller.TDAListas.LinkedList;
import Modelo.Estacion;
import controller.Utilidades.Utilidades;
import controller.grafo.Grafo;
import controller.grafo.GrafoEtiquetado;
import controller.grafo.GrafoEtiquetadoNoDirigido;
import java.io.FileWriter;
import javax.swing.JComboBox;
/**
 *
 * @author Usuario
 */
public class UtilesEstacionVista {

    public static void cargarComboEstacion(JComboBox cbx) throws Exception {

        cbx.removeAllItems();
        LinkedList<Estacion> lista = new ControllerEstacionPodocarpus().listAll();
        for (int i = 0; i < lista.getSize(); i++) {
            cbx.addItem(lista.get(i));
        }
    }

    public static Estacion getComboEstacion(JComboBox cbx) {
        return (Estacion) cbx.getSelectedItem();
    }

    public static Double DistanciaEstaciones(Estacion o, Estacion d) {
        return Utilidades.DistanciaDosPuntos(o.getLalitud(), o.getLongitud(), d.getLalitud(), d.getLongitud());
    }

    public static void crearMapa(Grafo grafo) {
        if (grafo instanceof GrafoEtiquetadoNoDirigido || grafo instanceof GrafoEtiquetado) {
            GrafoEtiquetadoNoDirigido ge = (GrafoEtiquetadoNoDirigido) grafo;
            String mapa = "var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',\n"
                    + "                    osmAttrib = '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors',\n"
                    + "                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});\n"
                    + "\n"
                    + "            var map = L.map('map').setView([-4.036, -79.201], 15);\n"
                    + "\n"
                    + "            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {\n"
                    + "                attribution: '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors'\n"
                    + "            }).addTo(map);";

            try {
                for (int i = 1; i <= ge.nroVertices(); i++) {
                    Estacion ec = (Estacion) ge.obtenerEt(i);
                    System.out.println(ec.getLalitud());
                    mapa += "L.marker([" + ec.getLalitud() + "," + ec.getLongitud() + "]).addTo(map)" + "\n";
                    mapa += ".bindPopup('" + ec.getNombre() + "')" + "\n";
                    mapa += ".openPopup();" + "\n";
                }

                FileWriter file = new FileWriter("C:\\Users\\Usuario\\Downloads\\hhhhh\\Ejem_Llantas\\src\\mapas\\mapa.js");
                file.write(mapa);
                file.close();
            } catch (Exception e) {
                System.out.println("Errror " + e);
            }
        }
    }
}
