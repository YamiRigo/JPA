/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Autor;
import Entidades.Editorial;
import Entidades.Libro;
import Entidades.ControladoraJPA;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yamila
 */
public class LibroService {
    
    ControladoraJPA control = new ControladoraJPA();
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    AutorService as = new AutorService();
    EditorialService es = new EditorialService();
    Menu m = new Menu();
    
    public void menuLibro(){
         System.out.println("Bienvenido al sistema de Libro ");
        int opcion = 20;
        do {
            do {
             System.out.println("Ingrese la operacion a realizar:\n1. Crear Libro \n2. Mostrar Libro\n3. Editar un libro"
                     + "\n4. Dar de baja y/o Eliminar un libro\n5. Mostrar lista completa de libros"
                     + "\n6. Mostrar libro por Titulo \n7. Mostrar libros por autor"
                     + "\n8. mostrar Libros por editorial \n0. Volver al menu principal");
            try {
                opcion = 20; // se reinicia con una opcion diferente a una valida
                opcion=Integer.parseInt(leer.next());
                break;
                }catch(Exception ex) {
                    System.out.println("Error, ingrese un numero ");
            }
        } while (opcion!=1 && opcion!=2 && opcion!=3  && opcion!=4 && opcion!=5 && opcion!=6 && opcion!=7 && opcion!=8 && opcion!=0);
        
            switch(opcion)
            {
                case 1:    
                    System.out.println("Vamos a crear un nuevo Libro");
                    Libro l1 = crearLibroMenu();
                    control.crearLibro(l1);
                    break;
                case 2:  
                    System.out.println("Ingrese el ISBN del libro: ");
                    long isbn= leer.nextLong();
                    System.out.println(control.traerLibro(isbn));
                break;
                case 3: 
                    System.out.println("Vamos a editar un libro: ");
                    mostrarListaLibros();
                    System.out.println("Ingrese el ISBN del libro a editar: ");
                    long isbn2 = leer.nextLong();
                    Libro libroEdit = control.traerLibro(isbn2);
                    menuEditarLibro(libroEdit);
                break;
                case 4:
                    System.out.println("Vamos a dar de baja un Libro");                   
                    mostrarListaLibros();
                    System.out.println("Seleccione el ISBN del LIBRO a dar de baja: ");
                    long isbnLibro= leer.nextInt();
                    Libro libroEdit2 = control.traerLibro(isbnLibro);
                    libroEdit2.setAlta(false);
                    control.editarLibro(libroEdit2);
                    System.out.println("Quieres eliminar completamente el libro de la base de datos ? s/n");
                    String respuesta = leer.next();
                        if (respuesta.equalsIgnoreCase("s")) {
                            control.eliminarLibro(isbnLibro);
                        }
                    break;
                case 5:
                    mostrarListaLibros();
                    break;
                case 6:
                    System.out.println("Vamos a mostrar un libro por titulo");
                    System.out.println("Ingrese el nombre del libro: ");
                    String buscarNombre = leer.next();
                    control.traerLibroPorTitulo(buscarNombre);
                    break;
                case 7:
                    System.out.println("Vamos a mostrar libros por nombre de autor: ");
                    System.out.println("Ingrese el nombre del autor: ");
                    String buscarAutor= leer.next();
                    control.traerLibroPorAutor(buscarAutor);
                    break;
                case 8:
                    System.out.println("Vamos a Mostrar libros por editorial");
                    System.out.println("Ingrese el nombre de la editorial: ");
                    String buscarEditorial=leer.next();
                    control.traerLibroPorEditorial(buscarEditorial);
                    break;
                case 0: 
                    m.menu();
                    break;
            }
        } while (opcion != 0);   
    }
    //Submetodos
    public Libro crearLibroMenu(){
        System.out.println("Ingrese el numero ISBN: ");
        long isbn = leer.nextLong();
        System.out.println("Ingrese el nombre del libro: ");
        String nombre= leer.next();
        System.out.println("Ingrese el año: ");
        int anio= leer.nextInt();
        System.out.println("Ingrese el total de ejemplares: ");
        int ejemplaresTotal = leer.nextInt();
        System.out.println("Ingrese el total de ejemplares prestados: ");
        int ejemplaresPrestados= leer.nextInt();
        int ejemplaresRestantes = ejemplaresTotal-ejemplaresPrestados;
        as.mostrarListaAutores();
        System.out.println("Seleccione el Id del autor del libro: ");
        int idAutor= leer.nextInt();
        Autor autorlibro = control.traerAutor(idAutor);
        es.mostrarListaEditoriales();
        System.out.println("Seleccione el Id de la editorial del libro: ");
        int idEditorial= leer.nextInt();
        Editorial editorialLibro = control.traerEditorial(idEditorial);
        
        return  new Libro(isbn, nombre, anio, ejemplaresTotal, ejemplaresPrestados, ejemplaresRestantes, true, autorlibro, editorialLibro);
    }
    
