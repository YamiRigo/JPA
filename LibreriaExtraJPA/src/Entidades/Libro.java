/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author yamila
 */
@Entity
public class Libro implements Serializable {

    @Id
    private long isbn;
    private String titulo;
    private int anio;
    private int ejemplares;
    private int ejemplaresPrestados;
    private int ejemplaresRestantes;
    private boolean alta;
    
    @OneToOne
    private Autor autor;
    
    @OneToOne
    private Editorial editorial;
    
    //contructores

    public Libro() {
    }
    
    public Libro(long isbn, String titulo, int anio, int ejemplares, int ejemplaresPrestados, int ejemplaresRestantes, boolean alta, Autor autor, Editorial editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }
    
    //getter and setters
    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public int getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(int ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public int getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(int ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.isbn ^ (this.isbn >>> 32));
        return hash;
    }

    //Hash code and equals
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
        final Libro other = (Libro) obj;
        if (this.isbn != other.isbn) {
            return false;
        }
        return true;
    }

    //to String

    @Override
    public String toString() {
        return isbn + "\t" + titulo + "\t" + anio + "\t" + ejemplares + "\t" + ejemplaresPrestados +
               "\t" + ejemplaresRestantes + "\t" + alta + "\t" + autor.getNombre() + "\t" + editorial.getNombre();
    }
    
    public void imprimirLindo(){
        System.out.printf( "%-5s %-20s %-10s %-10s %-10s %-10s %-10s %-20s %-10s\n", 
          isbn, titulo,anio,ejemplares,ejemplaresPrestados,ejemplaresRestantes,alta,
          autor.getNombre(),editorial.getNombre());
    }
    
    
}
