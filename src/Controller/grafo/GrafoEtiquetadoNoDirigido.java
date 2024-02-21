/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.grafo;

/**
 *
 * @author Usuario
 * @param <E>
 */
public class GrafoEtiquetadoNoDirigido<E> extends GrafoEtiquetado<E> {
    
    public GrafoEtiquetadoNoDirigido(Integer nroVertices, Class<E> clazz) {
        super(nroVertices, clazz);
        
    }
    
    @Override
    public void insertar(Integer origen, Integer destino, Double peso) throws Exception {
        
        System.out.println(getListaAb().length);        
        if (origen <= nroVertices() && destino <= nroVertices() && !origen.equals(destino)) {
            if (!existeArista(origen, destino)) {
               
                Abyacencia aux = new Abyacencia();
                aux.setPeso(peso);
                aux.setDestino(destino);
                
                Abyacencia auxInver = new Abyacencia();
                auxInver.setPeso(peso);
                auxInver.setDestino(origen);
                
                getListaAb()[origen].add(aux);
                getListaAb()[destino].add(auxInver);
                 setNroAristas(nroAristas() + 1);
            }
        } else {
            throw new ExceptionGrafo();
        }
    }
    
    public static void main(String[] args) {
        try {
            GrafoEtiquetadoNoDirigido<String> grafo = new GrafoEtiquetadoNoDirigido<>(3, String.class);

            // Etiquetar algunos vértices
            grafo.etiquetarVertice(1, "A");
            grafo.etiquetarVertice(2, "B");
            grafo.etiquetarVertice(3, "C");

            // Insertar aristas etiquetadas
            grafo.insertarAristaE("A", "B", 2.0);
            grafo.insertarAristaE("B", "C", 1.5);

            // Imprimir el grafo
            System.out.println(grafo.toString());
            System.out.println(grafo.pesoArista(1, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
