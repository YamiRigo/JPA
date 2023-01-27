/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;
import Persistencia.ControladoraPersistencia;

/**
 *
 * @author yamila
 */
public class ControladoraJPA {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    //Libro
    public void crearLibro(Libro libro){
        
        controlPersis.crearLibro(libro);  
    }
    
    public void eliminarLibro(long isbn){
        
        controlPersis.eliminarLibro(isbn);
    }
    
    public void editarLibro(Libro libro){
        
        controlPersis.editarLibro(libro);
    }
    
    public Libro traerLibro(long isbn){

        return controlPersis.traerLibro(isbn);
    }
    
    public ArrayList<Libro> traerListaLibros(){
        
        return controlPersis.traerListaLibros();        
    }
    public void traerLibroPorTitulo(String titulo){
        controlPersis.traerLibroPorTitulo(titulo);
    }
    
    public void traerLibroPorAutor( String autor){
        controlPersis.traerLibroPorAutor(autor);
    }
    
    public void traerLibroPorEditorial(String editorial){
        controlPersis.traerLibroPorEditorial(editorial);
    }
    
    
    //Editorial
    public void crearEditorial(Editorial editorial){
        
        controlPersis.crearEditorial(editorial);  
    }
    
    public void eliminarEditorial(int id){
        
        controlPersis.eliminarEditorial(id);
    }
    
    public void editarEditorial(Editorial editorial){
        
        controlPersis.editarEditorial(editorial);
    }
    
    public Editorial traerEditorial(int id){

        return controlPersis.traerEditorial(id);
    }
    
    public ArrayList<Editorial> traerListaEditorial(){
        
        return controlPersis.traerListaEditorial();        
    }
    
    
    //Autor
    
      public void crearAutor(Autor autor){
        
        controlPersis.crearAutor(autor);  
    }
    
    public void eliminarAutor(int id){
        
        controlPersis.eliminarAutor(id);
    }
    
    public void editarAutor(Autor autor){
        
        controlPersis.editarAutor(autor);
    }
    
    public Autor traerAutor(int id){

        return controlPersis.traerAutor(id);
    }
    
    public ArrayList<Autor> traerListaAutores(){
        
        return controlPersis.traerListaAutores();        
    }
    
    //Prestamo
    
     public void crearPrestamo(Prestamo prestamo){
        
        controlPersis.crearPrestamo(prestamo);  
    }
    
    public void eliminarPrestamo(int id){
        
        controlPersis.eliminarPrestamo(id);
    }
    
    public void editarPrestamo(Prestamo prestamo){
        
        controlPersis.editarPrestamo(prestamo);
    }
    
    public Prestamo traerPrestamo(int id){

        return controlPersis.traerPrestamo(id);
    }
    
    public ArrayList<Prestamo> traerListaPrestamos(){
        
        return controlPersis.traerListaPrestamos();        
    }
    
     public void traerPrestamoPorCliente( String cliente){
        controlPersis.traerPrestamoPorCliente(cliente);
    }
    
    //Cliente
      public void crearCliente(Cliente cliente){
        
        controlPersis.crearCliente(cliente);  
    }
    
    public void eliminarCliente(int id){
        
        controlPersis.eliminarCliente(id);
    }
    
    public void editarCliente(Cliente cliente){
        
        controlPersis.editarCliente(cliente);
    }
    
    public Cliente traerCliente(int id){

        return controlPersis.traerCliente(id);
    }
    
    public ArrayList<Cliente> traerListaClientes(){
        
        return controlPersis.traerListaClientes();        
    }
    
    
}
