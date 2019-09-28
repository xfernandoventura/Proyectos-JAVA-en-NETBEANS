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
import logica_de_negocios.categoriacliente;

public class categoriaclienteAD
{
   private String sql;
   private String msj;
    public String insertar(categoriacliente c,Connection con,Statement stm)
  {      
        int id = 1;
        try
        {
           ResultSet rs= stm.executeQuery("SELECT max(idcategoria) from categoriacliente");
           rs.next();
           id = rs.getInt(1) + 1;
           c.setid(id);
        } 
        catch (Exception e)
        {
               JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        
            sql= "INSERT INTO categoriacliente(idcategoria,categoria)"
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
   public void actualizar(categoriacliente c, Connection con,Statement stm)
  {
        
            sql="UPDATE categoriacliente SET ";       
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
   public void borrar(categoriacliente c,Connection con,Statement stm)
  {
      sql=" DELETE FROM categoriacliente WHERE idcategoria=" + c.getid();
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
   public ArrayList<categoriacliente> listacategorias(Connection con,Statement stm)
  {
        ArrayList<categoriacliente> lcat = new ArrayList<categoriacliente>();
        categoriacliente c;
        try 
        {
            stm= con.createStatement();
            ResultSet rs= stm.executeQuery(
                    "SELECT idcategoria,categoria FROM categoriacliente;");
            while(rs.next()) 
            {               
                    c = new categoriacliente();
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
