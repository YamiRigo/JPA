/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Cliente;
import Entidades.Libro;
import Entidades.Prestamo;
import Entidades.ControladoraJPA;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author yamila
 */
public class PrestamoService {
    
    ControladoraJPA control = new ControladoraJPA();
    LibroService ls = new LibroService();
    ClienteService cs = new ClienteService();
    private final Scanner leer = new Scanner(System.in).useDelimiter("\n");
    Menu m = new Menu();
    
    public void menuPrestamo(){
    
        System.out.println("Bienvenido al sistema de Prestamo ");
        int opcion = 20;
        do {
            do {
             System.out.println("Ingrese la operacion a realizar:\n1. Crear Prestamo"
                     + " \n2. Mostrar Prestamo por Id\n3. Editar Prestamo"
                     + "\n4. Dar de baja y/o Eliminar Prestamo\n5. Mostrar lista "
                     + "completa de Prestamos \n6. Finalizar un Prestamo"
                     + "(devolver un libro)\n7. Buscar todos los préstamos de un"
                     + " Cliente. \n0. Volver al menu principal");
             
            try {
                opcion = 20; // se reinicia con una opcion diferente a una valida
                opcion=Integer.parseInt(leer.next());
                break;
                }catch(Exception ex) {
                    System.out.println("Error, ingrese un numero ");
            }
            
            
        } while (opcion!=1 && opcion!=2 && opcion!=3 && opcion!=4 && opcion!=5 &&
                    opcion!=6 && opcion!=7 && opcion!=0 );
        
       
            switch(opcion)
            {
                case 1:    
                    System.out.println("Vamos a crear un prestamo:");
                    ls.mostrarListaLibros();
                    System.out.println("Ingrese el ISBN  del libro que quiere: ");
                    long isbnPrestar =leer.nextLong();
                    Libro l1 = control.traerLibro(isbnPrestar);
                    if (l1.getEjemplaresRestantes()> 0) {
                         Prestamo p1 = menuCrearPrestamo();
                        control.crearPrestamo(p1);
                        int diferencia = (int) (( p1.getFechaDevolucion().getTime()- p1.getFechaPrestamo().getTime())/1000/60/60/24);
                        System.out.println("El prestamo ha sido creado exitosamente y tiene una duracion de "
                            + diferencia + " dias");
                    }else{ System.out.println("Libro no disponible");}                   
                    break;
                case 2:
                    System.out.println("Ingrese el id del prestamo: ");
                    int id = leer.nextInt();
                    System.out.println(control.traerPrestamo(id));
                    break;
                case 3: 
                    System.out.println("Vamos a editar un prestamo: ");
                    mostrarListaPrestamos();
                    System.out.println("Seleccione el Id del prestamo a editar: ");
                    int idPrestamo= leer.nextInt();
                    Prestamo prestamoEdit = control.traerPrestamo(idPrestamo);
                    menuEditarPrestamo(prestamoEdit);  
                break;
                case 4:
                    System.out.println("Vamos a eliminar un prestamo");
                    mostrarListaPrestamos();
                    System.out.println("Seleccione el Id del prestamo: ");
                    int idPrestamo2= leer.nextInt();
                    Prestamo prestamoEdit2 = control.traerPrestamo(idPrestamo2);
                    control.eliminarPrestamo(idPrestamo2);
                    break;
                case 5:
                    mostrarListaPrestamos();
                    break;
                case 6:
                    System.out.println("Elegiste finalizar un prestamo. ");
                    mostrarListaPrestamos();
                    System.out.println("Ingrese el Id del prestamo a finalizar: ");
                    int idFinPrestamo = leer.nextInt();
                    Prestamo prestamoFin = control.traerPrestamo(idFinPrestamo);
                    prestamoFin.setCliente( null);
                    control.editarPrestamo(prestamoFin);
                    System.out.println("Desea eliminar completamente el prestamo de la BD? s/n");
                    String respuestaFin = leer.next();
                        if (respuestaFin.equalsIgnoreCase("s")) {
                            control.eliminarPrestamo(idFinPrestamo);
                        }
                    break;
                case 7:
                    System.out.println("Ingres el nombre del cliente para ver sus prestamos: ");
                    String buscarNombreCliente= leer.next();
                    control.traerPrestamoPorCliente(buscarNombreCliente);
                    break;
                case 0: 
                    m.menu();
                    break;
            }
        } while (opcion != 0);
    }
    
    //Submetodos
    