    public void mostrarListaLibros(){
        System.out.println("La lista total de Libros es la siguiente: ");
        ArrayList<Libro>listalibros = control.traerListaLibros();
        System.out.printf("%-5s %-20s %-10s %-10s %-10s %-10s %-10s %-20s %-10s\n", "ISBN","NOMBRE", "ANIO","TOTAL","PRESTADOS","RESTANTES","ALTA","AUTOR","EDITORIAL");
        for (Libro aux : listalibros) {
            aux.imprimirLindo();
        }
       System.out.println("------------------");
    }

    private void menuEditarLibro(Libro l1) {
        System.out.println("Bienvenodo al sistema edicion de un Libro ");
        int opcion = 20;
        do {
            do {
             System.out.println("Ingrese la operacion a realizar:\n1. Editar nombre \n2. Editar año\n3. Editar ejempleres totales"
                     + "\n4. editar ejempleres prestados\n5. Editar Autor\n6. Editar Editorial \n0. Volver al menu principal");
             
            try {
                opcion = 20; // se reinicia con una opcion diferente a una valida
                opcion=Integer.parseInt(leer.next());
                break;
                }catch(Exception ex) {
                    System.out.println("Error, ingrese un numero ");
            }     
            
        } while (opcion!=1 && opcion!=2 && opcion!=3 && opcion!=4 && opcion!=5 && opcion!=6 && opcion!=0 );
        
            switch(opcion)
            {
                case 1:    
                    System.out.println("Ingrese el titulo nuevo: ");
                    String nombreNuevo = leer.next();
                    l1.setTitulo(nombreNuevo);
                    control.editarLibro(l1);
                    break;
                case 2:  
                    System.out.println("Ingrese el nuevo año del libro: ");
                    int anioNuevo = leer.nextInt();
                    l1.setAnio(anioNuevo);
                    control.editarLibro(l1);
                break;
                case 3: 
                    System.out.println("Ingrese la nueva cantidad de ejemplares totales: ");
                    int ejempleresTotalesNuevos = leer.nextInt();
                    l1.setEjemplares(ejempleresTotalesNuevos);
                    control.editarLibro(l1);
                break;
                case 4:
                    System.out.println("Ingrese la nueva cantidad de ejemplares prestados: ");
                    int ejemplaresPrestadosNuevos = leer.nextInt();
                    l1.setEjemplares(ejemplaresPrestadosNuevos);
                    control.editarLibro(l1);
                    break;
                case 5:
                    as.mostrarListaAutores();
                    System.out.println("Seleccione el Id del nuevo autor del libro: ");
                    int idAutor= leer.nextInt();
                    Autor autorlibro = control.traerAutor(idAutor);
                    l1.setAutor(autorlibro);
                    control.editarLibro(l1);
                    break;
                case 6:
                    es.mostrarListaEditoriales();
                    System.out.println("Seleccione el Id de la editorial nueva del libro: ");
                    int idEditorial= leer.nextInt();
                    Editorial editorialLibro = control.traerEditorial(idEditorial);
                    l1.setEditorial(editorialLibro);
                    control.editarEditorial(editorialLibro);
                    break;
                case 0: 
                    m.menu();
                    break;
            }
        } while (opcion != 0);
    }
    
}
