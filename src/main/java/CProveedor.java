/*Antonio*/
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class CProveedor {
    int idProveedor;
    String nombre;
    String Telefono;

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
     
    public void insertarProveedor(JTextField Nombre,JTextField Telefono){
        setNombre(Nombre.getText().toString());
        setTelefono(Telefono.getText().toString());
        
        CConexion con = new CConexion();
        
        String sql = "insert into proveedores(nomprov,numtel) values (?,?)";
        
        try {
            CallableStatement cs = con.establecerConexion().prepareCall(sql);
            
            cs.setString(1, getNombre());
            cs.setString(2, getTelefono());
            
            cs.execute();
            
            Nombre.setText("");
            Telefono.setText("");
            JOptionPane.showMessageDialog(null, "Proveedor agregado exitosamente");
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: "+ e.toString());
        }
        
    }
    
    public void modificarProveedor(JTextField ID,JTextField Nombre,JTextField Telefono){
        setIdProveedor(Integer.parseInt(ID.getText()));
        setNombre(Nombre.getText().toString());
        setTelefono(Telefono.getText().toString());
        CConexion con = new CConexion();
        
        String sql = "update proveedores set nomprov=?,numtel=? where idprov=?";
        
        try {
            CallableStatement cs = con.establecerConexion().prepareCall(sql);
            
            cs.setString(1, getNombre());
            cs.setString(2, getTelefono());
            cs.setInt(3, getIdProveedor());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Proveedor modificado exitosamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: "+ e.toString());
        }
    }
    
    public void eliminarProveedor(JTextField ID){
        CConexion conn = new CConexion();
        setIdProveedor(Integer.parseInt(ID.getText()));
        String sql = "delete from proveedores where idprov=?";
        
        try{
            //conectamos  ala base de datos y mandamos la consulta.
            
            CallableStatement cs = conn.establecerConexion().prepareCall(sql);
            
            cs.setInt(1, getIdProveedor());
            //
            cs.execute();
            //
            System.out.println(getTelefono());
            JOptionPane.showMessageDialog(null, "Proveedor Eliminado con exito");
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error"+ ex.toString());}
    }
    
    public void mostrarProveedor(JTable Proveedor){
      CConexion con = new CConexion();
      DefaultTableModel modelo = new DefaultTableModel();
      String sql = "select * from proveedores";
      
      modelo.addColumn("ID");
      modelo.addColumn("Nombre");
      modelo.addColumn("Telefono");
      
      String [] datos = new String[3];
     
      try{
          
          Statement st = con.establecerConexion().createStatement();
          ResultSet rs = st.executeQuery(sql);
          while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                modelo.addRow(datos);

            }

            Proveedor.setModel(modelo);
          
           
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null, "error:"+ ex.toString());
      }
      
    }
    
    public void seleccionarProveedor(JTable Proveedor,JTextField ID,JTextField Nombre,JTextField Telefono){
        try{
            int fila = Proveedor.getSelectedRow();
            
            if(fila>=0){
                ID.setText(Proveedor.getValueAt(fila, 0).toString());
                Nombre.setText(Proveedor.getValueAt(fila, 1).toString());
                Telefono.setText(Proveedor.getValueAt(fila, 2).toString());
            }else{
                
                JOptionPane.showMessageDialog(null, "fila no seleccionada");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error: "+ex.toString());
        }
    }
    
    
}
