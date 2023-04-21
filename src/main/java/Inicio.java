
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Inicio extends javax.swing.JFrame {

    public Inicio() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PasswordJTX = new javax.swing.JTextField();
        UsernameJTX = new javax.swing.JTextField();
        EnviarBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usrename");

        jLabel2.setText("Password");

        PasswordJTX.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        PasswordJTX.setNextFocusableComponent(PasswordJTX);
        PasswordJTX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordJTXActionPerformed(evt);
            }
        });

        UsernameJTX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameJTXActionPerformed(evt);
            }
        });

        EnviarBTN.setText("Enviar");
        EnviarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(UsernameJTX, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(PasswordJTX, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnviarBTN))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addComponent(UsernameJTX, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(PasswordJTX, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(EnviarBTN)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UsernameJTXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameJTXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameJTXActionPerformed

    private void PasswordJTXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordJTXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordJTXActionPerformed

    private void EnviarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarBTNActionPerformed
        //mi variable sera igual al valor ingresado en el Jtextfield
        String user = UsernameJTX.getText();
        String pass = PasswordJTX.getText();

        String url = "select username,pass,privilegio from empleados where username='" + user + "'";

        try {
            //OBJETO DE CONEXION
            CConexion con = new CConexion();
            // establecemos conexión con su metodo.
            Connection conexion = con.establecerConexion();
            
            //se envia el query a la base de datos en el string (URL)
            PreparedStatement ps = conexion.prepareStatement(url);
            ResultSet rs = ps.executeQuery();
            //si obtenemos un fila en la tabla
            if(rs.next()){
                //Si existe el usuario obtendremos sus datos
                String u = rs.getString("username");
                String p = rs.getString("pass");
                String priv = rs.getString("privilegio");
                
                if(pass.equals(p)){
                    //vamos a JFrame correspondiente
                    if(priv.equals("Secre")){
                        Secretaria ventanaSecre = new Secretaria();
                        ventanaSecre.setVisible(true);
                        this.setVisible(false);
                        
                    }else if(priv.equals("Gerente")){
                        Gerente ventanaGerente = new Gerente();
                        ventanaGerente.setVisible(true);
                        this.setVisible(false);
                    }
                    else if(priv.equals("Analista")){
                        Analista ventanaAnalista = new Analista();
                        ventanaAnalista.setVisible(true);
                        this.setVisible(false);
                    }
                    else if(priv.equals("Almacen")){
                        Almacen ventanaAlmacen = new Almacen();
                        ventanaAlmacen.setVisible(true);
                        this.setVisible(false);
                    }
                    
                }else{
                    //la contraseña es incorrecta
                    JOptionPane.showMessageDialog(null,"LA CONTRASEÑA ES INCORRECTA");
                }
             
            }else{
                //no existe el usuario
                JOptionPane.showMessageDialog(null,"EL USUARIO NO EXISTE");
            }
            
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_EnviarBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EnviarBTN;
    private javax.swing.JTextField PasswordJTX;
    private javax.swing.JTextField UsernameJTX;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
