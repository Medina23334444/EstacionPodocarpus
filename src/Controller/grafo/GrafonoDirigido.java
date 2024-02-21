/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.grafo;


/**
 *
 * @author Usuario
 */
public class GrafonoDirigido extends GrafoDirigido {

    public GrafonoDirigido(Integer nroVertices) {
        super(nroVertices);
    }

    @Override
    public void insertar(Integer origen, Integer destino, Double peso) throws Exception {
        if (origen <= nroVertices() && destino <= nroVertices() && !origen.equals(destino)) {
            if (!existeArista(origen, destino)) {
                setNroAristas(nroAristas() + 1);
                Abyacencia aux = new Abyacencia();
                aux.setPeso(peso);
                aux.setDestino(destino);

                Abyacencia auxInver = new Abyacencia();
                auxInver.setPeso(peso);
                auxInver.setDestino(origen);
                getListaAb()[origen].add(aux);
                getListaAb()[destino].add(auxInver);
            }
        } else {
            throw new ExceptionGrafo();
        }
    }

//    public static void main(String[] args) {
//
//        try {
//            GrafonoDirigido grafo = new GrafonoDirigido(8);
//            grafo.insertar(1, 2, 23.4);
//            grafo.insertar(2, 5, 34.4);
//            grafo.insertar(4, 2, 45.2);
//            grafo.insertar(3, 4, 34.5);
//            grafo.insertar(4, 1, 45.3);
//            grafo.insertar(8, 3, 34.5);
//            grafo.insertar(6, 2, 89.3);
//            grafo.insertar(4, 8, 23.6);
//            grafo.insertar(5, 3, 56.8);
//            grafo.insertar(8, 7, 56.0);
//            grafo.insertar(6, 1, 56.0);
//
//            Dibujar_Grafo dibujarGrafo = new Dibujar_Grafo();
//            dibujarGrafo.generarContenidoGrafo(grafo);
//
//            String sistemaOperativo = Utilidades.getOS();
//            System.out.println(sistemaOperativo);
//
//            if (sistemaOperativo.toLowerCase().contains("windows")) {
//                Utilidades.abrirNavegadorWindowsDeterminado("file:///C:/Users/Usuario/Downloads/hhhhh/Ejem_Llantas/src/d3/grafo.html");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }

}
