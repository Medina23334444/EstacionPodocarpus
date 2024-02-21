/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller.grafo;

import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;

import java.util.HashMap;

/**
 *
 * @author Usuario
 */
public abstract class Grafo {

    public abstract Integer nroVertices();

    public abstract Integer nroAristas();

    public abstract Boolean existeArista(Integer origen, Integer destino) throws Exception;

    public abstract Double pesoArista(Integer origen, Integer destino) throws Exception;

    public abstract void insertar(Integer origen, Integer destino) throws Exception;

    public abstract void insertar(Integer origen, Integer destino, Double peso) throws Exception;

    public abstract LinkedList<Abyacencia> abyacentes(Integer a);

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder("GRAFOS \n");
        try {
            for (int i = 1; i <= nroVertices(); i++) {
                grafo.append("Vertice ").append(String.valueOf(i)).append("\n");
                if (!abyacentes(i).isEmpty()) {
                    Abyacencia[] lista = abyacentes(i).toArray();
                    for (int j = 0; j < lista.length; j++) {
                        Abyacencia a = lista[j];
                        grafo.append("Adycente ").append(a.getDestino().toString()).append("\n");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grafo.toString();
    }

    public HashMap<String, LinkedList<Integer>> floyd(GrafoEtiquetadoNoDirigido ad, Integer o, Integer d) throws Exception {
        HashMap<String, LinkedList<Integer>> p = new HashMap<>();
        int V = ad.nroVertices();
        LinkedList<Integer> vertices = new LinkedList<>();

        Integer[][] pre = new Integer[V + 1][V + 1];
        double[][] distancia = new double[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    pre[i][j] = i;
                    distancia[i][j] = 0.0;
                } else if (ad.existeArista(i, j)) {
                    pre[i][j] = i;
                    distancia[i][j] = ad.pesoArista(i, j); // Peso de la arista entre i y j
                } else {
                    pre[i][j] = null;
                    distancia[i][j] = Double.POSITIVE_INFINITY; // Utilizamos un valor infinito para indicar que no hay conexión
                }
            }
        }
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (distancia[i][k] != Double.POSITIVE_INFINITY && distancia[k][j] != Double.POSITIVE_INFINITY) {
                        if (distancia[i][k] + distancia[k][j] < distancia[i][j]) {
                            distancia[i][j] = distancia[i][k] + distancia[k][j];
                            pre[i][j] = pre[k][j];
                        }
                    }
                }
            }
        }

        if (pre[o][d] != null) {
            int temp = d;
            vertices.add(temp, 0);
            while (temp != o) {
                temp = pre[o][temp];
                vertices.add(temp); 
            }
        }

        p.put("vertices", vertices);
        return p;
    }

    public HashMap<Integer, Integer> dijkstra(Integer origen) throws Exception {
        HashMap<Integer, Integer> distancias = new HashMap<>();
        int V = nroVertices();
        Double[] distancia = new Double[V + 1];
        Boolean[] visitado = new Boolean[V + 1];
        HashMap<Integer, Integer> predecesores = new HashMap<>();

        for (int i = 1; i <= V; i++) {
            distancia[i] = Double.MAX_VALUE;
            visitado[i] = false;
        }

        distancia[origen] = 0.0;

        for (int i = 1; i <= V - 1; i++) {
            Double min = Double.MAX_VALUE;
            Integer min_index = -1;

            for (int v = 1; v <= V; v++) {
                if (!visitado[v] && distancia[v] <= min) {
                    min = distancia[v];
                    min_index = v;
                }
            }
            visitado[min_index] = true;

            for (int j = 1; j <= V; j++) {
                if (!visitado[j] && existeArista(min_index, j) && distancia[min_index] != Double.MAX_VALUE
                        && distancia[min_index] + pesoArista(min_index, j) < distancia[j]) {
                    distancia[j] = distancia[min_index] + pesoArista(min_index, j);
                    predecesores.put(j, min_index);
                }
            }
        }
        for (int i = 1; i <= V; i++) {
            distancias.put(i, distancia[i].intValue());
        }

        return predecesores;
    }

    public LinkedList<Integer> recorridoAnchura(int nodoI) throws VacioExpection {
        LinkedList<Integer> recorridos = new LinkedList<>();
        boolean[] visitadoAnchura = new boolean[nroVertices() + 1];
        LinkedList<Integer> cola = new LinkedList<>();
        cola.add(nodoI);
        visitadoAnchura[nodoI] = true;
        while (!cola.isEmpty()) {
            int j = cola.deleteFirst();
            recorridos.add(j);
            Abyacencia[] a = abyacentes(j).toArray();
            for (Abyacencia ady : a) {
                int adyacente = ady.getDestino();
                if (!visitadoAnchura[adyacente]) {
                    cola.add(adyacente);
                    visitadoAnchura[adyacente] = true;
                }
            }
        }
        return recorridos;
    }

    public LinkedList<Integer> recorridoProfundidad(int nodoI) {
        LinkedList<Integer> recorridos = new LinkedList<>();
        LinkedList<Integer> aux = new LinkedList<>();
        boolean[] visitadoProfundidad = new boolean[nroVertices() + 1];
        Abyacencia[] a = abyacentes(nodoI).toArray();
        for (Abyacencia ady : a) {
            int adyacente = ady.getDestino();
            if (!visitadoProfundidad[adyacente]) {
                aux = recorridoProfundidadAux(adyacente, visitadoProfundidad);
                Integer[] auxi = aux.toArray();
                for (Integer elemento : auxi) {
                    recorridos.add(elemento);
                }
            }
        }
        visitadoProfundidad[nodoI] = true;
        recorridos.add(nodoI);

        return recorridos;
    }

    private LinkedList<Integer> recorridoProfundidadAux(int nodoI, boolean[] visitado) {
        LinkedList<Integer> recorridos = new LinkedList<>();
        LinkedList<Integer> aux = new LinkedList<>();
        visitado[nodoI] = true;
        recorridos.add(nodoI);

        Abyacencia[] a = abyacentes(nodoI).toArray();
        for (Abyacencia ady : a) {
            int adyacente = ady.getDestino();
            if (!visitado[adyacente]) {
                aux = (recorridoProfundidadAux(adyacente, visitado));
                Integer[] auxi = aux.toArray();
                for (Integer elemento : auxi) {
                    recorridos.add(elemento);
                }
            }
        }
        return recorridos;
    }
}