    public void mostrarListaPrestamos(){
        System.out.println("La lista total de autores es la siguiente: ");
        ArrayList<Prestamo>listaprestamos = control.traerListaPrestamos();
        System.out.printf("%-5s %-20s %-20s %-20s %-10s\n", "ID","FECHA PRESTAMO", "FECHA DEVOLUCION",
                "LIBRO", "CLIENTE");
        for (Prestamo listapres : listaprestamos) {
            listapres.imprimirLindo();
        }
       System.out.println("------------------");
    }
    
    public Prestamo menuCrearPrestamo(){
        System.out.println("La fecha del prestamo se fija al dia de hoy");
        Date fechaPrestamo = new Date();
        int dia2, mes2, anio2;
        System.out.println("Fecha devolución del prestamo:");
        System.out.print("Día: ");
        dia2 = leer.nextInt();        
        System.out.print("Mes: ");
        mes2 = leer.nextInt();        
        System.out.print("Año: ");
        anio2 = leer.nextInt() - 1900;
        Date fechaDevolucion = (new Date(anio2, mes2 - 1, dia2)); 
        ls.mostrarListaLibros();
        System.out.println("Seleccione el ISBN del libro que quiere: ");
        long isbnprestamo = leer.nextLong();
        Libro libroPrestado = control.traerLibro(isbnprestamo);
        
        //ACA armo la logica para restar un libro y asinarlo a la BD
        libroPrestado.setEjemplaresPrestados(libroPrestado.getEjemplaresPrestados()+1);
        if (libroPrestado.getEjemplaresRestantes() > 0) {
            libroPrestado.setEjemplaresRestantes(libroPrestado.getEjemplaresRestantes()-1);
            }else{
                System.out.println("Libro no disponible");
            }
        
        control.editarLibro(libroPrestado);
        cs.mostrarListaClientes();
        System.out.println("Seleccione el Id del cliente: ");
        int idClientePrestamo = leer.nextInt();
        Cliente clientePrestamo = control.traerCliente(idClientePrestamo);
        
        return new Prestamo(fechaPrestamo, fechaDevolucion, libroPrestado, clientePrestamo);
    }
    
    public void menuEditarPrestamo(Prestamo p1){
        System.out.println("Bienvenido al sistema edicion de un Prestamo ");
        int opcion = 20;
        do {
            do {
             System.out.println("Ingrese la operacion a realizar:\n1. Editar fechaPrestamo \n2. Editar fechaDevolucion\n3. Editar libro prestado"
                     + "\n4. Editar Cliente de prestamo \n0. Volver al menu principal");
            try {
                opcion = 20; // se reinicia con una opcion diferente a una valida
                opcion=Integer.parseInt(leer.next());
                break;
                }catch(Exception ex) {
                    System.out.println("Error, ingrese un numero ");
            }    
        } while (opcion!=1 && opcion!=2 && opcion!=3 && opcion!=4 && opcion!=0 );
            switch(opcion)
            {
                case 1:    
                    int dia1, mes1, anio1;
                    System.out.println("Fecha nuevo  de inicio del prestamo: ");
                    System.out.print("Día: ");
                    dia1 = leer.nextInt();        
                    System.out.print("Mes: ");
                    mes1 = leer.nextInt();        
                    System.out.print("Año: ");
                    anio1 = leer.nextInt() - 1900; 
                    p1.setFechaPrestamo(new Date(anio1, mes1 - 1, dia1));
                    control.editarPrestamo(p1);
                    break;
                case 2:  
                    int dia2, mes2, anio2;
                    System.out.println("Fecha nueva de devolución:");
                    System.out.print("Día: ");
                    dia2 = leer.nextInt();        
                    System.out.print("Mes: ");
                    mes2 = leer.nextInt();        
                    System.out.print("Año: ");
                    anio2 = leer.nextInt() - 1900;
                    p1.setFechaDevolucion(new Date(anio2, mes2 - 1, dia2));
                    control.editarPrestamo(p1);
                break;
                case 3:
                    ls.mostrarListaLibros();
                    System.out.println("Ingrese el nuevo ISBN del libro a prestar: ");
                    long isbnNuevo = leer.nextInt();
                    Libro nuevoLibroPrestamo = control.traerLibro(isbnNuevo);
                    p1.setLibro(nuevoLibroPrestamo);
                    control.editarPrestamo(p1);
                break;
                case 4:
                    cs.mostrarListaClientes();
                    System.out.println("Ingrese el nuevo Id del cliente para el prestamo: ");
                    int nuevoIdPrestamo = leer.nextInt();
                    Cliente nuevoClientePrestamo = control.traerCliente(nuevoIdPrestamo);
                    p1.setCliente(nuevoClientePrestamo);
                    control.editarPrestamo(p1);
                    break;
                case 0: 
                    m.menu();
                    break;
            }
        } while (opcion != 0);
    }
    
}
