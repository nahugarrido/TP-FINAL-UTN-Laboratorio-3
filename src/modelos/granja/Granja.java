package modelos.granja;

import clima.ClimaAPI;
import enums.EnumColor;
import excepciones.ComidaNoSuficienteException;
import excepciones.HanComidoException;
import excepciones.LoteVacioExcepcion;
import enums.EnumRazas;
import excepciones.RecolectarHuevosException;
import genericas.GenericaMap;
import genericas.ListaGallinas;
import interfaces.Entidad;
import otros.GeneradorID;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Esta clase representa la lógica y el funcionamiento de una granja
 */
public class Granja implements Serializable, Entidad {
    private final int id;
    private String nombre;
    private String fecha;
    private HashSet<Integer> usuariosValidos;
    private double comidaDisponible;
    private int gallinasMuertas;
    private double saldo;
    private ListaGallinas listaGallinas;
    private boolean hanComido;
    private boolean hanRecolectado;


    public Granja(String nombre, String fecha, int idUsuario) {
        this.id = GeneradorID.generarIdGranja();
        this.nombre = nombre;
        this.fecha = fecha;
        this.usuariosValidos = new HashSet<>();
        usuariosValidos.add(idUsuario);
        this.comidaDisponible = 10;
        this.gallinasMuertas = 0;
        this.saldo = 5000;
        this.listaGallinas = new ListaGallinas();
        this.hanComido = false;
        this.hanRecolectado = false;
    }

