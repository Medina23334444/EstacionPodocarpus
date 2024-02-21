/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controller.Estacion.ControllerEstacionPodocarpus;
import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;
import Modelo.Estacion;
import Vista.Tabla.ModeloTablaEAbyacencia;
import Vista.Utilidades.UtilesEstacionVista;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import controller.Utilidades.Utilidades;
import controller.grafo.Dibujar_Grafo;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Usuario
 */
public class FrmPrincipal extends javax.swing.JFrame {

    ControllerEstacionPodocarpus ed = new ControllerEstacionPodocarpus();
    ModeloTablaEAbyacencia a = new ModeloTablaEAbyacencia();
    LinkedList<Integer> caminos;

    public FrmPrincipal() {
        initComponents();
        limpiar();
    }

    private void limpiar() {
        try {
            UtilesEstacionVista.cargarComboEstacion(cbxOrigen);
            UtilesEstacionVista.cargarComboEstacion(cbxDestino);
            cargarTabla();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void mostrarGrafo() {

        try {
            Dibujar_Grafo a = new Dibujar_Grafo();
            a.generarContenidoGrafo(ed.getGrafoEscuela());
            if (Utilidades.getOS().equalsIgnoreCase("windows 10")) {
                Utilidades.abrirNavegadorWindowsDeterminado("file:///C:/Users/Usuario/Downloads/hhhhh/Ejem_Llantas/src/d3/grafo.html");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void guardarGrafo() throws Exception {
        int i = JOptionPane.showConfirmDialog(null, "Deseas guardar el grafo", "Pregunta", JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
            try {
                ed.guardarGrafo();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    public void cargarTabla() throws VacioExpection, Exception {
        a.setGrafo(ed.getGrafoEscuela());
        a.fireTableDataChanged();
        tbl2.setModel(a);
        tbl2.updateUI();
    }

    private void abyacencia() {
        try {
            LinkedList<Estacion> lista = new LinkedList<>();
            lista = ed.listAll();
            Random random = new Random();
            for (int i = 0; i < lista.getSize(); i++) {
                Estacion estacion = lista.get(i);
                int conexiones = 0;
                while (conexiones < 2) {
                    Integer postD = random.nextInt(9) + 1;
                    if (postD != i) {
                        Estacion estacion1 = lista.get(postD);
                        Double peso = UtilesEstacionVista.DistanciaEstaciones(estacion, estacion1);
                        if (peso != 0) {
                            ed.getGrafoEscuela().insertarAristaE(ed.getLista().get(i), ed.getLista().get(postD), peso);
                            conexiones++;
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e + "Error");
        }
    }

    private void elegirRecorrido() throws Exception {
        Integer postO = cbxOrigen.getSelectedIndex() + 1;
        String criterio = cbxRecorridos.getSelectedItem().toString();
        if (criterio.equals("Recorrido Profundidad")) {
            LinkedList<Integer> a = ed.getGrafoEscuela().recorridoProfundidad(postO);
            lbl1.setText(a.print());
        } else {
            LinkedList<Integer> a = ed.getGrafoEscuela().recorridoAnchura(postO);
            lbl1.setText(a.print());
        }

    }

    private void cargarGrafo() throws Exception {
        int i = JOptionPane.showConfirmDialog(null, "Deseas cargar el grafo", "Pregunta", JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
            try {
                ed.cargarGrafo();
                limpiar();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    private void camino_Floyd() throws Exception {
        if (ed.getGrafoEscuela() != null) {
            Integer postO = cbxOrigen.getSelectedIndex() + 1;
            Integer postD = cbxDestino.getSelectedIndex() + 1;
            long tiempoInicio = System.nanoTime();
            HashMap<String, LinkedList<Integer>> predecesores = ed.getGrafoEscuela().floyd(ed.getGrafoEscuela(), postO, postD);
            long tiempoFin = System.nanoTime();
            double tiempoTotal = (tiempoFin - tiempoInicio) / 1e6;
            System.out.println("Tiempo total de Floyd: " + tiempoTotal + " milisegundos");
            caminos = predecesores.get("vertices");
            if (predecesores.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontró un camino entre los vértices seleccionados", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                StringBuilder mensaje = new StringBuilder("Camino encontrado: ");
                for (int i = 0; i < caminos.getSize(); i++) {
                    Integer v = caminos.get(i);
                    mensaje.append(ed.getGrafoEscuela().obtenerEt(v)).append(" -> ");
                }
                JOptionPane.showMessageDialog(null, mensaje);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El grafo es nulo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void camino_Dijkstra() throws Exception {
        if (ed.getGrafoEscuela() != null) {
            Integer postO = cbxOrigen.getSelectedIndex() + 1;
            Integer postD = cbxDestino.getSelectedIndex() + 1;
            long tiempoInicio = System.nanoTime();
            HashMap<Integer, Integer> predecesores = ed.getGrafoEscuela().dijkstra(postO);
            long tiempoFin = System.nanoTime();
            double tiempoTotal = (tiempoFin - tiempoInicio) / 1e6;
            System.out.println("Tiempo total de Dijkstra : " + tiempoTotal + " milisegundos");
            if (predecesores.isEmpty() || !predecesores.containsKey(postD)) {
                JOptionPane.showMessageDialog(null, "No se encontró un camino entre los vértices seleccionados", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                caminos = reconstruirCamino(predecesores, postD);
                StringBuilder mensaje = new StringBuilder("Camino encontrado: ");
                for (int i = 0; i < caminos.getSize(); i++) {
                    Integer v = caminos.get(i);
                    mensaje.append(ed.getGrafoEscuela().obtenerEt(v)).append(" -> ");

                }

                JOptionPane.showMessageDialog(null, mensaje);

            }
        } else {
            JOptionPane.showMessageDialog(null, "El grafo es nulo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private LinkedList<Integer> reconstruirCamino(HashMap<Integer, Integer> predecesores, Integer destino) throws VacioExpection {
        LinkedList<Integer> camino = new LinkedList<>();
        Integer actual = destino;
        while (actual != null) {
            camino.add(actual, 0);
            actual = predecesores.get(actual);
        }
        return camino;
    }

    private void elegir() throws Exception {
        String criterio = cbxCriterio.getSelectedItem().toString();
        if (criterio.equals("Dijkstra")) {
            camino_Dijkstra();
        } else {
            camino_Floyd();
        }
    }

    private void mostrarCamino() {
        Dibujar_Grafo dg = new Dibujar_Grafo();
        try {
            dg.crearArchivo(ed.getGrafoEscuela(), caminos);
            // System.out.println(caminos.print());
            if (Utilidades.getOS().equalsIgnoreCase("windows 10")) {
                Utilidades.abrirNavegadorWindowsDeterminado("file:///C:/Users/Usuario/Downloads/hhhhh/Ejem_Llantas/src/d3/grafo.html");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxDestino = new javax.swing.JComboBox<>();
        cbxOrigen = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();
        cbxCriterio = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbxRecorridos = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Punto de Origen");

        jLabel2.setText("Punto de Destino");

        cbxDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Calcular Aleatoriamente");
        jButton1.setMaximumSize(new java.awt.Dimension(154, 31));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Actualizar Tabla");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(cbxOrigen, 0, 242, Short.MAX_VALUE)
                    .addComponent(cbxDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(257, 257, 257))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2)
                        .addGap(9, 9, 9)
                        .addComponent(cbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 59, 970, 160));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbl2.setBackground(new java.awt.Color(204, 255, 204));
        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl2);

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dijkstra", "Floyd" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        cbxCriterio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCriterioActionPerformed(evt);
            }
        });

        jLabel3.setText("Calcular Camino:");

        cbxRecorridos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Recorrido Profundidad", "Recorrido Anchura" }));
        cbxRecorridos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxRecorridosItemStateChanged(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 102, 102));
        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Tabla del Grafo");

        jLabel4.setText("Resultado Recorrido:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbxRecorridos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 43, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxRecorridos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 217, 970, 430));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/d3/github/workflows/teoria-de-grafos.png"))); // NOI18N
        jButton4.setText("Mostrar Grafo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/d3/github/workflows/mapas-y-banderas.png"))); // NOI18N
        jButton5.setText("Mostrar Mapa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(169, 215, 194));
        jPanel5.setForeground(new java.awt.Color(190, 224, 192));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Menu");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel11)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(438, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 660));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 102));
        jLabel13.setText("PARQUE PODOCARPUS ESTACIONES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(280, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 970, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        abyacencia();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            guardarGrafo();
        } catch (Exception ex) {
            System.out.println("Error" + ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            cargarGrafo();
        } catch (Exception ex) {
            System.out.println("Error" + ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cbxCriterioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCriterioActionPerformed

    }//GEN-LAST:event_cbxCriterioActionPerformed

    private void cbxCriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriterioItemStateChanged
        try {
            elegir();
        } catch (Exception ex) {
            System.out.println("Error" + ex);
        }
    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            mostrarCamino();
        } catch (Exception e) {
            System.out.println(e + "Error");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            UtilesEstacionVista.crearMapa(ed.getGrafoEscuela());
            if (Utilidades.getOS().equalsIgnoreCase("windows 10")) {
                Utilidades.abrirNavegadorWindowsDeterminado("C:\\Users\\Usuario\\Downloads\\hhhhh\\Ejem_Llantas\\src\\mapas\\index.html");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cbxRecorridosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxRecorridosItemStateChanged
        try {
            elegirRecorrido();
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }//GEN-LAST:event_cbxRecorridosItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize LaF");
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxDestino;
    private javax.swing.JComboBox<String> cbxOrigen;
    private javax.swing.JComboBox<String> cbxRecorridos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JTable tbl2;
    // End of variables declaration//GEN-END:variables
}
