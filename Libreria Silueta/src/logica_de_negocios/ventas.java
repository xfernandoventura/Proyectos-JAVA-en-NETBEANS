
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.ventaAD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
public class ventas
{
   private ArrayList<venta> lv;
   public ventas()
   {
       this.lv = new ArrayList<venta>();
   }
  
   public ArrayList<venta> getventas(Connection con,Statement stm)
   {
       ventaAD pad = new ventaAD();
       this.lv = pad.listaventas(con, stm);
       return this.lv;
   }
   
   public ArrayList<venta> getventaletracliente(String letra,Connection con,Statement stm)
   {
      int cantcaracteres = letra.length();
       ArrayList<venta> lv1 = new  ArrayList<venta>();
       for(venta v: getventas(con, stm))
       {
           String nombre = v.getcliente().getnombre();
           if(nombre.substring(0,cantcaracteres).equals(letra.toLowerCase()) || nombre.substring(0,cantcaracteres).equals(letra.toUpperCase()))
           {
               lv1.add(v);
           }
       }
       return lv1;
   }
   
   public ArrayList<venta> getventaletraempleado(String letra,Connection con,Statement stm)
   {
      int cantcaracteres = letra.length();
       ArrayList<venta> lv1 = new  ArrayList<venta>();
       for(venta v: getventas(con, stm))
       {
           String nombre = v.getempleado().getnombre();
           if(nombre.substring(0,cantcaracteres).equals(letra.toLowerCase()) || nombre.substring(0,cantcaracteres).equals(letra.toUpperCase()))
           {
               lv1.add(v);
           }
       }
       return lv1;
   }
   
   public venta getventabyid(int id,Connection con,Statement stm)
   {
       venta v = new venta();
       for(venta ven: getventas(con,stm))
       {
           if(id == v.getid())
           {
               v = ven;
           }
       }
       return v;
   }
   
   public venta getventabyidcliente(int id,Connection con,Statement stm)
   {
       venta v = new venta();
       for(venta ven: getventas(con, stm))
       {
           if(ven.getcliente().getid() == id)
           {
               v = ven;
               break;
           }
       }
       return v;
   }
}
