
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
import logica_de_negocios.proveedor;
public class proveedorAD
{
  private String sql;
  private proveedor p;
  private String msj;
   public void insertar(proveedor p,Connection con,Statement stm)
  {      
        int id = 1;
        try
        {
           ResultSet rs= stm.executeQuery("SELECT max(idproveedor) from proveedores");
           rs.next();
           id = rs.getInt(1) + 1;
           p.setid(id);
        } 
        catch (Exception e)
        {
               JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        
            sql= "INSERT INTO proveedores(nombre,apellido,direccion,ciudad,pais,telefono,idproveedor)"
           + "VALUES ('";       
                sql += p.getnombre()+ "','"
                +p.getapellido()+ "','"
                +p.getdireccion()+ "','"
                +p.getciudad()+ "','"
                +p.getpais()+ "','"
                +p.gettelefono()+ "','"
                +p.getid()+ "');"; 
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
  
  public void actualizar(proveedor p, Connection con,Statement stm)
  {
        
            sql="UPDATE proveedores SET ";       
        //sólo el índice del registro NO es actualizable
            sql +="nombre='"
                + p.getnombre()+"',apellido='"
                + p.getapellido()+"',direccion='"
                + p.getdireccion()+"',ciudad='"
                + p.getciudad()+"',pais='"
                + p.getpais()+"',telefono='"
                + p.gettelefono()+
                "' WHERE idproveedor=" + p.getid();
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
  
  public ArrayList<proveedor> listaproveedores(Connection con,Statement stm)
  {
        ArrayList<proveedor> lpro = new ArrayList<proveedor>();
        try 
        {
            stm= con.createStatement();
            ResultSet rs= stm.executeQuery(
                    "SELECT idproveedor,nombre,apellido,direccion,ciudad,pais,telefono FROM proveedores "
                            + " ORDER BY nombre, apellido DESC");
            while(rs.next()) 
            {               
                    p = new proveedor();
                    p.setid(rs.getInt(1));
                    p.setnombre(rs.getString(2));
                    p.setapellido(rs.getString(3));
                    p.setdireccion(rs.getString(4));
                    p.setciudad(rs.getString(5));
                    p.setpais(rs.getString(6));
                    p.setteleffono(rs.getInt(7));
                    lpro.add(p);
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
        return lpro;
  } 
  
  public String borrar(proveedor p,Connection con,Statement stm)
  {
      sql=" DELETE FROM proveedores WHERE idproveedor=" + p.getid();
      try 
        {
            stm= con.createStatement();
            stm.executeUpdate(sql);
            msj ="borrado eitoso";
        } 
        catch (Exception e)
        { 
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
      return msj;
  }
}
