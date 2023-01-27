/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.Scanner;
import java.util.UUID;
import entidades.Editorial;
import persistencia.EditorialDAO;


/**
 *
 * @author yamila
 */
public class EditorialService {

    private final EditorialDAO editorial_DAO = new EditorialDAO();

    public Editorial crearEditorial(String nombreE, Boolean alta) {
        Editorial editorial = new Editorial();
        try {
            editorial.setNombre(nombreE);
            editorial.setAlta(alta);
            return editorial;
        } catch (Exception e) {
            System.out.println("No se guardo la direccion de forma correcta EDITORIAL" + e.getMessage());
            return null;
        }
    }
    
        public void modificarEditorial(String buscarnombre) throws Exception {

        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            Editorial editorial = editorial_DAO.buscarEditorialporNombre(buscarnombre);
            
            System.out.println("=====================================");
            System.out.print(" NOMBRE");
            editorial.setNombre(leer.next().toUpperCase());
            editorial_DAO.guardarEditorial(editorial);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
}
