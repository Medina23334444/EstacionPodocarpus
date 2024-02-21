/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controller.Estacion.ControllerEstacionPodocarpus;
import Vista.Tabla.ModeloTablaEstacion;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import controller.Utilidades.Utilidades;
import java.io.File;
import java.util.UUID;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Usuario
 */
public class FrmEstacion extends javax.swing.JFrame {

    ControllerEstacionPodocarpus ce = new ControllerEstacionPodocarpus();
    ModeloTablaEstacion mt = new ModeloTablaEstacion();
    private File foto1;
    private File foto2;
    private File foto3;
    private File foto4;

    public FrmEstacion() {
        initComponents();
        cargarTabla();
    }

    public void cargarTabla() {
        mt.setEstaciones(ce.listAll());
        tbl1.setModel(mt);
        tbl1.updateUI();
    }

    private Boolean validar() {
        return !txtLatitud.getText().trim().isEmpty()
                && !txtDescripcion.getText().trim().isEmpty()
                && !txtNombre.getText().trim().isEmpty()
                && !txtAcceso.getText().trim().isEmpty()
                && !txtCapacidad.getText().trim().isEmpty()
                && !txtLongitud.getText().trim().isEmpty();
    }

    private void limpiar() {
        foto1 = null;
        foto2 = null;
        foto3 = null;
        foto4 = null;
        ce.setEstacion(null);
        txtLatitud.setText("");
        txtNombre.setText("");
        txtFoto1.setText("");
        txtFoto2.setText("");
        txtFoto3.setText("");
        txtFoto4.setText("");
        txtLongitud.setText("");
    }

    private void guardar() {
        if (validar()) {
            try {
                String uuid = UUID.randomUUID().toString();
                String uuid2 = UUID.randomUUID().toString();
                String uuid3 = UUID.randomUUID().toString();
                String uuid4 = UUID.randomUUID().toString();
                Utilidades.copiarArchivo(foto1, new File("multimedia/" + uuid + "." + Utilidades.extension(foto1.getName())));
                Utilidades.copiarArchivo(foto2, new File("multimedia/" + uuid2 + "." + Utilidades.extension(foto2.getName())));
                Utilidades.copiarArchivo(foto3, new File("multimedia/" + uuid3 + "." + Utilidades.extension(foto3.getName())));
                Utilidades.copiarArchivo(foto4, new File("multimedia/" + uuid4 + "." + Utilidades.extension(foto4.getName())));

                ce.getEstacion().setLalitud(Double.parseDouble(txtLatitud.getText()));

                ce.getEstacion().setLongitud(Double.parseDouble(txtLongitud.getText()));
                ce.getEstacion().setNombre(txtNombre.getText());
                ce.getEstacion().setDecripcion(txtDescripcion.getText());
                ce.getEstacion().setAcceso(txtAcceso.getText());

                ce.getEstacion().setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                ce.getEstacion().setFoto1(uuid + Utilidades.extension(foto1.getName()));
                ce.getEstacion().setFoto2(uuid2 + Utilidades.extension(foto2.getName()));
                ce.getEstacion().setFoto3(uuid3 + Utilidades.extension(foto3.getName()));
                ce.getEstacion().setFoto4(uuid4 + Utilidades.extension(foto4.getName()));
                ce.getEstacion().setId(ce.generarId());
                if (ce.saved()) {
                    cargarTabla();
                    JOptionPane.showMessageDialog(null, "Se ha guardado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha guardado correctamente");
                }

            } catch (Exception e) {
            }
        }

    }

