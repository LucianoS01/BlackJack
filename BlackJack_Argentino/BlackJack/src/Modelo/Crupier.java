package Modelo;



//Esta clase va ser heredar a Jugador ya que tiene las mosmar reglas por asi decir.
public class Crupier extends Jugador  {


    public Crupier() {
        //Super era que llamaba al construcuro de Jugador.
        super("Crupier", 0);
    }

    public boolean jugar(Mazo mazo, Mano mano) {
        boolean sigue = true;
        while (mano.calcularPuntos() < 17 && sigue){
            pedirCarta(mazo.repartirCarta());
        }
        //Si es false quiere decir que el crupier se planta y mostrar puntos.
        obtenerPuntajePrimeraCarta();
        return false;
    }



}
