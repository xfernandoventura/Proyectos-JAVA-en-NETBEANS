
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.proveedorAD;
import java.sql.Connection;
import java.sql.Statement;
public class proveedor extends persona
{
    private proveedorAD pad;
    public proveedor()
    {
        super("","","","","",0);
        pad = new proveedorAD();
    }
    
    public proveedor(String nombre,String apellido,String direccion,String ciudad,String pais,int telefono)
    {
        super(nombre,apellido,direccion,ciudad,pais,telefono);
        pad = new proveedorAD();
    }
    
    public void insertar(Connection con,Statement stm)
    {
        pad.insertar(this, con, stm);
    }
    
    public void actualizar(Connection con,Statement stm)
    {
        pad.actualizar(this, con, stm);
    }
    
    public String borrar(Connection con,Statement stm)
    {
        return pad.borrar(this, con, stm);
    }
}
