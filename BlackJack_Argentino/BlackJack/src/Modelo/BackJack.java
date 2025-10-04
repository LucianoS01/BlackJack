package Modelo;
import Controlador.Controlador;
import Controlador.IControlador;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class BackJack extends ObservableRemoto implements IJuego {

    //Vamos a implementar 2 jugadores.
    private List<Jugador> jugadores;

    private int TurnoActual = 0;
    private static IJuego instancia = null;
    private Jugador jugador;
    private Mazo mazo;
    private Crupier crupier;
    private EntradaEsperada SituacionDeJuego;

    private EstadoDeJuego estadoPartida;

    //tendria que ser una interface.
    private IControlador controladorr;

    private float apuesta;
   private String DatoPedido;

   private boolean turnoCurpier = false;

   private int OpcionJ;
   private boolean seDoblo = false;

   private boolean puedeBlackJack = true;

   private boolean limpiarcartas = false;

    private boolean esPrimeraVez = true;

   private  int contadorParaLimparCartasDeCrupier = 0;

   private Ranking ranking;


    public static IJuego getInstancia() throws RemoteException{
        if (instancia == null) {
            instancia = new BackJack();
        }
        return instancia;
    }

    //Constructor.
    public BackJack()throws RemoteException {
        mazo = new Mazo();
        jugadores = new ArrayList<>();
        jugador = new Jugador("NombreJugador", 500);
        crupier = new Crupier();
        ranking = new Ranking();

    }

    //Seteamos la Apuesta.
    public void setApuesta(float apuesta) throws RemoteException {
        this.apuesta = apuesta;
        continuarConApuesta(apuesta);
    }

    public void setDatoPedido(String datoPedido) {
        DatoPedido = datoPedido;
    }

    public void setOpcion(int opcion) throws RemoteException {

    }

    /*
    La idea es remplzar todo lo que sea controlar.
    por notificarObservadores.
     */

    public int getTurnoActual() {
        return TurnoActual;
    }

    public void setTurnoActual(int turnoActual) {
        TurnoActual = turnoActual;
    }


    //Obtengo el jugador actual en la lista en ese momento dado.
    public Jugador getJugadorActual(){

        if (jugadores.isEmpty()) {
            System.err.println("No hay jugadores en la lista");
            return null;
        }

        // Asegurar que TurnoActual esté dentro del rango válido
        if (TurnoActual >= jugadores.size()) {
            TurnoActual = 0; // Volver al primer jugador
        }

       // return jugadores.get(TurnoActual);

        if (TurnoActual >= 0 && TurnoActual < jugadores.size()) {
            return jugadores.get(TurnoActual);
        } else {
            System.err.println("Error se fue de rango  TurnoActual : " + TurnoActual);
            return null; // o lanzar una excepción controlada
        }
    }

    //Agregamos Nuevo Jugador.
    public void agregarNuevoJugador(String nombre) throws RemoteException{

        //tenemos que ver que si se queda sin saldo, lo elimino de la lista  de ranking.




        //Hacemos Persistir Un jugador.
        //-------------------------------------------------------------------------------------------------------
        //Si esta el jugador persistimos con el saldo que tenie en ese momento.  sino le damos los 500 iniciales.
        List<Jugador> listaRanking = ranking.obtenerRanking(); // obtenemos jugadores del archivo
        int saldoJugador = 500;
        Jugador nuevoJugador = new Jugador(nombre, saldoJugador);
        if (listaRanking != null && !listaRanking.isEmpty()) {
            for (int i = 0; i < listaRanking.size(); i++) {
                Jugador j = listaRanking.get(i);
                if (nombre.equals(j.getNombre()) ){
                    nuevoJugador.setSaldo(j.getSaldo());
                }
            }
        }
        System.out.println("SEEEE pudo persistir el jugador : "+ nuevoJugador.getNombre() + "con saldo : "+ nuevoJugador.getSaldo());
        //-------------------------------------------------------------------------------------------------------


        jugadores.add(nuevoJugador);
        //Cuando le asignamos 2 cartas al jugador que agregamos al ultimo que esta en la lista..
        Jugador ultimoJugador = jugadores.get(jugadores.size() - 1);
        ultimoJugador.pedirCarta(mazo.repartirCarta());
        ultimoJugador.pedirCarta(mazo.repartirCarta());
        setNombreJugador(nombre);

        // Mostrar toda la lista
        System.out.println("Lista de jugadores: " + jugadores.toArray());
        System.out.println("Sus cartas: " + ultimoJugador.mostrarMano());
        System.out.println("Saldo: " + ultimoJugador.getSaldo());

        System.out.println("nombre: " + nuevoJugador.getNombre());
        System.out.println("Saldo: " + nuevoJugador.getSaldo());

        //Nuevo jugador y notificamos con el nombre y el saldo actual.
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, "Se agrego : " + nuevoJugador.getNombre() + " a la partida "));

    }

    //Cantiadad de Jugadores actuales.
    private int cantidadDeJugadores(){
        return jugadores.size();
    }

    // Método para obtener la lista de jugadores
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    //Crear la cantiada de jugadores.
    //Ya tenemos 1 jugador que va ser el que le demos el nombre. por eso I = 1 en este caso.
    public void empezarCantidadDejugador(int cantidadDeJugadores) throws RemoteException {
        for (int i = 0; i < cantidadDeJugadores; i++){
            Jugador jugador = new Jugador("pepe" + (i + 1), 500);
            jugadores.add(jugador);
        }
    }


    private int ProximoJugaor() {
        int ProxTurno = TurnoActual;
        //  this.TurnoActual = (TurnoActual + 1) % jugadores.size();
        ProxTurno++;
        if (ProxTurno >= jugadores.size()) {
            ProxTurno = 0; // vuelve al primer jugador
        }

        return ProxTurno;
    }


    //Siguiente turno.
    private void siguienteTurno() throws RemoteException  {
        int TurnoAnterior = TurnoActual;
      //  this.TurnoActual = (TurnoActual + 1) % jugadores.size();
        TurnoActual++;
        if (TurnoActual >= jugadores.size()) {
            TurnoActual = 0; // vuelve al primer jugador
        }
        if (TurnoActual == TurnoAnterior){
            System.out.println("No se puede cambiar de turno porque es un turno solo  : "+ getNombreJugador());
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE," No se cambia de Cambio de turno : " + getNombreJugador()));
        } else {
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE,"Cambio de turno : " + getNombreJugador()));
            System.out.println("Le toca al jugador : "+ getNombreJugador());
        }
        //llamos para bloequear ventanas.
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.ACTUALIZAR_VENTANA, getJugadorActual().getSaldo()));
    }



    //Mostramos Los turnos.
    public void mostrarTurno(Jugador jugador) throws RemoteException  {
        System.out.println("Es el turno de: " + jugador.getNombre());
        System.out.println("Sus cartas: " + jugador.mostrarMano());
        System.out.println("Su puntaje es: " + jugador.obtenerPuntaje());
    }


    //Mostramos Manos de Jugadores.
    public void mostrarManosJugadores() throws RemoteException  {
        for (Jugador j : jugadores) {
            System.out.println("Jugador: " + j.getNombre());
            System.out.println("lista de cartas: " + j.mostrarMano());
            System.out.println("-------------------");
        }
    }


    //limpiamos las cartas y decimmos que el crupier tenga nueva mano.
    private void limpiarCartas() throws RemoteException {
        //Reinicimos todas la masnos por las dudas:

        //Limpio todos los jugadores. Porque empezo la nueva RONDAA.
        for (Jugador j : jugadores) {
            j.nuevaMano();
        }

       // contadorParaLimparCartasDeCrupier = contadorParaLimparCartasDeCrupier + 1;
        crupier.nuevaMano();
    }

    //Consultar si es el turno.
    public boolean esMiTurno(String nombreJugador)throws RemoteException{
        return jugadores.get(TurnoActual).getNombre().equals(nombreJugador);
    }


    //Cada vez que finalizamos la ronda.
    private void finalizarRonda() throws RemoteException {
        //Hacemos Persistir los jugadores
        ranking.guardarJugadores(jugadores);

        //Mostamos en consila:
        List<Jugador> listaRanking = ranking.obtenerRanking(); // obtenemos jugadores del archivo
        System.out.println("----- Ranking de jugadores -----");
        if (listaRanking != null && !listaRanking.isEmpty()) {
            for (int i = 0; i < listaRanking.size(); i++) {
                Jugador j = listaRanking.get(i);
                System.out.println((i + 1) + ". " + j.getNombre() + " - Saldo: $" + j.getSaldo());
            }
        } else {
            System.out.println("No hay jugadores en el ranking.");
        }

        limpiarCartas();
        esPrimeraVez = true;
       // siguienteTurno();
        TurnoActual = 0;

        //Perdir cartas 2  al crupíer.
        reapartirCartasInciales(mazo);
        jugar();
    }


        //Datos Iniciales para comenzar el Juego.
    public void datosPreIniciar()throws RemoteException{
        //Toda la informacion a penas enpezar
        //iNDICAMOS LA CANTIDAD DE jGUADORES:  iuglamente vamos a seterar cuandon inicie el conrolador.
        empezarCantidadDejugador(0);

        //Empezamos.
        if (jugadores.size() > 0 ){
            //vamos a decirle al constructor que ya reparta las cartas al crupier.
            //reapartirCartasInciales(mazo);

            if (crupier.getMano().cantidadCartas() < 2){
                crupier.pedirCarta(mazo.repartirCarta());
                crupier.pedirCarta(mazo.repartirCarta());
            }
            jugar();
            //Setemoas al turno 0:
            setTurnoActual(0);
        }
        else {
            System.out.println("No hay jguadores activos.");
        }

    }


    //Metodo de Jugar.
    public void jugar() throws RemoteException {
        System.out.println("cantidad e jugadores : " + cantidadDeJugadores());
        for (Jugador j : jugadores) {
            System.out.println("Jugador: " + j.getNombre());
            System.out.println("lista de cartas: " + j.mostrarMano());
            System.out.println("Su Dinero es: " + j.getSaldo());
            System.out.println("-------------------");
        }
        System.out.println("LA Lista de Crupier : " + crupier.mostrarMano());

        seDoblo = false;
        turnoCurpier = false;
        //Ponemos el estado en pedir apuesta.
        SituacionDeJuego = EntradaEsperada.APOSTAR;
        //Ponemos el estado de partida en ninguna.
        estadoPartida = EstadoDeJuego.NINGUNA;

        if (getJugadorActual().getSaldo() < 0 ){
            //evemto por parametro.
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE,"Te quedaste sin saldo. Fin del Juego"));
            return;
        }

        //Datos del mazo:
        //   String Mensaje ="* Se recarga el mazo y se sigue mandando cartas*";
        System.out.println("cantidad cartas que queda en el maso son: "+  mazo.cantidadDeCartasEnElMazo());

        //Si el mazo no tiene cartas para repartir entonces mezcamos.
        if (mazo.cantidadDeCartasEnElMazo() < 15 ){
            volveraCargarMazo();
        }


        //Mostramos el Saldo:
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_SALDO, getJugadorActual().getSaldo()));
        //Pedimos el dato y mostrmos un mensaje.

        //MostraR LAS CARTAS DE TODOS LOS JUGAOR.
        jugadores.toString();
        mostrarManosJugadores();
        System.out.println("turno: " + TurnoActual);
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.SOLICITAR_APUESTA, " SU Apuesta Señor: \""));
        System.out.println("this Dato es: " + this.DatoPedido);
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE,"Es el turno del jugador : " + getNombreJugador()));
    }

    //Setemoas el nombre del Jugador.
    public void setNombreJugador(String nombreJugador) throws RemoteException{
        //Seteo siempre el nombre del primere jugaor.
        //jugadores.get(0).setNombre(nombreJugador);
        jugadores.get(jugadores.size() - 1).setNombre(nombreJugador);
      // jugadores.get(cantidadDeJugadores()).setNombre(nombreJugador);
        System.out.println("el nombre del jugadior : " + nombreJugador);
    }

    //Obtengo el nombre de los Jugador.
    public String getNombreJugador() throws RemoteException{
        return   getJugadorActual().getNombre();
    }

   //Mostramos el Estado De Partida.
    public String mostrarEstadoDePartida() throws RemoteException {
        return String.valueOf(estadoPartida);
    }

    //continuamos la Apuesta.
    public void continuarConApuesta(float apuesta)  throws RemoteException{
        //notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_SALDO, jugador.getSaldo()));
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_APUESTA, apuesta));

        //Repartir 2 cartas para los jugadores
        //Repartimos cartas Iniciales.
       // reapartirCartasInciales(mazo);
        getJugadorActual().apostar(apuesta);

        //Mostrar saldo :
        String mensaje = ("El saldo actual es de: " + getJugadorActual().getSaldo());
       // notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, "El saldo actual es de:  "));
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_SALDO, getJugadorActual().getSaldo()));

        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_PUNTAJE_DE_MANO, new Object[]{ getJugadorActual().obtenerPuntaje(), crupier.obtenerPuntajePrimeraCarta()}));


        //--- Aca vammos a editarlo vamos a poner el del jugador y el del crupier.
        System.out.println("cupi" + crupier.obtenerPrimeraCarta());

        if (jugadores.size() > 1){
            //notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_MANO_SOLOJUGADORES_PRIMERAMANO_CRUPIER, new Object[]{getJugadorActual().mostrarMano(), getJugadorActual().getMano().getRutasImagenes(), crupier.obtenerPrimeraCarta(), jugadores.get(ProximoJugaor()).mostrarMano() }));


            int puntajeRival = 0;
            if (TurnoActual == 0){
                puntajeRival = -1;
            }else {
                puntajeRival = jugadores.get(ProximoJugaor()).obtenerPuntaje();
            }


            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_MANO_SOLOJUGADORES_PRIMERAMANO_CRUPIER, new Object[]{getJugadorActual().getNombre(), getJugadorActual().getMano().getRutasImagenes(), crupier.obtenerPrimeraCarta(), jugadores.get(ProximoJugaor()).getMano().getRutasImagenes(), esPrimeraVez, getJugadorActual().obtenerPuntaje(), puntajeRival }) );

        }else {
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_MANO_SOLOJUGADOR_PRIMERAMANO_CRUPIER, new Object[]{getJugadorActual().mostrarMano(), getJugadorActual().getMano().getRutasImagenes() ,  crupier.obtenerPrimeraCarta()}));

        }


        System.out.println("? Listas de jugador : "+ getJugadorActual().mostrarMano());
        System.out.println("? Lista de Crupier : " + crupier.mostrarMano());
        esPrimeraVez = false;

        //Tuerno del jugador y elecccion.
        //Ponemos el estado en pedir apuesta.
      //  estadoDeJuego = EntradaEsperada.ELEGIR_OPCION;
        //llamos la funcion de controlador para que nos pase el dato:

        //Turno del jugador actual.
        mostrarTurno(getJugadorActual());

        //Veremos si hay que sacarlo.
        comportamientoJugador(getJugadorActual());
    }



    //Contiar con la accion del jugador de Partida:
    public void comportamientoJugador(Jugador jugador) throws RemoteException{
        //Pedir el dato aca
        //Ponemos el estado en pedir apuesta.
        SituacionDeJuego = EntradaEsperada.ELEGIR_OPCION;
        //llamos la funcion de controlador para que nos pase el dato:
        if (getJugadorActual().getSaldo() >= jugador.getApuesta() && !seDoblo){
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.SOLICITAR_APUESTA, "--¿Desea pedir carta (1) o plantarse (2)? o doblar (3) : \""));
            this.seDoblo = true;
        }
        else  {
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.SOLICITAR_APUESTA, "--¿Desea pedir carta (1) o plantarse (2)? : \""));
        }
    }


    //tenemos que eliminar:
    public void comprobarBLACKJACK()throws RemoteException
    {
            //llamamos al comportamiento                   de crupier solo para verificar si lo puede empartar.
        comportamientoCrupier();
        if(crupier.obtenerPuntaje() != 21){
            String mensaje = " !!! HICISTE BLACKJACK "+ EstadoDeJuego.GANADOR.toString() + " Jugador : " + jugador.obtenerPuntaje() + " -  Crupier tuvo : " + crupier.obtenerPuntaje();
            //Le hacemos ganar al jugador, la suma de la apuesta + la mitad de la misma.
            float  apuestaOriginal =getJugadorActual().getApuesta();
            apuestaOriginal = apuestaOriginal + (apuestaOriginal/2);
            getJugadorActual().setApuesta(apuestaOriginal);
            getJugadorActual().gane();
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, "!!! HICISTE BLACKJACK "));
            mensaje = " !!! Tu ganancia es de : "+ getJugadorActual().getApuesta();
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_SALDO, getJugadorActual().getSaldo()));
            //Estado final Ganador.
            estadoPartida = EstadoDeJuego.BLACKJACK;
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_RESULTADO_PARTIDA, new Object[]{getJugadorActual().obtenerPuntaje(), crupier.obtenerPuntaje()}));
           // jugar();
          //  limpiarcartas = true;
            System.out.println("Se cambio de turno enBlackJack ");
        }
        else {
            estadoPartida = EstadoDeJuego.EMPATE;
            getJugadorActual().empate();
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_RESULTADO_PARTIDA, new Object[]{getJugadorActual().obtenerPuntaje(), crupier.obtenerPuntaje()}));
           // siguienteTurno();
            System.out.println("Se cambio de turno en EMPATe ");
        }

    }



    // Avanza el Turno de jugador
    private void avanzarTurnoJugador() throws RemoteException {
        System.out.println("ACa estamos mi nombre es  : " + getJugadorActual().getNombre());

        if (TurnoActual >= jugadores.size() - 1) {
            // Último jugador  entonces es  turno del crupier
            turnoCrupier();
        } else {
            // Pasarmos  al siguiente jugador
            TurnoActual++;
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE,
                    "Turno del jugador: " + getJugadorActual().getNombre()));

            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.ACTUALIZAR_VENTANA,
                    getJugadorActual().getSaldo()));

            System.out.println("ACa estamos mi nombre es  : " + getJugadorActual().getNombre());
            System.out.println("? Listas de jugador : "+ getJugadorActual().mostrarMano());
            System.out.println("Puntitos  DE : " + getJugadorActual().obtenerPuntaje());

            jugar();
            // Continuar con el turno del siguiente jugador
           // comportamientoJugador(getJugadorActual());
        }

    }


    //Turno del Crupier.
    private void turnoCrupier() throws RemoteException {
        System.out.println("---------estamos en turno crupier:  ");
        this.turnoCurpier = true;
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, "Turno del Crupier"));

        comportamientoCrupier();

        // Mostrar mano final del crupier
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_PUNTAJE_CRUPIER,
                crupier.obtenerPuntaje()));
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_MANO_COMPLETA,
                new Object[]{crupier.mostrarMano(), crupier.getMano().getRutasImagenes()}));

     //  notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_MANO_SOLOJUGADORES_PRIMERAMANO_CRUPIER, new Object[]{getJugadorActual().getNombre(), getJugadorActual().getMano().getRutasImagenes(), crupier.mostrarMano(), jugadores.get(ProximoJugaor()).getMano().getRutasImagenes(), esPrimeraVez, getJugadorActual().obtenerPuntaje(), jugadores.get(ProximoJugaor()).obtenerPuntaje() }) );

        /*
        //Hacemos una pausa de 1 segunado asi vemos el resutlado de crupiier.
        try{
            Thread.sleep(500);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

         */


        // Evaluar resultados para TODOS los jugadores
        for (Jugador j : jugadores) {
            evaluarResultado(j);

        }
        //Luego Finalizamos la pArtida   y volvemos a 0
        finalizarRonda();




    }



    //Evaluamos los resultados.
    private void evaluarResultado(Jugador jugador) throws RemoteException {
        int puntajeJugador = jugador.obtenerPuntaje();
        int puntajeCrupier = crupier.obtenerPuntaje();
        String Mensaje = "";

        // En este caso hizo blackJack pero Tenemos que corroborar que son solos sus 2 cartas.
        if (puntajeJugador == 21 && crupier.obtenerPuntaje() != 21 && jugador.getMano().tieneBlackJack()) {
            //Obtenemos las 2 primeras cartas :
                String mensaje = " !!! HICISTE BLACKJACK "+ EstadoDeJuego.GANADOR.toString() + " Jugador : " + jugador.obtenerPuntaje() + " -  Crupier tuvo : " + crupier.obtenerPuntaje();
                //Le hacemos ganar al jugador, la suma de la apuesta + la mitad de la misma.
                float  apuestaOriginal = jugador.getApuesta();
                apuestaOriginal = apuestaOriginal + (apuestaOriginal/2);
                jugador.setApuesta(apuestaOriginal);
                jugador.gane();

                estadoPartida = EstadoDeJuego.BLACKJACK;
                Mensaje = "!!! HICISTE BLACKJACK";
        }
        //ya se paso de 21
        else if (puntajeJugador > 21) {
            estadoPartida = EstadoDeJuego.PERDEDOR;
            Mensaje = " se pasó de 21. Pierde.";
            sigueTeniendoSaldo();
        }
        else if (puntajeJugador == puntajeCrupier) {
            jugador.empate();
            estadoPartida = EstadoDeJuego.EMPATE;
            Mensaje = " empata con el crupier.";

        }  else if (puntajeCrupier > 21 || puntajeJugador > puntajeCrupier) {
                jugador.gane();
                estadoPartida = EstadoDeJuego.GANADOR;
                Mensaje =  " gana contra el crupier.";

        } else {
            estadoPartida = EstadoDeJuego.PERDEDOR;
            Mensaje = " pierde contra el crupier.";
            sigueTeniendoSaldo();
        }

        //La idea es que se repita solo una vez por jugador:
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, Mensaje));
        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_RESULTADO_PARTIDA,
                new Object[]{jugador.getNombre(), jugador.obtenerPuntaje(), puntajeCrupier}));

    }



    //Decision del Jugador:
    public void decisionJugador() throws RemoteException {
        puedeBlackJack = false;
        String mensaje;

        // --- PEDIR CARTA  1 ---
        if (this.OpcionJ == 1) {
            getJugadorActual().pedirCarta(mazo.repartirCarta());
            System.out.println("? Listas de jugador : " + getJugadorActual().mostrarMano());

            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_PUNTAJE, getJugadorActual().obtenerPuntaje()));
            if (jugadores.size() > 1) {
                int puntajeRival = esPrimeraVez ? -1 : jugadores.get(ProximoJugaor()).obtenerPuntaje();

                if (TurnoActual == 0){
                    puntajeRival = -1;
                }
               // notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_MANO_SOLOJUGADORES_PRIMERAMANO_CRUPIER, new Object[]{getJugadorActual().getNombre(), getJugadorActual().getMano().getRutasImagenes(), crupier.obtenerPrimeraCarta(), jugadores.get(ProximoJugaor()).getMano().getRutasImagenes(), false}));
                notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_MANO_SOLOJUGADORES_PRIMERAMANO_CRUPIER, new Object[]{getJugadorActual().getNombre(), getJugadorActual().getMano().getRutasImagenes(), crupier.obtenerPrimeraCarta(), jugadores.get(ProximoJugaor()).getMano().getRutasImagenes(), false, getJugadorActual().obtenerPuntaje(),puntajeRival}) );

            } else {
                notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_MANO_SOLOJUGADOR_PRIMERAMANO_CRUPIER, new Object[]{getJugadorActual().mostrarMano(), getJugadorActual().getMano().getRutasImagenes(), crupier.obtenerPrimeraCarta()}));
            }

            // Si no se paso de 21 entonces seguimos con el mismo jugador.
            if (getJugadorActual().obtenerPuntaje() <= 21) {
                comportamientoJugador(getJugadorActual()) ;
                //aca tendriasmos que pedir otra vez.
                return;
            }

        }
        // --- DOBLAR 3 ---
        else if (this.OpcionJ == 3) {
            getJugadorActual().doblar(mazo.repartirCarta());

            mensaje = ("Tu saldo actualizado con la doblada es : " + getJugadorActual().getNombre() + " Es de : " + getJugadorActual().getSaldo());
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, mensaje));

            mensaje = ("El puntaje del jugador es : " + getJugadorActual().obtenerPuntaje() + " y su saldo quedó en : " + getJugadorActual().getSaldo());
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, mensaje));

            if (jugadores.size() > 1) {

                int puntajeRival = esPrimeraVez ? -1 : jugadores.get(ProximoJugaor()).obtenerPuntaje();

                if (TurnoActual == 0){
                    puntajeRival = -1;
                }
                //notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_MANO_SOLOJUGADORES_PRIMERAMANO_CRUPIER, new Object[]{getJugadorActual().getNombre(), getJugadorActual().getMano().getRutasImagenes(), crupier.obtenerPrimeraCarta(), jugadores.get(ProximoJugaor()).getMano().getRutasImagenes(), esPrimeraVez, getJugadorActual().obtenerPuntaje(), jugadores.get(ProximoJugaor()).obtenerPuntaje()}) );
                notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_MANO_SOLOJUGADORES_PRIMERAMANO_CRUPIER, new Object[]{getJugadorActual().getNombre(), getJugadorActual().getMano().getRutasImagenes(), crupier.obtenerPrimeraCarta(), jugadores.get(ProximoJugaor()).getMano().getRutasImagenes(), false, getJugadorActual().obtenerPuntaje(),puntajeRival}) );

            } else {
                notificarObservadores(new EventoDeJuego(
                        EventoDeJuego.TipoEvento.MOSTRAR_MANO_SOLOJUGADOR_PRIMERAMANO_CRUPIER,
                        new Object[]{
                                getJugadorActual().mostrarMano(),
                                getJugadorActual().getMano().getRutasImagenes(),
                                crupier.obtenerPrimeraCarta()
                        }));
            }

            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_SALDO, getJugadorActual().getSaldo()));
        }
        // --- PLANTARSE  2 ---
        else if (this.OpcionJ == 2) {
            mensaje = getJugadorActual().getNombre() + " decidió plantarse.";
            System.out.println(mensaje);
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, mensaje));
        }
        else {
            System.out.println("No se cargó la opción");
        }



        // --- VERIFICAR PUNTAJE ---
        int valorDeJugador = getJugadorActual().obtenerPuntaje();
        if (valorDeJugador > 21) {
            sigueTeniendoSaldo();
            avanzarTurnoJugador(); // ️ si se pasó entonces siguiente jugador
            return;
        }

        // --- SI YA NO QUEDAN JUGADORES, JUEGA EL CRUPIER ---
        if ( !(TurnoActual < jugadores.size())) {
            turnoCrupier();

        } else {
            avanzarTurnoJugador();
        }
    }


    //Cuando se quedo sin dinero pedir que ingrese "R" regargar dinero
    public void recargarDinero(boolean resultado, String nombrevolvioJugador) throws RemoteException {
        if (resultado) {
            //Volvemos a cargar el jugador al juego.
            agregarNuevoJugador(nombrevolvioJugador);
            esPrimeraVez = true;

            String mensaje = "Haz vuelto y Regadado $500 : "+ nombrevolvioJugador;
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, mensaje));
            jugar();
            limpiarcartas = true;
        }
        else {
            String mensaje = "FIN DEL JUEGO!!!";
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, mensaje));
            return;
        }
    }


    //Tipos de enum EntradaEsperada del usuario.
    public enum EntradaEsperada {
        APOSTAR,
        ELEGIR_OPCION,
        RECARGAR
    }



    //aca vemos a donde vamos con el dato recibido.
    public void datoRecibido_() throws RemoteException{
        //Verificamos is el dato es recibido de ser asi vamos a seguir con el programa.
        System.out.println("dato es recibidop ? "+ this.DatoPedido);
        switch (SituacionDeJuego){
            case APOSTAR:
                float apuestita = Float.parseFloat(this.DatoPedido);
                continuarConApuesta(apuestita);
                break;
            case ELEGIR_OPCION:
                OpcionJ = Integer.parseInt(this.DatoPedido);
                decisionJugador();
                break;
            case RECARGAR:
              //  recargarDinero();
                break;
        }

    }

    //Una vez que pedimos el dato, desde el controlador llamamos a esta funcion:
    public void recibirDatoPedido(String texto) throws RemoteException{
        this.DatoPedido = texto;
        datoRecibido_();
    }


    //Comportamiento que va hacer el crupier si es menor a 17 entonces va a tirar mas cartas.
    public void comportamientoCrupier() throws RemoteException{
        //Esta funcion explica que pasa con el crupier, si tenemos menos de 17 puntos en la mano puede pedir cartas.
        while (crupier.obtenerPuntaje() < 17 ){
            crupier.pedirCarta(mazo.repartirCarta());
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_MANO_COMPLETA, new Object[]{ crupier.mostrarMano(), crupier.getMano().getRutasImagenes()}));
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_PUNTAJE_CRUPIER,crupier.obtenerPuntaje() ));
           // controladorr.mostrarSoloManoDeCrupier(crupier.mostrarMano());
            //System.out.println("Lista de Crupier : " + crupier.mostrarMano());
            int valor= crupier.obtenerPuntaje();
        }
        turnoCurpier = true;
    }

    //Mostrar el Puntaje del jugador.
     public int mostrarPuntaje() throws RemoteException{
        //mostrar solo el puntaje del jugador
        return jugador.obtenerPuntaje();
     }



    //aca anunciamos el estado de la mano, Si Gane, Perdi, Empate.
    public void estadoDeLaMano() throws RemoteException{
        int valorDeJugador = getJugadorActual().obtenerPuntaje();
        int valordeCrupier = crupier.obtenerPuntaje();

        String mensaje;
        if (valorDeJugador > 21){
            estadoPartida = EstadoDeJuego.PERDEDOR;
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_RESULTADO_PARTIDA, new Object[]{getJugadorActual().obtenerPuntaje(), crupier.obtenerPuntaje()}));
            //controladorr.mostrarResultadoPartida();
            //Ponemos el estado en pedir apuesta.
            sigueTeniendoSaldo();
            return;
        }

        if (valordeCrupier > 21 || valorDeJugador > valordeCrupier && valorDeJugador <= 21 ){
            mensaje = " !!! "+ EstadoDeJuego.GANADOR.toString() + " Jugador : " + valorDeJugador + " -  Crupier tuvo : " + valordeCrupier;
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_PUNTAJE_DE_MANO, new Object[]{ getJugadorActual().obtenerPuntaje(), crupier.obtenerPuntaje()}));
           // controladorr.mostrarCartas(jugador.obtenerPuntaje(), crupier.obtenerPuntaje());
            getJugadorActual().gane();
            //Estado final Ganador.
            estadoPartida = EstadoDeJuego.GANADOR;
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_RESULTADO_PARTIDA, new Object[]{getJugadorActual().obtenerPuntaje(), crupier.obtenerPuntaje()}));
            //controladorr.mostrarResultadoPartida();
        }
        else if (valordeCrupier == valorDeJugador) {
            mensaje = " !!! "+ EstadoDeJuego.EMPATE.toString() + " Jugador : " + valorDeJugador + " -  Crupier tuvo : " + valordeCrupier;
            getJugadorActual().empate();
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_PUNTAJE_DE_MANO, new Object[]{ getJugadorActual().obtenerPuntaje(), crupier.obtenerPuntaje()}));
           // controladorr.mostrarCartas(jugador.obtenerPuntaje(), crupier.obtenerPuntaje());
            //Estado final Empate.
            estadoPartida = EstadoDeJuego.EMPATE;
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_RESULTADO_PARTIDA, new Object[]{getJugadorActual().obtenerPuntaje(), crupier.obtenerPuntaje()}));
           // controladorr.mostrarResultadoPartida();
        }
        else {
            mensaje = " !!! "+ EstadoDeJuego.PERDEDOR.toString() + " Jugador : " + valorDeJugador + " -  Crupier tuvo : " + valordeCrupier;
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_PUNTAJE_DE_MANO, new Object[]{ getJugadorActual().obtenerPuntaje(), crupier.obtenerPuntaje()}));

           // controladorr.mostrarCartas(jugador.obtenerPuntaje(), crupier.obtenerPuntaje());
            //Estado final Perdedor.
            estadoPartida = EstadoDeJuego.PERDEDOR;
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_RESULTADO_PARTIDA, new Object[]{getJugadorActual().obtenerPuntaje(), crupier.obtenerPuntaje()}));
           // controladorr.mostrarResultadoPartida();

        }

        notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, mensaje));
        finalizarRonda();
    }

    //Consultamos si seguimos teniendo Saldo. Sino es asi
    public void sigueTeniendoSaldo() throws RemoteException{
        if (getJugadorActual().getSaldo() > 0 ){
           // finalizarRonda();
            return;
        }
        else if (getJugadorActual().getSaldo() == 0  || getJugadorActual().getSaldo() < 25 ){
          //  setDatoPedido(" ");
            SituacionDeJuego = EntradaEsperada.RECARGAR;
            estadoPartida = EstadoDeJuego.FinDeJuego;
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MOSTRAR_RESULTADO_PARTIDA, new Object[]{getJugadorActual().getNombre()
                    ,getJugadorActual().obtenerPuntaje(), crupier.obtenerPuntaje()}));
            System.out.println("No tiene dinero tiene que recargar");
            //llamos al controldor.
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.SOLICITAR_RECARGA_DINERO, "Ya no tienes dinero!!! Desear Regargar? S/N"));

            //Si es fin de juego, eliminarmos ese jugador y seguimos jugando.
            eliminarJugadorActual();
            return;
        }

    }

    //Si no tiene dinero y no decide recargar entonces eliminarmos el jugador actual.
    private void eliminarJugadorActual() throws RemoteException {
        //Eliminamos el jugador por falta de Saldo.


        //Eliminamos jugador de la lsita. de persistencia
        ranking.eliminarJugadorDeRanking(getJugadorActual().getNombre());
        jugadores.remove(getJugadorActual());

        //Mostamos en consila:
        List<Jugador> listaRanking = ranking.obtenerRanking(); // obtenemos jugadores del archivo
        System.out.println("----- Ranking de jugadores -----");
        if (listaRanking != null && !listaRanking.isEmpty()) {
            for (int i = 0; i < listaRanking.size(); i++) {
                Jugador j = listaRanking.get(i);
                System.out.println((i + 1) + ". " + j.getNombre() + " - Saldo: $" + j.getSaldo());
            }
        } else {
            System.out.println("No hay jugadores en el ranking.");
        }

        System.out.println("Salio " + getJugadorActual().getNombre() +" por falta de saldo " );
        if (!jugadores.isEmpty()) {
            //siguienteTurno();
            finalizarRonda();
        }else {
            notificarObservadores(new EventoDeJuego(EventoDeJuego.TipoEvento.MENSAJE, "Ya no hay jugadores."));
        }





    }

    //Cuando nos quedamos sin cartas suficientes para repartir volvemos a cargar el mazo.:
    public void volveraCargarMazo() throws RemoteException{
        mazo = new Mazo();
        System.out.println("El mazo se volvio a cargar y esta sigo mezclado");

    }


    //repartir cartas Iniciales:
    public void reapartirCartasInciales( Mazo mazo) throws RemoteException{

        //REPARTE 2 CARTAS A CADA JUGADOR.
          for (Jugador jugador: jugadores){
              getJugadorActual().pedirCarta(mazo.repartirCarta());
              getJugadorActual().pedirCarta(mazo.repartirCarta());
              siguienteTurno();
          }
        System.out.println(" el turno cambio a : " + TurnoActual);
        // Repartir 2 cartas al crupier
        crupier.pedirCarta(mazo.repartirCarta());
        crupier.pedirCarta(mazo.repartirCarta());
    }

    //Obtenermos Jugador.
    public Jugador getJugador()  throws RemoteException{
        return getJugadorActual();
    }


    //Obtenemos el mazp
    public Mazo getMazo() throws RemoteException {
        return mazo;
    }

    //Obtenemos el Crupier.
    public Crupier getCrupier() throws RemoteException {
        return crupier;
    }





}


