/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica_de_negocios;

/**
 *
 * @author Fernando Ventura and Vanessa Ayala
 */
public class persona
{
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String ciudad;
    private String pais;
    private int telefono;
    
    public persona(String nombre,String apellido,String direccion,String ciudad,String pais,int telefono)
    {
       this.nombre = nombre;
       this.apellido = apellido;
       this.direccion = direccion;
       this.ciudad = ciudad;      
       this.pais = pais;
       this.telefono = telefono;
    }
    
    public void setid(int id)
    {
        this.id = id;
    }
    
    public int getid()
    {
        return this.id;
    }
    
    public void setnombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public String getnombre()
    {
        return this.nombre;
    }
    
    public void setapellido(String apellido)
    {
        this.apellido = apellido;
    }
    
    public String getapellido()
    {
        return this.apellido;
    }
    
    public void setdireccion(String direccion)
    {
        this.direccion = direccion;
    }
    
    public String getdireccion()
    {
        return this.direccion;
    }
    
    public void setciudad(String ciudad)
    {
        this.ciudad = ciudad;
    }
    
    public String getciudad()
    {
        return this.ciudad;
    }
    
    public void setpais(String pais)
    {
        this.pais = pais;
    }
    
    public String getpais()
    {
        return this.pais;
    }
    
     public void setteleffono(int telefono)
    {
        this.telefono = telefono;
    }
    
    public int gettelefono()
    {
        return this.telefono;
    }
}

