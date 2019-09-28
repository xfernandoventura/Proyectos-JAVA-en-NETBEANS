
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */

import acceso_a_datos.empleadoAD;
import java.sql.Connection;
import java.sql.Statement;
public class empleado extends persona
{
   private gerente g;
   private String fecha_nac;
   private String fecha_contratacion;
   private String clave;
   private cargo c;
   private Double Salario;
   empleadoAD empad;
   public empleado()
   {
       super("","","","","",0);
       this.clave = "";      
       this.fecha_nac = "";
       this.fecha_contratacion = "";
       this.Salario = 0.0;
       this.empad = new empleadoAD();
       this.g = new gerente();
       this.c = new cargo();
   }
    public empleado(String nombre,String apellido,String direccion,String clave,String fecha_nac,String fecha_contratacion,cargo c,Double Salario)
    {
       super(nombre,apellido,direccion,"","",0);
       this.clave = clave;      
       this.fecha_nac = fecha_nac;
       this.fecha_contratacion = fecha_contratacion;
       this.Salario = Salario;
       this.empad = new empleadoAD();
       this.c = c;
       this.g = new gerente();
    }    
    
    public void setSalario(Double Salario)
    {
        this.Salario = Salario;
    }
    
    public Double getSalario()
    {
        return this.Salario;
    }  
    
    public void setclave(String clave)
    {
        this.clave = clave;
    }
    
    public String getclave()
    {
        return this.clave;
    }
    
    public void setfecha_nac(String fecha_nac)
    {
        this.fecha_nac = fecha_nac;
    }
    
    public String getfecha_nac()
    {
        return this.fecha_nac;
    }
    
    public void setfecha_contratacion(String fecha_contratacion)
    {
        this.fecha_contratacion = fecha_contratacion;
    }
    
    public String getfechaa_contratacion()
    {
        return this.fecha_contratacion;
    }
    
    public void setcargo(cargo c)
    {
        this.c = c;
    }
    
    public cargo getcargo()
    {
        return this.c;
    }
    
    public void setgerente(gerente g)
    {
        this.g = g;
    }
    
    public gerente getgerente()
    {
        return this.g;
    }
    
   public void insertar(Connection con,Statement stm)
   {
       empad.insertar(this, con, stm);
   }
   
   public void modificar(Connection con, Statement stm)
   {
        empad.actualizar(this, con, stm);
   }
   
   public String borrar(Connection con, Statement stm)
   {
      return empad.borrar(this, con, stm);
   }
}
