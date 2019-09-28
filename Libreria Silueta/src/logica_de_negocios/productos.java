
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.productoAD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
public class productos
{
   ArrayList<producto> productos;
   productoAD proad;
   public productos()
   {
     productos = new ArrayList<producto>();
   }
   
   public ArrayList<producto> getlistaproductos(Connection con, Statement stm)
   {
       proad = new productoAD();
       productos = proad.listaproductos(con, stm);
       return this.productos;
   }
   
   public ArrayList<producto> getproductosporletra(Connection con, Statement stm,String letra)
   {
       this.productos.clear();
       for(producto p:this.proad.productosporletra(con, stm, letra))
       {
           this.productos.add(p);
       }
       return this.productos;
   }
   
   public producto getproductobyid(int id,Connection con, Statement stm)
   {
       producto p = new producto();
       for(producto pro: getlistaproductos(con,stm))
       {
           if(id == pro.getid())
           {
               p = pro;
           }
       }
       return p;
   }
   public producto getproductobyname(String name,Connection con, Statement stm)
   {
       producto p = new producto();
       for(producto pro: getlistaproductos(con,stm))
       {
           if(pro.getnombre().equals(name))
           {
               p = pro;
           }
       }
       return p;
   }
}
