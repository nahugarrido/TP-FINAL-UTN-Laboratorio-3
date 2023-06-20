package modelos.usuarios;

import java.io.Serializable;
import java.util.Scanner;
/**
 * Clase Empleado, sub-clase de Usuario. Tiene diferente nivel de acceso en Navegacion.
 */
public class Empleado extends Usuario implements Serializable {
    public Empleado(String usuario, String clave) {
        super(usuario, clave);
    }

    /**
     * Muestra las opciones que puede realizar un Empleado
     * @return opcion seleccionada
     */
    @Override
    public int mostrarMenu() {
        Scanner scan = new Scanner(System.in);
        int opcion = 100;
        do {
            System.out.println("MENU EMPLEADO---------------------------");
            System.out.println("1) Ver datos clima");// la gallina come en gramos, la comida disponible esta en kilos.
            System.out.println("2) Alimentar gallinas");// la gallina come en gramos, la comida disponible esta en kilos.
            System.out.println("3) Matar gallinas");
            System.out.println("4) Ver estado gallinas");// mostrar
            System.out.println("5) Recoger huevos"); //resetear la cant huevos de la gallina y el alimento. diferenciar y contabilizar los tipos de huevos.
            System.out.println("6) Revisar vida util gallinas" ); // retornar cuantas gallinas han alcanzado su vida util y cuantas estan proximas a alzazarlo.
            System.out.println("7) Avanzar un dia");
            System.out.println("8) Salir");
            System.out.println("----------------------------------------");
            System.out.print("Navegar: ");
            try {
                opcion = Integer.parseInt(scan.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Debes seleccionar una opcion valida.");
            }
        } while (opcion > 8);

        return opcion;
    }

    @Override
    public String toString() {
        return "Empleado{" + super.toString() + "}";
    }

}
