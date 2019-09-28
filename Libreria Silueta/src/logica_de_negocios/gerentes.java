
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.gerenteAD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
public class gerentes
{
   private gerenteAD gad;
   private ArrayList<gerente> lg;
   public gerentes()
   {
       lg = new ArrayList<gerente>();
   }
   
   public ArrayList<gerente> getlistagerentes(Connection con, Statement stm)
   {
       gad = new gerenteAD();
       lg = gad.listagerentes(con, stm);
       return this.lg;
   }
   
   public gerente getgerentebyid(int id,Connection con, Statement stm)
   {
       gerente g = new gerente();
       for(gerente gg: getlistagerentes(con,stm))
       {
           if(id == gg.getid())
           {
               g = gg;
           }
       }
       return g;
   }
}
