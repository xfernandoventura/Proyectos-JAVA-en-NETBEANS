
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.proveedorAD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
public class proveedores
{
   private ArrayList<proveedor> lpro;
   public proveedores()
   {
       this.lpro = new ArrayList<proveedor>();
   }
  
   public ArrayList<proveedor> getproveedores(Connection con,Statement stm)
   {
       proveedorAD pad = new proveedorAD();
       this.lpro = pad.listaproveedores(con, stm);
       return this.lpro;
   }
   
   public proveedor getproveedorbyid(int id,Connection con,Statement stm)
   {
       proveedor pro = new proveedor();
       for(proveedor p: getproveedores(con,stm))
       {
           if(id == p.getid())
           {
               pro = p;
           }
       }
       return pro;
   }
   
   public proveedor getproveedorbyname(String name,Connection con,Statement stm)
   {
       proveedor pro = new proveedor();
       for(proveedor p: getproveedores(con,stm))
       {
           if(p.getnombre().equals(name))
           {
               pro = p;
           }
       }
       return pro;
   }
}