    @Override
    public String toString() {
        return "Granja{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", usuariosValidos=" + usuariosValidos +
                ", comidaDisponible=" + comidaDisponible +
                ", gallinasMuertas=" + gallinasMuertas +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public boolean equals(Object aComparar) {
        if (aComparar != null && aComparar instanceof Granja) {
            return this.getId() == ((Granja) aComparar).getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 4;
    }

    public String ingresarFecha() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Debes ingresar la fecha en el formato: yyyy-mm-dd");
        System.out.println("INGRESA UNA FECHA POR FAVOR:");
        String leerFecha = scan.nextLine();

        this.setFecha(leerFecha);

        return "La fecha ingresada es: " + this.getFecha();
    }

    public void avanzarUnDia() throws RecolectarHuevosException {
        if (this.isHanComido() && !this.isHanRecolectado()) {
            throw new RecolectarHuevosException("Debes recolectar los huevos antes de avanzar! ");
        }

        if (!this.isHanComido()) {
            listaGallinas.aumentarContadorDiasSinComer();
            System.out.println("Debes tener cuidado, si no alimentas a tus gallinas, pueden morir!");
        }

        LocalDate fechaDate = LocalDate.parse(this.getFecha());
        // Agregar un día a la fecha anterior
        LocalDate nuevaFecha = fechaDate.plusDays(1);

        // Formatear la nueva fecha como una cadena en el formato "yyyy-mm-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nuevaFechaStr = nuevaFecha.format(formatter);

        // Actualizar fecha
        this.setFecha(nuevaFechaStr);

        // Actualizar datos del clima
        ClimaAPI.actualizarDatosClima(this.getFecha());

        /// LOGICA DE AVANZAR UN DIA DEBAJO

        /// RESETEAR BOLEANOS
        this.setHanComido(false);
        this.setHanRecolectado(false);

        /// ALTERAR ESTADO DE LAS GALLINAS
        listaGallinas.alterarEstadoGallinas();
    }

    public String costosFijos() {
        this.setSaldo(this.getSaldo() - 500);
        return "Se aplican los costos fijos (-500), el nuevo saldo de la granja es: " + this.getSaldo();
    }

    /**
     * Se encarga de alimentar a las gallinas en la granja.
     * @param comidaKg
     * @throws ComidaNoSuficienteException
     * @throws HanComidoException
     */
    public void alimentarGallinas(double comidaKg) throws ComidaNoSuficienteException, HanComidoException {
        if (hanComido == true) {
            throw new HanComidoException("Las gallinas ya han comido hoy.");
        }
        if (comidaKg > this.getComidaDisponible()) {
            throw new ComidaNoSuficienteException("", this.getComidaDisponible(), comidaKg);
        } else {
            /// descontamos el valor a darle a las gallinas
            System.out.println("TEST: anterior valor en comida, VALOR: " + this.getComidaDisponible());
            this.setComidaDisponible(this.getComidaDisponible() - comidaKg);
            System.out.println("TEST: nuevo valor en comida, VALOR: " + this.getComidaDisponible());
            /// realizamos la operacion
            double comidaRestante = listaGallinas.alimentarGallinas(comidaKg);
            /// actualizamos el valor de la comida en la granja en caso de que haya sobrado
            this.setComidaDisponible(this.getComidaDisponible() + comidaRestante);
            System.out.println("TEST: nuevo, nuevo valor (luego de ajuste) valor en comida, VALOR: " + this.getComidaDisponible());
            this.setHanComido(true);
        }

    }

    public String obtenerEstadoGallinas() {
        return listaGallinas.listarElementos();
    }

    public String calcularPromediosEstados() {
        double[] estados = listaGallinas.calcularPromediosEstados();
        return "Promedio de gallinas felices: " + estados[0] + "%\n" +
                "Promedio de gallinas estresadas: " + estados[1] + "%";
    }

    /**
     * El método recogerHuevos se encarga de recolectar huevos de las gallinas en un lote
     * @return Lote
     * @throws LoteVacioExcepcion
     * @throws RecolectarHuevosException
     */
    public Lote recogerHuevos() throws LoteVacioExcepcion, RecolectarHuevosException {
        if (this.hanRecolectado) {
            throw new RecolectarHuevosException("Los huevos ya han sido recogidos hoy");
        }
        GenericaMap<EnumColor, Integer> huevosRecogidos = listaGallinas.recogerHuevos();
        this.setHanRecolectado(true);
        if (huevosRecogidos.obtenerValor(EnumColor.MEDIO_CLARO) == 0 && huevosRecogidos.obtenerValor(EnumColor.BLANCO) == 0 && huevosRecogidos.obtenerValor(EnumColor.CREMA) == 0) {
            throw new LoteVacioExcepcion("No hay huevos que recoger");
        }
        return new Lote(this.getId(), this.getFecha(), huevosRecogidos);
    }

    /**
     *  Analiza la vida útil de las gallinas en la lista y proporciona un resumen estadístico.
     * @return String
     */
    public String revisarVidaUtilGallinas() {
        int totalGallinas = listaGallinas.contarElementos();
        int[] contadores = listaGallinas.revisarVidaUtilGallinas();
        double promedioAlcanzaronVidaUtil = (double) contadores[0] / totalGallinas * 100;
        double promedioProximasVidaUtil = (double) contadores[1] / totalGallinas * 100;

        return "Total de gallinas = (" + totalGallinas + ")" + "\n" +
                "Promedio de gallinas que alcanzaron su vida útil: " + promedioAlcanzaronVidaUtil + "(" + contadores[0] + ")" + "%\n" +
                "Promedio de gallinas próximas a alcanzar su vida útil: " + promedioProximasVidaUtil + "(" + contadores[0] + ")" + "%";
    }

    /**
     *
     El método matarGallinasVidaUtil describe la eliminación de gallinas basada en su vida útil.
     * @return String
     */
    public String matarGallinasVidaUtil() {
        String texto = "";
        double random = Math.random();

        if(random > 0) {
            texto = "Has ido a matar a Cleta, pero has visto sus brillantes ojos y no has podido hacerlo.";
        }
        if(random > 0.20) {
            texto = "Has tratado de matar a Turuleca, pero sus hijos observaban y te has arrepentido.";
        }
        if(random > 0.40) {
            texto = "Has tratado de matar a Julia, pero es programadora y simio no mata simio.";
        }
        if(random > 0.60) {
            texto = "Te has tropezado agarrando el cuchillo, decides que hoy no mataras a Cleta.";
        }

        int contador = listaGallinas.matarGallinasPorVidaUtil();
        this.setGallinasMuertas(this.getGallinasMuertas() + contador);

        if(contador > 0) {
            texto = "Hoy a dios has desafiado y " + contador + " vidas pollunas te has quedado.";
        }

        return texto;
    }

    /**
     * El método devuelve un resumen diario de la granja.
     * @return texto.
     */
    public String madreNaturaleza() {
        String texto = "Hoy ha sido un dia pacifico y calmado en " + this.getNombre() + ".";
        int contador = listaGallinas.matarGallinasPorHambre();
        this.setGallinasMuertas(this.getGallinasMuertas() + contador);
        if(contador > 0) {
            texto = "Hoy la madre naturaleza ha obrado y tu sin " + contador + " gallinas te has quedado.";
        }
        return texto;
    }

    /**
     * El método compra gallinas de una raza específica en una cantidad determinada.
     * @param raza gallina.
     * @param cantidad solicitada.
     * @return cantidad comprada.
     */
    public int comprarGallinas(EnumRazas raza, int cantidad) {
        double precioPorGallina = 0.0;

        switch (raza) {
            case RHODE_ISLAND_RED:
                precioPorGallina = 10.0;
                break;
            case SUSSEX:
                precioPorGallina = 12.0;
                break;
            case FILIBAR:
                precioPorGallina = 8.0;
                break;
        }

        double costoTotal = precioPorGallina * cantidad;

        if (costoTotal <= this.getSaldo()){ // Verificar si el saldo es suficiente para realizar la compra
            for (int i = 0; i < cantidad; i++) { // Realizar la compra de las gallinas y actualizar el saldo
                Gallina gallina = new Gallina(raza);
                listaGallinas.agregarElemento(gallina);
            }
            this.setSaldo(this.getSaldo() -costoTotal);
        }

        else {
            int cantidadAsequible = (int) (this.getSaldo() / precioPorGallina);
            // Realizar la compra de las gallinas asequibles y actualizar el saldo
            for (int i = 0; i < cantidadAsequible; i++) {
                Gallina gallina = new Gallina(raza);
                listaGallinas.agregarElemento(gallina);
            }
            this.setSaldo(this.getSaldo()- (cantidadAsequible* precioPorGallina));
        }

        return cantidad;
    }


    /**
     * El método comprarAlimento compra alimentos siempre y cuando haya saldo disponible.
     * @param cantidad solicitada.
     * @return cantidad alimento comprado.
     */
    public double comprarAlimento(double cantidad) {
        double precioPorKilo = 10.0; // Precio del alimento por kilo
        double maxCantidad = this.getSaldo() / precioPorKilo; // Cantidad máxima que se puede comprar con el saldo disponible

        if (cantidad <= maxCantidad) {
            double costoTotal = precioPorKilo * cantidad;
            this.setComidaDisponible(this.getComidaDisponible() + cantidad);
            this.setSaldo(this.getSaldo() - costoTotal);
            return cantidad; // Se compra la cantidad solicitada
        } else if (maxCantidad > 0) {
            double costoTotal = precioPorKilo * maxCantidad;
            this.setComidaDisponible(this.getComidaDisponible() + cantidad);
            this.setSaldo(this.getSaldo() - costoTotal);
            return maxCantidad; // Se compra la cantidad máxima posible
        } else {
            return 0; // No se puede comprar ninguna cantidad
        }

    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public HashSet<Integer> getUsuariosValidos() {
        return usuariosValidos;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getComidaDisponible() {
        return comidaDisponible;
    }

    public void setComidaDisponible(double comidaDisponible) {
        this.comidaDisponible = comidaDisponible;
    }

    public int getGallinasMuertas() {
        return gallinasMuertas;
    }

    public void setGallinasMuertas(int gallinasMuertas) {
        this.gallinasMuertas = gallinasMuertas;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void agregarUsuarioValido(int idUsuarioNuevo) {
        usuariosValidos.add(idUsuarioNuevo);
    }

    public boolean isHanComido() {
        return hanComido;
    }

    public void setHanComido(boolean hanComido) {
        this.hanComido = hanComido;
    }

    public boolean isHanRecolectado() {
        return hanRecolectado;
    }

    public void setHanRecolectado(boolean hanRecolectado) {
        this.hanRecolectado = hanRecolectado;
    }
}
