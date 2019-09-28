
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
import logica_de_negocios.gerente;
public class gerenteAD
{
  String sql;
  gerente g;
  String msj;
  public void insertar(gerente g,Connection con,Statement stm)
  {      
        int id = 1;
        try
        {
           ResultSet rs= stm.executeQuery("SELECT max(idgerente) from gerente");
           rs.next();
           id = rs.getInt(1) + 1;
           g.setid(id);
        } 
        catch (Exception e)
        {
               JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        
            sql= "INSERT INTO gerente(nombre,apellido,direccion,ciudad,pais,telefono,clave,idgerente)"
           + "VALUES ('";       
                sql += g.getnombre()+ "','"
                +g.getapellido()+ "','"
                +g.getdireccion()+ "','"
                +g.getciudad()+ "','"
                +g.getpais()+ "','"
                +g.gettelefono()+ "','"
                +g.getclave()+ "','"
                +g.getid()    + "');";     
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
  
  public void actualizar(gerente g, Connection con,Statement stm)
  {
        
            sql="UPDATE gerente SET ";       
        //sólo el índice del registro NO es actualizable
            sql +="nombre='"
                + g.getnombre()+"',apellido='"
                + g.getapellido()+"',direccion='"
                + g.getdireccion()+"',ciudad='"
                + g.getciudad()+"',pais='"
                + g.getpais()+"',telefono='"
                + g.gettelefono() +"',clave='"
                + g.getclave()    + "' WHERE idgerente=" + g.getid();
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
  
  public ArrayList<gerente> listagerentes(Connection con,Statement stm)
  {
        ArrayList<gerente> lg = new ArrayList<gerente>();
        try 
        {
            stm= con.createStatement();
            ResultSet rs= stm.executeQuery(
                    "SELECT idgerente,nombre,apellido,direccion,ciudad,pais,telefono,clave FROM gerente "
                            + " ORDER BY nombre, apellido DESC");
            while(rs.next()) 
            {               
                    g = new gerente();
                    g.setid(rs.getInt(1));
                    g.setnombre(rs.getString(2));
                    g.setapellido(rs.getString(3));
                    g.setdireccion(rs.getString(4));
                    g.setciudad(rs.getString(5));
                    g.setpais(rs.getString(6));
                    g.setteleffono(rs.getInt(7));
                    g.setclave(rs.getString(8));
                    lg.add(g);
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
        return lg;
  } 
  
  public String borrar(gerente g,Connection con,Statement stm)
  {
      sql=" DELETE FROM gerente WHERE idgerente=" + g.getid();
      try 
        {
            stm= con.createStatement();
            stm.executeUpdate(sql);
            this.msj = "borrado exitoso";
        } 
        catch (Exception e)
        { 
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
      return this.msj;
  }
}
