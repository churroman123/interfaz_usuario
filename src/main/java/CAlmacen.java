
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CAlmacen {

    int idAlmacen;
    String codProd;
    int cantidad;
    String desc;
    String nomProv;
    float precio;

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNomProv() {
        return nomProv;
    }

    public void setNomProv(String nomProv) {
        this.nomProv = nomProv;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    
    public void mostrarStock(JTable TablaStock){
    
        CConexion con = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="select * from almacen";
        
        modelo.addColumn("id");
        modelo.addColumn("codigo");
        modelo.addColumn("cantidad");
        modelo.addColumn("descripción");
        modelo.addColumn("proveedor");
        modelo.addColumn("precio");
        
        TablaStock.setModel(modelo);
        
        String [] datos = new String[6];
        
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
            
            TablaStock.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
        
        
    }
}
