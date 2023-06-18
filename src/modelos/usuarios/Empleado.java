package modelos.usuarios;

import java.io.Serializable;
import java.util.Scanner;

public class Empleado extends Usuario implements Serializable {
    public Empleado(String usuario, String clave) {
        super(usuario, clave);
    }

    @Override
    public int mostrarMenu() {
        Scanner scan = new Scanner(System.in);
        int opcion = 100;
        do {
            System.out.println("MENU EMPLEADO---------------------------");
            System.out.println("1) Alimentar gallinas");// la gallina come en gramos, la comida disponible esta en kilos.
            System.out.println("2) Matar gallinas");
            System.out.println("3) Ver estado gallinas");// mostrar
            System.out.println("4) Recoger huevos"); //resetear la cant huevos de la gallina y el alimento. diferenciar y contabilizar los tipos de huevos.
            System.out.println("5) Revisar vida util gallinas" ); // retornar cuantas gallinas han alcanzado su vida util y cuantas estan proximas a alzazarlo.
            System.out.println("6) Avanzar un dia");
            System.out.println("7) Salir");
            System.out.println("----------------------------------------");
            System.out.print("Navegar: ");
            opcion = Integer.parseInt(scan.nextLine());
        } while (opcion > 7);

        return opcion;
    }

    @Override
    public String toString() {
        return "Empleado{" + super.toString() + "}";
    }

    @Override
    public int compareTo(Usuario aComparar) {
        int retorno = 0;
        if (this.getId() < aComparar.getId()) {
            retorno = -1;
        }
        if (this.getId() > aComparar.getId()) {
            retorno = 1;
        }
        return retorno;
    }
}
