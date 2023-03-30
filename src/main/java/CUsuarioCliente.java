
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class CUsuarioCliente {
    int idus;
    String username;
    String pass_us;
    String nomus;
    String ap_pu;
    String ap_mu;
    String gen;
    String estado;
    String cp;
    String calle;
    String numcasa;

    public int getIdus() {
        return idus;
    }

    public void setIdus(int idus) {
        this.idus = idus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass_us() {
        return pass_us;
    }

    public void setPass_us(String pass_us) {
        this.pass_us = pass_us;
    }

    public String getNomus() {
        return nomus;
    }

    public void setNomus(String nomus) {
        this.nomus = nomus;
    }

    public String getAp_pu() {
        return ap_pu;
    }

    public void setAp_pu(String ap_pu) {
        this.ap_pu = ap_pu;
    }

    public String getAp_mu() {
        return ap_mu;
    }

    public void setAp_mu(String ap_mu) {
        this.ap_mu = ap_mu;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumcasa() {
        return numcasa;
    }

    public void setNumcasa(String numcasa) {
        this.numcasa = numcasa;
    }
    
    public void mostrarUsClientes(JTable TablaUsClientes){
        CConexion con = new CConexion();
        
        DefaultTableModel model = new DefaultTableModel ();
        
        String sql = "select idus,username,nomus,ap_pu,ap_mu,gen,estad,cp,calle,numcas from us_clientes";
        
        model.addColumn("id usuario");
        model.addColumn("username");
        model.addColumn("nombre");
        model.addColumn("Apellido Pa.");
        model.addColumn("Apellido Ma.");
        model.addColumn("genero");
        model.addColumn("estado");
        model.addColumn("CP");
        model.addColumn("calle");
        model.addColumn("#casa");
        
        
        TablaUsClientes.setModel(model);
        
        String [] datos = new String[10];
        
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
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                
                model.addRow(datos);
            }
            
            TablaUsClientes.setModel(model);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }
    
    public void busquedaUsClientes(JTable TablaUsClientes, String user){
        String username = user;
        
        CConexion con = new CConexion();
        
        String sql ="select idus,username,nomus,ap_pu,ap_mu,gen,estad,cp,calle,numcas from us_clientes where us";
        
        
    }
}
