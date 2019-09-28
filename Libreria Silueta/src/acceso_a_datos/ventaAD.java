
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
import logica_de_negocios.clientes;
import logica_de_negocios.empleados;
import logica_de_negocios.producto;
import logica_de_negocios.productos;
import logica_de_negocios.tiposdepagos;
import logica_de_negocios.venta;
public class ventaAD
{
    private String sql;
    private venta v;
    private productos pro_bd;
    public String insertar(venta v,Connection con,Statement stm)
    {      
        String mensaje ="";
        int id = 1;
        //compra
             try
             {
                 ResultSet rs= stm.executeQuery("SELECT max(idpedido) from pedidos");
                 rs.next();
                 id = rs.getInt(1) + 1;
                 v.setid(id);
             } 
             catch (Exception e)
             {
                JOptionPane.showMessageDialog(null, e.getMessage());
             } 
             
            try 
            {
                  sql= "INSERT INTO pedidos(idpedido,idtipodepago,idcliente,idempleado,fechapedido)"
                  + " VALUES ('";       
                  sql += v.getid()+ "','"
                  +v.gettipopago().getid()+ "','"
                  +v.getcliente().getid()+ "','"
                  +v.getempleado().getid()+ "','"
                  +v.getfecha()+ "');"; 
        
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
             
            for(producto pro: v.getcanasta())
            {
                pro_bd = new productos();
                //resta de productos de compra con los existentes
                for(producto pro_de_base_de_datos: pro_bd.getlistaproductos(con, stm))
                {
                  if(pro.getid() == pro_de_base_de_datos.getid())
                  {
                     pro_de_base_de_datos.setcantidad(pro_de_base_de_datos.getcantidad() - pro.getcantidad());
                     pro_de_base_de_datos.actualizar(con, stm);
                  }
                }
                
                //insertar datos
                   try 
                   {
                      sql ="";
                      sql += "INSERT INTO detallespedidos(idpedido,idproducto,preciounidad,cantidad)"
                      + " VALUES ('";       
                      sql += v.getid()+ "','"
                      +pro.getid()+ "','"
                      +pro.getprec_uni()+ "','"
                      +pro.getcantidad()+ "');";                
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
            mensaje ="compra realizada";
            
        return mensaje;
    }
    
    public ArrayList<venta> listaventas(Connection con,Statement stm)
    {
        ArrayList<venta> lv = new ArrayList<venta>();        
           
            tiposdepagos tip = new tiposdepagos();
            clientes cli = new clientes();
            empleados emp = new empleados();
            productos p = new productos();
            try 
            {
               stm= con.createStatement();
               ResultSet rs= stm.executeQuery(
                    "SELECT idpedido,idtipodepago,idcliente,idempleado,fechapedido FROM pedidos");
                while(rs.next()) 
                {      
                    v = new venta();
                    v.setid(rs.getInt(1));
                    v.agregartipopago(tip.gettipopagobyid(rs.getInt(2), con, stm));
                    v.agregarcliente(cli.getclientebyid(rs.getInt(3), con, stm));
                    v.agregarempleado(emp.getempleadobyid(rs.getInt(4), con, stm));
                    v.agregarfecha(rs.getString(5));
                    
                    try 
                    {
                            Statement stm1 = con.createStatement();;
                            ResultSet rs1= stm1.executeQuery(
                             "SELECT idproducto,preciounidad,cantidad FROM detallespedidos where idpedido=" + v.getid() + ";");
                             while(rs1.next()) 
                             {  
                                   producto pro = new producto();
                                   pro = p.getproductobyid(rs1.getInt(1),con,stm);
                                   pro.setprec_uni(rs1.getDouble(2));
                                   pro.setcantidad(rs1.getInt(3));
                                   v.agregarproductooacanasta(pro,con,stm);                   
                             }
                    }  
                    catch (SQLException e) 
                    {
                         JOptionPane.showMessageDialog(null, e.getMessage());
                         return null;
                    }
                    v.setpreciototal();
                    lv.add(v);
                }
            }  
            catch (SQLException e) 
            {
               JOptionPane.showMessageDialog(null, e.getMessage());
               return null;
            }
            
            
        
        return lv;
  } 
    
    
    public void borrar(venta v,Connection con,Statement stm)
  {
      sql=" DELETE FROM pedidos WHERE idpedido=" + v.getid();
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
}
