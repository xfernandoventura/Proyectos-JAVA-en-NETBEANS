
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.categoriaAD;
import java.sql.Connection;
import java.sql.Statement;
public class categoria
{
   private int id;
   private String categoria;
   private categoriaAD cad;
   public categoria()
   {
       this.id = 0;
       this.categoria = "";
       cad = new categoriaAD();
   }
   
   public categoria(String categoria)
   {
       this.categoria = categoria;
       cad = new categoriaAD();
   }
   
   public void setid(int id)
   {
       this.id = id;
   }
   
   public int getid()
   {
       return this.id;
   }
   
   public void setcategoria(String categoria)
   {
       this.categoria = categoria;
   }
   
   public String getcategoria()
   {
       return this.categoria;
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
