/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Scanner;
import servicios.AutorService;
import servicios.EditorialService;
import servicios.LibroService;

/**
 *
 * @author yamila
 */
public class Menu {

    private final Scanner leer;
    private final LibroService libroService;
    private final EditorialService editorialService;
    private final AutorService autorService;

    public Menu() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.libroService = new LibroService();
        this.editorialService = new EditorialService();
        this.autorService = new AutorService();
    }

    public void menu() throws Exception {

        String respuesta;
        do {
            System.out.println("=====================================");
            System.out.println("<<<<< MENU PRINCIPAL >>>>>");
            System.out.println("=====================================");

            System.out.println(" 1- Cargar Libro");
            System.out.println(" 2- Modificar Libro");
            System.out.println(" 3- Modificar Autor");
            System.out.println(" 4- Modificar Editorial");
            System.out.println(" 5- Busqueda de un Autor por Nombre");
            System.out.println(" 6- Busqueda de un libro por ISBN");
            System.out.println(" 7- Busqueda de un libro por Título");
            System.out.println(" 0- Salir");
            System.out.print(" Opcion : ");

            int opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    libroService.crearLibro(CargarISBN(), CargarTitulo(), CargarAnio(), CargarEjemplares(), CargarEjemplaresPrestados(), CargarEjemplaresRestantes(), CargarBoleeano(), CargarNombreEditorial(), CargarNombreAutor());
                    break;
                case 2:
                    System.out.println("=====================================" + "\n<<<<< MENU - MODIFICAR LIBRO >>>>>" + "\n=====================================");
                    CargarMenuModificarLibro();
                    libroService.modificarLibro(CargarBuscarTitulo());
                    break;
                case 3:
                    System.out.println("=====================================" + "\n<<<<< MENU - MODIFICAR AUTOR >>>>>" + "\n=====================================");
                    System.out.println(" Modificar nombre");
                    autorService.modificarAutor(CargarBuscarAutor());      
                    break;
                case 4:
                    System.out.println("=====================================" + "\n<<<<< MENU - MODIFICAR EDITORIAL >>>>>" + "\n=====================================");
                    System.out.println(" Modificar nombre");
                    editorialService.modificarEditorial(CargarBuscarEditorial());
                    break;
                case 5:
                    autorService.mostrarAutorporNombre(CargarNombreAutor());
                    break;
                case 6:
                    libroService.imprimirLibroporIsbn(CargarISBN());
                    break;
                case 7:
                    libroService.imprimirLibroporTitulo(CargarTitulo());
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
            System.out.println("=====================================");
            System.out.println("Desea realizar una nueva consulta o gestion en MENU PRINCIPAL ???:SI/NO");
            respuesta = leer.next();
            respuesta = respuesta.toUpperCase();
            limpiarPantalla();
        } while ("SI".equals(respuesta));

    }

    public void limpiarPantalla() throws AWTException {
        //Dejo esre metodo para ir borrando la consola.. y que no sea un desorden.
        Robot pressbot = new Robot();
        pressbot.setAutoDelay(30); // Tiempo de espera antes de borrar
        pressbot.keyPress(17); // Orden para apretar CTRL key
        pressbot.keyPress(76);// Orden para apretar L key
        pressbot.keyRelease(17); // Orden para soltar CTRL key
        pressbot.keyRelease(76); // Orden para soltar L key

    }

    public Long CargarISBN() {
        System.out.println("=====================================");
        System.out.println("<<< LIBRO >>>");
        System.out.print(" ISBN: ");
        Long isbnI = leer.nextLong();
        return isbnI;
    }

    public String CargarTitulo() {
        System.out.print(" TITULO: ");
        String tituloI = leer.next().toUpperCase();
        return tituloI;
    }

    public Integer CargarAnio() {
        System.out.print(" AÑO: ");
        Integer yearI = leer.nextInt();
        return yearI;
    }

    public Integer CargarEjemplares() {
        System.out.print(" EJEMPLARES: ");
        Integer ejemplaresI = leer.nextInt();
        return ejemplaresI;
    }

    public Integer CargarEjemplaresPrestados() {
        System.out.print(" EJEMPLARES PRESTADOS: ");
        Integer ejemplaresPrestadosI = leer.nextInt();
        return ejemplaresPrestadosI;
    }

    public Integer CargarEjemplaresRestantes() {
        System.out.print(" EJEMPLARES RESTANTES: ");
        Integer ejemplaresPrestadosI = leer.nextInt();
        return ejemplaresPrestadosI;
    }

    public Boolean CargarBoleeano() {
        Boolean altaI = true;
        System.out.print(" ALTA: " + altaI);
        return altaI;
    }

    public String CargarNombreEditorial() {
        System.out.println("\n=====================================");
        System.out.println("<<< EDITORIAL >>>");
        System.out.print(" NOMBRE: ");
        String nombreI = leer.next().toUpperCase();
        return nombreI;
    }

    public String CargarNombreAutor() throws Exception {
        System.out.println("=====================================");
        System.out.println("<<< AUTOR >>>");
        System.out.print(" NOMBRE: ");
        String nombreI = leer.next().toUpperCase();
        return nombreI;
    }

    public Integer CargarOpcion() {
        System.out.print(" Opcion: ");
        Integer opcI = leer.nextInt();
        return opcI;
    }

    public void CargarMenuModificarLibro() {
        System.out.println(" 1- Modificar ISBN" + "\n 2- Modificar Titulo" + "\n 3- Modificar Año"+"\n 4- Salir");
    }

    public String CargarBuscarTitulo() {
        System.out.print(" Nombre del libro a modificar(Titulo): ");
        String tituloI = leer.next().toUpperCase();
        return tituloI;
    }
    
    public String CargarBuscarAutor() {
        System.out.print(" Nombre Del Autor a modificar: ");
        String nombreI = leer.next().toUpperCase();
        return nombreI;
    }
    
    public String CargarBuscarEditorial() {
        System.out.print(" NOMBRE DE LA EDITORIAL: ");
        String nombreI = leer.next().toUpperCase();
        return nombreI;
    }

}
