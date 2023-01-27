/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.Collection;
import java.util.Scanner;
import java.util.UUID;
import entidades.*;
import persistencia.LibroDAO;
/**
 *
 * @author yamila
 */
public class LibroService {

    private final LibroDAO daolibro;
    private final EditorialService direccionService;
    private final AutorService autorService;

    public LibroService() {
        this.daolibro = new LibroDAO();
        this.direccionService = new EditorialService();
        this.autorService = new AutorService();
    }

    public void crearLibro(Long isbn, String titulo, Integer year, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, String nombreE, String nombreA) throws Exception {

        try {

            if (isbn == null) {
                throw new Exception("ISBN NULL");
            }
            if (titulo == null) {
                throw new Exception("Tituo NULL");
            }
            if (year == null) {
                throw new Exception("Year NULL");
            }
            if (ejemplares == null) {
                throw new Exception("Ejemplores NULL");
            }
            if (ejemplaresPrestados == null || ejemplaresPrestados > ejemplares) {
                throw new Exception("EjemplaresPrestados NULL");
            }
            if (ejemplaresRestantes == null) {
                throw new Exception("EjemplaresRestantes NULL");
            }
            if (nombreE == null) {
                throw new Exception("NombreEditorial NULL");
            }
            if (nombreA == null) {
                throw new Exception("NombreAutor NULL");
            }

            //Creamos el libro
            Libro libro = new Libro();

            Editorial nueva_editorial = direccionService.crearEditorial(nombreE, alta);
            Autor nuevo_autor = autorService.crearAutor(nombreA, alta);

            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setYear(year);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAlta(alta);

            libro.setEditorial(nueva_editorial);

            libro.setAutor(nuevo_autor);

            daolibro.guardarLibro(libro);

        } catch (Exception e) {
            System.out.println("No se creo el Libro" + e.getMessage());
        }

    }

    public void modificarLibro(String buscartitulo) throws Exception {

        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            Libro libro = daolibro.buscarLibroporTitulo(buscartitulo);
            String respuesta;
            do {
                System.out.print(" Opcion: ");
                switch (leer.nextInt()) {
                    case 1:
                        System.out.print("ISBN: ");
                        libro.setIsbn(leer.nextLong());
                        break;
                    case 2:
                        System.out.print("TITULO: ");
                        libro.setTitulo(leer.next().toUpperCase());
                        break;
                    case 3:
                        System.out.print("AÑO: ");
                        libro.setYear(leer.nextInt());
                        break;
                    case 4:
                        System.exit(4);
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                        break;
                }
                System.out.println("=====================================");
                System.out.println("Desea realizar una nueva gestion en MODIFICAR LIBRO ???:SI/NO");
                respuesta = leer.next();
                respuesta = respuesta.toUpperCase();

            } while ("SI".equals(respuesta));
            daolibro.modificarLibro(libro);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    
    public void imprimirLibroporIsbn(Long isbn) throws Exception{
        try {
            Libro imprimirLibro = daolibro.buscarLibroporISBN(isbn);
            System.out.println(imprimirLibro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void imprimirLibroporTitulo(String tirulo)throws Exception{
        try {
            Libro imprimirLibro = daolibro.buscarLibroporTitulo(tirulo);
            System.out.println(imprimirLibro);        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }      
    }
   
}
