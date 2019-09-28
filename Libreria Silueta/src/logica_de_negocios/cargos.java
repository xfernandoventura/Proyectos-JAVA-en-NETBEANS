
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */

import acceso_a_datos.cargoAD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
public class cargos
{
   private ArrayList<cargo> lcar;
   public cargos()
   {
       lcar = new ArrayList<cargo>();
       
   }
   
   public ArrayList<cargo> getcargos(Connection con,Statement stm)
   {
       cargoAD cad = new cargoAD();
       lcar = cad.listacargos(con, stm);
       return this.lcar;
   }
   
   public cargo getcargobyid(int id,Connection con,Statement stm)
   {
       cargo car = new cargo();
       for(cargo c: this.getcargos(con,stm))
       {
           if(id == c.getid())
           {
                car = c;
           }
       }
       return car;
   }
   public cargo getcargobyname(String name,Connection con,Statement stm)
   {
       cargo car = new cargo();
       for(cargo c: this.getcargos(con,stm))
       {
           if(c.getcargo().equals(name))
           {
                car = c;
           }
       }
       return car;
   }
}
