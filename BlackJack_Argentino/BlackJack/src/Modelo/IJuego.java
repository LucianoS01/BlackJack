package Modelo;
import Controlador.Controlador;
import Controlador.IControlador;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;
import java.util.List;

public interface IJuego extends IObservableRemoto {

   // void setControlador(IControlador controlador);

   // void  static  BackJack getInstancia();



    static IJuego getInstancia() throws RemoteException {
        return null;
    }


    void jugar() throws RemoteException;

     void comportamientoJugador(Jugador jugador) throws RemoteException;

     void comportamientoCrupier() throws RemoteException;

     void estadoDeLaMano()  throws RemoteException;

    void volveraCargarMazo() throws RemoteException;

    void reapartirCartasInciales(Mazo mazo) throws RemoteException;

    void setApuesta(float apuesta) throws RemoteException;

    void setOpcion(int opcion) throws RemoteException;

    void recibirDatoPedido(String texto) throws RemoteException;

    void sigueTeniendoSaldo()  throws RemoteException;

    int mostrarPuntaje() throws RemoteException;

    String mostrarEstadoDePartida() throws RemoteException;

    Jugador getJugador() throws RemoteException;
     Mazo getMazo()throws RemoteException;
     Crupier getCrupier()   throws RemoteException;


    boolean esMiTurno(String nombreJugador)throws RemoteException;
    void setNombreJugador(String nombreJugador) throws RemoteException;

    String getNombreJugador() throws RemoteException;

    void agregarNuevoJugador(String nombreJugador) throws RemoteException;

    void recargarDinero(boolean resultado, String nombrevolvioJugador) throws RemoteException;

   //oid setControladorr(Controlador controlador);


    void datosPreIniciar()throws RemoteException;
}
