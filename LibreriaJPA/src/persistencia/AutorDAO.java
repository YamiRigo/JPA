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
import entidades.Autor;

/**
 *
 * @author yamila
 */
public final class AutorDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaJPAPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarAutor(Autor autor) throws Exception { // Este metodo es para ingresar 
        em.getTransaction().begin();
        em.persist(autor); // Para guardar - envio el objeto completo
        em.getTransaction().commit();
    }

    public void modificarAutor(Autor autor) throws Exception { // Este metodo es para ingresar o modificar
        em.getTransaction().begin();
        em.merge(autor); // Para modificar un objeto - Sobreescribe los atributos
        em.getTransaction().commit();
    }
    
    public Autor buscarAutorporNombre(String nombre) throws Exception {
        Autor autor = (Autor) em.createQuery("SELECT a "
                + " FROM Autor a"
                + " WHERE a.nombre LIKE :nombre").
                setParameter("nombre", nombre).
                getSingleResult();      
        return autor;
    }

}
