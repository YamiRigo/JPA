/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Cliente;
import Entidades.ControladoraJPA;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yamila
 */
public class ClienteService {
    
    ControladoraJPA control = new ControladoraJPA();
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    Menu m = new Menu();

    
    public void menuCliente(){
         System.out.println("Bienvenido al sistema de Cliente ");
        int opcion = 20;
        do {
            do {
             System.out.println("Ingrese la operacion a realizar:\n1. Crear Cliente \n2. Mostrar "
                     + "Cliente por Id\n3. Editar Cliente"
                     + "\n4. Eliminar Cliente\n5. Mostrar lista completa de Clientes \n0. Volver al menu principal");
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
                    System.out.println("Vamoa a crear un Cliente:");
                    System.out.println("Ingrese el documento del nuevo cliente: ");
                    long documento = leer.nextLong();
                    System.out.println("Ingrese el nombre del nuevo Cliente: ");
                    String nombre = leer.next();
                    System.out.println("Ingrese el nombre del nuevo Cliente: ");
                    String apellido = leer.next();
                    System.out.println("Ingrese el telefono del nuevo Cliente: ");
                    String telefono = leer.next();
                    Cliente c1 = new Cliente(documento, nombre, apellido, telefono);
                    control.crearCliente(c1);
                    break;
                case 2:
                    System.out.println("Ingrese el id del Cliente: ");
                    int id = leer.nextInt();
                    System.out.println(control.traerCliente(id));
                    break;
                case 3: 
                    System.out.println("Vamos a editar un cliente: ");
                    mostrarListaClientes();
                    System.out.println("Seleccione el Id del cliente a editar: ");
                    int idCliente= leer.nextInt();
                    Cliente clienteEdit = control.traerCliente(idCliente);
                    menuEditarCliente(clienteEdit);
                break;
                case 4:
                    System.out.println("Vamos a eliminar un cliente");
                    mostrarListaClientes();
                    System.out.println("Seleccione el Id del cliete: ");
                    int idcliente2= leer.nextInt();
                    Cliente clienteEdit2 = control.traerCliente(idcliente2);
                    control.eliminarAutor(idcliente2);
                    break;
                case 5:
                    mostrarListaClientes();
                    break;
                case 0: 
                    m.menu();
                    break;
            }
        } while (opcion != 0);
    }
    
    
    //Submetodos
    
    public void mostrarListaClientes(){
        System.out.println("La lista total de autores es la siguiente: ");
        ArrayList<Cliente>listaClientes = control.traerListaClientes();
        System.out.printf("%-5s%-10s %-10s %-10s %-10s\n", "ID","DOCUMENTO","NOMBRE", "APELLIDO","TELEFONO" );
        for (Cliente listaCliente : listaClientes) {
            listaCliente.imprimirLindo();
        }
       System.out.println("------------------");
    }
    
    private void menuEditarCliente(Cliente c1) {
        System.out.println("Bienvenido al sistema edicion de un Libro ");
        int opcion = 20;
        do {
            do {
             System.out.println("Ingrese la operacion a realizar:\n1. Editar documente \n2. Editar nombre\n3. Editar apellido"
                     + "\n4. Editar telefono \n0. Volver al menu principal");
             
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
                    System.out.println("Ingrese el documento nuevo: ");
                    long documentoNuevo = leer.nextLong();
                    c1.setDocumento(documentoNuevo);
                    control.editarCliente(c1);
                    break;
                case 2:  
                    System.out.println("Ingrese el nuevo nombre del Cliente: ");
                    String nombreNuevo = leer.next();
                    c1.setNombre(nombreNuevo);
                    control.editarCliente(c1);
                break;
                case 3: 
                    System.out.println("Ingrese el nuevo apellido: ");
                    String apellidoNuevo = leer.next();
                    c1.setApellido(apellidoNuevo);
                    control.editarCliente(c1);
                break;
                case 4:
                    System.out.println("Ingrese el nuevo telefono: ");
                    String telefonoNuevo = leer.next();
                    c1.setTelefono(telefonoNuevo);
                    control.editarCliente(c1);
                    break;
                case 0: 
                    m.menu();
                    break;
            }
        } while (opcion != 0);
    }
    
}
