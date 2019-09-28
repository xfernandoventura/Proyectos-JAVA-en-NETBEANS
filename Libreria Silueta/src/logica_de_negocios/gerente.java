
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.gerenteAD;
import java.sql.Connection;
import java.sql.Statement;
public class gerente extends persona
{
    private gerenteAD gad;
    private String clave;
   public gerente()
   {
       super("","","","","",0);
       gad = new gerenteAD();
       this.clave = "";  
   }
   public gerente(String nombre,String apellido,String direccion,String ciudad,String pais,int telefono)
   {
       super(nombre,apellido,direccion,ciudad,pais,telefono);
       gad = new gerenteAD();
   }
   
   public void setclave(String clave)
    {
        this.clave = clave;
    }
    
    public String getclave()
    {
        return this.clave;
    }
   
    public void insertar(Connection con,Statement stm)
    {
        gad.insertar(this, con, stm);
    }
    
    public void actualizar(Connection con,Statement stm)
    {
        gad.actualizar(this, con, stm);
    }
    
    public String borrar(Connection con,Statement stm)
    {
        return gad.borrar(this, con, stm);
    }
}
