/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entidades.Libro;

/**
 *
 * @author yamila
 */
public final class LibroDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaJPAPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarLibro(Libro libro) throws Exception { // Este metodo es para ingresar 
        em.getTransaction().begin();
        em.persist(libro); // Para guardar - envio el objeto completo
        em.getTransaction().commit();
    }

    public void modificarLibro(Libro libro) throws Exception { // Este metodo es para ingresar o modificar
        em.getTransaction().begin();
        em.merge(libro); // Para modificar un objeto - Sobreescribe los atributos
        em.getTransaction().commit();
    }

    public Libro buscarLibroporTitulo(String titulo) throws Exception {
        Libro libro = (Libro) em.createQuery("SELECT l "
                + " FROM Libro l"
                + " WHERE l.titulo LIKE :titulo").
                setParameter("titulo", titulo).
                getSingleResult();
        return libro;
    }

    public Libro buscarLibroporISBN(Long isbn) throws Exception {
        Libro libro = (Libro) em.createQuery("SELECT l "
                + " FROM Libro l"
                + " WHERE l.isbn LIKE :isbn").
                setParameter("isbn", isbn).
                getSingleResult();
        return libro;
    }
    
        public List<Libro> listarLibroporAutor(String nombre) throws Exception {
        List<Libro> librosFiltados = em.createQuery("SELECT a.titulo,a.isbn,a.year FROM Libro a WHERE a.autor.nombre = :nombre").
                setParameter("nombre", nombre)
                .getResultList();
        return librosFiltados;
    }

}


