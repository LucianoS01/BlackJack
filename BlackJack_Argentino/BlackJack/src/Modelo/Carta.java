package Modelo;

public class Carta {
    private String Palo;
    private String Valor;
    private int Puntos;

    public Carta(String palo, String valor, int puntos) {
        Palo = palo;
        Valor = valor;
        Puntos = puntos;
    }

    public String getPalo() {
        return Palo;
    }

    public String getValor() {
        return Valor;
    }

    public int getPuntos() {
        return Puntos;
    }


}
