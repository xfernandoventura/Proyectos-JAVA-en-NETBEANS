
package acceso_a_datos;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import java.sql.Connection;
import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import logica_de_negocios.cargo;
public class cargoAD
{
   private String sql;
   private cargo c;
   private String msj;
    public String insertar(cargo c,Connection con,Statement stm)
  {      
        int id = 1;
        try
        {
           ResultSet rs= stm.executeQuery("SELECT max(idcargo) from cargos");
           rs.next();
           id = rs.getInt(1) + 1;
           c.setid(id);
        } 
        catch (Exception e)
        {
               JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        
            sql= "INSERT INTO cargos(idcargo,cargo)"
           + "VALUES ('";       
                sql += c.getid()+ "','"
                +c.getcargo()+ "');";    
        try 
        {
            con.setAutoCommit(false);
            con.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
            stm = (Statement)con.createStatement();
            stm.executeUpdate(sql);            
            con.commit();
            con.setAutoCommit(true);
            this.msj = "insercion correcta";
        } 
        catch (Exception e)
        {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return this.msj;
  }
   public void actualizar(cargo c, Connection con,Statement stm)
  {
        
            sql="UPDATE cargos SET ";       
        //sólo el índice del registro NO es actualizable
            sql +="cargo='"
                + c.getcargo() + "' WHERE idcargo=" + c.getid();
        try 
        {
            stm= con.createStatement();
            stm.executeUpdate(sql);
        } 
        catch (Exception e)
        { 
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
  }
   public void borrar(cargo c,Connection con,Statement stm)
  {
      sql=" DELETE FROM cargos WHERE idcargo=" + c.getid();
      try 
        {
            stm= con.createStatement();
            stm.executeUpdate(sql);
        } 
        catch (Exception e)
        { 
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
  }
   public ArrayList<cargo> listacargos(Connection con,Statement stm)
  {
        ArrayList<cargo> lcar = new ArrayList<cargo>();
        cargo c;
        try 
        {
            stm= con.createStatement();
            ResultSet rs= stm.executeQuery(
                    "SELECT idcargo,cargo FROM cargos"
                            + " ORDER BY cargo DESC");
            while(rs.next()) 
            {               
                    c = new cargo();
                    c.setid(rs.getInt(1));
                    c.setcargo(rs.getString(2));
                    lcar.add(c);
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lcar;
  } 
}
