
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.clienteAD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
public class clientes
{
   private ArrayList<cliente> cli;
   public clientes()
   {
       this.cli = new ArrayList<cliente>();
   }
  
   public ArrayList<cliente> getclientes(Connection con,Statement stm)
   {
       clienteAD cad = new clienteAD();
       this.cli = cad.listaClientes(con, stm);
       return this.cli;
   }
   
   public cliente getclientebyid(int id,Connection con, Statement stm)
   {
       cliente cli = new cliente();
       for(cliente c: getclientes(con,stm))
       {
           if(id == c.getid())
           {
               cli = c;
           }
       }
       return cli;
   }
   
   public cliente getclientebyname(String name,Connection con, Statement stm)
   {
       cliente cli = new cliente();
       for(cliente c: getclientes(con,stm))
       {
           if(c.getnombre().equals(name))
           {
               cli = c;
           }
       }
       return cli;
   }
   public ArrayList<cliente> getclientesporletra(String letra,Connection con, Statement stm)
   {
       this.cli.clear();
       clienteAD cad = new clienteAD();
       this.cli = cad.clientesporletra(con, stm, letra);
       return this.cli;
   }
}
