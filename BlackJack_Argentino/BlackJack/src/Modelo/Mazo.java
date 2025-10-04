package Modelo;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Mazo {

    //52 Cartas.
    private List <Carta> ListaDeCartas;

    public Mazo() {
        ListaDeCartas = new ArrayList<>();
        String[] palos = {"Corazones", "Diamantes", "Treboles", "Picas"};
        String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};


        for (String palo : palos) {
            for (String valor : valores) {
                //llamamos a la funcion de Obtener el valor.
                int puntos = obtenerElValor(valor);
                ListaDeCartas.add(new Carta(palo, valor, puntos));
            }
        }
        //una vez que tenemoos todas la lista de la cartas con las cartas tenemos que mezclarlas.
        mezclar();

    }



    //Cantidad de cartas Restantes:
    public int cantidadDeCartasEnElMazo(){
        return ListaDeCartas.size();
    }

    //Repartir cartas
    public Carta repartirCarta(){
        //Decimos si la lista no es vacia vamos sacando de la lista una carta.
        if (!ListaDeCartas.isEmpty()){
           return ListaDeCartas.remove(0);

        }
        return null; //Entonces esta vacia.
    }



    //Mezcar.
    public void mezclar(){
        // Funcion para obtener elemento alaetorio.
        Collections.shuffle(ListaDeCartas);
    }


    //Obtener el Valor.
    public int obtenerElValor(String valor){
        switch (valor) {
            case "A":
                return 11; // El As puede ser 1 o 11.
            case "K":
            case "Q":
            case "J":
                return 10;
            default:
                return Integer.parseInt(valor); // va a comvertir el valor que es. Si es un "4" entonces convierete a un 4.
        }
    }


    //vamos a borrar:
    public String mostrarTodoElMaso(){
        StringBuilder StringDelMazo = new StringBuilder();
        for (Carta carta : ListaDeCartas){
            StringDelMazo.append(carta.getValor()).append(" de ").append(carta.getPalo()).append("\n");
        }
        return StringDelMazo.toString();
    }





}
