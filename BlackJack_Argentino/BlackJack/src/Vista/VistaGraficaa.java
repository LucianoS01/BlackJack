package Vista;

import Controlador.Controlador;
import Controlador.IControlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;

public class VistaGraficaa extends JFrame implements IVista {
    private int opcionn;
    private JTextArea areaTexto;
    private JTextField campoEntrada;
    private JButton botonEnviar;
    private IControlador controlador;
    private boolean recargar = false;

    private  boolean entro = false;

    public VistaGraficaa() {
       // this.controlador = controlador;
       // controlador.setVista(this);
        inicializarComponentes();
    }

    public void setControlador(){
        //this.controlador = controlador;
    }

    private void inicializarComponentes() {
        setTitle("Consola Gráfica BlackJack");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BorderLayout());

        campoEntrada = new JTextField();
        botonEnviar = new JButton("Enviar");

        panelInferior.add(campoEntrada, BorderLayout.CENTER);
        panelInferior.add(botonEnviar, BorderLayout.EAST);

        add(panelInferior, BorderLayout.SOUTH);

       // botoEnvia();

    }





    private void botoEnvia(){
        /*
                // Acción del botón
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoIngresado = campoEntrada.getText();
                campoEntrada.setText("");

                if (!textoIngresado.isEmpty()) {
                    controlador.mostrarMensajeDesdeElModelo(textoIngresado);
                }
            }
        });
         */
         /*
        botonEnviar.addActionListener(e -> {
            String entradaPorTeclado = campoEntrada.getText().trim();
            campoEntrada.setText("");

            if (!entradaPorTeclado.isEmpty()) {
                //Llamamos a la funcion para proceasr la entrada y enviarlcela al coontrolador.
                controlador.obternerOpcion(entradaPorTeclado);
            }
        });

          */

    }




    //En este vamos a procesar las secciones de entrada del usuario y se las mandamos al controlador para que haha un pasamanos hasta mnodelo.
    public void procesarEntradaUsuario(String texto, Consumer<String> callback) {
        // En vez de usar Scanner, procesás aquí lo que el usuario escribe.
        // Podés agregar lógica tipo controlador.solicitarApuesta(texto)
        // Eliminar ActionListeners previos para evitar múltiples envíos

        for (ActionListener al : botonEnviar.getActionListeners()) {
            botonEnviar.removeActionListener(al);
        }

        areaTexto.append("> " + texto + "\n");
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = campoEntrada.getText().trim();
                campoEntrada.setText("");
                callback.accept(texto);  // Devuelve el texto al controlador
                //return false;
            }
        });

    }

    @Override
    public void obtenerResultadoPartida(String texto, String NombreJugador, int puntajeJugador, int puntajeCrupier) {
        //vamos a ver que ponemos aca pero es retornar como fue la partida.
    }

    @Override
    public boolean mostrarRecargar() {
        //Falta conmpletar aca.

        return recargar;
    }

    @Override
    public void mostrarRecargar(String nombreJugador, Consumer<Boolean> callback) {

    }

    @Override
    public void mostrarPuntajeSoloCrupierV(int puntajeCrupier) {
        areaTexto.append("Puntaje solo del crupier: " + puntajeCrupier+ "\n");
    }


    @Override
    public void mostrarManoJugadorYdeCrupier(String manojugadoor, String manocrupier, List<String> rutasImagenes, List<String> rutasImagenes1) {

    }

    @Override
    public String getMinombreVista() {
        return null;
    }

    @Override
    public void Iniciar() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            controlador.iniciarJuego();  // Arranca el juego
        });
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public IControlador getControlador() {
        return controlador;
    }

    @Override
    public void mostrarMensaje(String Mensaje) {
        areaTexto.append(Mensaje + "\n");
    }

    @Override
    public void mostrarCartasMazo(String mostrarMazo, int cantidad) {
        areaTexto.append("Carta: " + mostrarMazo + " - Valor: " + cantidad + "\n");
    }

    @Override
    public void mostrarSaldo(String jugador, float saldo) {
        areaTexto.append("Saldo del jugador: " + jugador + " - $: " + saldo + "\n");
    }


    private void limpiarListenersBotonEnviar() {
        for (ActionListener al : botonEnviar.getActionListeners()) {
            botonEnviar.removeActionListener(al);
        }
    }
    @Override
    public float pedirApuesta() {
        botonEnviar.addActionListener(e -> {
            try {
                float apuesta = Float.parseFloat(campoEntrada.getText().trim());
                campoEntrada.setText("");
                System.out.println("Se ingreso la apuesta correctamtnen");
                controlador.recibiLaApuesta(apuesta);  // avisamos al controlador
            } catch (NumberFormatException ex) {
                mostrarMensaje("Ingrese un número álido.");
            }
        });
        return -1;
    }

    @Override
    public void actualizarTurno(String JugadorActual, float saldoDelJugador) {

    }


    @Override
    public int pedirOpcion(String mensaje) {
        mostrarMensaje(mensaje); // Mostrás el mensaje en tu TextArea o similar
        limpiarListenersBotonEnviar();
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionElegida = campoEntrada.getText();
                switch (opcionElegida){
                    case "1" -> opcionn = 1;
                    case "2" -> opcionn = 2;
                    case "3" -> opcionn = 3;
                }
               // return false;
            }
        });
        return opcionn;

    }

    @Override
    public void mostrarCartas(int cartasJugador, int cartasCrupier) {
        areaTexto.append("Puntaje del jugador: " + cartasJugador+ "\n");
        areaTexto.append("Primera carta del crupier: " + cartasCrupier+ "\n");

    }

    @Override
    public void mostrarApuesta(float apuesta) {
        areaTexto.append("La apuesta es : $ " +apuesta+ "\n");
    }

    @Override
    public void mostrarCartasSoloJugador(int cartasJugador) {
        areaTexto.append("ahora el Puntaje del jugador: " + cartasJugador+ "\n");
    }

    @Override
    public void mostrarManoJugador(String manoDeJugador, List<String> ruta, String cartaCrupier) {
        areaTexto.append("Mano del Jugador: " + manoDeJugador+ "\n");
    }

    @Override
    public void mostrarManoJugadores(String manoDeJugador, List<String> rutas, String cartaCrupier, List<String> cartaJugadores, boolean esPrimeravez,  int puntajeJugador, int PuntajeRival) {

    }

    @Override
    public void mostrarManoJugadorYdeCrupier(String manoDeJugador, String manoDeCrupier) {

    }

    @Override
    public void mostrarManoCrupier(String manodeCrupier,List<String> ruta) {

    }
}
