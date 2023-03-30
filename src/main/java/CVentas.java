
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

public class CVentas {
    int idventa;
    float totalventa;
    String cliente;
    String cod_prod;
    int cantidad;
    String fecha;

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public float getTotalventa() {
        return totalventa;
    }

    public void setTotalventa(float totalventa) {
        this.totalventa = totalventa;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(String cod_prod) {
        this.cod_prod = cod_prod;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    //Metodos de busqueda
    public void MostrarVentas (JTable TablaTotalVentas){
        CConexion con = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="select * from ventas";
        
        modelo.addColumn("id");
        modelo.addColumn("total");
        modelo.addColumn("cliente");
        modelo.addColumn("Cod. Producto");
        modelo.addColumn("cantidad");
        modelo.addColumn("Fecha");
        
        TablaTotalVentas.setModel(modelo);
        
        String [] datos = new String[6];
        
        //variables para mandar query
        
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
                
                
                modelo.addRow(datos);
                
            }
            
            TablaTotalVentas.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
        
    }
    public void TablaBuscarIdVenta(JTable busquedaId,String idVenta){
        CConexion con = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="select * from ventas where idventa="+idVenta;
        
        modelo.addColumn("id");
        modelo.addColumn("total");
        modelo.addColumn("cliente");
        modelo.addColumn("Cod. Producto");
        modelo.addColumn("cantidad");
        modelo.addColumn("Fecha");
        
        busquedaId.setModel(modelo);
        
        String [] datos = new String[6];
        
        //variables para mandar query
        
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
                
                
                modelo.addRow(datos);
                
            }
            
            busquedaId.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }
    
    public void BuscarUsuario(JTable busquedaUsuario, String user){
        CConexion con = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="select * from ventas where cliente='"+user+"'";
        
        modelo.addColumn("id");
        modelo.addColumn("total");
        modelo.addColumn("cliente");
        modelo.addColumn("Cod. Producto");
        modelo.addColumn("cantidad");
        modelo.addColumn("Fecha");
        
        busquedaUsuario.setModel(modelo);
        
        String [] datos = new String[6];
        
        //variables para mandar query
        
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
                
                
                modelo.addRow(datos);
                
            }
            
            busquedaUsuario.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }
    
    public void BuscarUsuarioIdVenta(JTable busquedaUsuaroiId,String user,String id){
        CConexion con = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="select * from ventas where idventa="+id+"and cliente='"+user+"'";
        
        modelo.addColumn("id");
        modelo.addColumn("total");
        modelo.addColumn("cliente");
        modelo.addColumn("Cod. Producto");
        modelo.addColumn("cantidad");
        modelo.addColumn("Fecha");
        
        busquedaUsuaroiId.setModel(modelo);
        
        String [] datos = new String[6];
        
        //variables para mandar query
        
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
                
                
                modelo.addRow(datos);
                
            }
            
            busquedaUsuaroiId.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }
    
    public void MostrarVentasmeses (JTable TablaTotalVentas){
         CConexion con = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="select * from ventas where fecha between now() - interval '6 months' and now();";
        
        modelo.addColumn("id");
        modelo.addColumn("total");
        modelo.addColumn("cliente");
        modelo.addColumn("Cod. Producto");
        modelo.addColumn("cantidad");
        modelo.addColumn("Fecha");
        
        TablaTotalVentas.setModel(modelo);
        
        String [] datos = new String[6];
        
        //variables para mandar query
        
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
                
                
                modelo.addRow(datos);
                
            }
            
            TablaTotalVentas.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }
}
