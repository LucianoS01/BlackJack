package Vista;
import Controlador.Controlador;
import Controlador.IControlador;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class VistaConsola implements IVista{

    private IControlador controlador;
    private Scanner scanner = new Scanner(System.in);


    public VistaConsola() {
       // this.controlador = controlador;
       // this.controlador.setVista(this);
    }




    @Override
    public void Iniciar() {
        System.out.println("--------------------------------------------------------------");
        System.out.println(" Bienvenido al BlackJack! ");
        controlador.iniciarJuego();


    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public IControlador getControlador() {
        return controlador;
    }

    /*
    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador
    }

     */


//    public float pedirApuesta(){
 //       System.out.print("Ingrese su apuesta: ");
  //      return scanner.nextFloat();
  //  }




    public int pedirOpcion(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextInt();
    }

    public void mostrarCartasSoloJugador(int cartasJugadoro){
        System.out.println("Cartas del jugador: " + cartasJugadoro);
    }

    @Override
    public void mostrarManoJugador(String manoDeJugador, List<String> ruta, String cartaCrupier) {

    }

    @Override
    public void mostrarManoJugadores(String manoDeJugador, List<String> rutas, String cartaCrupier, List<String> cartaJugadores, boolean esPrimeravez, int puntajeJugador, int PuntajeRival) {

    }

    public void mostrarCartas(int cartasJugador, int cartasCrupier) {
        System.out.println("Puntaje del jugador: " + cartasJugador);
        System.out.println("Primera carta del crupier: " + cartasCrupier);
    }


    public void mostrarManoJugador(String manoDeJugador){
        System.out.println("Mano del Jugador: " + manoDeJugador);
    }

    public void mostrarManoCrupier(String manodeCrupier, List<String> ruta){
        System.out.println("Mano del Crupier: " + manodeCrupier);
    }

    @Override
    public void procesarEntradaUsuario(String texto, Consumer<String> callback) {

    }

    @Override
    public void obtenerResultadoPartida(String texto, String NombreJugador, int puntajeJugador, int puntajeCrupier) {

    }

    @Override
    public boolean mostrarRecargar() {
        return false;
    }

    @Override
    public void mostrarRecargar(String nombreJugador, Consumer<Boolean> callback) {

    }

    @Override
    public void mostrarPuntajeSoloCrupierV(int puntajeCrupier) {

    }

    @Override
    public void mostrarManoJugadorYdeCrupier(String manojugadoor, String manocrupier, List<String> rutasImagenes, List<String> rutasImagenes1) {

    }

    @Override
    public String getMinombreVista() {
        return null;
    }

    public void mostrarManoJugadorYdeCrupier(String manoDeJugador, String manoDeCrupier){
        System.out.println("Mano del Jugador: " + manoDeJugador);
        System.out.println("Mano del Crupier: " + manoDeCrupier);
    }

    public void mostrarMensaje(String Mensaje){
        System.out.println(Mensaje);
    }

    public void mostrarApuesta(float apuesta){
        System.out.println("La apuesta es : $ " +apuesta);
    }


    public void mostrarCartasMazo(String mostrarMazo, int cantidad){
        System.out.println(mostrarMazo + " Y la cantidada de cartas son: " + cantidad );
    }




    public void mostrarSaldo(String jugador, float saldo){
        System.out.println("El saldo del jugador : "+ jugador + " : $ "+ saldo);
    }

    @Override
    public float pedirApuesta() {
        return 0;
    }

    @Override
    public void actualizarTurno(String JugadorActual, float saldoDelJugador) {

    }


}
