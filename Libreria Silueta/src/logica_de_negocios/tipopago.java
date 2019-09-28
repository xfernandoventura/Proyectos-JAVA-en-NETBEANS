
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.tipopagoAD;
import java.sql.Connection;
import java.sql.Statement;
public class tipopago
{
    private int id;
    private String tipo;
    private tipopagoAD tipad;
    public tipopago()
    {
        this.id = 0;
        this.tipo ="";
        tipad = new tipopagoAD();
    }
    public tipopago(int id,String tipopago)
    {
        this.id = id;
        this.tipo = tipopago;
        tipad = new tipopagoAD();
    }
    public void setid(int id)
   {
       this.id = id;
   }
   
   public int getid()
   {
       return this.id;
   }
   
   public void settipopago(String tipopago)
   {
       this.tipo = tipopago;
   }
   
   public String gettipopago()
   {
       return this.tipo;
   }
   
   public void insertar(Connection con,Statement stm)
   {
       tipad.insertar(this, con, stm);
   }
   
   public void modificar(Connection con, Statement stm)
   {
        tipad.actualizar(this, con, stm);
   }
   
   public void borrar(Connection con, Statement stm)
   {
      tipad.borrar(this, con, stm);
   }
}
