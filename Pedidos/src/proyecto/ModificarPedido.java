/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class ModificarPedido extends javax.swing.JFrame {

    /**
     * Creates new form ModificarPedido
     */
     ArrayList<JRadioButton> botones = new ArrayList<>();
     private String codigo = "";
    public ModificarPedido() {
        initComponents();
        this.setTitle("Modificar pedido");
        error.setVisible(false);
        this.setResizable(false);
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
                CiudadesPanel1.add(radio);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        fechaPedido = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        CiudadesPanel = new javax.swing.JScrollPane();
        CiudadesPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        empleadoList = new javax.swing.JList<>();
        companiasList = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        Reiniciar = new javax.swing.JButton();
        Buscar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
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

        jPanel1.setBackground(new java.awt.Color(204, 204, 0));

        jLabel8.setText("Fecha Pedido: ");

        jLabel1.setText("Nombre de la compañía cliente: ");

        jLabel2.setText("Compañía de envios: ");

        CiudadesPanel.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("Ciudades"));

        CiudadesPanel1.setLayout(new javax.swing.BoxLayout(CiudadesPanel1, javax.swing.BoxLayout.Y_AXIS));
        CiudadesPanel.setViewportView(CiudadesPanel1);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados"));

        jScrollPane3.setViewportView(empleadoList);

        companiasList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:" }));
        companiasList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companiasListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(companiasList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CiudadesPanel)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(fechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(companiasList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(103, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CiudadesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(17, 17, 17))))
        );

        Reiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/imagenes/actualizar.png"))); // NOI18N
        Reiniciar.setText("Reinciar");
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

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/imagenes/editar.png"))); // NOI18N
        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jPanel2.add(modificar);

        error.setForeground(java.awt.Color.red);
        error.setText("Selecciona una fila primero");
        jPanel3.add(error);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarActionPerformed
        // TODO add your handling code here:
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
        fechaPedido.setText("");
        cliente.setText("");
        companiasList.setSelectedIndex(0);
        empleadoList.clearSelection();
        grupoBotonesCiudades.clearSelection();
        String sql = "select clientes.nomcompania as cliente, concat(empleados.nombre, ' ', empleados.apellidos) as empleado, pedidos.fechapedido, pedidos.fechaentrega, ciudades.nombre as ciudad, compenvios.nombre as compenvio from clientes join pedidos on clientes.codigo = pedidos.codcliente join empleados on empleados.codigo = pedidos.codempleado join compenvios on compenvios.codigo = pedidos.codempreenvio join ciudades on ciudades.codigo = pedidos.codciudad;";
        mostrarContenidoTabla(con,sql);
        try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ReiniciarActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        // TODO add your handling code here:
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

    private void companiasListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companiasListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_companiasListActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
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
                 + "empleados.nombre as nombreEmpleado,"
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
                 Modificar pedido = new Modificar();
                 pedido.setVisible(true); 
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
                String codigo = resul.getString("codigo");
                String direccion = resul.getString("direccion");
                String codpostal = resul.getString("codpostal");
                String cargo = resul.getString("cargo");
                String fechapedido = resul.getString("fechapedido");
                String fechaenvio = resul.getString("fechaenvio");
                String compenvio = resul.getString("compenvio");
                String cliente = resul.getString("cliente");
                String ciudad = resul.getString("ciudad");
                String nombreEmpleado = resul.getString("nombreEmpleado");
                pedido.pasarDatos(direccion,codpostal,cargo,fechapedido,fechaenvio,compenvio,cliente,ciudad,nombreEmpleado, codigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_modificarActionPerformed

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
            java.util.logging.Logger.getLogger(ModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JScrollPane CiudadesPanel;
    private javax.swing.JPanel CiudadesPanel1;
    private javax.swing.JButton Reiniciar;
    private javax.swing.JTextField cliente;
    private javax.swing.JComboBox<String> companiasList;
    private javax.swing.JList<String> empleadoList;
    private javax.swing.JLabel error;
    private javax.swing.JTextField fechaPedido;
    private javax.swing.ButtonGroup grupoBotonesCiudades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton modificar;
    private javax.swing.JTable tablaPedidos;
    // End of variables declaration//GEN-END:variables
}
