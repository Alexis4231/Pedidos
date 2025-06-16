/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author usuario
 */
public class BuscarPedido extends javax.swing.JFrame {

    /**
     * Creates new form BuscarPedido
     */
    
    private ArrayList<JRadioButton> botones = new ArrayList<>();
    private String codigo = "";
    public BuscarPedido() {
        initComponents();
        this.setTitle("Buscar pedido");
        this.setResizable(false);
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
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
                String nombre = resul.getString("nombre");
                JRadioButton radio = new JRadioButton();
                radio.setText(nombre);
                grupoBotonesCiudades.add(radio);
                CiudadesPanel.add(radio);
                botones.add(radio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          sql = "Select * from empleados";
         
         try {
            stmt = con.createStatement(); //con contiene la conexión a la base de datos.
            resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           sql = "Select * from compenvios";
         
        try {
            stmt = con.createStatement(); //con contiene la conexión a la base de datos.
            resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
                String nombre = resul.getString("nombre");
               this.companiasList.addItem(nombre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = "select clientes.nomcompania as cliente, concat(empleados.nombre, ' ', empleados.apellidos) as empleado, pedidos.fechapedido, pedidos.fechaentrega, ciudades.nombre as ciudad, compenvios.nombre as compenvio from clientes join pedidos on clientes.codigo = pedidos.codcliente join empleados on empleados.codigo = pedidos.codempleado join compenvios on compenvios.codigo = pedidos.codempreenvio join ciudades on ciudades.codigo = pedidos.codciudad;";
        mostrarContenidoTabla(con,sql);
         
         try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setCodigo(String cod){
       codigo = cod;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fechaPedido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        CiudadesPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        empleadoList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        companiasList = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        Reiniciar = new javax.swing.JButton();
        Buscar = new javax.swing.JButton();
        info = new javax.swing.JButton();
        error = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

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
        jScrollPane2.setViewportView(tablaPedidos);

        jPanel1.setBackground(new java.awt.Color(0, 255, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 400));

        jLabel1.setText("Fecha de pedido: ");

        jLabel2.setText("Nombre de la compañía cliente: ");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ciudades"));

        CiudadesPanel.setLayout(new javax.swing.BoxLayout(CiudadesPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(CiudadesPanel);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados"));

        jScrollPane3.setViewportView(empleadoList);

        jLabel3.setText("Compañía de envios: ");

        companiasList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(companiasList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(companiasList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 40, 5);
        flowLayout1.setAlignOnBaseline(true);
        jPanel2.setLayout(flowLayout1);

        Reiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/imagenes/actualizar.png"))); // NOI18N
        Reiniciar.setText("Reiniciar");
        Reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarActionPerformed(evt);
            }
        });
        jPanel2.add(Reiniciar);

        Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/imagenes/buscar (1).png"))); // NOI18N
        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        jPanel2.add(Buscar);

        info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/imagenes/info.png"))); // NOI18N
        info.setText("Info Fila");
        info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoActionPerformed(evt);
            }
        });
        jPanel2.add(info);

        jLabel4.setForeground(java.awt.Color.red);
        jLabel4.setText("Selecciona una fila primero");
        error.add(jLabel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(error, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
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
        error.setVisible(false);
        String companiaElegida = companiasList.getItemAt(companiasList.getSelectedIndex());
        if(companiaElegida.equals("Seleccionar:")){
            companiaElegida = "compenvios.nombre";
        }else{
            companiaElegida = "'"+companiaElegida+"'";
        }
        
        String empleadoElegido = empleadoList.getSelectedValue();
        if(empleadoElegido == null){
            empleadoElegido = "empleados.nombre";
        }else{
            empleadoElegido = "'"+empleadoElegido+"'";
        }
        
        String fechaElegida = fechaPedido.getText();
        if(fechaElegida.equals("")){
            fechaElegida = "pedidos.fechapedido";
        }else{
            fechaElegida = "'"+fechaElegida+"'";
        }   
        
        String ciudadElegida = "ciudades.nombre";
        for(int i=0; i<botones.size(); i++){
            if(botones.get(i).isSelected()){
                ciudadElegida = "'"+botones.get(i).getText()+"'";
            }
        }
        
        String clienteElegido = cliente.getText();
        if(clienteElegido.equals("")){
            clienteElegido = "clientes.nomcompania";
        }else{
            clienteElegido = "'"+clienteElegido+"'";
        }
       
        String sql = "select clientes.nomcompania as cliente, concat(empleados.nombre, ' ', empleados.apellidos) as empleado, pedidos.fechapedido, pedidos.fechaentrega, ciudades.nombre as ciudad, compenvios.nombre as compenvio from clientes join pedidos on clientes.codigo = pedidos.codcliente join empleados on empleados.codigo = pedidos.codempleado join compenvios on compenvios.codigo = pedidos.codempreenvio join ciudades on ciudades.codigo = pedidos.codciudad where compenvios.nombre = " + companiaElegida + " and empleados.nombre = " + empleadoElegido + " and pedidos.fechapedido = " + fechaElegida + " and ciudades.nombre = " + ciudadElegida + " and clientes.nomcompania = " + clienteElegido + ";";
        mostrarContenidoTabla(con, sql);
        try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }//GEN-LAST:event_BuscarActionPerformed

    private void ReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarActionPerformed
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
        fechaPedido.setText("");
        cliente.setText("");
        companiasList.setSelectedIndex(0);
        empleadoList.clearSelection();
        grupoBotonesCiudades.clearSelection();
        String sql = "select clientes.nomcompania as cliente, concat(empleados.nombre, ' ', empleados.apellidos) as empleado, pedidos.fechapedido, pedidos.fechaentrega, ciudades.nombre as ciudad, compenvios.nombre as compenvio from clientes join pedidos on clientes.codigo = pedidos.codcliente join empleados on empleados.codigo = pedidos.codempleado join compenvios on compenvios.codigo = pedidos.codempreenvio join ciudades on ciudades.codigo = pedidos.codciudad;";
        mostrarContenidoTabla(con,sql);
        error.setVisible(false);
        try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ReiniciarActionPerformed

    private void infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoActionPerformed
       int row = tablaPedidos.getSelectedRow();
       if(row < 0){
           error.setVisible(true);
       }else{
           error.setVisible(false);
           String fechaEntrega = (String) tablaPedidos.getValueAt(row,3);
            if(fechaEntrega == null){
                fechaEntrega = "is null";
            }else{
                fechaEntrega = "= '" + fechaEntrega + "' ";
            }
 String sql = "select pedidos.codigo as codigo, "
                 + "pedidos.codcliente as codcliente, "
                 + "clientes.nomcompania as cliente, "
                 + "pedidos.codempleado as codempleado, "
                 + "concat(empleados.nombre, ' ', empleados.apellidos) as nombreEmpleado,"
                 + " pedidos.fechapedido as fechapedido, "
                 + "pedidos.fechaenvio as fechaenvio,"
                 + " pedidos.fechaentrega,"
                 + " pedidos.cargo as cargo,"
                 + "pedidos.direccion as direccion, "
                 + "pedidos.codciudad as codciudad,"
                 + "ciudades.nombre as ciudad,"
                 + " pedidos.codpostal,"
                 + " pedidos.codempreenvio,"
                 + " compenvios.nombre as compenvio from clientes join pedidos on clientes.codigo = pedidos.codcliente join empleados on empleados.codigo = pedidos.codempleado join compenvios on compenvios.codigo = pedidos.codempreenvio join ciudades on ciudades.codigo = pedidos.codciudad where clientes.nomcompania = '" + tablaPedidos.getValueAt(row, 0) + "' and pedidos.fechapedido = '" + tablaPedidos.getValueAt(row, 2) + "' and pedidos.fechaentrega " + fechaEntrega + " and compenvios.nombre = '" + tablaPedidos.getValueAt(row, 5) + "';";
       Info info = new Info(this,true,sql);
       info.setSize(new Dimension(1500,80));
       info.setVisible(true); 
       }
    }//GEN-LAST:event_infoActionPerformed

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
            java.util.logging.Logger.getLogger(BuscarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JPanel CiudadesPanel;
    private javax.swing.JButton Reiniciar;
    private javax.swing.JTextField cliente;
    private javax.swing.JComboBox<String> companiasList;
    private javax.swing.JList<String> empleadoList;
    private javax.swing.JPanel error;
    private javax.swing.JTextField fechaPedido;
    private javax.swing.ButtonGroup grupoBotonesCiudades;
    private javax.swing.JButton info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaPedidos;
    // End of variables declaration//GEN-END:variables
}
