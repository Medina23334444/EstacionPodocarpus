/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.grafo;

import Controller.TDAListas.LinkedList;
import controller.Utilidades.Utilidades;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Usuario
 */
public class Dibujar_Grafo {

    String URL = "C:\\Users\\Usuario\\Downloads\\hhhhh\\Ejem_Llantas\\src\\d3\\grafo.js";

    public void generarContenidoGrafo(Grafo grafo) {
        StringBuilder contenido = new StringBuilder();
        contenido.append("var nodes = new vis.DataSet([");

        for (int i = 1; i <= grafo.nroVertices(); i++) {

            contenido.append("{ id:").append(i).append(", label: \"Punto").append(i).append("\", color: '#FFFF00' },");

        }

        contenido.append("]);\n")
                .append("\n")
                .append("// create an array with edges\n")
                .append("var edges = new vis.DataSet([");

        for (int i = 1; i <= grafo.nroVertices(); i++) {
            LinkedList<Abyacencia> ad = grafo.abyacentes(i);
            if (!ad.isEmpty()) {
                Abyacencia[] a = ad.toArray();
                for (Abyacencia an : a) {
                    int verticeDestino = an.getDestino();
                    Double n = an.getPeso();
                    contenido.append("{ from: ").append(i).append(", to: ").append(verticeDestino).append(",value: 3").append(", label: \"").append(n).append("\" },");
                }
            }
        }
        contenido
                .append("]);\n")
                .append("\n")
                .append("// create a network\n")
                .append("var container = document.getElementById(\"mynetwork\");\n")
                .append("var data = {\n")
                .append("nodes: nodes,\n")
                .append("edges: edges,\n")
                .append("};\n")
                .append("var options = {};\n")
                .append("var network = new vis.Network(container, data, options);");

        try (FileWriter file = new FileWriter(URL)) {
            file.write(contenido.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearArchivo(GrafoEtiquetadoNoDirigido g, LinkedList<Integer> caminoCorto) throws Exception {
        StringBuilder adf = new StringBuilder();
        adf.append("var nodes = new vis.DataSet([");

        for (int i = 1; i <= g.nroVertices(); i++) {
            adf.append("{ id: ").append(i).append(", label: 'Punto").append(i).append("' },");
        }
        adf.append("]);\n\n");

        adf.append("var edges = new vis.DataSet([");
        for (int i = 1; i <= g.nroVertices(); i++) {
            if (g.abyacentes(i) != null && !g.abyacentes(i).isEmpty()) {
                Abyacencia[] ady = g.abyacentes(i).toArray();
                for (Abyacencia a : ady) {
                    if (g.existeArista(i, a.getDestino())) {
                        String color = caminoCorto.contains(i) && caminoCorto.contains(a.getDestino()) ? ", color: '#FFFF00' " : "";
                        Double x = Utilidades.redondear(a.getPeso());
                        String label = x.toString();
                        adf.append("{ from: ").append(i).append(", to: ").append(a.getDestino()).append(", label: '").append(label).append("'").append(color).append(" },");
                    }
                }
            }
        }
        adf.append("]);\n\n");

        String finalArchivo = """
        var container = document.getElementById("mynetwork");
        var data = {
            nodes: nodes,
            edges: edges
        };
        var options = {};
        var network = new vis.Network(container, data, options);
    """;

        try (FileWriter file = new FileWriter(URL)) {
            file.write(adf.toString());
            file.write(finalArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
