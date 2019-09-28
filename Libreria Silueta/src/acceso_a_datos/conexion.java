/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso_a_datos;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class conexion
{   
   private String mensaje;
   private Connection dbConnection;
   Statement stm;
   private boolean conexionexitosa;
   private String Usuario;
   private String clave;
   public conexion()
   {
       this.mensaje = "";
       this.conexionexitosa = false;
   }
   
  public String establecerconexion(String Usuario,String Clave)
   {
        this.mensaje = "conexion correcta";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/bd_libreria";
            this.dbConnection = DriverManager.getConnection(url, Usuario, Clave);
            this.stm= dbConnection.createStatement();
            this.conexionexitosa = true;
            this.Usuario = Usuario;
            this.clave = Clave;
        }
        catch(Exception e) 
        {
            this.mensaje = "error en conexion ";
            this.conexionexitosa = false;
        }  
        return this.mensaje;
   }
  
  public boolean getconexioncorrecta()
  {
      return this.conexionexitosa;
  }
  
   public String cerrarConexion()
   {
       this.mensaje = "desconexion correcta";
       try
       {
          this.stm.close();
          this.dbConnection.close();
       }
       catch(Exception e)
       {
           this.mensaje = "error en desconexion" + e.toString();
       }
       return this.mensaje;
   }
   
   public Connection getConexion()
   {
       return this.dbConnection;
   }
   
   public Statement getEstado()
   {
       return this.stm;
   }
   
   public String getUsuario()
   {
       return this.Usuario;
   }
   
   public String getClave()
   {
       return this.clave;
   }
}

