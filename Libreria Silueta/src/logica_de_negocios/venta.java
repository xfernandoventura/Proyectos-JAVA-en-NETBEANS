
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
import acceso_a_datos.ventaAD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
public class venta
{
  private int id;
  private cliente cli;
  private empleado emp;
  private producto pro;
  private ArrayList<producto> canasta;
  private tipopago tipag;
  private String fecha;
  private double preciototal;
  private ventaAD vad;
  private boolean productoRepetido = false;
  public venta()
  {
      this.cli = new cliente();
      this.emp = new empleado();
      this.pro = new producto();
      this.tipag = new tipopago();
      this.canasta = new ArrayList<producto>();
      this.fecha = "0/0/0";
      this.preciototal = 0.0;
      this.vad = new ventaAD();
  }
  
   public void setid(int id)
   {
       this.id = id;
   }
   
   public int getid()
   {
       return this.id;
   }
   
  public String agregarproductooacanasta(producto p,Connection con,Statement stm)
  {
      String mensaje = "";
      productoRepetido = false;
      productos productosdebasededatos = new productos();
      if(this.canasta.size() == 0)//si es el primer producto de un solo se mete porq no estara repetido 
      {
          pro = productosdebasededatos.getproductobyid(p.getid(), con, stm);//obtengo el producto de la base de datos con el id del producto k se agregara a la canasta de compra
          if(p.getcantidad() > pro.getcantidad())//comprueba que hay suficiente cantidad de productos en la base de datos
          {
             mensaje = "el producto no se agrego por mtivos de existencias";
          }
          else
          {
             this.canasta.add(p);
             mensaje = "Agregado correctamente";
          }
      }
      else//en el caso de que ya aygan productos en la canasta de compras
      {          
          for(producto pro: this.canasta)//verifico si el producto nuevo ya esta en la canasta
          {
              if(p.getid() == pro.getid())
              {
                  productoRepetido = true;
              }
          }
          
          if(productoRepetido == true)
          {
               mensaje = "producto ya agregado";
          }
          else
          {
              pro = productosdebasededatos.getproductobyid(p.getid(), con, stm);//obtengo el producto de la base de datos con el id del producto k se agregara a la canasta de compra
              if(p.getcantidad() > pro.getcantidad())//comprueba que hay suficiente cantidad de productos en la base de datos
              {
                 mensaje = "el producto no se agrego por mtivos de existencias";
              }
              else
              {
                 this.canasta.add(p);
                 mensaje = "Agregado correctamente";
              }
          }
      }
      return mensaje;
  }
  
  public ArrayList<producto> getcanasta()
  {
      return this.canasta;
  }
  
  public void agregarcliente(cliente c)
  {
      this.cli = c;
  }
  
  public cliente getcliente()
  {
      return this.cli;
  }
  
  public void agregarempleado(empleado e)
  {
      this.emp = e;
  }
  
  public empleado getempleado()
  {
      return this.emp;
  }
  
  public void agregartipopago(tipopago p)
  {
      this.tipag = p;
  }
  
  public tipopago gettipopago()
  {
      return this.tipag;
  }
  
  public void agregarfecha(String f)
  {
      this.fecha = f;
  }
  
  public String getfecha()
  {
      return this.fecha;
  }
  
  public void setpreciototal()
  {
      for(producto p:this.canasta)
      {
          this.preciototal += p.getprec_uni() * p.getcantidad();
      }
  }
  
  public double getpreciototal()
  {
      return this.preciototal;
  }
  
  public String insertar(Connection con,Statement stm)
   {
          return vad.insertar(this, con, stm);
   }
   
   public void borrar(Connection con,Statement stm)
    {
        vad.borrar(this, con, stm);
    }
}
