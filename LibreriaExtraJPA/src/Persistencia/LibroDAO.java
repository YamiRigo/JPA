/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.Libro;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.Exceptions.NonexistentEntityException;

/**
 *
 * @author yamila
 */
public class LibroDAO implements Serializable{
    
    public LibroDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
     //  CREAR CONSTRUCTOR PARA CREAR LA ENTITY MANAGER FACTORY.-
    public LibroDAO(){
        emf = Persistence.createEntityManagerFactory("LibreriaExtraJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libro libro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libro libro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            libro = em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = libro.getIsbn();
                if (findLibro(id) == null) {
                    throw new NonexistentEntityException("The libro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro libro;
            try {
                libro = em.getReference(Libro.class, id);
                libro.getIsbn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libro with id " + id + " no longer exists.", enfe);
            }
            em.remove(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libro> findLibroEntities() {
        return findLibroEntities(true, -1, -1);
    }

    public List<Libro> findLibroEntities(int maxResults, int firstResult) {
        return findLibroEntities(false, maxResults, firstResult);
    }

    private List<Libro> findLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Libro findLibro(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    public int getLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Libro> rt = cq.from(Libro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //Metodos agregados
    
    public void consutaLibroNombre(String titulo) {
        EntityManager em = getEntityManager();
        try {
            List<Libro> l = em.createQuery("SELECT a FROM Libro a"
                    + " WHERE a.titulo = :titulo").setParameter("titulo", titulo).getResultList();
            for (Libro libro : l) {
                System.out.println(libro);

            }
        } catch (Exception e) {
            System.out.println("Nombre no encontrado");
            System.out.println(e);
        }
    }
    
     public void consultaLibroAutor(String autor) {
         EntityManager em = getEntityManager();
        try {
            //List<Libro> libros = em.createQuery("SELECT a FROM Libro a Autor b"
            //  + " JOIN a.autor.nombre, b.nombre  = :autor").setParameter("autor", autor).getResultList();

            List<Libro> libros = em.createQuery("SELECT a FROM Libro a"
                    + " WHERE a.autor.nombre LIKE :autor").setParameter("autor", autor).getResultList();
            for (Libro libro : libros) {
                System.out.println(libro.toString());
                System.out.println("");
            }

        } catch (Exception e) {
            System.out.println("Nombre no encontrado");
            System.out.println(e);
        }

    }
     
      public void consultaLibroEditorial(String editorial) {
          EntityManager em = getEntityManager();
        try {
            List<Libro> libros = em.createQuery("SELECT a FROM Libro a"
                    + " WHERE a.editorial.nombre = :editorial").setParameter("editorial", editorial).getResultList();
            for (Libro libro : libros) {
                System.out.println(libro.toString());
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println("Editorial no encontrada");
            System.out.println(e);
        }
    }
    
}
