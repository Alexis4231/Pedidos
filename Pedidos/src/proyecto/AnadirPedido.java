/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */

public class AnadirPedido extends javax.swing.JFrame {

    /**
     * Creates new form AnadirPedido
     */
    ArrayList<JRadioButton> botones = new ArrayList<>();
    private String codigo = "";
    public AnadirPedido() {
        initComponents();
        this.setTitle("Añadir pedido");
        setResizable(false);
        error.setVisible(false);
        String usuario = "SA";
        String clave = "SA";
        Connection con = null;
        String bbdd = "jdbc:hsqldb:hsql://localhost/";
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection(bbdd, usuario, clave);
            if (con!= null) {
                System.out.println("Connection created successfully");
            }else{
                System.out.println("Problem with creating connection");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }  
        
        String sql = "Select * from ciudades";
        ResultSet resul = null;
        Statement stmt;
      
        try {
            stmt = con.createStatement(); //con contiene la conexión a la base de datos.
            resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(AnadirPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
                String nombre = resul.getString("nombre");
                JRadioButton radio = new JRadioButton();
                radio.setText(nombre);
                grupoBotonesCiudades.add(radio);
                ciudadesPanel.add(radio);
                botones.add(radio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnadirPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         sql = "Select * from empleados";
         
         try {
            stmt = con.createStatement(); //con contiene la conexión a la base de datos.
            resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(AnadirPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         try {
            //RECORREMOS LOS DATOS
            DefaultListModel<String> modelo = new DefaultListModel<>();
            while (resul.next()) {
                String nombre = resul.getString("nombre");
                modelo.addElement(nombre);
            }
            this.empleadoList.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(AnadirPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          sql = "Select * from compenvios";
         
         try {
            stmt = con.createStatement(); //con contiene la conexión a la base de datos.
            resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(AnadirPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
                String nombre = resul.getString("nombre");
               this.companiasList.addItem(nombre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnadirPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        sql = "select clientes.nomcompania as cliente, concat(empleados.nombre, ' ', empleados.apellidos) as empleado, pedidos.fechapedido, pedidos.fechaentrega, ciudades.nombre as ciudad, compenvios.nombre as compenvio from clientes join pedidos on clientes.codigo = pedidos.codcliente join empleados on empleados.codigo = pedidos.codempleado join compenvios on compenvios.codigo = pedidos.codempreenvio join ciudades on ciudades.codigo = pedidos.codciudad;";
        mostrarContenidoTabla(con,sql);
         
         try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnadirPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mostrarContenidoTabla(Connection con, String sql){
        Statement stmt = null;
        ResultSet resul = null;
        try {
            stmt = con.createStatement(); //con contiene la conexión a la base de datos.
            resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         Object[] cabecera = new Object[] {"Cliente","Empleado", "FechaPedido", "FechaEntrega", "Ciudad", "CompañiaEnvio"};
         Object[][] datos = new Object[][]{};
         DefaultTableModel modelo = new DefaultTableModel(datos,cabecera);
         tablaPedidos.setModel(modelo);
         try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
               String cliente = resul.getString("cliente");
               String empleado = resul.getString("empleado");
               String fechapedido = resul.getString("fechapedido");
               String fechaentrega = resul.getString("fechaentrega");
               String ciudad = resul.getString("ciudad");
               String compenvio = resul.getString("compenvio");
               modelo.addRow(new Object[]{cliente,empleado,fechapedido,fechaentrega, ciudad, compenvio}); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnadirPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setCodigo(String cod){
        codigo = cod;
    }
    
    public void anadirCliente(String cliente){
        clienteCompania.setText(cliente);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotonesCiudades = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Direccion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        CodigoPostal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Cargo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        FechaPedido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        FechaEnvio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        companiasList = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ciudadesPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        empleadoList = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        clienteCompania = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        actualizar = new javax.swing.JButton();
        anadir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaPedidos);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(840, 275));

        jLabel1.setText("Direccion:");

        jLabel2.setText("Código postal:");

        jLabel3.setText("Cargo (total €): ");

        jLabel4.setText("Fecha pedido: ");

        jLabel5.setText("Fecha envío: ");

        jLabel6.setText("Compañía de envíos: ");

        companiasList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:" }));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ciudades"));

        ciudadesPanel.setLayout(new javax.swing.BoxLayout(ciudadesPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(ciudadesPanel);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados"));

        jScrollPane3.setViewportView(empleadoList);

        jLabel7.setText("Cliente:");

        clienteCompania.setEditable(false);
        clienteCompania.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteCompaniaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(companiasList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clienteCompania, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(FechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(20, 20, 20)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CodigoPostal)
                    .addComponent(FechaEnvio)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(CodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(FechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(FechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(companiasList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(clienteCompania, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setMaximumSize(new java.awt.Dimension(200, 33));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 40, 5));

        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/imagenes/actualizar.png"))); // NOI18N
        actualizar.setText("Actualizar");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        jPanel2.add(actualizar);

        anadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/imagenes/anadir.png"))); // NOI18N
        anadir.setText("Añadir");
        anadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirActionPerformed(evt);
            }
        });
        jPanel2.add(anadir);

        error.setForeground(java.awt.Color.red);
        jPanel3.add(error);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clienteCompaniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteCompaniaMouseClicked
        // TODO add your handling code here:
        ClienteAnadir cliente = new ClienteAnadir(this,true);
        cliente.setSize(new Dimension(870,650));
        cliente.setVisible(true);
    }//GEN-LAST:event_clienteCompaniaMouseClicked

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        // TODO add your handling code here:
        Direccion.setText("");
        CodigoPostal.setText("");
        Cargo.setText("");
        FechaPedido.setText("");
        FechaEnvio.setText("");
        companiasList.setSelectedIndex(0);
        clienteCompania.setText("");
        grupoBotonesCiudades.clearSelection();
        empleadoList.clearSelection();
        error.setVisible(false);
    }//GEN-LAST:event_actualizarActionPerformed

    private void anadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirActionPerformed
        // TODO add your handling code here:
        String ciudadElegida = "";
        for(int i=0; i<botones.size(); i++){
            if(botones.get(i).isSelected()){
                ciudadElegida = botones.get(i).getText();
            }
        }
        if(Direccion.getText().equals("") || CodigoPostal.getText().equals("") || Cargo.getText().equals("") || FechaPedido.getText().equals("") || FechaEnvio.getText().equals("") || companiasList.getItemAt(companiasList.getSelectedIndex()).equals("Seleccionar:") || clienteCompania.getText().equals("") || ciudadElegida.equals("") || empleadoList.getSelectedValue() == null){
            error.setText("Faltan datos por introducir");
            error.setForeground(Color.red);
            error.setVisible(true);
        }else{    
        String sql = "select codigo from compenvios where nombre = '" + companiasList.getItemAt(companiasList.getSelectedIndex()) + "';";
        String codEnvio = "";
        String codEmpleado = "";
        String codCiudad = "";
        String usuario = "SA";
        String clave = "SA";
        Connection con = null;
        String bbdd = "jdbc:hsqldb:hsql://localhost/";
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection(bbdd, usuario, clave);
            if (con!= null) {
                System.out.println("Connection created successfully");
            }else{
                System.out.println("Problem with creating connection");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }    
         Statement stmt = null;
         ResultSet resul = null;
         try {
           stmt = con.createStatement(); //con contiene la conexión a la base de datos.
           resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
               codEnvio = resul.getString("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
       sql = "select codigo from empleados where nombre = '" + empleadoList.getSelectedValue() + "';";
      
         try {
           stmt = con.createStatement(); //con contiene la conexión a la base de datos.
           resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
               codEmpleado = resul.getString("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }

       String botonElegido = "";
        for(int i=0; i<botones.size(); i++){
            if(botones.get(i).isSelected()){
                botonElegido = botones.get(i).getText();
            }
        }
        sql = "select codigo from ciudades where nombre = '" + botonElegido + "';";
         try {
           stmt = con.createStatement(); //con contiene la conexión a la base de datos.
           resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
               codCiudad = resul.getString("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        sql = "insert into pedidos(codCliente,codEmpleado,fechapedido,fechaentrega,fechaenvio,cargo,direccion,codciudad,codpostal,codempreenvio) values(" + codigo + ", " + codEmpleado + ", '" + FechaPedido.getText() + "', null, '" + FechaEnvio.getText() + "', " + Cargo.getText() + ", '" + Direccion.getText() + "', " + codCiudad + ", '" + CodigoPostal.getText() + "', '"+ codEnvio + "');";  
        try {
           stmt = con.createStatement(); //con contiene la conexión a la base de datos.
           stmt.executeUpdate(sql); //sql contiene la sentencia “Select”.

        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
       try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
       error.setText("Operación realizada correctamente");
       error.setForeground(Color.green);
       error.setVisible(true);
        }
    }//GEN-LAST:event_anadirActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AnadirPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnadirPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnadirPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnadirPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AnadirPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Cargo;
    private javax.swing.JTextField CodigoPostal;
    private javax.swing.JTextField Direccion;
    private javax.swing.JTextField FechaEnvio;
    private javax.swing.JTextField FechaPedido;
    private javax.swing.JButton actualizar;
    private javax.swing.JButton anadir;
    private javax.swing.JPanel ciudadesPanel;
    private javax.swing.JTextField clienteCompania;
    private javax.swing.JComboBox<String> companiasList;
    private javax.swing.JList<String> empleadoList;
    private javax.swing.JLabel error;
    private javax.swing.ButtonGroup grupoBotonesCiudades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaPedidos;
    // End of variables declaration//GEN-END:variables
}
