/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Editorial;
import Entidades.ControladoraJPA;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yamila
 */
public class EditorialService {
    
    ControladoraJPA control = new ControladoraJPA();
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    Menu m = new Menu();
    
    public void menuEditorial(){
         System.out.println("Bienvenido al sistema de Editoria ");
        int opcion = 20;
        do {
            do {
             System.out.println("Ingrese la operacion a realizar:\n1. Crear Editorial \n2. Mostrar Editorial por Id"
                     + "\n3. Editar Editorial\n4. Dar de baja y/o Eliminar Editorial\n5. Mostrar Lista completa de Editoriales \n0. Volver al menu principal");
             
            try {
                opcion = 20; // se reinicia con una opcion diferente a una valida
                opcion=Integer.parseInt(leer.next());
                break;
                }catch(Exception ex) {
                    System.out.println("Error, ingrese un numero ");
            }
            
            
        } while (opcion!=1 && opcion!=2 && opcion!=3 && opcion !=4 && opcion!=5 && opcion!=0 );
        
       
            switch(opcion)
            {
                case 1:    
                    System.out.println("Vamoa a crear una Editorial:");
                    System.out.println("Ingrese el nombre de la Editorial: ");
                    String nombre = leer.next();
                    Editorial a1 = new Editorial(nombre, true);
                    control.crearEditorial(a1);
                    break;
                case 2:
                    System.out.println("Ingrese el id de la Editorial: ");
                    int id = leer.nextInt();
                    System.out.println(control.traerEditorial(id));
                    break;
                case 3: 
                    System.out.println("Vamos a editar una Editorial: ");
                    mostrarListaEditoriales();
                    System.out.println("Seleccione el Id de la editorial a editar: ");
                    int idEditorial= leer.nextInt();
                    Editorial editorialEdit = control.traerEditorial(idEditorial);
                    System.out.println("Ingrese el nombre nuevo: ");
                    String nombreNuevo= leer.next();
                    editorialEdit.setNombre(nombreNuevo);
                    control.editarEditorial(editorialEdit);
                break;
                case 4:
                    System.out.println("Vamos a dar de baja una Editorial");
                    mostrarListaEditoriales();
                    System.out.println("Seleccione el Id de la editorial a dar de baja: ");
                    int idEditorial2= leer.nextInt();
                    Editorial editorialEdit2 = control.traerEditorial(idEditorial2);
                    editorialEdit2.setAlta(false);
                    control.editarEditorial(editorialEdit2);
                    System.out.println("Quieres eliminar completamente la editorial de la base de datos ? s/n");
                    String respuesta = leer.next();
                        if (respuesta.equalsIgnoreCase("s")) {
                                control.eliminarEditorial(idEditorial2);
                        }
                    break;
                case 5:
                    mostrarListaEditoriales();
                    break;
                case 0: 
                    m.menu();
                    break;
            }
        } while (opcion != 0);  
    }
    
    //Submetodos
     public void mostrarListaEditoriales(){
        System.out.println("La lista total de Editoriales es la siguiente: ");
        ArrayList<Editorial>listaEditoriales = control.traerListaEditorial();
        System.out.printf("%-5s %-15s %-10s\n", "ID","NOMBRE", "ALTA");
        
        //System.out.println("Id\tNOMBRE\t\tALTA");
        for (Editorial listaautore : listaEditoriales) {
            listaautore.imprimirLindo();
        }
       System.out.println("------------------");
    }
    
    
}
