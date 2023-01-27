/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.Collection;
import java.util.Scanner;
import entidades.Autor;
import persistencia.AutorDAO;

/**
 *
 * @author yamila
 */
public class AutorService {

    private final AutorDAO autor_DAO = new AutorDAO();

    public Autor crearAutor(String nombreA, Boolean alta) {
        Autor autor = new Autor();
        try {
            autor.setNombre(nombreA);
            autor.setAlta(alta);
//            autor_DAO.guardarAutor(autor);
            return autor;
        } catch (Exception e) {
            System.out.println("No se guardo la direccion de forma correcta AUTOR" + e.getMessage());
            return null;
        }
    }

    public void modificarAutor(String buscarnombre) throws Exception {

        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            Autor autor = autor_DAO.buscarAutorporNombre(buscarnombre);
            
            System.out.println("=====================================");
            System.out.print(" Nombre: ");
            autor.setNombre(leer.next().toUpperCase());
            autor_DAO.guardarAutor(autor);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    public void mostrarAutorporNombre(String buscarnombre) throws Exception{
        try {
            Autor imprimir_autor = autor_DAO.buscarAutorporNombre(buscarnombre);
            System.out.println(imprimir_autor);
                       
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }       
        
    }

}