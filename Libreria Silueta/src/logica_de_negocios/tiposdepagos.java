
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.tipopagoAD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
public class tiposdepagos
{
   private  ArrayList<tipopago> ltip;
   private tipopagoAD tipad;
   public tiposdepagos()
   {
       ltip = new ArrayList<tipopago>();
   }
   
   public ArrayList<tipopago> getlistatiposdepagos(Connection con, Statement stm)
   {
       tipad = new tipopagoAD();
       ltip = tipad.listatiposdepagos(con, stm);
       return this.ltip;
   }
   
   public tipopago gettipopagobyid(int id,Connection con, Statement stm)
   {
       tipopago tip = new tipopago();
       for(tipopago t: getlistatiposdepagos(con,stm))
       {
           if(id == t.getid())
           {
               tip = t;
           }
       }
       return tip;
   }
   public tipopago gettipopagobyname(String name,Connection con, Statement stm)
   {
       tipopago tip = new tipopago();
       for(tipopago t: getlistatiposdepagos(con,stm))
       {
           if(t.gettipopago().equals(name))
           {
               tip = t;
           }
       }
       return tip;
   }
}
