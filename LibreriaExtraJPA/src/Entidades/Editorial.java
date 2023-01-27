/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author yamila
 */
@Entity
public class Editorial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private boolean alta;
    
    //contructores

    public Editorial() {
    }

    public Editorial(String nombre, boolean alta) { //constructor sin id
        this.nombre = nombre;
        this.alta = alta;
    }

    public Editorial(int id, String nombre, boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
    }
        
    //Getter and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }
    
    //Hash code and equals
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        return  hash ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Editorial other = (Editorial) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    //to String
    @Override
    public String toString() {
        return  id + "\t" + nombre + "\t" + alta ;
    }
    
     public void imprimirLindo(){
        System.out.printf( "%-5d %-15s %-10b\n", id, nombre, alta );
    }
    
}
