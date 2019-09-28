
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */

import acceso_a_datos.cargoAD;
import java.sql.Connection;
import java.sql.Statement;
public class cargo
{
   private int id;
   private String cargo;
   private cargoAD cad;
   public cargo()
   {
       this.id = 0;
       this.cargo = "";
       cad = new cargoAD();
   }
   
   public cargo(String cargo)
   {
       this.cargo = cargo;
       cad = new cargoAD();
   }
   
    public void setid(int id)
    {
        this.id = id;
    }
    
    public int getid()
    {
        return this.id;
    }
    
    public void setcargo(String cargo)
    {
        this.cargo = cargo;
    }
    
    public String getcargo()
    {
        return this.cargo;
    }
    
    public String insertar(Connection con,Statement stm)
   {
      return cad.insertar(this, con, stm);
   }
   
   public void modificar(Connection con, Statement stm)
   {
        cad.actualizar(this, con, stm);
   }
   
   public void borrar(Connection con, Statement stm)
   {
      cad.borrar(this, con, stm);
   }
}
