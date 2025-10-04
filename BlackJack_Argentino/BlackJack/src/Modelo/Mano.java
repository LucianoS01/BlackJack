package Modelo;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mano  {

    private List<Carta> ListaDeMano;

    public Mano() {
       ListaDeMano = new ArrayList<>();

    }

    //Cantidad De cartas
    public int cantidadCartas(){
        return ListaDeMano.size();
    }

    //Agregar una carta a la mano.
    public void agregarCarta(Carta carta){
        ListaDeMano.add(carta);
    }

    //Puntaje de esta mano hasta el  momento;
    public int calcularPuntos(){
        int PuntajeTotal = 0;
        for (Carta carta: ListaDeMano){
          PuntajeTotal = PuntajeTotal + carta.getPuntos();
        }
        return PuntajeTotal;
    }

    //Nuevo CalcularPuntosTotales por el A si tomarlo como un 11 o un 1 depende la conveniencias.
    public int calcularPuntosTotal(){
        int total = 0;
        int cantidadDeA = 0;

        for (Carta carta: ListaDeMano){
            String valor = carta.getValor();
            if (valor.equals("A")){
                cantidadDeA = cantidadDeA + 1;
                total = total + 11;
            }else if (valor.equals("K") || valor.equals("Q") || valor.equals("J")) {
                total = total + 10;
            }
            else{
                total = total + Integer.parseInt(valor);
            }
        }

        //Si nos pasmos de 21 y tenemos alguna As entonces pasamos de 11 a 1.
        while (total > 21 && cantidadDeA > 0){
            total = total - 10; //Digamos que si lo habiamos interpretado como 11 entonces le restamos 10 y nos queda un 1.
            cantidadDeA = cantidadDeA - 1;
        }


        //eliminar:
        //total = 21;
        return total;

    }



    //Puntaje Solo la primeraCarta;
    public int calcularPuntosPrimeraCarta(){
        int PuntajeTotal = 0;
        PuntajeTotal= ListaDeMano.get(0).getPuntos();
        return PuntajeTotal;

    }

    //Puntaje Solo las 2 primeras Cartas para Comprobar si hizo BlackJack;
    public int calcularPuntosPrimeras2Cartas(){
        if (ListaDeMano.size() < 2) {
            System.err.println("Error: No hay suficientes cartas para calcular primeras 2 cartas. Cartas actuales: " + ListaDeMano.size());
            return 0; // o lanzar una excepción controlada
        }

        int PuntajeTotal = 0;
        PuntajeTotal= ListaDeMano.get(0).getPuntos() +  ListaDeMano.get(1).getPuntos();
        return PuntajeTotal;
    }


    public boolean tieneBlackJack() {
        return ListaDeMano.size() == 2 && calcularPuntosTotal() == 21;
    }

    //cada vez que termina la mano tenemos que eliminar las cartas.
    public void vaciarListaDeMano(){
        ListaDeMano.clear();
    }

    public List<Carta> getListaDeMano() {
        return ListaDeMano;
    }


    //Muesta las cartas de la Mano correspondiente.
    public String mostrarCartas() {
        StringBuilder sb = new StringBuilder();
        for (Carta carta : ListaDeMano) {
            sb.append(carta.getValor() +  carta.getPalo()).append(" ");
        }
        return sb.toString();


    }


    //Obtener los nombres de las imagnes que voy a insertar para mostrar las cartas graficamentes.

    public List<String> getRutasImagenes(){
        List<String> rutas = new ArrayList<>();
        for (Carta c: ListaDeMano){
            String ruta = "/resources/cartas/"+ c.getValor() +"_" + c.getPalo() + ".png";
            System.out.println(ruta);
            rutas.add(ruta);
        }
        return rutas;
    }

    //Obtenemos la primera carta. esto nos va a servir para la de crupier.
    public String obtenerPrimerCarta(){
        String rutaPrimeraCarta = "/resources/cartas/"+ ListaDeMano.get(0).getValor() + "_" + ListaDeMano.get(0).getPalo() + ".png";
        return rutaPrimeraCarta;
    }



    public ImageIcon getCartaIcon(Carta carta){
        String ruta = "/resources/cartas/"+ carta.getValor() +"_" + carta.getValor() + ".png";
        return new ImageIcon(getClass().getResource(ruta));
    }




}
