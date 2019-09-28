

package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */

import acceso_a_datos.clienteAD;
import java.sql.Connection;
import java.sql.Statement;
public class cliente extends persona
{
    private categoriacliente categoria;
    private int cantvisitas;
    private double descuento;
    private clienteAD cad;
    public cliente(String nombre,String apellido,String direccion,String ciudad,String pais,int telefono,categoriacliente categoria)
    {
        super(nombre,apellido,direccion,ciudad,pais,telefono);
        this.categoria = new categoriacliente();
        this.cantvisitas = 1;
        this.descuento = 0.0;
        cad = new clienteAD();
    }
    
    public cliente()
    {
        super("","","","","",0);
        this.categoria = new categoriacliente();
        this.cantvisitas = 0;
        this.descuento = 0.0;
        cad = new clienteAD();
    }
    
    public void setcategoria(categoriacliente categoria)
    {
        this.categoria = categoria;
    }
    
    public categoriacliente getcategoria()
    {
        return this.categoria;
    }
    
    public void setvisita(int visita)
    {
        this.cantvisitas = visita;
    }
    
    public int getvisitas()
    {
        return this.cantvisitas;
    }
    
    public void agregarvisita()
    {
        this.cantvisitas++;
    }
    
    public void setdesc(double descuento)
    {
        this.descuento = descuento;
    }
    
    public double getdesc()
    {
        return this.descuento;
    }
    
    public void insertar(Connection con,Statement stm)
    {
        cad.insertar(this, con, stm);
    }
    
    public void actualizar(Connection con,Statement stm)
    {
        cad.actualizar(this, con, stm);
    }
    
    public String borrar(Connection con,Statement stm)
    {
        return cad.borrar(this, con, stm);
    }
}
