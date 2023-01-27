/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.ControladoraJPA;
import java.util.Scanner;

/**
 *
 * @author yamila
 */
public class Menu {
    
    ControladoraJPA control = new ControladoraJPA();
    LibroService ls = new LibroService();
    AutorService as = new AutorService();
    ClienteService cs = new ClienteService();
    PrestamoService ps = new PrestamoService();
    EditorialService es = new EditorialService();
    private final Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public void menu(){
         System.out.println("Bienvenido al sistema de Libreria ");
        int opcion = 20;
        do {
            do {
             System.out.println("Ingrese la operacion a realizar:\n1. Libro \n2. Autor\n3. Editorial"
                     + "\n4. Cliente\n5. Prestamo \n0. Salir"); 
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
                    ls.menuLibro();
                    break;
                case 2:  
                    as.menuAutor();
                    break;
                case 3: 
                   es.menuEditorial();
                    break;
                case 4:
                    cs.menuCliente();
                    break;
                case 5:
                    ps.menuPrestamo();
                    break;
                case 0:
                    System.out.println("ADIOS!!");
                break;
            }
        } while (opcion != 0);   
    }
    
}
