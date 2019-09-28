package acceso_a_datos;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */

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
import logica_de_negocios.categoriasclientes;
import logica_de_negocios.cliente;

public class clienteAD
{
    String msj;
  String sql;
  cliente c;
  public void insertar(cliente c,Connection con,Statement stm)
  {      
        int id = 1;
        try
        {
           ResultSet rs= stm.executeQuery("SELECT max(idcliente) from clientes");
           rs.next();
           id = rs.getInt(1) + 1;
           c.setid(id);
        } 
        catch (Exception e)
        {
               JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        
            sql= "INSERT INTO clientes(nombre,apellido,direccion,ciudad,pais,telefono,idcategoria,idcliente,descuento,visitas)"
           + "VALUES ('";       
                sql += c.getnombre()+ "','"
                +c.getapellido()+ "','"
                +c.getdireccion()+ "','"
                +c.getciudad()+ "','"
                +c.getpais()+ "','"
                +c.gettelefono()+ "','"
                +c.getcategoria().getid()+ "','"   
                +c.getid() + "','" 
                +c.getdesc() + "','"  
                +c.getvisitas() + "');";     
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
  
  public void actualizar(cliente c, Connection con,Statement stm)
  {
        
            sql="UPDATE clientes SET ";       
        //sólo el índice del registro NO es actualizable
            sql +="nombre='"
                + c.getnombre()+"',apellido='"
                + c.getapellido()+"',direccion='"
                + c.getdireccion()+"',ciudad='"
                + c.getciudad()+"',pais='"
                + c.getpais()+"',telefono='"
                + c.gettelefono()+"',idcategoria='"
                + c.getcategoria().getid() +"',descuento='"
                + c.getdesc() +"',visitas='"
                + c.getvisitas() + "' WHERE idcliente=" + c.getid();
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
  
  public ArrayList<cliente> listaClientes(Connection con,Statement stm)
  {
        ArrayList<cliente> lcli = new ArrayList<cliente>();
        categoriasclientes cats = new categoriasclientes();
        try 
        {
            stm= con.createStatement();
            ResultSet rs= stm.executeQuery(
                    "SELECT idcliente,nombre,apellido,direccion,ciudad,pais,telefono,idcategoria,visitas,descuento FROM clientes "
                            + " ORDER BY nombre, apellido DESC");
            while(rs.next()) 
            {               
                    c = new cliente();
                    c.setid(rs.getInt(1));
                    c.setnombre(rs.getString(2));
                    c.setapellido(rs.getString(3));
                    c.setdireccion(rs.getString(4));
                    c.setciudad(rs.getString(5));
                    c.setpais(rs.getString(6));
                    c.setteleffono(rs.getInt(7));
                    c.setcategoria(cats.getcategoriabyid(rs.getInt(8), con, stm));
                    c.setvisita(rs.getInt(9));
                    c.setdesc(rs.getDouble(10));
                    lcli.add(c);
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
        return lcli;
  } 
  
  public ArrayList<cliente> clientesporletra(Connection con,Statement stm,String letra)
  {
      ArrayList<cliente> lcli = new ArrayList<cliente>();
      categoriasclientes cats = new categoriasclientes();
       try 
        {
            stm= con.createStatement();
            ResultSet rs= stm.executeQuery(
                    "SELECT idcliente,nombre,apellido,direccion,ciudad,pais,telefono,idcategoria,visitas,descuento FROM clientes "
                            + " WHERE nombre like '" + letra + "%'");
            while(rs.next()) 
            {               
                    c = new cliente();
                    c.setid(rs.getInt(1));
                    c.setnombre(rs.getString(2));
                    c.setapellido(rs.getString(3));
                    c.setdireccion(rs.getString(4));
                    c.setciudad(rs.getString(5));
                    c.setpais(rs.getString(6));
                    c.setteleffono(rs.getInt(7));
                    c.setcategoria(cats.getcategoriabyid(rs.getInt(8), con, stm));
                    c.setvisita(rs.getInt(9));
                    c.setdesc(rs.getDouble(10));
                    lcli.add(c);
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
      return lcli;
  }
  
  public String borrar(cliente c,Connection con,Statement stm)
  {
      sql=" DELETE FROM clientes WHERE idcliente=" + c.getid();
      try 
        {
            stm= con.createStatement();
            stm.executeUpdate(sql);
            msj = "borrado exitoso";
        } 
        catch (Exception e)
        { 
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
      return msj;
  }
}
