
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
import logica_de_negocios.cargos;
import logica_de_negocios.empleado;
import logica_de_negocios.gerentes;
public class empleadoAD
{
    private String sql;
    private String msj;
    public void insertar(empleado emp,Connection con,Statement stm)
    {      
        int id = 1;
        int codcargo = 0;
        
        try
        {
           ResultSet rs= stm.executeQuery("SELECT max(idempleado) from empleados");
           rs.next();
           id = rs.getInt(1) + 1;
           emp.setid(id);
        } 
        catch (Exception e)
        {
               JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        sql= "INSERT INTO empleados(nombre,apellidos,direccion,fechanacimiento,fechacontratacion,clave,idempleado,salario,idcargo,idgerente)"
           + "VALUES ('";
           
         sql += emp.getnombre()+ "','"
                +emp.getapellido()+ "','"
                +emp.getdireccion()+ "','"
                +emp.getfecha_nac()+ "','"
                +emp.getfechaa_contratacion()+ "','"
                +emp.getclave()+ "','"
                +id+ "','" 
                +emp.getSalario()+ "','" 
                +emp.getcargo().getid() + "','"
                +emp.getgerente().getid() + "');"; 
                
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
    
    public void actualizar(empleado emp,Connection con,Statement stm)
    {    
        sql="UPDATE empleados SET ";       
        //sólo el índice del registro NO es actualizable
            sql +="nombre='"
                + emp.getnombre()+"',apellidos='"
                + emp.getapellido()+"',direccion='"
                + emp.getdireccion()+"',idcargo='"
                + emp.getcargo().getid()+"',fechanacimiento='"
                + emp.getfecha_nac()+"',fechacontratacion='"
                + emp.getfechaa_contratacion()+"',clave='"
                + emp.getclave() +"',salario='"
                + emp.getSalario() +"',idgerente='"
                + emp.getgerente().getid() + "' WHERE idempleado=" + emp.getid();
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
    
    public String borrar(empleado emp,Connection con,Statement stm)
    {
       sql=" DELETE FROM empleados WHERE idempleado=" + emp.getid();
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
    
    public ArrayList<empleado> listaempleados(Connection con,Statement stm)
    {   
         
         cargos c = new cargos();
         gerentes g = new gerentes();
         ArrayList<empleado> lemp = new ArrayList<empleado>();   
         try 
         {
                    stm= con.createStatement();
                    ResultSet rs= stm.executeQuery(
                    "SELECT idempleado,nombre,apellidos,direccion,fechanacimiento,fechacontratacion,clave,salario,idcargo,idgerente FROM empleados "
                    + " ORDER BY nombre, apellidos DESC");
                     while(rs.next()) 
                    {             
                        empleado emp = new empleado();
                        emp.setid(rs.getInt(1));
                        emp.setnombre(rs.getString(2));
                        emp.setapellido(rs.getString(3));
                        emp.setdireccion(rs.getString(4));
                        emp.setfecha_nac(rs.getString(5));
                        emp.setfecha_contratacion(rs.getString(6));
                        emp.setclave(rs.getString(7));
                        emp.setSalario(rs.getDouble(8));                        
                        emp.setcargo(c.getcargobyid(rs.getInt(9), con, stm));  
                        emp.setgerente(g.getgerentebyid(rs.getInt(10), con, stm));
                        lemp.add(emp);             
                    }
         }
         catch (SQLException e) 
         {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
         }
         return lemp;
    }    
}
