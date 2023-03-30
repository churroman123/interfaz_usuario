

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CConexion{
    Connection conectar = null;
    String usuario = "postgres";
    String contraseña = "123456789";
    String bd = "BD_ArtesGraficas";
    String ip= "localhost";
    String puerto = "5432";
    
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerConexion(){
        
        try{
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contraseña);
            //JOptionPane.showMessageDialog(null,"CONEXIÓN EXITOSA" );

            return conectar;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error de conexión: " +e );
            return null;
        }
        
    }
}