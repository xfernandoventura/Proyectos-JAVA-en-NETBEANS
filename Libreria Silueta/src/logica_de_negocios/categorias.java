
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.categoriaAD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
public class categorias
{
   private categoriaAD cad;
   private ArrayList<categoria> lca;
   public categorias()
   {
       lca = new ArrayList<categoria>();
   }
   
   public ArrayList<categoria> getlistacategorias(Connection con, Statement stm)
   {
       cad = new categoriaAD();
       lca = cad.listacategorias(con, stm);
       return this.lca;
   }
   
   public categoria getcategoriabyid(int id,Connection con, Statement stm)
   {
       categoria cat = new categoria();
       for(categoria c: getlistacategorias(con,stm))
       {
           if(id == c.getid())
           {
               cat = c;
           }
       }
       return cat;
   }
   
   public categoria getcategoriabyname(String name,Connection con, Statement stm)
   {
       categoria cat = new categoria();
       for(categoria c: getlistacategorias(con,stm))
       {
           if(c.getcategoria().equals(name))
           {
               cat = c;
           }
       }
       return cat;
   }
}
