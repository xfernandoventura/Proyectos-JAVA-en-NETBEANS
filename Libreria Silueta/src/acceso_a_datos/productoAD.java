
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
import logica_de_negocios.categorias;
import logica_de_negocios.producto;
import logica_de_negocios.proveedores;
public class productoAD
{
  private producto pro;
  private String sql;
  private String msj;
  public void insertar(producto pro,Connection con,Statement stm)
  {      
        int id = 1;
        try
        {
           ResultSet rs= stm.executeQuery("SELECT max(idproducto) from productos");
           rs.next();
           id = rs.getInt(1) + 1;
           pro.setid(id);
        } 
        catch (Exception e)
        {
               JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        
            sql= "INSERT INTO productos(idproducto,idproveedor,idcategoria,nombre,descripcion,unidaddemedida,preciounidad,stock)"
           + "VALUES ('";       
                sql += pro.getid()+ "','"
                +pro.getproveedor().getid() + "','"
                +pro.getcategoria().getid() + "','"
                +pro.getnombre() + "','"
                +pro.getdescripcion() + "','"
                +pro.getunidadmedida() + "','"
                +pro.getprec_uni() + "','"
                +pro.getcantidad()+ "');";    
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
  
  public void actualizar(producto pro, Connection con,Statement stm)
  {
        
            sql="UPDATE productos SET ";       
        //sólo el índice del registro NO es actualizable
            sql +="idproveedor='"
                + pro.getproveedor().getid() + "',idcategoria='"
                + pro.getcategoria().getid()+"',nombre='"
                + pro.getnombre()+"',descripcion='"
                + pro.getdescripcion()+"',unidaddemedida='"
                + pro.getunidadmedida()+"',preciounidad='"
                + pro.getprec_uni()+"',stock='"
                + pro.getcantidad() + "' WHERE idproducto=" + pro.getid();
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
  
  public String borrar(producto pro,Connection con,Statement stm)
  {
      sql=" DELETE FROM productos WHERE idproducto=" + pro.getid();
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
      return msj;
  }
  
  public ArrayList<producto> listaproductos(Connection con,Statement stm)
    {   
         
         proveedores provs = new proveedores();
         categorias cats = new categorias();
         ArrayList<producto> lpro = new ArrayList<producto>();   
         try 
         {
                    stm= con.createStatement();
                    ResultSet rs= stm.executeQuery(
                    "SELECT idproducto,idproveedor,idcategoria,nombre,descripcion,unidaddemedida,preciounidad,stock FROM productos "
                    + " ORDER BY nombre DESC");
                     while(rs.next()) 
                    {     
                        pro = new producto();
                        pro.setid(rs.getInt(1));
                        pro.setproveedor(provs.getproveedorbyid(rs.getInt(2), con, stm));
                        pro.setcategoria(cats.getcategoriabyid(rs.getInt(3), con, stm));
                        pro.setnombre(rs.getString(4));
                        pro.setdescripcion(rs.getString(5));
                        pro.setunidadmedida(rs.getString(6));
                        pro.setprec_uni(rs.getDouble(7));
                        pro.setcantidad(rs.getInt(8));                        
                        lpro.add(pro);             
                    }
         }
         catch (SQLException e) 
         {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
         }
         return lpro;
    }    
  
   public ArrayList<producto> productosporletra(Connection con,Statement stm,String letra)
   {
        proveedores provs = new proveedores();
        categorias cats = new categorias();
        ArrayList<producto> lpro = new ArrayList<producto>(); 
         try 
         {
                    stm= con.createStatement();
                    ResultSet rs= stm.executeQuery(
                    "SELECT idproducto,idproveedor,idcategoria,nombre,descripcion,unidaddemedida,preciounidad,stock FROM productos "
                    + " WHERE nombre like '" + letra +"%'");
                     while(rs.next()) 
                    {     
                        pro = new producto();
                        pro.setid(rs.getInt(1));
                        pro.setproveedor(provs.getproveedorbyid(rs.getInt(2), con, stm));
                        pro.setcategoria(cats.getcategoriabyid(rs.getInt(3), con, stm));
                        pro.setnombre(rs.getString(4));
                        pro.setdescripcion(rs.getString(5));
                        pro.setunidadmedida(rs.getString(6));
                        pro.setprec_uni(rs.getDouble(7));
                        pro.setcantidad(rs.getInt(8));                        
                        lpro.add(pro);             
                    }
         }
         catch (SQLException e) 
         {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
         }
        return lpro;
   }
  
}
