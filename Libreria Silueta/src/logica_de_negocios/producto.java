
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.productoAD;
import java.sql.Connection;
import java.sql.Statement;
public class producto
{
  private int id;
  private categoria cat;
  private proveedor pro;
  private String nombre;
  private String descripcion;
  private String unidadmedida;
  private double prec_uni;
  private int cant;
  private productoAD proad;
  public producto()
  {
     this.id = 0;
     this.cat = new categoria();
     this.pro = new proveedor();
     this.nombre = "";
     this.descripcion = "";
     this.unidadmedida = "";
     this.prec_uni = 0.0;
     this.cant = 0;
     this.proad = new productoAD();
  }
  
  public producto(int id,categoria cat,proveedor pro,String nombre,String descripcion,String unidadmedida,double prec_uni,int stock)
  {
      this.id = id;
      this.cat = cat;
      this.pro = pro;
      this.nombre = nombre;
      this.descripcion = descripcion;
      this.unidadmedida = unidadmedida;
      this.prec_uni = prec_uni;
      this.cant = stock;
      this.proad = new productoAD();
  }
  
  public void setid(int id)
   {
       this.id = id;
   }
   
   public int getid()
   {
       return this.id;
   }
   
   public void setcategoria(categoria cat)
   {
       this.cat = cat;
   }
   
   public categoria getcategoria()
   {
       return this.cat;
   }
   
   public void setproveedor(proveedor pro)
   {
       this.pro = pro;
   }
   
   public proveedor getproveedor()
   {
       return this.pro;
   }
   
   public void setnombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public String getnombre()
    {
        return this.nombre;
    }
    
    public void setdescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
    
    public String getdescripcion()
    {
        return this.descripcion;
    }
    
    public void setunidadmedida(String unidadmedida)
    {
        this.unidadmedida = unidadmedida;
    }
    
    public String getunidadmedida()
    {
        return this.unidadmedida;
    }
    
    public void setprec_uni(double prec_uni)
    {
       this.prec_uni = prec_uni;
    }
    
    public double getprec_uni()
    {
        return this.prec_uni;
    }
    
     public void setcantidad(int cant)
   {
       this.cant = cant;
   }
   
   public int getcantidad()
   {
       return this.cant;
   }
   
   public void insertar(Connection con,Statement stm)
    {
        proad.insertar(this, con, stm);
    }
    
    public void actualizar(Connection con,Statement stm)
    {
        proad.actualizar(this, con, stm);
    }
    
    public String borrar(Connection con,Statement stm)
    {
       return   proad.borrar(this, con, stm);
    }
}
