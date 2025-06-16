/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package proyecto;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class ClienteAnadir extends javax.swing.JDialog {

    /**
     * Creates new form Cliente
     */
    private ArrayList<JRadioButton> botones = new ArrayList<>();
    AnadirPedido padre = null;
    public ClienteAnadir(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Añadir cliente");
        this.setResizable(false);
        error.setVisible(false);
        padre = (AnadirPedido)parent;
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
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           sql = "Select * from cargos";
         
         try {
            stmt = con.createStatement(); //con contiene la conexión a la base de datos.
            resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
               String nombre = resul.getString("nombre");
               this.cargoList.addItem(nombre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         sql = "select clientes.nomcompania, clientes.nomcontacto,cargos.nombre as cargo, clientes.direccion,ciudades.nombre as ciudad,codigopostal,telefono from clientes join cargos on cargos.codigo = clientes.codcargo join ciudades on ciudades.codigo = clientes.codciudad"; 
         try {
            stmt = con.createStatement(); //con contiene la conexión a la base de datos.
            resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         Object[] cabecera = new Object[] {"NombreCompania", "NombreContacto", "Cargo","Direccion","Ciudad","CodigoPostal","Telefono"};
         Object[][] datos = new Object[][]{};
         DefaultTableModel modelo = new DefaultTableModel(datos,cabecera);
         tablaClientes.setModel(modelo);
         try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
               String nombrecompania = resul.getString("nomcompania");
               String nombrecontacto = resul.getString("nomcontacto");
               String cargo = resul.getString("cargo");
               String direccion = resul.getString("direccion");
               String ciudad = resul.getString("ciudad");
               String codigopostal = resul.getString("codigopostal");
               String telefono = resul.getString("telefono");
               modelo.addRow(new Object[]{nombrecompania,nombrecontacto,cargo,direccion,ciudad,codigopostal,telefono}); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
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

        grupoBotonesCiudades = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombreCompania = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nombreContacto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cargoList = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        ciudadesPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Reiniciar = new javax.swing.JButton();
        anadir = new javax.swing.JButton();
        Buscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaClientes);

        jLabel1.setText("Nombre de la compañía:");

        jLabel2.setText("Nombre de contacto:");

        jLabel3.setText("Cargo:");

        cargoList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:" }));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ciudades"));

        ciudadesPanel.setLayout(new javax.swing.BoxLayout(ciudadesPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(ciudadesPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cargoList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(nombreCompania)
                        .addComponent(nombreContacto, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombreCompania, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cargoList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 40, 5));

        Reiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/imagenes/actualizar.png"))); // NOI18N
        Reiniciar.setText("Reiniciar");
        Reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarActionPerformed(evt);
            }
        });
        jPanel2.add(Reiniciar);

        anadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/imagenes/anadir.png"))); // NOI18N
        anadir.setText("Añadir");
        anadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirActionPerformed(evt);
            }
        });
        jPanel2.add(anadir);

        Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/imagenes/buscar (1).png"))); // NOI18N
        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        jPanel2.add(Buscar);

        error.setForeground(java.awt.Color.red);
        error.setText("Selecciona una fila primero");
        jPanel3.add(error);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        
        String companiaElegida = nombreCompania.getText();
        if(companiaElegida.equals("")){
            companiaElegida = "clientes.nomcompania";
        }else{
            companiaElegida = "'"+companiaElegida+"'";
        }
        
        String contactoElegido = nombreContacto.getText();
        if(contactoElegido.equals("")){
            contactoElegido = "clientes.nomcontacto";
        }else{
            contactoElegido = "'"+contactoElegido+"'";
        }
        
        String cargoElegido = (String) cargoList.getSelectedItem();
        if(cargoElegido.equals("Seleccionar:")){
            cargoElegido = "cargos.nombre";
        }else{
            cargoElegido = "'"+cargoElegido+"'";
        }
        
        String botonElegido = "ciudades.nombre";
         for(int i=0; i<botones.size(); i++){
             if(botones.get(i).isSelected()){
                 botonElegido = "'"+botones.get(i).getText()+"'";
             }
         }
         
         String sql = "select clientes.nomcompania, clientes.nomcontacto,cargos.nombre as cargo, clientes.direccion,ciudades.nombre as ciudad,codigopostal,telefono from clientes join cargos on cargos.codigo = clientes.codcargo join ciudades on ciudades.codigo = clientes.codciudad where clientes.nomcompania = " + companiaElegida + " and clientes.nomcontacto = " + contactoElegido + " and cargos.nombre = " + cargoElegido + " and ciudades.nombre = " + botonElegido + ";"; 
         Statement stmt = null;
         ResultSet resul = null;
         try {
           stmt = con.createStatement(); //con contiene la conexión a la base de datos.
           resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         Object[] cabecera = new Object[] {"NombreCompania", "NombreContacto", "Cargo","Direccion","Ciudad","CodigoPostal","Telefono"};
         Object[][] datos = new Object[][]{};
         DefaultTableModel modelo = new DefaultTableModel(datos,cabecera);
         tablaClientes.setModel(modelo);
         try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
               String nombrecompania = resul.getString("nomcompania");
               String nombrecontacto = resul.getString("nomcontacto");
               String cargo = resul.getString("cargo");
               String direccion = resul.getString("direccion");
               String ciudad = resul.getString("ciudad");
               String codigopostal = resul.getString("codigopostal");
               String telefono = resul.getString("telefono");
               modelo.addRow(new Object[]{nombrecompania,nombrecontacto,cargo,direccion,ciudad,codigopostal,telefono}); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BuscarActionPerformed

    private void ReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarActionPerformed
        // TODO add your handling code here:
        error.setVisible(false);
        nombreCompania.setText("");
        nombreContacto.setText("");
        cargoList.setSelectedIndex(0);
        grupoBotonesCiudades.clearSelection();
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
         
         String sql = "select clientes.nomcompania, clientes.nomcontacto,cargos.nombre as cargo, clientes.direccion,ciudades.nombre as ciudad,codigopostal,telefono from clientes join cargos on cargos.codigo = clientes.codcargo join ciudades on ciudades.codigo = clientes.codciudad;"; 
         Statement stmt = null;
         ResultSet resul = null;
         try {
           stmt = con.createStatement(); //con contiene la conexión a la base de datos.
           resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         Object[] cabecera = new Object[] {"NombreCompania", "NombreContacto", "Cargo","Direccion","Ciudad","CodigoPostal","Telefono"};
         Object[][] datos = new Object[][]{};
         DefaultTableModel modelo = new DefaultTableModel(datos,cabecera);
         tablaClientes.setModel(modelo);
         try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
               String nombrecompania = resul.getString("nomcompania");
               String nombrecontacto = resul.getString("nomcontacto");
               String cargo = resul.getString("cargo");
               String direccion = resul.getString("direccion");
               String ciudad = resul.getString("ciudad");
               String codigopostal = resul.getString("codigopostal");
               String telefono = resul.getString("telefono");
               modelo.addRow(new Object[]{nombrecompania,nombrecontacto,cargo,direccion,ciudad,codigopostal,telefono}); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ReiniciarActionPerformed

    private void anadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirActionPerformed
        // TODO add your handling code here:
       int row = tablaClientes.getSelectedRow();
      if(row < 0){
           error.setVisible(true);
       }else{
           error.setVisible(false);
       String sql = "select clientes.codigo as codigo, clientes.nomcompania as nomcompania from clientes join cargos on cargos.codigo = clientes.codcargo join ciudades on ciudades.codigo = clientes.codciudad where clientes.nomcompania = '" + tablaClientes.getValueAt(row, 0) + "' and clientes.nomcontacto = '" + tablaClientes.getValueAt(row, 1) + "' and cargos.nombre = '" + tablaClientes.getValueAt(row, 2) + "' and clientes.direccion = '" + tablaClientes.getValueAt(row, 3)+ "'and ciudades.nombre = '" + tablaClientes.getValueAt(row, 4) + "' and clientes.codigopostal = '" + tablaClientes.getValueAt(row, 5) + "' and clientes.telefono = '" + tablaClientes.getValueAt(row, 6)+"';"; 
       initComponents();
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
               String codigo = resul.getString("codigo");
               String nombrecompania = resul.getString("nomcompania");
               padre.setCodigo(codigo);
               padre.anadirCliente(nombrecompania);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
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
            java.util.logging.Logger.getLogger(ClienteAnadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteAnadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteAnadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteAnadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClienteAnadir dialog = new ClienteAnadir(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JButton Reiniciar;
    private javax.swing.JButton anadir;
    private javax.swing.JComboBox<String> cargoList;
    private javax.swing.JPanel ciudadesPanel;
    private javax.swing.JLabel error;
    private javax.swing.ButtonGroup grupoBotonesCiudades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombreCompania;
    private javax.swing.JTextField nombreContacto;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
