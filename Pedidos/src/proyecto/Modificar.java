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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.ListModel;

/**
 *
 * @author usuario
 */
public class Modificar extends javax.swing.JFrame {

/**
 *
    /**
     * Creates new form Modificar
     */
      ArrayList<JRadioButton> botones = new ArrayList<>();
      private String codigo = "";
      private String direccion = "";
      private String codpostal = "";
      private String cargo = "";
      private String fechapedido = "";
      private String fechaenvio = "";
      private String compenvio = "";
      private String cliente = "";
      private String ciudad = "";
      private String nombreEmpleado = "";
      private String codigo_pedido = "";

    public Modificar() {
        initComponents();
        this.setTitle("Modificar fila");
        error.setVisible(false);
        setResizable(false);
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
         
         try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
      public void setCodigo(String cod){
        codigo = cod;
    }
    
    public void anadirCliente(String cliente){
        clienteCompania.setText(cliente);
    }
    
    public void pasarDatos(String direccion,String codpostal,String cargo,String fechapedido,String fechaenvio,String compenvio,String cliente,String ciudad,String nombreEmpleado,String codigo_pedido){
        this.direccion = direccion;
        this.codpostal = codpostal;
        this.cargo = cargo;
        this.fechapedido = fechapedido;
        this.fechaenvio = fechaenvio;
        this.compenvio = compenvio;
        this.cliente = cliente;
        this.ciudad = ciudad;
        this.nombreEmpleado = nombreEmpleado;
        this.codigo_pedido = codigo_pedido;
        
        Direccion.setText(direccion);
        CodigoPostal.setText(codpostal);
        Cargo.setText(cargo);
        FechaPedido.setText(fechapedido);
        FechaEnvio.setText(fechaenvio);
        clienteCompania.setText(cliente);
        ListModel<String> modelo = companiasList.getModel();
        for (int i = 0; i < modelo.getSize(); i++) {
        if (modelo.getElementAt(i).equals(compenvio)) {
            companiasList.setSelectedIndex(i); // Seleccionar el índice correspondiente
        }}
        for(int i=0; i<botones.size(); i++){
            if(botones.get(i).getText().equals(ciudad)){
                botones.get(i).setSelected(true);
            }
        }
        for (int i = 0; i < empleadoList.getModel().getSize(); i++) {
        if (empleadoList.getModel().getElementAt(i).equals(nombreEmpleado)) {
        empleadoList.setSelectedIndex(i); // Selecciona el índice encontrado
    }
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
        jLabel7 = new javax.swing.JLabel();
        clienteCompania = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ciudadesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        empleadoList = new javax.swing.JList<>();
        guardar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Direccion: ");

        jLabel2.setText("Codigo postal: ");

        jLabel3.setText("Cargo (total €): ");

        jLabel4.setText("Fecha pedido: ");

        jLabel5.setText("Fecha envío: ");

        jLabel6.setText("Compañía de envíos: ");

        companiasList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:" }));

        jLabel7.setText("Cliente:");

        clienteCompania.setEditable(false);
        clienteCompania.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteCompaniaMouseClicked(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ciudades"));

        ciudadesPanel.setLayout(new javax.swing.BoxLayout(ciudadesPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(ciudadesPanel);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados"));

        jScrollPane2.setViewportView(empleadoList);

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        error.setForeground(java.awt.Color.red);
        error.setText("faltan datos por introducir");
        jPanel1.add(error);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CodigoPostal))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(companiasList, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(39, 39, 39)
                                        .addComponent(clienteCompania, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(FechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(FechaEnvio, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(guardar)
                                                .addGap(58, 58, 58)))))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(CodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(FechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(FechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(companiasList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(clienteCompania, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addComponent(guardar)))
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clienteCompaniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteCompaniaMouseClicked
        // TODO add your handling code here:
        ClienteModificar cliente = new ClienteModificar(this,true);
        cliente.setSize(new Dimension(870,650));
        cliente.setVisible(true);
    }//GEN-LAST:event_clienteCompaniaMouseClicked

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
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
        
        String sql = "select codigo from clientes where nomcompania = '" + clienteCompania.getText() + "';";
        Statement stmt = null;
        ResultSet resul = null;
        int codigo_cliente = -1;
        try {
           stmt = con.createStatement(); //con contiene la conexión a la base de datos.
           resul = stmt.executeQuery(sql);
           while(resul.next()){
               codigo_cliente = resul.getInt("codigo");
           }
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
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
         String codCiudad = "";
         try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
               codCiudad = resul.getString("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           sql = "select codigo from compenvios where nombre = '" + companiasList.getItemAt(companiasList.getSelectedIndex()) + "';";
         try {
           stmt = con.createStatement(); //con contiene la conexión a la base de datos.
           resul = stmt.executeQuery(sql); //sql contiene la sentencia “Select”.
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
         String codEmpEnvio = "";
         try {
            //RECORREMOS LOS DATOS
            while (resul.next()) {
               codEmpEnvio = resul.getString("codigo");
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
         String codEmpleado = "";
     try {
            //RECORREMOS LOS DATOS
            
            while (resul.next()) {
               codEmpleado = resul.getString("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         sql = "update pedidos set direccion = '" + Direccion.getText() + "', codpostal = '" + CodigoPostal.getText() + "', cargo = " + Cargo.getText() + ", fechapedido = '" + FechaPedido.getText() + "', fechaenvio = '" + FechaEnvio.getText() + "', codcliente = " + codigo_cliente + ", codciudad = " + codCiudad + ", codEmpleado = " + codEmpleado +", codempreenvio = " + codEmpEnvio + " where codigo = " + codigo_pedido +";";
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
        
         try {
            //cerramos conexion
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }   
         this.dispose();
        }
    }//GEN-LAST:event_guardarActionPerformed

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
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Cargo;
    private javax.swing.JTextField CodigoPostal;
    private javax.swing.JTextField Direccion;
    private javax.swing.JTextField FechaEnvio;
    private javax.swing.JTextField FechaPedido;
    private javax.swing.JPanel ciudadesPanel;
    private javax.swing.JTextField clienteCompania;
    private javax.swing.JComboBox<String> companiasList;
    private javax.swing.JList<String> empleadoList;
    private javax.swing.JLabel error;
    private javax.swing.ButtonGroup grupoBotonesCiudades;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
