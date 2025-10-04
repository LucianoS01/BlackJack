package Modelo;

import java.io.Serializable;
public class EventoDeJuego implements Serializable {
    private static final long serialVersionUID = 1L; // buena práctica en serializables
    private TipoEvento tipo;
    private Object[] dato; //voy agregar un array por si quiero pasar mas paremetros.

    public EventoDeJuego(TipoEvento tipo, Object[] dato) {

        this.tipo = tipo;
        this.dato = dato;
    }

    // Constructor más sencillo: un solo dato
    public EventoDeJuego(TipoEvento tipo, Object dato) {
        this.tipo = tipo;
        this.dato = new Object[]{dato}; // lo metemos en un array
    }

    public TipoEvento getTipo() { return tipo; }
    public Object[] getDato() { return dato; }



    public enum TipoEvento {
        MENSAJE,           // Un mensaje de texto para mostrar
        MOSTRAR_SALDO,     // Actualizar el saldo del jugador
        MOSTRAR_MANO_SOLOJUGADOR_PRIMERAMANO_CRUPIER,      // Mostrar la mano de jugador o crupier

        MOSTRAR_PUNTAJE,
        MOSTRAR_PUNTAJE_CRUPIER,
        MOSTRAR_PUNTAJE_AMBOS,

        SOLICITAR_RECARGA_DINERO,
        MOSTRAR_MANO_COMPLETA,
        MOSTRAR_RESULTADO_PARTIDA,
        MOSTRAR_PUNTAJE_DE_MANO,  //Muestra el puntaje de la mano.

        ACTUALIZAR_VENTANA,
        MOSTRAR_MANO_SOLOJUGADORES_PRIMERAMANO_CRUPIER,
        NUEVO_JUGADOR,
        MOSTRAR_APUESTA,    //Mostrar apuesta.
        SOLICITAR_APUESTA, // Indicar que se debe pedir la apuesta al jugador
        RESULTADO_PARTIDA  // Indicar que la mano terminó y mostrar resultado


    }

}
