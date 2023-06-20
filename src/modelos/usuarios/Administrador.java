package modelos.usuarios;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Clase administrador, sub-clase de Usuario. Tiene diferente nivel de acceso en Navegacion.
 */
public class Administrador extends Usuario implements Serializable {
    public Administrador(String usuario, String clave) {
        super(usuario, clave);
    }

    /**
     * Muestra las opciones que puede realizar un Administrador
     * @return opcion seleccionada
     */
    @Override
    public int mostrarMenu() {
        Scanner scan = new Scanner(System.in);
        int opcion = 100;
        do {
            System.out.println("MENU ADMIN---------------------------");
            System.out.println("1) Ver datos clima");
            System.out.println("2) Ver estado gallinas");
            System.out.println("3) Ver estado financiero");
            System.out.println("4) Ver alimento disponible");
            System.out.println("5) Comprar gallinas");
            System.out.println("6) Comprar alimento");
            System.out.println("7) Ver historial lotes");
            System.out.println("8) Vender lotes disponibles");
            System.out.println("9) Crear usuario nuevo");
            System.out.println("10) Salir");
            System.out.println("----------------------------------------");
            System.out.print("Navegar: ");
            try {
                opcion = Integer.parseInt(scan.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Debes seleccionar una opcion valida.");
            }
        } while (opcion > 10);

        return opcion;
    }

    @Override
    public String toString() {
        return "Administrador{" + super.toString() + "}";
    }

}
