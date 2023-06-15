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
            System.out.println("1) Ver datos clima");
            System.out.println("2) Ver estado gallinas");
            System.out.println("3) Ver estado financiero");
            System.out.println("4) Comprar gallinas");
            System.out.println("5) Comprar alimento");
            System.out.println("6) Ver historial lotes");
            System.out.println("7) Vender lotes disponibles");
            System.out.println("8) Crear usuario nuevo");
            System.out.println("9) Salir");
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
