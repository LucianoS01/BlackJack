package Controlador;

import Vista.IVista;

import java.util.List;
import java.util.function.Consumer;

public interface IControlador {

    void iniciarJuego();

    void NotificarEvento();

    void mostrarMensajeDesdeElModelo(String mensaje);


    float solicitarApuesta();

    int solicitarOpcion(String mensaje);

    void mostrarCartas(int cartasJugador, int cartasCrupier);

    void mostrarCartasSoloJugador(int cartasJugador);

    void mostrarManoSoloJugador(String manojugadoor, List<String> ruta, String PrimerCartaCrupier);
    void mostrarManoJugadorYCrupier(String manojugadoor, String manocrupier);
    void mostrarSoloManoDeCrupier(String manocrupoier, List<String> ruta );

    void mostrarMazo(String mazo, int cantidad);
    void mostrarSaldoDesdeModelos(String jugador, float saldo);

    void actualizarbloqueoTurno(float saldoDelJugador);
    void mostrarApuesta(float apuesta);

    void setVista(IVista vista);
    void recibiLaOpcion(int opcion);

     void recibiLaApuesta(float apuesta);

      void obternerOpcion(String opcion);

      void mostrarSoloPuntajeJugador();

    void setNombreJugador (String Nombre);

    String obtenerNombreJugador();

     void mostrarResultadoPartida(String NombreJugador, int puntajeJugador, int puntajeCrupier);

    void mostrarPuntajeSoloCrupier(int puntajeCrupier);

    void solicitarRecargar_Dinero(String Mensaje);


    void mostrarManoSoloJugadores(String manojugadoor,  List<String> ruta, String cartaCrupier,  List<String> cartaJugaores, boolean esPrimeravez, int puntajeJugador, int PuntajeRival );



}