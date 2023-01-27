/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entidades.Editorial;

/**
 *
 * @author yamila
 */
public final class EditorialDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaJPAPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarEditorial(Editorial editorial) throws Exception { // Este metodo es para ingresar 
        em.getTransaction().begin();
        em.persist(editorial); // Para guardar - envio el objeto completo
        em.getTransaction().commit();
    }

    public void modificarEitorial(Editorial editorial) throws Exception { // Este metodo es para ingresar o modificar
        em.getTransaction().begin();
        em.merge(editorial); // Para modificar un objeto - Sobreescribe los atributos
        em.getTransaction().commit();
    }
    
        public Editorial buscarEditorialporNombre(String nombre) throws Exception {
        Editorial editorial = (Editorial) em.createQuery("SELECT e "
                + " FROM Editorial e"
                + " WHERE e.nombre LIKE :nombre").
                setParameter("nombre", nombre).
                getSingleResult();      
        return editorial;
    }
   
}
