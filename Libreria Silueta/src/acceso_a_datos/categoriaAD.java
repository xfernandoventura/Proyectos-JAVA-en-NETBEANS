
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
import logica_de_negocios.categoria;
public class categoriaAD
{
   private String sql;
   private categoria c;
   private String msj;
    public String insertar(categoria c,Connection con,Statement stm)
  {      
        int id = 1;
        try
        {
           ResultSet rs= stm.executeQuery("SELECT max(idcategoria) from categorias");
           rs.next();
           id = rs.getInt(1) + 1;
           c.setid(id);
        } 
        catch (Exception e)
        {
               JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        
            sql= "INSERT INTO categorias(idcategoria,nombre)"
           + "VALUES ('";       
                sql += c.getid()+ "','"
                +c.getcategoria()+ "');";    
        try 
        {
            con.setAutoCommit(false);
            con.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
            stm = (Statement)con.createStatement();
            stm.executeUpdate(sql);            
            con.commit();
            con.setAutoCommit(true);
            msj = "insercion correcta";
        } 
        catch (Exception e)
        {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return msj;
  }
   public void actualizar(categoria c, Connection con,Statement stm)
  {
        
            sql="UPDATE categorias SET ";       
        //sólo el índice del registro NO es actualizable
            sql +="nombre='"
                + c.getcategoria() + "' WHERE idcategoria=" + c.getid();
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
   public void borrar(categoria c,Connection con,Statement stm)
  {
      sql=" DELETE FROM categorias WHERE idcategoria=" + c.getid();
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
   public ArrayList<categoria> listacategorias(Connection con,Statement stm)
  {
        ArrayList<categoria> lcat = new ArrayList<categoria>();
        categoria c;
        try 
        {
            stm= con.createStatement();
            ResultSet rs= stm.executeQuery(
                    "SELECT idcategoria,nombre FROM categorias"
                            + " ORDER BY nombre DESC");
            while(rs.next()) 
            {               
                    c = new categoria();
                    c.setid(rs.getInt(1));
                    c.setcategoria(rs.getString(2));
                    lcat.add(c);
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lcat;
  } 
}
