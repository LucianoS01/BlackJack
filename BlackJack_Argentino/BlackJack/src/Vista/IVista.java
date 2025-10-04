package Vista;

import Controlador.Controlador;
import Controlador.IControlador;

import java.util.List;
import java.util.function.Consumer;

public interface IVista  {
    void Iniciar();

    void setControlador(Controlador controlador);
     IControlador getControlador();

    void mostrarMensaje(String Mensaje);


    void mostrarCartasMazo(String mostrarMazo, int cantidad);

    void mostrarSaldo(String jugador, float saldo);

    float pedirApuesta();


    void actualizarTurno(String JugadorActual, float saldoDelJugador);
    int pedirOpcion(String mensaje);
    void mostrarCartas(int cartasJugador, int cartasCrupier);

    void mostrarApuesta(float apuesta);

    void mostrarCartasSoloJugador(int cartasJugador);

    void mostrarManoJugador(String manoDeJugador, List<String> ruta, String cartaCrupier);

    void mostrarManoJugadores(String manoDeJugador , List<String> rutas, String cartaCrupier, List<String> cartaJugadores, boolean esPrimeravez,  int puntajeJugador, int PuntajeRival);

    void mostrarManoJugadorYdeCrupier(String manoDeJugador, String manoDeCrupier);
    void mostrarManoCrupier(String manodeCrupier, List<String> ruta);

    void procesarEntradaUsuario(String texto, Consumer<String> callback);

    void obtenerResultadoPartida(String texto, String NombreJugador, int puntajeJugador, int puntajeCrupier);

    boolean mostrarRecargar();

    void mostrarRecargar(String nombreJugador, Consumer<Boolean> callback);

    void mostrarPuntajeSoloCrupierV(int puntajeCrupier);

    void mostrarManoJugadorYdeCrupier(String manojugadoor, String manocrupier, List<String> rutasImagenes, List<String> rutasImagenes1);

    String getMinombreVista();
}

