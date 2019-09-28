
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
import logica_de_negocios.tipopago;
public class tipopagoAD
{
  private String sql;
  private tipopago c;
  public void insertar(tipopago tip,Connection con,Statement stm)
  {      
        int id = 1;
        try
        {
           ResultSet rs= stm.executeQuery("SELECT max(idtipodepago) from tiposdepago");
           rs.next();
           id = rs.getInt(1) + 1;
           tip.setid(id);
        } 
        catch (Exception e)
        {
               JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        
            sql= "INSERT INTO tiposdepago(idtipodepago,tipodepago)"
           + "VALUES ('";       
                sql += tip.getid()+ "','"
                +tip.gettipopago()+ "');";    
        try 
        {
            con.setAutoCommit(false);
            con.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
            stm = (Statement)con.createStatement();
            stm.executeUpdate(sql);            
            con.commit();
            con.setAutoCommit(true);
        } 
        catch (Exception e)
        {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
  }
   public void actualizar(tipopago tip, Connection con,Statement stm)
  {
        
            sql="UPDATE tiposdepago SET ";       
        //sólo el índice del registro NO es actualizable
            sql +="tipodepago='"
                + c.gettipopago() + "' WHERE idtipodepago=" + tip.getid();
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
   public void borrar(tipopago tip,Connection con,Statement stm)
  {
      sql=" DELETE FROM tiposdepago WHERE idtipodepago=" + tip.getid();
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
   public ArrayList<tipopago> listatiposdepagos(Connection con,Statement stm)
  {
        ArrayList<tipopago> ltip = new ArrayList<tipopago>();
        tipopago tip;
        try 
        {
            stm= con.createStatement();
            ResultSet rs= stm.executeQuery(
                    "SELECT idtipodepago,tipodepago FROM tiposdepago"
                            + " ORDER BY tipodepago DESC");
            while(rs.next()) 
            {               
                    tip = new tipopago();
                    tip.setid(rs.getInt(1));
                    tip.settipopago(rs.getString(2));
                    ltip.add(tip);
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return ltip;
  } 
}
