package modelos.usuarios;

import java.io.Serializable;
import java.util.Scanner;

public class Administrador extends Usuario implements Serializable {
    public Administrador(String usuario, String clave) {
        super(usuario, clave);
    }

    @Override
    public int mostrarMenu() {
        Scanner scan = new Scanner(System.in);
        int opcion = 100;
        do {
            System.out.println("MENU ADMIN---------------------------");
            System.out.println("1) Opcion 1");
            System.out.println("2) Opcion 2");
            System.out.println("3) Opcion 3");
            System.out.println("4) Opcion 4");
            System.out.println("5) Salir");
            System.out.println("----------------------------------------");
            System.out.print("Navegar: ");
            opcion = Integer.parseInt(scan.nextLine());
        } while (opcion > 5);

        return opcion;
    }

    @Override
    public String toString() {
        return "Administrador{" + super.toString() + "}";
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
