package Modelo;

import services.Serializador;
import java.util.ArrayList;
import java.util.List;

public class Ranking {

    private Serializador serializador = new Serializador("Datos.dat");

    // Guardar toda la lista de jugadores en el archivo


    /*
                    Jugador copia = new Jugador(j.getNombre(), j.getSaldo());
                serializador.addOneObject(copia); // siempre agregamos al final
     */


    public void guardarJugadores(List<Jugador> jugadores) {
        if (jugadores == null || jugadores.isEmpty()) return;

        List<Jugador> todosJugadores = obtenerRanking();
        if (todosJugadores == null) {
            todosJugadores = new ArrayList<>();
        }

// Actualizamos o agregamos jugadores nuevos
        for (Jugador j : jugadores) {
            todosJugadores.removeIf(existente -> existente.getNombre().equalsIgnoreCase(j.getNombre()));
            todosJugadores.add(new Jugador(j.getNombre(), j.getSaldo()));
        }

        if (!todosJugadores.isEmpty()) {
            // Guardamos el primer jugadfor sobreescribiendo el archivoo
            serializador.writeOneObject(todosJugadores.get(0));

            // Guardamos el resto agregando al archivo
            for (int i = 1; i < todosJugadores.size(); i++) {
                serializador.addOneObject(todosJugadores.get(i));
            }
        }


    }




    //eliminamos Jugador del rancking:
    public void eliminarJugadorDeRanking(String nombreJugador) {
        // Leemos todos los jugadores guardados
        List<Jugador> jugadores = obtenerRanking();

        if (jugadores != null && !jugadores.isEmpty()) {
            // Eliminamos el jugador que coincide con el nombre
            boolean eliminado = jugadores.removeIf(j -> j.getNombre().equalsIgnoreCase(nombreJugador));

            if (eliminado) {
                System.out.println("Se eliminó a: " + nombreJugador);

                // Reescribimos el archivo desde cero
                if (!jugadores.isEmpty()) {
                    // Guardamos el primero sobrescribiendo
                    serializador.writeOneObject(jugadores.get(0));
                    // Guardamos los demás con append
                    for (int i = 1; i < jugadores.size(); i++) {
                        serializador.addOneObject(jugadores.get(i));
                    }
                } else {
                    // Si ya no queda ningún jugador, dejamos archivo vacío
                    serializador.clearFile();
                }
            } else {
                System.out.println("No se encontró al jugador: " + nombreJugador);
            }
        }
    }




    // Obtener todos los jugadores y ordenar por saldo descendente
    public List<Jugador> obtenerRanking() {
        Object[] objs = serializador.readObjects();
        List<Jugador> ranking = new ArrayList<>();

        if (objs != null) {
            for (Object obj : objs) {
                if (obj instanceof Jugador) {
                    ranking.add((Jugador) obj);
                }
            }
        }

        // Orden descendente por saldo
        ranking.sort((j1, j2) -> Double.compare(j2.getSaldo(), j1.getSaldo()));
        return ranking;
    }





    public Serializador getSerializador() {
        return serializador;
    }

    public void setSerializador(Serializador serializador) {
        this.serializador = serializador;
    }
}
