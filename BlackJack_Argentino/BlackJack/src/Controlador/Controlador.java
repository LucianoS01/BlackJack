package Controlador;

import Modelo.IJuego;
import Modelo.EventoDeJuego;
import Vista.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import p.VistaPartida;


import java.rmi.RemoteException;
import java.util.*;

public class Controlador implements IControladorRemoto, IControlador {

    IJuego modelo;
     IVista vista;
    private List<String> mensajesPendientes = new ArrayList<>();


    /*
    public void agregarObservador(Observer observador){
        modelo.addObserver(observador);
    }
     */


    public Controlador(IVista vista) {
      //  this.modelo = modelo;
        //this.modelo.setControlador(this);
        this.vista = vista;
       // this.modelo = modelo.getInstancia;
        //iniciarJuego();
    }

    public IJuego getModelo(){
        return this.modelo;
    }

    public void setModelo(IJuego modelo){
        this.modelo = modelo;
      //  this.modelo.addObserver(this);

    }

    //
    public void NotificarEvento(){


    }


    public void iniciarJuego()  {
        //Mostrar por pantalla iniciar:
       // vista.Iniciar();
        try {
           // modelo.setControladorr(this);
            modelo.datosPreIniciar();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    public void mostrarMensajeDesdeElModelo(String mensaje){
        vista.mostrarMensaje(mensaje);
    }

    public float solicitarApuesta(){
      //  return vista.pedirApuesta();
        return -1;
    }

    public int solicitarOpcion(String mensaje){
        return vista.pedirOpcion(mensaje);
    }




    public void mostrarCartasSoloJugador(int cartasJugador){
        vista.mostrarCartasSoloJugador(cartasJugador);
    }

    public void mostrarPuntajeSoloCrupier(int puntajeCrupier){
        vista.mostrarPuntajeSoloCrupierV(puntajeCrupier);
    }


    public void mostrarCartas(int cartasJugador, int cartasCrupier){
        vista.mostrarCartas(cartasJugador, cartasCrupier);
    }

    public void mostrarMazo(String mazo, int cantidad){
        vista.mostrarCartasMazo(mazo, cantidad);
    }


    public void mostrarApuesta(float apuesta){
        vista.mostrarApuesta(apuesta);
    }

    public void mostrarSaldoDesdeModelos(String jugador, float saldo){
        vista.mostrarSaldo(jugador, saldo);
    }

    public void mostrarManoSoloJugador(String manojugadoor,  List<String> ruta, String cartaCrupier){
        vista.mostrarManoJugador(manojugadoor,ruta, cartaCrupier);
        //Vamos a crear una funcion en la vista para que reciba un listString de la tabla de rutas.
    }


    public void mostrarManoSoloJugadores(String manojugadoor,  List<String> ruta, String cartaCrupier, List<String> cartaJugaores, boolean esPrimeravez, int puntajeJugador, int PuntajeRival){
        vista.mostrarManoJugadores(manojugadoor,ruta, cartaCrupier, cartaJugaores,esPrimeravez , puntajeJugador, PuntajeRival);
        //Vamos a crear una funcion en la vista para que reciba un listString de la tabla de rutas.
    }




    public void mostrarManoJugadorYCrupier(String manojugadoor, String manocrupier){
        //vamos a insertar la lista.
        try {
            vista.mostrarManoJugadorYdeCrupier(manojugadoor, manocrupier, modelo.getJugador().getMano().getRutasImagenes(), modelo.getCrupier().getMano().getRutasImagenes());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarSoloManoDeCrupier(String manocrupoier, List<String> ruta){
        vista.mostrarManoCrupier(manocrupoier, ruta);

    }

    public void setVista(IVista vista){
        this.vista = vista;
        System.out.println("Vista actualizada en el controlador: " + vista.getClass().getSimpleName());
        // Reenvío lo acumulado
       // iniciarJuego();
    }

    public void actualizarbloqueoTurno(float saldoDelJugador){
        //cuadno recibamos el nombre del jugador. vamos a ver si bloqueamo la ventana o no.
        //Si es el nombre del jugador dejamos en true sino false.
        try {
            vista.actualizarTurno(modelo.getNombreJugador(), saldoDelJugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarSoloPuntajeJugador()  {
        try {
            vista.mostrarMensaje(String.valueOf(modelo.mostrarPuntaje()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    //<recibimos la apuesta.
    public void recibiLaApuesta(float apuesta){
        try {
            modelo.setApuesta(apuesta);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }


    public void recibiLaOpcion(int opcion) {
        try {
            modelo.setOpcion(opcion);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    public void obternerOpcion(String mensaje) {
        vista.procesarEntradaUsuario(mensaje, texto -> {
            try {
                modelo.recibirDatoPedido(texto); // Le pasa el texto directamente al modelo
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //Esto es para mostar las opciones de recarga.

    public String obtenerNombreJugador(){
        try {
            return modelo.getNombreJugador();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void setNombreJugador (String Nombre){
        try {
            modelo.agregarNuevoJugador(Nombre);
           // modelo.setNombreJugador(Nombre);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }



    public void solicitarRecargar_Dinero(String Mensaje){
            vista.mostrarMensaje(Mensaje);
            /*
           boolean opcion =  vista.mostrarRecargar();
            try {
                modelo.recargarDinero(opcion);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

             */

        String nombreJugador = null;
        try {
            nombreJugador = modelo.getNombreJugador();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        vista.mostrarRecargar(nombreJugador, result -> {
            if (result) {
                // El usuario apretó "RECARGAR"
                try {
                    modelo.recargarDinero(true, vista.getMinombreVista());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                // El usuario apretó "NO"
                try {
                    modelo.recargarDinero(false, vista.getMinombreVista());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void mostrarResultadoPartida(String NombreJugador, int puntajeJugador, int puntajeCrupier){
        //aca llamos a la funcion del modelo que va retornar el estado de la partida.
        try {
            vista.obtenerResultadoPartida(modelo.mostrarEstadoDePartida(), NombreJugador, puntajeJugador,  puntajeCrupier);
            System.out.println("resultado de partida: " + modelo.mostrarEstadoDePartida());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }




    //funcion para poder mostrar las cartas graficas:



/*
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String mensaje){
            vista.mostrarMensaje(mensaje);
        }
    }


 */
    //Libreriaas RMI.

    @Override
    public void actualizar(IObservableRemoto modelo, Object cambio) throws RemoteException {
        //Vamos  a meter cambiosd de eventos que tengamos como si fuera blackJack o  si fue Ganador.

        /*
        if (cambio instanceof String){
            String mensaje = (String) cambio;
            //Que sria mostrarMensaje del modelo.
            mostrarMensajeDesdeElModelo(mensaje);
            if (vista != null) {
                vista.mostrarMensaje(mensaje);
            } else {
                mensajesPendientes.add(mensaje);
            }
        }

         */
        if (cambio instanceof EventoDeJuego) {
            EventoDeJuego ev = (EventoDeJuego) cambio;
            switch (ev.getTipo()) {
                case MENSAJE -> vista.mostrarMensaje((String) ev.getDato()[0]);
                case MOSTRAR_SALDO -> {
                    //actualizarbloqueoTurno((Float) ev.getDato()[0]);
                    vista.mostrarSaldo("Jugador: ", (Float) ev.getDato()[0]);
                }
               // case MOSTRAR_MANO -> vista.mostrarMano(ev.getDato()); // podría ser un List<Carta>
                case SOLICITAR_APUESTA -> obternerOpcion((String) ev.getDato()[0]);
                case MOSTRAR_APUESTA -> mostrarApuesta((Float) ev.getDato()[0]);
                case MOSTRAR_PUNTAJE_DE_MANO ->{
                    int puntajeJugador = (int) ev.getDato()[0];
                    int puntajeCrupier = (int) ev.getDato()[1];
                    mostrarCartas(puntajeJugador, puntajeCrupier);
                }
                case MOSTRAR_MANO_SOLOJUGADOR_PRIMERAMANO_CRUPIER ->{
                        mostrarManoSoloJugador((String) ev.getDato()[0], (List<String>) ev.getDato()[1], (String) ev.getDato()[2]);
                }
                case MOSTRAR_RESULTADO_PARTIDA ->{
                    String NombreJugador = (String) ev.getDato()[0];
                    int puntajeJugador = (int) ev.getDato()[1];
                    int puntajeCrupier = (int) ev.getDato()[2];
                    mostrarResultadoPartida(NombreJugador, puntajeJugador, puntajeCrupier);
                }
                case MOSTRAR_PUNTAJE ->{
                    int puntaje = (int) ev.getDato()[0];
                    mostrarCartasSoloJugador(puntaje);
                }
                case MOSTRAR_MANO_COMPLETA->{
                    mostrarSoloManoDeCrupier((String) ev.getDato()[0],(List<String>) ev.getDato()[1] );
                }
                case SOLICITAR_RECARGA_DINERO->{
                    solicitarRecargar_Dinero((String) ev.getDato()[0]);
                }
                case MOSTRAR_PUNTAJE_CRUPIER ->{
                    int puntaje = (int) ev.getDato()[0];
                    mostrarPuntajeSoloCrupier(puntaje);
                }
                case ACTUALIZAR_VENTANA ->{
                    float SaldoDelJugador = (float) ev.getDato()[0];
                    actualizarbloqueoTurno(SaldoDelJugador);

                }
                case NUEVO_JUGADOR ->{
                    float SaldoDelJugador = 500.0F;
                    actualizarbloqueoTurno(SaldoDelJugador);
                }

                case MOSTRAR_MANO_SOLOJUGADORES_PRIMERAMANO_CRUPIER ->{
                    mostrarManoSoloJugadores((String) ev.getDato()[0], (List<String>) ev.getDato()[1], (String) ev.getDato()[2], (List<String>) ev.getDato()[3], (boolean) ev.getDato()[4], (int) ev.getDato()[5], (int) ev.getDato()[6]);
                }





            }
        }






    }



    @Override //Inyeccion de Dependencias.
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modelo = (IJuego) modeloRemoto;
    }

}
