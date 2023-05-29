import modelos.Granja;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Granja granja = new Granja("2023-01-01");
        System.out.println(granja.toString());

        for(int i = 0; i < 31; i++) {
            granja.avanzarUnDia();
            System.out.println(granja.toString());
        }


    }


}