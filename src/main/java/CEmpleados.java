
import java.sql.*;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class CEmpleados {
    int idEmpleado;
    String username;
    String password;
    String nombre;
    String apPaterno;
    String apMaterno;
    String genero;
    String telefono;
    String cargo;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
  
    
    
    public void insertarEmpleado(String username, String password, String nombre, String apPaterno, String apMaterno, String genero, String telefono, String cargo){
        CConexion conn = new CConexion();
        String sql = "insert into empleados(username,pass,nom,ap_p,ap_m,gen,numtel,privilegio)values(?,?,?,?,?,?,?,?)";
        
        setUsername(username);
        setPassword(password);
        setNombre(nombre);
        setApPaterno(apPaterno);
        setApMaterno(apMaterno);
        setGenero(genero);
        setTelefono(telefono);
        setCargo(cargo);
        
        System.out.println(getGenero());
        
        try{
            //conectamos  ala base de datos y mandamos la consulta.
            
            CallableStatement cs = conn.establecerConexion().prepareCall(sql);
            
            cs.setString(1, getUsername());
            cs.setString(2, getPassword());
            cs.setString(3, getNombre());
            cs.setString(4, getApPaterno());
            cs.setString(5, getApMaterno());
            cs.setString(6, getGenero());
            cs.setString(7, getTelefono());
            cs.setString(8, getCargo());
            //
            System.out.println(getGenero());
            cs.execute();
            //
            System.out.println(getTelefono());
            JOptionPane.showMessageDialog(null, "Empleado registrado con exito");
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error"+ ex.toString());}
            
        }
        
    public void mostrarEmpleados(JTable Empleados){
        CConexion con = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "select idempleado,username,nom,ap_p,ap_m,gen,numtel,privilegio from empleados;";
        
        
        modelo.addColumn("ID");
        modelo.addColumn("Username");
        modelo.addColumn("Nombre");
        modelo.addColumn("Ap. Paterno");
        modelo.addColumn("Ap. Materno");
        modelo.addColumn("Genero");
        modelo.addColumn("Telefono");
        modelo.addColumn("Cargo");
        
        Empleados.setModel(modelo);
        
         String[] datos = new String[8];

        //variables para mandar query
        Statement st;

        try {
            st = con.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);

                modelo.addRow(datos);

            }

            Empleados.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error:"+ e.toString());
        }
        
        
       
        
        
        
    }   
        
    public void seleccionarEmpleado(JTable Empleados,JTextField ID,JTextField username, JTextField nombre, JTextField apPaterno, JTextField apMaterno, JComboBox genero, JTextField telefono, JComboBox cargo){
        try{
            int fila = Empleados.getSelectedRow();
            
            if(fila>=0){
                ID.setText(Empleados.getValueAt(fila, 0).toString());
                username.setText(Empleados.getValueAt(fila, 1).toString());
                nombre.setText(Empleados.getValueAt(fila, 2).toString());
                apPaterno.setText(Empleados.getValueAt(fila, 3).toString());
                apMaterno.setText(Empleados.getValueAt(fila, 4).toString());
                telefono.setText(Empleados.getValueAt(fila, 6).toString());
                genero.setSelectedItem(Empleados.getValueAt(fila, 5).toString());
                cargo.setSelectedItem(Empleados.getValueAt(fila, 7).toString());
            }else{
                
                JOptionPane.showMessageDialog(null, "fila no seleccionada");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error: "+ex.toString());
        }
    }    
    
    public void modificarEmpleado(JTextField ID,JTextField username, JTextField nombre, JTextField apPaterno, JTextField apMaterno, JComboBox genero, JTextField telefono, JComboBox cargo){
        CConexion conn = new CConexion();
        
        String sql = "update empleados set username = ?,nom = ?,ap_p =?,ap_m = ?,gen = ?,numtel = ?,privilegio = ? where idempleado=?";
        setIdEmpleado(Integer.parseInt(ID.getText()));
        setUsername(username.getText());
        setNombre(nombre.getText());
        setApPaterno(apPaterno.getText());
        setApMaterno(apMaterno.getText());
        setGenero(genero.getSelectedItem().toString());
        setTelefono(telefono.getText());
        setCargo(cargo.getSelectedItem().toString());
        
        try{
            //conectamos  ala base de datos y mandamos la consulta.
            
            CallableStatement cs = conn.establecerConexion().prepareCall(sql);
            
            cs.setString(1, getUsername());
            cs.setString(2, getNombre());
            cs.setString(3, getApPaterno());
            cs.setString(4, getApMaterno());
            cs.setString(5, getGenero());
            cs.setString(6, getTelefono());
            cs.setString(7, getCargo());
            cs.setInt(8, getIdEmpleado());
            //
            cs.execute();
            //
            System.out.println(getTelefono());
            JOptionPane.showMessageDialog(null, "Empleado Modificado con exito");
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error"+ ex.toString());}
            
        }
    
    public void eliminarEmpleado(JTextField ID){
        CConexion conn = new CConexion();
        
        String sql = "delete from empleados where idempleado=?";
        setIdEmpleado(Integer.parseInt(ID.getText()));
        
        try{
            //conectamos  ala base de datos y mandamos la consulta.
            
            CallableStatement cs = conn.establecerConexion().prepareCall(sql);
            
            cs.setInt(1, getIdEmpleado());
            //
            cs.execute();
            //
            System.out.println(getTelefono());
            JOptionPane.showMessageDialog(null, "Empleado Eliminado con exito");
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error"+ ex.toString());}
            
        }
    
    public void buscarEmpleadoUsername(JTable Empleado,String user){
        String username = user;
        //objeto conexión para conectar con la base de datos
        CConexion con = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        //definimos el modelo de la tabla como default
       
        
        String sql ="select idempleado,username,nom,ap_p,ap_m,gen,numtel,privilegio from empleados where username='"+username+"'";
        
        modelo.addColumn("ID");
        modelo.addColumn("Username");
        modelo.addColumn("Nombre");
        modelo.addColumn("Ap. Paterno");
        modelo.addColumn("Ap. Materno");
        modelo.addColumn("Genero");
        modelo.addColumn("Telefono");
        modelo.addColumn("Cargo");
        
        Empleado.setModel(modelo);
        String [] datos = new String[8];
        
        Statement st;
        
        try {
            st = con.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);               
                modelo.addRow(datos);
            }
            
            Empleado.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }
    
    public void buscarEmpleadoID(JTable Empleado, String id){
        String ID = id;
        //objeto conexión para conectar con la base de datos
        CConexion con = new CConexion();
        
        //definimos el modelo de la tabla como default
        DefaultTableModel model = new DefaultTableModel ();
        
        String sql ="select idempleado,username,nom,ap_p,ap_m,gen,numtel,privilegio from empleados where idempleado='"+ID+"'";
        
        model.addColumn("ID");
        model.addColumn("Username");
        model.addColumn("Nombre");
        model.addColumn("Ap. Paterno");
        model.addColumn("Ap. Materno");
        model.addColumn("Genero");
        model.addColumn("Telefono");
        model.addColumn("Cargo");
        
        Empleado.setModel(model);
        
        String [] datos = new String[8];
        
        Statement st;
        
        try {
            st = con.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);               
                model.addRow(datos);
            }
            
            Empleado.setModel(model);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }
    
    public void buscarEmpleadoIDUsername(JTable Empleado,String id,String user){
        String username = user;
        String ID = id;
        //objeto conexión para conectar con la base de datos
        CConexion con = new CConexion();
        
        //definimos el modelo de la tabla como default
        DefaultTableModel modelo = new DefaultTableModel ();
        
        String sql ="select idempleado,username,nom,ap_p,ap_m,gen,numtel,privilegio from empleados where username='"+username+"' and idempleado ="+ID;
        
        modelo.addColumn("ID");
        modelo.addColumn("Username");
        modelo.addColumn("Nombre");
        modelo.addColumn("Ap. Paterno");
        modelo.addColumn("Ap. Materno");
        modelo.addColumn("Genero");
        modelo.addColumn("Telefono");
        modelo.addColumn("Cargo");
        
        Empleado.setModel(modelo);
        
        String [] datos = new String[8];
        
        Statement st;
        
        try {
            st = con.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);               
                modelo.addRow(datos);
            }
            
            Empleado.setModel(modelo);
            
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }
}
    

