
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */

import acceso_a_datos.empleadoAD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
public class empleados
{
   private ArrayList<empleado> emp;
   public empleados()
   {
       this.emp = new ArrayList<empleado>();
   }
  
   public ArrayList<empleado> getempleados(Connection con,Statement stm)
   {
       empleadoAD empad = new empleadoAD();
       this.emp = empad.listaempleados(con, stm);
       return this.emp;
   }
   
   public empleado getempleadobyid(int id,Connection con, Statement stm)
   {
       empleado emp = new empleado();
       for(empleado e: getempleados(con,stm))
       {
           if(id == e.getid())
           {
               emp = e;
           }
       }
       return emp;
   }
   public empleado getempleadobyname(String name,Connection con, Statement stm)
   {
       empleado emp = new empleado();
       for(empleado e: getempleados(con,stm))
       {
           if(e.getnombre().equals(name))
           {
               emp = e;
           }
       }
       return emp;
   }
}
