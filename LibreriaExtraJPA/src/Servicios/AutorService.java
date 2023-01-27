/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Autor;
import Entidades.ControladoraJPA;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yamila
 */
public class AutorService {
    
    ControladoraJPA control = new ControladoraJPA();
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    Menu m = new Menu();
    
    public void menuAutor(){
         System.out.println("Bienvenido al sistema de Autor ");
        int opcion = 20;
        do {
            do {
             System.out.println("Ingrese la operacion a realizar:\n1. Crear Autor \n2. Mostrar Autor por Id\n3. Editar Autor"
                     + "\n4. Dar de baja y/o Eliminar Autor\n5. Mostrar lista completa de autores \n0. Volver al menu principal");
             
            try {
                opcion = 20; // se reinicia con una opcion diferente a una valida
                opcion=Integer.parseInt(leer.next());
                break;
                }catch(Exception ex) {
                    System.out.println("Error, ingrese un numero ");
            }   
        } while (opcion!=1 && opcion!=2 && opcion!=3 && opcion!=4 && opcion!=5 && opcion!=0 );
        
       
            switch(opcion)
            {
                case 1:    
                    System.out.println("Vamos a crear un autor:");
                    System.out.println("Ingrese el nombre de autor: ");
                    String nombre = leer.next();
                    Autor a1 = new Autor(nombre, true);
                    control.crearAutor(a1);
                    break;
                case 2:
                    System.out.println("Ingrese el id del autor: ");
                    int id = leer.nextInt();
                    System.out.println(control.traerAutor(id));
                    break;
                case 3: 
                    System.out.println("Vamos a editar un autor: ");
                    mostrarListaAutores();
                    System.out.println("Seleccione el Id del autor a editar: ");
                    int idAutor= leer.nextInt();
                    Autor autorEdit = control.traerAutor(idAutor);
                    System.out.println("Ingrese el nombre nuevo: ");
                    String nombreNuevo= leer.next();
                    autorEdit.setNombre(nombreNuevo);
                    control.editarAutor(autorEdit);
                break;
                case 4:
                    System.out.println("Vamos a dar de baja a un Autor");
                    mostrarListaAutores();
                    System.out.println("Seleccione el Id del autor a dar de baja: ");
                    int idAutor2= leer.nextInt();
                    Autor autorEdit2 = control.traerAutor(idAutor2);
                    autorEdit2.setAlta(false);
                    control.editarAutor(autorEdit2);
                    System.out.println("Quieres eliminar completamente el autor de la base de datos ? s/n");
                    String respuesta = leer.next();
                        if (respuesta.equalsIgnoreCase("s")) {
                        control.eliminarAutor(idAutor2);
                        }
                    break;
                case 5:
                    mostrarListaAutores();
                    break;
                case 0: 
                    m.menu();
                    break;
            }
        } while (opcion != 0);    
    }
    
    //SubMetodos
    public void mostrarListaAutores(){
        System.out.println("La lista total de autores es la siguiente: ");
        ArrayList<Autor>listaautores = control.traerListaAutores();
        System.out.printf("%-5s %-15s %-10s\n", "ID","NOMBRE", "ALTA");
        for (Autor listaautore : listaautores) {
            listaautore.imprimirLindo();
        }
       System.out.println("------------------");
    }
    
}
