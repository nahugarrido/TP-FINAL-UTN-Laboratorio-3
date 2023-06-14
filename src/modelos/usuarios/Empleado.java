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
            System.out.println("1) Opcion 1");
            System.out.println("2) Opcion 2");
            System.out.println("3) Salir");
            System.out.println("----------------------------------------");
            System.out.print("Navegar: ");
            opcion = Integer.parseInt(scan.nextLine());
        } while (opcion > 3);

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
