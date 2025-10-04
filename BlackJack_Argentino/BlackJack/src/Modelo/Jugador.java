package Modelo;

import java.io.Serializable;
import java.util.List;

public class Jugador extends Mano implements Serializable {

    private String Nombre;
    private float Saldo;
    private float Apuesta;

  //  private Mano mano;

    private transient Mano mano;

    public Jugador(String nombre, float saldo) {
        Nombre = nombre;
        Saldo = saldo;
        this.Apuesta = 0;
        this.mano = new Mano();
    }

    public void setSaldo(float saldo) {
        Saldo = saldo;
    }

    public void setApuesta(float apuesta) {
        Apuesta = apuesta;
    }
//Operaciones que puede hacer el jugador:


    //apostar:
    public void apostar(float monto){
        this.Saldo = Saldo - monto;
        this.Apuesta = monto;
    }


    //recibirCartas
    public void pedirCarta(Carta carta) {
        mano.agregarCarta(carta);
    }


    //plantarce;
    public  void plantarce(){
        //No se que poner aca porque sigue el tuerno.
    }


    //Puntos del jugador:
    public int obtenerPuntaje(){
        return mano.calcularPuntosTotal();
    }


    public int obtenerPuntajePrimeraCarta(){
        return mano.calcularPuntosPrimeraCarta();
    }

    public String obtenerPrimeraCarta(){
        return mano.obtenerPrimerCarta();
    }


    //doblar : aca lo que hacemos es pedir una carta mas y duplicar la apuesta anteriomente dicha.
    public void doblar(Carta carta){
        float apuestaAnterior = this.Apuesta;
        apostar(apuestaAnterior);
        this.Apuesta = Apuesta * 2;
        pedirCarta(carta);
    }


    //dividir: Si las 2 cartas que tenemos son del mismo valor entonces dividimos en 2 manos y se duplica la apuesta.;





    //nuevaMano
    public void nuevaMano(){
        mano.vaciarListaDeMano();
        Apuesta = 0;
    }


    public void gane(){
        this.Saldo = Saldo + Apuesta * 2;
    }

    public void empate(){
        Saldo = Saldo + Apuesta;
    }

    public float getSaldo() {
        return Saldo;
    }

    public String getNombre(){
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public float getApuesta() {
        return Apuesta;
    }

    public Mano getMano() {
        return mano;
    }

    public String mostrarMano() {
        return mano.mostrarCartas();
    }

}