    private void filechooser() {
        JFileChooser fileChoosser = new JFileChooser();
        fileChoosser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Multimedia", "jpg", "png", "jpeg");
        fileChoosser.addChoosableFileFilter(filter);
        Integer i = fileChoosser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            foto1 = fileChoosser.getSelectedFile();
            txtFoto1.setText(foto1.getName());
            System.out.println("Si escogio" + foto1.getName());
        }
    }
    
    

    private void filechooserFoto2() {
        JFileChooser fileChoosser = new JFileChooser();
        fileChoosser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Multimedia", "jpg", "png", "jpeg");
        fileChoosser.addChoosableFileFilter(filter);
        Integer i = fileChoosser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            foto2 = fileChoosser.getSelectedFile();
            txtFoto2.setText(foto2.getName());
            System.out.println("Si escogio" + foto2.getName());
        }
    }

    private void filechooserFoto3() {
        JFileChooser fileChoosser = new JFileChooser();
        fileChoosser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Multimedia", "jpg", "png", "jpeg");
        fileChoosser.addChoosableFileFilter(filter);
        Integer i = fileChoosser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            foto3 = fileChoosser.getSelectedFile();
            txtFoto3.setText(foto3.getName());
            System.out.println("Si escogio" + foto3.getName());
        }
    }

    private void filechooserFoto4() {
        JFileChooser fileChoosser = new JFileChooser();
        fileChoosser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Multimedia", "jpg", "png", "jpeg");
        fileChoosser.addChoosableFileFilter(filter);
        Integer i = fileChoosser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            foto4 = fileChoosser.getSelectedFile();
            txtFoto4.setText(foto4.getName());
            System.out.println("Si escogio" + foto4.getName());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelReflect1 = new org.edisoncor.gui.panel.PanelReflect();
        txtNombre = new org.edisoncor.gui.textField.TextFieldRound();
        txtDescripcion = new org.edisoncor.gui.textField.TextFieldRound();
        txtAcceso = new org.edisoncor.gui.textField.TextFieldRound();
        txtFoto1 = new org.edisoncor.gui.textField.TextFieldRound();
        txtFoto2 = new org.edisoncor.gui.textField.TextFieldRound();
        txtFoto3 = new org.edisoncor.gui.textField.TextFieldRound();
        txtCapacidad = new org.edisoncor.gui.textField.TextFieldRound();
        txtFoto4 = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLatitud = new org.edisoncor.gui.textField.TextFieldRound();
        txtLongitud = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelReflect1.setBackground(new java.awt.Color(255, 255, 255));

        txtDescripcion.setMinimumSize(new java.awt.Dimension(29, 20));

        txtFoto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFoto1MouseClicked(evt);
            }
        });

        txtFoto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFoto2MouseClicked(evt);
            }
        });

        txtFoto3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFoto3MouseClicked(evt);
            }
        });

        txtFoto4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFoto4MouseClicked(evt);
            }
        });

        jLabel1.setText("Nombre:");

        jLabel2.setText("Descripcion:");

        jLabel3.setText("Acceso:");

        jLabel4.setText("Capacidad:");

        jLabel5.setText("Foto 1");

        jLabel6.setText("Foto 2");

        jLabel8.setText("Foto 4:");

        jLabel9.setText("Longitud:");

        jLabel10.setText("Latitud:");

        jLabel12.setText("Foto 3");

        javax.swing.GroupLayout panelReflect1Layout = new javax.swing.GroupLayout(panelReflect1);
        panelReflect1.setLayout(panelReflect1Layout);
        panelReflect1Layout.setHorizontalGroup(
            panelReflect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReflect1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelReflect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAcceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(panelReflect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12)
                    .addComponent(txtFoto1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                    .addComponent(txtFoto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFoto3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelReflect1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8))
                    .addComponent(txtFoto4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(80, 80, 80)
                .addGroup(panelReflect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
        panelReflect1Layout.setVerticalGroup(
            panelReflect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelReflect1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelReflect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelReflect1Layout.createSequentialGroup()
                        .addGroup(panelReflect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelReflect1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel6)
                                .addGap(12, 12, 12)
                                .addComponent(txtFoto2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelReflect1Layout.createSequentialGroup()
                                .addComponent(txtFoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFoto3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFoto4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelReflect1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
            .addGroup(panelReflect1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(15, 15, 15)
                .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(12, 12, 12)
                .addComponent(txtLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(panelReflect1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 930, -1));

        tbl1.setBackground(new java.awt.Color(190, 224, 192));
        tbl1.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl1.setGridColor(new java.awt.Color(204, 255, 204));
        tbl1.setSelectionBackground(new java.awt.Color(204, 255, 204));
        jScrollPane1.setViewportView(tbl1);

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Tabla de Estaciones");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(jLabel7)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 930, 300));

        jLabel13.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 102));
        jLabel13.setText("PARQUE PODOCARPUS ESTACIONES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(412, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel13)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 1080, -1));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setForeground(new java.awt.Color(204, 255, 204));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/d3/github/workflows/camara.png"))); // NOI18N
        jButton6.setText("Cargar Foto 1");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/d3/github/workflows/camara.png"))); // NOI18N
        jButton3.setText("Cargar Foto 2");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/d3/github/workflows/camara.png"))); // NOI18N
        jButton1.setText("Cargar Foto 3");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/d3/github/workflows/camara.png"))); // NOI18N
        jButton2.setText("Cargar Foto 4");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setText("Guardar");
        jButton5.setMaximumSize(new java.awt.Dimension(103, 23));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(169, 215, 194));
        jPanel4.setForeground(new java.awt.Color(190, 224, 192));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Menu");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel11)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addGap(21, 21, 21))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(277, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        filechooserFoto3();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        filechooserFoto2();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        filechooserFoto4();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      filechooser();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
      if (evt.getClickCount() >= 2) {
            new FrmFoto(true, foto1).setVisible(true);
        }
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
       if (evt.getClickCount() >= 2) {
            new FrmFoto(true, foto2).setVisible(true);
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (evt.getClickCount() >= 2) {
            new FrmFoto(true, foto3).setVisible(true);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
         if (evt.getClickCount() >= 2) {
            new FrmFoto(true, foto4).setVisible(true);
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void txtFoto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFoto1MouseClicked
       if (evt.getClickCount() >= 2) {
            new FrmFoto(true, foto1).setVisible(true);
        }
    }//GEN-LAST:event_txtFoto1MouseClicked

    private void txtFoto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFoto2MouseClicked
      if (evt.getClickCount() >= 2) {
            new FrmFoto(true, foto2).setVisible(true);
        }
    }//GEN-LAST:event_txtFoto2MouseClicked

    private void txtFoto3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFoto3MouseClicked
       if (evt.getClickCount() >= 2) {
            new FrmFoto(true, foto3).setVisible(true);
        }
    }//GEN-LAST:event_txtFoto3MouseClicked

    private void txtFoto4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFoto4MouseClicked
        if (evt.getClickCount() >= 2) {
            new FrmFoto(true, foto4).setVisible(true);
        }
    }//GEN-LAST:event_txtFoto4MouseClicked

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
                new FrmEstacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.PanelReflect panelReflect1;
    private javax.swing.JTable tbl1;
    private org.edisoncor.gui.textField.TextFieldRound txtAcceso;
    private org.edisoncor.gui.textField.TextFieldRound txtCapacidad;
    private org.edisoncor.gui.textField.TextFieldRound txtDescripcion;
    private org.edisoncor.gui.textField.TextFieldRound txtFoto1;
    private org.edisoncor.gui.textField.TextFieldRound txtFoto2;
    private org.edisoncor.gui.textField.TextFieldRound txtFoto3;
    private org.edisoncor.gui.textField.TextFieldRound txtFoto4;
    private org.edisoncor.gui.textField.TextFieldRound txtLatitud;
    private org.edisoncor.gui.textField.TextFieldRound txtLongitud;
    private org.edisoncor.gui.textField.TextFieldRound txtNombre;
    // End of variables declaration//GEN-END:variables
}
