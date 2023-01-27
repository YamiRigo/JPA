/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.Autor;
import Entidades.Cliente;
import Entidades.Editorial;
import Entidades.Libro;
import Entidades.Prestamo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Persistencia.Exceptions.NonexistentEntityException;

/**
 *
 * @author yamila
 */
public class ControladoraPersistencia {
    
    LibroDAO libroDAO = new LibroDAO();
    EditorialDAO editorialDAO = new EditorialDAO();
    AutorDAO autorDAO = new AutorDAO();
    PrestamoDAO prestamoDAO = new PrestamoDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    
    //Metodos libro
    public void crearLibro(Libro libro) {
        libroDAO.create(libro);
    }

    public void eliminarLibro(long bn) {
        try {
            libroDAO.destroy(bn);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarLibro(Libro libro) {
        try {
            libroDAO.edit(libro);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Libro traerLibro(long bn) {
        return libroDAO.findLibro(bn);
    }

    public ArrayList<Libro> traerListaLibros() {
       List<Libro> lista = libroDAO.findLibroEntities();
       ArrayList<Libro>listaLibros = new ArrayList<>(lista);
       return listaLibros;
    }
    
    public void traerLibroPorTitulo( String titulo){
        libroDAO.consutaLibroNombre(titulo);
    }
    
    public void traerLibroPorAutor(String autor){
        libroDAO.consultaLibroAutor(autor);
    }
    
    public void traerLibroPorEditorial(String editorial){
        libroDAO.consultaLibroEditorial(editorial);
    }
    
    //Metodos Editorial
    public void crearEditorial(Editorial editorial) {
        editorialDAO.create(editorial);
    }

    public void eliminarEditorial(int id) {
        try {
            editorialDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarEditorial(Editorial editorial) {
        try {
            editorialDAO.edit(editorial);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Editorial traerEditorial(int id) {
        return editorialDAO.findEditorial(id);
    }

    public ArrayList<Editorial> traerListaEditorial() {
        List<Editorial>lista = editorialDAO.findEditorialEntities();
        ArrayList<Editorial> listaEditorials = new ArrayList<>(lista);
        return listaEditorials;
    }

    
    //Metodos Autor
    public void crearAutor(Autor autor) {
       autorDAO.create(autor);
    }

    public void eliminarAutor(int id) {
        try {
            autorDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarAutor(Autor autor) {
        try {
            autorDAO.edit(autor);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Autor traerAutor(int id) {
        return autorDAO.findAutor(id);
    }

    public ArrayList<Autor> traerListaAutores() {
        List<Autor> lista = autorDAO.findAutorEntities();
        ArrayList<Autor>listaAutors = new ArrayList<>(lista);
        return listaAutors;
    }
    
    
    //Metodos Prestamo
    public void crearPrestamo(Prestamo prestamo) {
        prestamoDAO.create(prestamo);
    }

    public void eliminarPrestamo(int id) {
        try {
            prestamoDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarPrestamo(Prestamo prestamo) {
        try {
            prestamoDAO.edit(prestamo);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Prestamo traerPrestamo(int id) {
        return prestamoDAO.findPrestamo(id);
    }

    public ArrayList<Prestamo> traerListaPrestamos() {
        List<Prestamo> listap = prestamoDAO.findPrestamoEntities();
        ArrayList<Prestamo> listaPrestamo = new ArrayList<>(listap);
        return listaPrestamo;
    }
    
     public void traerPrestamoPorCliente(String cliente) {
        prestamoDAO.consultaPrestamoPorCliente(cliente);
    }
    
    //Metodos Cliente
    public void crearCliente(Cliente cliente) {
        clienteDAO.create(cliente);
    }

    public void eliminarCliente(int id) {
        try {
            clienteDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarCliente(Cliente cliente) {
        try {
            clienteDAO.edit(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente traerCliente(int id) {
        return clienteDAO.findCliente(id);
    }

    public ArrayList<Cliente> traerListaClientes() {
        List<Cliente> lista = clienteDAO.findClienteEntities();
        ArrayList<Cliente> listaClientes = new ArrayList<>(lista);
        return listaClientes;
    }   
    
}
