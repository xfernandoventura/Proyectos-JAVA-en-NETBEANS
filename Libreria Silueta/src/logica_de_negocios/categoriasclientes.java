
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */

import acceso_a_datos.categoriaclienteAD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
public class categoriasclientes
{
  private categoriaclienteAD cad;
   private ArrayList<categoriacliente> lca;
   public categoriasclientes()
   {
       lca = new ArrayList<categoriacliente>();
   }
   
   public ArrayList<categoriacliente> getlistacategorias(Connection con, Statement stm)
   {
       cad = new categoriaclienteAD();
       lca = cad.listacategorias(con, stm);
       return this.lca;
   }
   
   public categoriacliente getcategoriabyid(int id,Connection con, Statement stm)
   {
       categoriacliente cat = new categoriacliente();
       for(categoriacliente c: getlistacategorias(con,stm))
       {
           if(id == c.getid())
           {
               cat = c;
           }
       }
       return cat;
   }
   
   public categoriacliente getcategoriabyname(String name,Connection con, Statement stm)
   {
       categoriacliente cat = new categoriacliente();
       for(categoriacliente c: getlistacategorias(con,stm))
       {
           if(c.getcategoria().equals(name))
           {
               cat = c;
           }
       }
       return cat;
   }
}
