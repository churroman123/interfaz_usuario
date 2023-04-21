
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CAlmacen {

    int idAlmacen;
    String codProd;
    int cantidad;
    String desc;
    String nomProv;
    double precio;
    String tipo;
    String nombreProducto;

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    
    public void mostrarStock(JTable TablaStock){
    
        CConexion con = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="select * from almacen";
        
        modelo.addColumn("id");
        modelo.addColumn("codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("cantidad");
        modelo.addColumn("descripción");
        modelo.addColumn("proveedor");
        modelo.addColumn("precio");
        
        TablaStock.setModel(modelo);
        
        String [] datos = new String[7];
        
        Statement st;
        
        try {
            st = con.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(7);
                datos[3] = rs.getString(3);
                datos[4] = rs.getString(4);
                datos[5] = rs.getString(5);
                datos[6] = rs.getString(6);
                
                
                modelo.addRow(datos);
                
            }
            
            TablaStock.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
        
        
    }
    
    public void agregarProducto(JTextField codigo,JTextField nombre,JTextField cantidad,JTextField descripcion, JTextField precio,JComboBox proveedor){
        
        setCodProd(codigo.getText());
        setNombreProducto(nombre.getText());
        setCantidad(Integer.parseInt(cantidad.getText()));
        setDesc(descripcion.getText());
        setPrecio(Double.parseDouble(precio.getText()));
        setNomProv(proveedor.getSelectedItem().toString());
        
        CConexion con = new CConexion();
        String sql = "Insert into almacen(cod_prod,nombreprod,cant,des,precio,nomprov) values (?,?,?,?,?,?)";
        
        try{
            CallableStatement cs = con.establecerConexion().prepareCall(sql);
            
            cs.setString(1, getCodProd());
            cs.setString(2, getNombreProducto());
            cs.setInt(3, getCantidad());
            cs.setString(4, getDesc());
            cs.setDouble(5, getPrecio());
            cs.setString(6, getNomProv());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Producto registrado con exito");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al registrar: "+ ex.toString());
        }
    }
    
    public void seleccionarProdcuto(JTable Producto,JTextField ID,JTextField codigo,JTextField nombre, JTextField cantidad, JTextField descripcion, JTextField precio, JComboBox proveedor){
        try{
            int fila = Producto.getSelectedRow();
            
            if(fila>=0){
                ID.setText(Producto.getValueAt(fila, 0).toString());
                codigo.setText(Producto.getValueAt(fila, 1).toString());
                nombre.setText(Producto.getValueAt(fila, 2).toString());
                cantidad.setText(Producto.getValueAt(fila, 3).toString());
                descripcion.setText(Producto.getValueAt(fila, 4).toString());
                proveedor.setSelectedItem(Producto.getValueAt(fila, 5).toString());
                precio.setText(Producto.getValueAt(fila, 6).toString());
                
            }else{
                
                JOptionPane.showMessageDialog(null, "fila no seleccionada");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error: "+ex.toString());
        }
    }  
    
    public void eliminarProducto(JTextField ID){
        CConexion conn = new CConexion();
        
        String sql = "delete from almacen where idalmacen=?";
        setIdAlmacen(Integer.parseInt(ID.getText()));
        
        try{
            //conectamos  ala base de datos y mandamos la consulta.
            
            CallableStatement cs = conn.establecerConexion().prepareCall(sql);
            
            cs.setInt(1, getIdAlmacen());
            //
            cs.execute();
            //
            JOptionPane.showMessageDialog(null, "Producto Eliminado con exito");
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error"+ ex.toString());}
            
        }
    
    public void modificarProdcuto(JTextField ID,JTextField codigo,JTextField nombre, JTextField cantidad, JTextField descripcion, JTextField precio, JComboBox proveedor){
        CConexion conn = new CConexion();
        
        String sql = "update almacen set cod_prod = ?,nombreprod = ?,cant =?,des = ?,precio = ?,nomprov=? where idalmacen=?";
        setIdAlmacen(Integer.parseInt(ID.getText()));
        setCodProd(codigo.getText().toString());
        setNombreProducto(nombre.getText().toString());
        setCantidad(Integer.parseInt(cantidad.getText()));
        setDesc(descripcion.getText().toString());
        setPrecio(Double.parseDouble(precio.getText()));
        setNomProv(proveedor.getSelectedItem().toString());
        
        try{
            //conectamos  ala base de datos y mandamos la consulta.
            
            CallableStatement cs = conn.establecerConexion().prepareCall(sql);
            
            cs.setString(1, getCodProd());
            cs.setString(2, getNombreProducto());
            cs.setInt(3, getCantidad());
            cs.setString(4, getDesc());
            cs.setDouble(5, getPrecio());
            cs.setString(6, getNomProv());
            cs.setInt(7, getIdAlmacen());
            //
            cs.execute();
            //
            JOptionPane.showMessageDialog(null, "Producto Modificado con exito");
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error"+ ex.toString());
        }
    }  
}
