package p;
import Controlador.Controlador;
import Controlador.IControlador;
import Vista.IVista;

import javax.swing.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author lucci
 * 
 */
public class VistaPartida extends javax.swing.JFrame implements IVista {

    private Controlador controlador;
    private float  inputApuesta = 0;

    private float saldoActual =0;
    private String estadoPartida;

    public String Mi_Nombre;
    public String NombreJugadorACtual;

    private boolean estoyFueraDeJuego = false;

    // Map que relaciona el nombre del jugador con su panel
    private Map<String, JPanel> panelesJugadores = new HashMap<>();
    /**
     * Creates new form Partida
     */



    public VistaPartida() {

        initComponents();

        /*
        // Obtener tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.4);   // 40% del ancho
        int height = (int) (screenSize.height * 0.4); // 40% del alto

        // Configurar ventana
        setSize(width, height);
        setLocationRelativeTo(null); // centrar en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


         */
        //controlador.iniciarJuego();

        //controlador.setVista(this);


       // this.controlador = controlador;
        //this.controlador.setVista(this);
        //controlador.setVista(this);
        //No modificar el tamaño de la ventana:
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Iniciar();
        jLabelSubtitulos.setVisible(false);
        jContendorDeSubtitulo.setVisible(false);
        ButtonPedir.setEnabled(false);
        ButtonPlantarse.setEnabled(false);
        ButtonDoblar.setEnabled(false);
        jButtonApostar.setEnabled(false);
        jLabelMonedas.setVisible(false);
        //Final
        jbuttonRecargar.setVisible(false);
        jLabelYaNoTienesSaldo.setVisible(false);
        jLabelFlechita.setVisible(false);
        jLabelNegro.setVisible(false);
        this.setVisible(true);
        //BLoquear Por turno:
       // jButtonApostar.setVisible(false);


    }

    public String getMinombreVista(){
        return this.Mi_Nombre;
    }


    //patopato

    public void esMiTurno(String JugadorActual){

        if (this.Mi_Nombre.equals(JugadorActual)){
            jPanelCartasJugador.setVisible(true);
            //  jLabelPuntajeJugador.setVisible(true);
            // jPanelCartasJugador.setVisible(true);
           // jPanelCartasJugador2.setVisible(true);
            jButtonApostar.setVisible(true);
            ButtonPedir.setVisible(true);
            ButtonDoblar.setVisible(true);
            ButtonPlantarse.setVisible(true);
         //   ButtonDividir.setVisible(true);
            jLabelSaldo.setVisible(false);
            jMoneda25.setEnabled(true);
            jMoneda50.setEnabled(true);
            jMoneda100.setEnabled(true);
            jLabelSaldo.setVisible(true);
        }else {
            jButtonApostar.setVisible(false);
            ButtonPedir.setVisible(false);
            ButtonDoblar.setVisible(false);
            ButtonPlantarse.setVisible(false);
            ButtonDividir.setVisible(false);
            jLabelSaldo.setVisible(false);
            jMoneda25.setEnabled(false);
            jMoneda50.setEnabled(false);
            jMoneda100.setEnabled(false);
        }
        this.NombreJugadorACtual = JugadorActual;

    }


    //Vamos a bloquear los botones si no es el turno.
    public void actualizarTurno(String JugadorActual, float saldoDelJugador){

        System.out.println("SE HAA CMABIADO EL TURNO A : "+ JugadorActual);

        //Vamos a mostrar el saldo que realmnente tendria que ir.
        this.NombreJugadorACtual = JugadorActual;
        //Si es true bloqueo sino no.
        if (this.Mi_Nombre.equals(JugadorActual)){
            System.out.println("entro a que es igual el nombre");
            this.saldoActual = saldoDelJugador;

          //  jLabelPuntajeJugador.setVisible(true);
           // jPanelCartasJugador.setVisible(true);
            jButtonApostar.setVisible(true);
            jButtonApostar.setEnabled(true);
            ButtonPedir.setVisible(true);
            ButtonPedir.setEnabled(true);
            ButtonDoblar.setVisible(true);
            ButtonDoblar.setEnabled(true);


            ButtonPlantarse.setVisible(true);
            ButtonPedir.setEnabled(false);
            ButtonPlantarse.setEnabled(false);
            ButtonDoblar.setEnabled(false);



         //   ButtonDividir.setVisible(true);
            ButtonDividir.setVisible(false);
         //   ButtonDividir.setEnabled(true);
            //Agregamos el saldo que corresponde al jugador.
            jLabelSaldo.setText(String.valueOf(saldoDelJugador));
            jLabelSaldo.setVisible(true);
            jMoneda25.setEnabled(true);
            jMoneda50.setEnabled(true);
            jMoneda100.setEnabled(true);
            //Ponemos en true
            jLabelPuntajeCrupier.setVisible(true);
            jLabelPuntajeRival.setVisible(true);
            jLabelPuntajeJugador.setVisible(true);
        }else {
            // si no es mi turno entonces no quiero ver cosas de otro jugador.
         //   jLabelPuntajeJugador.setVisible(false);
         //   jPanelCartasJugador.setVisible(false);

         //   jLabelPuntajeJugador.setVisible(false);
            //   jLabelPuntajeCrupier.setVisible(false);
            //   jPanelCartasCrupier.setVisible(false);
            //   jPanelCartasJugador.setVisible(false);

            jButtonApostar.setVisible(false);
            ButtonPedir.setVisible(false);
            ButtonDoblar.setVisible(false);
            ButtonPlantarse.setVisible(false);
            ButtonDividir.setVisible(false);
            //De igula manera me gusraia ver el saldo
            jLabelSaldo.setVisible(false);
            jMoneda25.setEnabled(false);
            jMoneda50.setEnabled(false);
            jMoneda100.setEnabled(false);
            System.out.println("dICE QUE NO ES IGUAL EL NOMBE");
        }

    }


    public String getMi_Nombre() {
        return Mi_Nombre;
    }

    public void setMi_Nombre(String mi_Nombre) {
        Mi_Nombre = mi_Nombre;
        System.out.println("nomrbecito " + this.Mi_Nombre);
        jContendorDeSubtitulo.setVisible(true);
        jLabelSubtitulos.setVisible(true);
        jLabelSubtitulos.setText("YO: "+ this.Mi_Nombre);
        System.out.println("YO: "+ this.Mi_Nombre);

        /*
        this.NombreJugadorACtual = controlador.obtenerNombreJugador();

        //Veremos si funciona.-
        if (this.Mi_Nombre.equals(NombreJugadorACtual)){
            jPanelCartasJugador.setVisible(true);
            //  jLabelPuntajeJugador.setVisible(true);
            // jPanelCartasJugador.setVisible(true);
            jButtonApostar.setVisible(true);
            ButtonPedir.setVisible(true);
            ButtonDoblar.setVisible(true);
            ButtonPlantarse.setVisible(true);
            button6.setVisible(true);
            //Agregamos el saldo que corresponde al jugador.
            jLabelSaldo.setVisible(true);
        }else {
            jButtonApostar.setVisible(false);
            ButtonPedir.setVisible(false);
            ButtonDoblar.setVisible(false);
            ButtonPlantarse.setVisible(false);
            button6.setVisible(false);
            jLabelSaldo.setVisible(false);
            jPanelCartasJugador.setVisible(false);
            jPanelCartasCrupier.setVisible(false);
            jLabelPuntajeJugador.setVisible(false);
            jLabelPuntajeCrupier.setVisible(false);
        }
         */

        esMiTurno(controlador.obtenerNombreJugador());
    }

    public void obtenerResultadoPartida(String texto, String NombreJuugador, int puntajeJugador, int puntajeCrupier) {

        if (NombreJuugador.equals(Mi_Nombre)){

            estadoPartida = texto;
            String mensaje = "";
            String titulo = "El Estado De La Partida es: ";
            int tipoMensaje = JOptionPane.INFORMATION_MESSAGE;
            System.out.println("estadoPartida = " + estadoPartida);

            // Configurar mensaje según el resultado
            switch (estadoPartida) {
                case "GANADOR":
                    mensaje = "¡Ganaste! \n " + NombreJuugador ;
                    break;
                case "PERDEDOR":
                    mensaje = "Perdiste  \n " + NombreJuugador ;
                    System.out.println("NO fi papa");
                    break;
                case "BLACKJACK":
                    mensaje = "¡Ganaste HICISTE BLACKJACK!  \n  " + NombreJuugador ;
                    break;
                case "EMPATE":
                    mensaje = "Empate  \n " + NombreJuugador ;
                    tipoMensaje = JOptionPane.WARNING_MESSAGE;
                    break;
                case "FinDeJuego" :
                    System.out.println("fin papa");
                    // Opcional: No mostrar nada o manejar aparte
                    break;
                default:
                    break;
            }


            jLabelPuntajeJugador.setVisible(true);
            jLabelPuntajeRival.setVisible(true);
            jLabelPuntajeCrupier.setVisible(true);

            //Si estoy Fuera de juego no mostrar Ninguna Mensaje.
            if (!estoyFueraDeJuego){
                // Mostrar mensaje si corresponde
                if (!mensaje.isEmpty()) {
                    System.out.println("Mostrando mensaje: " + mensaje);
                    // JOptionPane.showMessageDialog(this, mensaje, titulo, tipoMensaje);
                    mostrarResultadoEnFrame(mensaje, NombreJuugador, titulo,  puntajeJugador,  puntajeCrupier);
                }
            }


        }


    }

    @Override
    public boolean mostrarRecargar() {
        return false;
    }


    //Con este metodo mostramos el resultado y solucionamos el error que tenemos de no abirl el dialogl.
    public void mostrarResultadoEnFrame(String mensaje, String NombreJugador,  String titulo, int puntajeJugador, int puntajeCrupier) {


        jLabelPuntajeJugador.setText(String.valueOf(puntajeJugador));
        jLabelPuntajeRival.setVisible(true);
        jLabelPuntajeCrupier.setText(String.valueOf(puntajeCrupier));

        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Mensaje principal (GANASTE, PERDISTE, etc.)
        JLabel labelResultado = new JLabel(mensaje, SwingConstants.CENTER);
        labelResultado.setFont(new Font("Arial", Font.BOLD, 16));
        // Texto extra con los puntajes

        //String detalle = "";
        String detalle = "JUGADOR : " + NombreJugador +
                "  Puntaje Jugador: " + puntajeJugador +
                " | Puntaje Crupier: " + puntajeCrupier;

       // String detalle = "JUGADOR : " + Mi_Nombre +  "  Puntaje Jugador: " + puntajeJugador + " | Puntaje Crupier: " + puntajeCrupier;
        if (mensaje.equals("Perdiste")){
            detalle = " | HAZ PERDIDO!! | Puntaje del lugador "+ "[" +Mi_Nombre + "]" +  puntajeJugador ;

        }
        JLabel labelDetalle = new JLabel(detalle, SwingConstants.CENTER);
        labelDetalle.setFont(new Font("Arial", Font.PLAIN, 14));



        // Botón aceptar
        JButton botonCerrar = new JButton("Aceptar");
        botonCerrar.addActionListener(e -> {
            frame.dispose(); // Cierra la ventana
           // jLabelSubtitulos.setVisible(false);
            jLabelPuntajeJugador.setVisible(false);
            jLabelPuntajeRival.setVisible(false);
            jLabelPuntajeCrupier.setVisible(false);
            ButtonPedir.setEnabled(false);
            ButtonPlantarse.setEnabled(false);
            ButtonDoblar.setEnabled(false);
            jButtonBorrarApuesta.setEnabled(true);
            jMoneda50.setEnabled(true);
            jMoneda25.setEnabled(true);
            jMoneda100.setEnabled(true);
            setInputApuesta(0);
            jlabelApuesta.setText("$" + this.inputApuesta);
            jLabelMonedas.setVisible(false);
            jPanelCartasJugador.removeAll();
            jPanelCartasCrupier.removeAll();
           // jLabelPuntajeCrupier.setText(String.valueOf(puntajeCrupier));
            jLabelSubtitulosCrupier.setText("Ingrese la apuesta Señor");
            jPanelCartasJugador.revalidate();
            jPanelCartasJugador.repaint();
            jPanelCartasCrupier.revalidate();
            jPanelCartasCrupier.repaint();

            jLabelSubtitulos.setText("YO: "+ this.Mi_Nombre);

            //--------------------- Puntaje de Rival y limpiar las cartas:
           // jPanelCartasJugador2.setVisible(false);


            jLabelPuntajeRival.setVisible(false);
            jLabelPuntajeRival.removeAll(); //Limpiamos
            jLabelPuntajeRival.revalidate(); //Rea actualizamos el diseño.
            jLabelPuntajeRival.repaint();  //Vuelve a dibujarlo.


            jLabelPuntajeJugador.removeAll();
            jLabelPuntajeJugador.revalidate();
            jLabelPuntajeJugador.repaint();


            jPanelCartasJugador2.removeAll();
            jPanelCartasJugador2.revalidate();
            jPanelCartasJugador2.repaint();


        });

        // Armar layout
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(labelResultado);
        centerPanel.add(labelDetalle);

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(botonCerrar, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }






    public void mostrarRecargar(String nombreJugador, Consumer<Boolean> callback) {
        if (this.Mi_Nombre.equals(nombreJugador)){
            estoyFueraDeJuego = true;
            JFrame frame = new JFrame("No tiene más saldo");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(350, 200);
            frame.setLocationRelativeTo(null);
            JPanel panel = new JPanel(new BorderLayout());
            JLabel label = new JLabel("NO TIENES DINERO. ¿Deseas Recargar?", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(label, BorderLayout.CENTER);
            JButton botonRecargar = new JButton("RECARGAR");
            JButton botonNo = new JButton("NO");
            JPanel botones = new JPanel();
            botones.add(botonRecargar);
            botones.add(botonNo);
            panel.add(botones, BorderLayout.SOUTH);

            botonRecargar.addActionListener(e -> {
                // Aquí avisamos al controlador que eligió "recargar"

                callback.accept(true);
                jLabel1.setVisible(true);
                jbuttonRecargar.setVisible(false);
                jLabelFlechita.setVisible(false);
                jLabelNegro.setVisible(false);
                frame.dispose();
                estoyFueraDeJuego = false;
                /*
                jPanelCartasJugador.setVisible(true);
                jPanelCartasJugador2.setVisible(true);
                jPanelCartasCrupier.setVisible(true);

                 */
                jPanelCartasJugador2.setVisible(true);
                jPanelCartasJugador2.removeAll(); //Limpiamos
                jPanelCartasJugador2.revalidate(); //Rea actualizamos el diseño.
                jPanelCartasJugador2.repaint();  //Vuelve a dibujarlo.


                
                jLabelSubtitulos.setVisible(true);
                jLabelSubtitulosCrupier.setVisible(true);
                jContendorDeSubtituloCrupier.setVisible(true);
            });



            jbuttonRecargar.addActionListener(e -> {
                // Aquí avisamos al controlador que eligió "recargar"
                callback.accept(true);
                jLabel1.setVisible(true);
                jbuttonRecargar.setVisible(false);
                jLabelFlechita.setVisible(false);
                jLabelNegro.setVisible(false);
                jLabelYaNoTienesSaldo.setVisible(false);
                frame.dispose();
                /*
                jPanelCartasJugador.setVisible(true);

                jPanelCartasCrupier.setVisible(true);

                 */

                jPanelCartasJugador2.setVisible(true);
                jPanelCartasJugador2.removeAll(); //Limpiamos
                jPanelCartasJugador2.revalidate(); //Rea actualizamos el diseño.
                jPanelCartasJugador2.repaint();  //Vuelve a dibujarlo.



                jLabelSubtitulos.setVisible(true);
                jLabelSubtitulosCrupier.setVisible(true);
                jContendorDeSubtituloCrupier.setVisible(true);
            });


            botonNo.addActionListener(e -> {
                callback.accept(false);

                jLabel1.setVisible(false);
                jbuttonRecargar.setVisible(true);
                jLabelFlechita.setVisible(true);
                jLabelNegro.setVisible(true);
                jLabelYaNoTienesSaldo.setVisible(true);
                //  jLabelSubtitulos.setVisible(false);

                jPanelCartasJugador.setVisible(false);
                jPanelCartasJugador2.setVisible(false);
                jPanelCartasCrupier.setVisible(false);
                jLabelMonedas.setVisible(false);
                jLabelSubtitulosCrupier.setVisible(false);
                jContendorDeSubtituloCrupier.setVisible(false);

                jLabelPuntajeJugador.setVisible(false);
                jLabelPuntajeRival.setVisible(false);
                jLabelPuntajeCrupier.setVisible(false);
                ButtonPedir.setEnabled(false);
                ButtonPlantarse.setEnabled(false);
                ButtonDoblar.setEnabled(false);
                jButtonBorrarApuesta.setEnabled(true);
                jMoneda50.setEnabled(false);
                jMoneda25.setEnabled(false);
                jMoneda100.setEnabled(false);
                frame.dispose();
            });

            frame.add(panel);
            frame.setVisible(true);
        }
    }


    public String getEstadoPartida() {
        return estadoPartida;
    }

    public void setEstadoPartida(String estadoPartida) {
        this.estadoPartida = estadoPartida;
    }

    public float getInputApuesta() {
        return inputApuesta;
    }

    public void setInputApuesta(float inputApuesta) {
        this.inputApuesta = inputApuesta;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textArea1 = new java.awt.TextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabelPuntajeJugador = new javax.swing.JLabel();
        jPanelCartasCrupier = new javax.swing.JPanel();
        jPanelCartasJugador2 = new javax.swing.JPanel();
        jPanelCartasJugador = new javax.swing.JPanel();
        jLabelMonedas = new javax.swing.JLabel();
        button1 = new java.awt.Button();
        ButtonPedir = new java.awt.Button();
        ButtonDividir = new java.awt.Button();
        ButtonDoblar = new java.awt.Button();
        ButtonPlantarse = new java.awt.Button();
        jLabelPuntajeRival = new javax.swing.JLabel();
        jLabelPuntajeCrupier = new javax.swing.JLabel();
        jButtonApostar = new javax.swing.JButton();
        jButtonBorrarApuesta = new javax.swing.JButton();
        jMoneda25 = new javax.swing.JButton();
        jMoneda50 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanelContenedorApuesta = new javax.swing.JPanel();
        jlabelApuesta = new javax.swing.JLabel();
        jMoneda100 = new javax.swing.JButton();
        jContendorDeSubtituloCrupier = new javax.swing.JPanel();
        jLabelSubtitulosCrupier = new javax.swing.JLabel();
        jContendorDeSubtitulo = new javax.swing.JPanel();
        jLabelSubtitulos = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelSaldo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jbuttonRecargar = new javax.swing.JButton();
        jLabelYaNoTienesSaldo = new javax.swing.JLabel();
        jLabelFlechita = new javax.swing.JLabel();
        jLabelNegro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BlackJack Argentino");

        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPuntajeJugador.setBackground(new java.awt.Color(0, 0, 0));
        jLabelPuntajeJugador.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabelPuntajeJugador.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabelPuntajeJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, 70, 40));

        jPanelCartasCrupier.setOpaque(false);

        javax.swing.GroupLayout jPanelCartasCrupierLayout = new javax.swing.GroupLayout(jPanelCartasCrupier);
        jPanelCartasCrupier.setLayout(jPanelCartasCrupierLayout);
        jPanelCartasCrupierLayout.setHorizontalGroup(
            jPanelCartasCrupierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelCartasCrupierLayout.setVerticalGroup(
            jPanelCartasCrupierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelCartasCrupier, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 150, 120));

        jPanelCartasJugador2.setEnabled(false);
        jPanelCartasJugador2.setOpaque(false);

        javax.swing.GroupLayout jPanelCartasJugador2Layout = new javax.swing.GroupLayout(jPanelCartasJugador2);
        jPanelCartasJugador2.setLayout(jPanelCartasJugador2Layout);
        jPanelCartasJugador2Layout.setHorizontalGroup(
            jPanelCartasJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelCartasJugador2Layout.setVerticalGroup(
            jPanelCartasJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelCartasJugador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 150, 110));

        jPanelCartasJugador.setOpaque(false);

        javax.swing.GroupLayout jPanelCartasJugadorLayout = new javax.swing.GroupLayout(jPanelCartasJugador);
        jPanelCartasJugador.setLayout(jPanelCartasJugadorLayout);
        jPanelCartasJugadorLayout.setHorizontalGroup(
            jPanelCartasJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelCartasJugadorLayout.setVerticalGroup(
            jPanelCartasJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelCartasJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 180, 150));

        jLabelMonedas.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMonedas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Monedas/fichas_juntas_reducidas.png"))); // NOI18N
        jPanel1.add(jLabelMonedas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 90, 70));

        button1.setLabel("<");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel1.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, 40));

        ButtonPedir.setLabel("PEDIR");
        ButtonPedir.setName(""); // NOI18N
        ButtonPedir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPedirActionPerformed(evt);
            }
        });
        jPanel1.add(ButtonPedir, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 50, -1));

        ButtonDividir.setLabel("DIVIDIR");
        ButtonDividir.setVisible(false);
        jPanel1.add(ButtonDividir, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 520, 60, -1));

        ButtonDoblar.setLabel("DOBLAR");
        jPanel1.add(ButtonDoblar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 520, 60, -1));

        ButtonPlantarse.setLabel("PLANTAR");
        ButtonPlantarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPlantarseActionPerformed(evt);
            }
        });
        jPanel1.add(ButtonPlantarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 520, 60, -1));

        jLabelPuntajeRival.setBackground(new java.awt.Color(0, 0, 0));
        jLabelPuntajeRival.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabelPuntajeRival.setForeground(new java.awt.Color(255, 255, 102));
        jPanel1.add(jLabelPuntajeRival, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 70, 60));

        jLabelPuntajeCrupier.setBackground(new java.awt.Color(0, 0, 0));
        jLabelPuntajeCrupier.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabelPuntajeCrupier.setForeground(new java.awt.Color(255, 255, 102));
        jPanel1.add(jLabelPuntajeCrupier, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, 70, 60));

        jButtonApostar.setText("Apostar");
        jButtonApostar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApostarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonApostar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 520, 90, 20));

        jButtonBorrarApuesta.setText("Borrar Apuesa");
        jButtonBorrarApuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarApuestaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBorrarApuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 110, 20));

        jMoneda25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Imagenes/Ficha_25_reducida.png"))); // NOI18N
        jMoneda25.setBorder(null);
        jMoneda25.setContentAreaFilled(false);
        jMoneda25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMoneda25ActionPerformed(evt);
            }
        });
        jPanel1.add(jMoneda25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, 50));

        jMoneda50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Monedas/Ficha_50_volumen_reducida.png"))); // NOI18N
        jMoneda50.setBorder(null);
        jMoneda50.setContentAreaFilled(false);
        jMoneda50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMoneda50ActionPerformed(evt);
            }
        });
        jPanel1.add(jMoneda50, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 80, 50));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("APUESTA");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 390, 120, -1));

        jPanelContenedorApuesta.setBackground(new java.awt.Color(0, 0, 0));
        jPanelContenedorApuesta.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jPanelContenedorApuesta.setForeground(new java.awt.Color(51, 51, 51));

        jlabelApuesta.setFont(new java.awt.Font("Bodoni MT Black", 1, 24)); // NOI18N
        jlabelApuesta.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelContenedorApuestaLayout = new javax.swing.GroupLayout(jPanelContenedorApuesta);
        jPanelContenedorApuesta.setLayout(jPanelContenedorApuestaLayout);
        jPanelContenedorApuestaLayout.setHorizontalGroup(
            jPanelContenedorApuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenedorApuestaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlabelApuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelContenedorApuestaLayout.setVerticalGroup(
            jPanelContenedorApuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenedorApuestaLayout.createSequentialGroup()
                .addComponent(jlabelApuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelContenedorApuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, 220, 40));

        jMoneda100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Monedas/Ficha_100_volumen_Reducida.png"))); // NOI18N
        jMoneda100.setBorder(null);
        jMoneda100.setContentAreaFilled(false);
        jMoneda100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMoneda100ActionPerformed(evt);
            }
        });
        jPanel1.add(jMoneda100, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, 70, 60));

        jContendorDeSubtituloCrupier.setBackground(new java.awt.Color(0, 0, 0));
        jContendorDeSubtituloCrupier.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jContendorDeSubtituloCrupier.setForeground(new java.awt.Color(51, 51, 51));

        jLabelSubtitulosCrupier.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelSubtitulosCrupier.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSubtitulosCrupier.setText("Crupier: \"Ingrese Su Apuesta : \"");

        javax.swing.GroupLayout jContendorDeSubtituloCrupierLayout = new javax.swing.GroupLayout(jContendorDeSubtituloCrupier);
        jContendorDeSubtituloCrupier.setLayout(jContendorDeSubtituloCrupierLayout);
        jContendorDeSubtituloCrupierLayout.setHorizontalGroup(
            jContendorDeSubtituloCrupierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jContendorDeSubtituloCrupierLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSubtitulosCrupier, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        jContendorDeSubtituloCrupierLayout.setVerticalGroup(
            jContendorDeSubtituloCrupierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelSubtitulosCrupier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jContendorDeSubtituloCrupier, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 390, 50));

        jContendorDeSubtitulo.setBackground(new java.awt.Color(0, 0, 0));
        jContendorDeSubtitulo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jContendorDeSubtitulo.setForeground(new java.awt.Color(51, 51, 51));

        jLabelSubtitulos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelSubtitulos.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSubtitulos.setText("Subtitulos");

        javax.swing.GroupLayout jContendorDeSubtituloLayout = new javax.swing.GroupLayout(jContendorDeSubtitulo);
        jContendorDeSubtitulo.setLayout(jContendorDeSubtituloLayout);
        jContendorDeSubtituloLayout.setHorizontalGroup(
            jContendorDeSubtituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jContendorDeSubtituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSubtitulos, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jContendorDeSubtituloLayout.setVerticalGroup(
            jContendorDeSubtituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jContendorDeSubtituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSubtitulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jContendorDeSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 270, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 0)));
        jPanel2.setForeground(new java.awt.Color(51, 51, 51));

        jLabelSaldo.setFont(new java.awt.Font("Britannic Bold", 1, 24)); // NOI18N
        jLabelSaldo.setForeground(new java.awt.Color(255, 255, 0));
        jLabelSaldo.setText("saldo");
        jLabelSaldo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabelSaldo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, 210, 40));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 51));
        jLabel2.setText("SALDO");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 450, 70, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Imagenes/mesaPartida20Reducido.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 570));

        jbuttonRecargar.setBackground(new java.awt.Color(51, 204, 0));
        jbuttonRecargar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jbuttonRecargar.setText("RECARGAR $500");
        jbuttonRecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonRecargarActionPerformed(evt);
            }
        });
        jPanel1.add(jbuttonRecargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 480, 80));

        jLabelYaNoTienesSaldo.setBackground(new java.awt.Color(0, 0, 0));
        jLabelYaNoTienesSaldo.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabelYaNoTienesSaldo.setForeground(new java.awt.Color(102, 255, 51));
        jLabelYaNoTienesSaldo.setText("YA NO TIENES SALDO!!!");
        jPanel1.add(jLabelYaNoTienesSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 580, 100));

        jLabelFlechita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/p/Fechita.png"))); // NOI18N
        jPanel1.add(jLabelFlechita, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, 320));

        jLabelNegro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Imagenes/mesaPartida20_negra_Reducida.jpg"))); // NOI18N
        jLabelNegro.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 0)));
        jPanel1.add(jLabelNegro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 570));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonPlantarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPlantarseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonPlantarseActionPerformed

    private void ButtonPedirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPedirActionPerformed
        // TODO add your handling code here:
        String nombrejugador = controlador.obtenerNombreJugador();
        actualizarSubtitulos(  nombrejugador + ": "  + "Che, te pido una carta");
    }//GEN-LAST:event_ButtonPedirActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        Lobby lb = new Lobby();
        lb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_button1ActionPerformed

    private void jMoneda100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMoneda100ActionPerformed
        // TODO add your handling code here:
        actualizarApuestalabel(100);
        jButtonApostar.setEnabled(true);
    }//GEN-LAST:event_jMoneda100ActionPerformed

    private void jMoneda25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMoneda25ActionPerformed
                // TODO add your handling code here:
                actualizarApuestalabel(25);
                jButtonApostar.setEnabled(true);
    }//GEN-LAST:event_jMoneda25ActionPerformed

    private void jMoneda50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMoneda50ActionPerformed
        // TODO add your handling code here:
         actualizarApuestalabel(50);
         jButtonApostar.setEnabled(true);
    }//GEN-LAST:event_jMoneda50ActionPerformed

    private void jButtonBorrarApuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarApuestaActionPerformed
        // TODO add your handling code here:
        setInputApuesta(0);
        jlabelApuesta.setText("$" + String.valueOf(this.inputApuesta));
        jButtonApostar.setEnabled(false);
        jLabelMonedas.setVisible(false);

      //  jlabelApuesta.setVisible(false);

        
    }//GEN-LAST:event_jButtonBorrarApuestaActionPerformed

    private void jButtonApostarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApostarActionPerformed
        // TODO add your handling code here:



        /*
        JPanel panelCartasCrupier = jPanelCartasCrupier; // el panel exclusivo para cartas
        ImageIcon icon = new ImageIcon(getClass().getResource(cartaCrupier));
        JLabel cartacrupier = new JLabel(icon);
        panelCartasCrupier.add(cartacrupier);
        cartacrupier.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        panelCartasCrupier.add(cartacrupier, 0); // siempre al fondo

         */



        /*
        jLabelPuntajeJugador.setVisible(true);
        jLabelPuntajeCrupier.setVisible(true);
        //Los botones van estar  habilidatoos cunando se opima:
        ButtonPedir.setEnabled(true);
        ButtonPlantarse.setEnabled(true);
        ButtonDoblar.setEnabled(true);

        //Cuando ya apuestoo voy a hnabilidar los botoens de apuesta:


        jButtonApostar.setEnabled(false);



         */



        
    }//GEN-LAST:event_jButtonApostarActionPerformed

    private void jbuttonRecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonRecargarActionPerformed
        // TODO add your handling code here:
        //aca va a recargar 500.
    }//GEN-LAST:event_jbuttonRecargarActionPerformed

    private void actualizarSubtitulos(String texto){
        jLabelSubtitulos.setVisible(true);
        jContendorDeSubtitulo.setVisible(true);
        jLabelSubtitulos.setText(texto);
        
    }

    private void actualizarApuestalabel(int valor){
        inputApuesta = inputApuesta + valor;
        jlabelApuesta.setVisible(true);
        jlabelApuesta.setText("$" + String.valueOf(inputApuesta));

    }


    /**
     * @param args the command line arguments
     */

    
       @Override
    public void Iniciar() {
       // controlador.iniciarJuego();
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public IControlador getControlador() {
        return controlador;
    }

    /*
    @Override
    public void setControlador(IControlador controlador) {
        this.controlador = controlador;
    }


     */
    @Override
    public void mostrarMensaje(String Mensaje) {
            textArea1.append(Mensaje + "\n");
            jLabelSubtitulosCrupier.setText("Crupier: " + Mensaje);
            //Aca vamos a mostrar los mensaje.
            System.out.println("hablo el curpier: " + Mensaje);
    }

    @Override
    public void mostrarCartasMazo(String mostrarMazo, int cantidad) {
        textArea1.append("Carta: " + mostrarMazo + " - Valor: " + cantidad + "\n");
    }

    @Override
    public void mostrarSaldo(String jugador, float saldo) {
        this.saldoActual = saldo;
        //System.out.println("actual saldo; "+ this.saldoActual);
        jLabelSaldo.setText(String.valueOf("$" + saldo));
        textArea1.append("Saldo: $ " +String.valueOf("$" + saldo)+ "\n");



    }




    @Override
    public float pedirApuesta() {
        return 0;
    }

    @Override
    public int pedirOpcion(String mensaje) {
        return 0;
    }

    @Override
    public void mostrarCartas(int cartasJugador, int cartasCrupier) {
        textArea1.append("Puntaje del jugador: " + cartasJugador+ "\n");
        jLabelPuntajeJugador.setText(String.valueOf(cartasJugador));
        jLabelPuntajeCrupier.setText(String.valueOf(cartasCrupier));
        textArea1.append("Primera carta del crupier: " + cartasCrupier+ "\n");

        //Esto estara bien?
        jLabelPuntajeRival.setText(String.valueOf(cartasJugador));
    }



    @Override
    public void mostrarApuesta(float apuesta) {
         textArea1.append("La apuesta es : $ " +apuesta+ "\n");
        //label2.setText("La apuesta es : $ " +apuesta+ "\n");

    }

   public void mostrarPuntajeSoloCrupierV(int puntajeCrupier){
       jLabelPuntajeCrupier.setText(String.valueOf(puntajeCrupier));
       textArea1.append("Puntaje solo del crupier: " + puntajeCrupier+ "\n");
       System.out.println("Puntaje solo del crupier: " + puntajeCrupier+ "\n");
    }


    @Override
    public void mostrarCartasSoloJugador(int cartasJugador) {
            //Aca actulizamos el puntaje.
            textArea1.append("Puntaje solo del jugador: " + cartasJugador+ "\n");
            jLabelPuntajeJugador.setText(String.valueOf(cartasJugador));
    }

    @Override
    public void mostrarManoJugador(String manoDeJugador , List<String> rutas, String cartaCrupier) {
        jPanelCartasJugador2.setVisible(false);
        // Limpiar el panel antes de agregar nuevas cartas
        jPanelCartasJugador.removeAll();
        jPanelCartasCrupier.removeAll();
        int x = 0; // posición inicial en X
        int y = 0; // posición fija en Y
        int offset = 25; // cuanto se solapan las cartas
           //Vamos hacer apareceer las cartas



        //Cartas de Jugador.
        JPanel panelCartas = jPanelCartasJugador; // el panel exclusivo para cartas
        for (String ruta : rutas) {
                ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
                JLabel lbl = new JLabel(icon);
                panelCartas.add(lbl);
                lbl.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
                panelCartas.add(lbl, 0); // siempre al fondo
                x += 30; // separacion entre cartas
        }
        panelCartas.revalidate();
        panelCartas.repaint();



        //Cartas de Jugador2.
        JPanel panelCartas2 = jPanelCartasJugador2; // el panel exclusivo para cartas
        for (String ruta : rutas) {
            ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
            JLabel lbl = new JLabel(icon);
            panelCartas2.add(lbl);
            lbl.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
            panelCartas2.add(lbl, 0); // siempre al fondo
            x += 30; // separacion entre cartas
        }
        panelCartas2.revalidate();
        panelCartas2.repaint();

         x = 20; // posición inicial en X
         y = 10; // posición fija en Y


        //Primera Carta Crupier:
        JPanel panelCartasCrupier = jPanelCartasCrupier; // el panel exclusivo para cartas
        ImageIcon icon = new ImageIcon(getClass().getResource(cartaCrupier));
        JLabel cartacrupier = new JLabel(icon);
        panelCartasCrupier.add(cartacrupier);
        cartacrupier.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        panelCartasCrupier.add(cartacrupier, 0); // siempre al fondo
        x = 60; // separacion entre cartas

        x += 22; // separacion entre cartas
        //Carta boca atras:
        String cartaOculta = "/resources/cartas/carta_bocaAtras.png"; // reverso de la carta
         icon = new ImageIcon(getClass().getResource(cartaOculta));
        JLabel cartaCrupier2 = new JLabel(icon);
        panelCartasCrupier.add(cartaCrupier2);
        cartaCrupier2.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());

        panelCartasCrupier.revalidate();
        panelCartasCrupier.repaint();


    }





    /*
        public void mostrarManoJugadores(String manoDeJugador , List<String> rutas, String cartaCrupier, String cartaJugadores) {

            System.out.println("Estoy aca");
            // Limpiar el panel antes de agregar nuevas cartas
            jPanelCartasJugador.removeAll();
            jPanelCartasCrupier.removeAll();
            int x = 0; // posición inicial en X
            int y = 0; // posición fija en Y
            int offset = 25; // cuanto se solapan las cartas
            //Vamos hacer apareceer las cartas



            //Cartas de Jugador.
            JPanel panelCartas = jPanelCartasJugador; // el panel exclusivo para cartas
            for (String ruta : rutas) {
                ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
                JLabel lbl = new JLabel(icon);
                panelCartas.add(lbl);
                lbl.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
                panelCartas.add(lbl, 0); // siempre al fondo
                x += 30; // separacion entre cartas
            }
            panelCartas.revalidate();
            panelCartas.repaint();



            //Cartas de Jugador2.
            JPanel panelCartas2 = jPanelCartasJugador2; // el panel exclusivo para cartas

            ImageIcon icon2 = new ImageIcon(getClass().getResource(cartaJugadores));
            JLabel lbl = new JLabel(icon2);
            panelCartas2.add(lbl);
            lbl.setBounds(x, y, icon2.getIconWidth(), icon2.getIconHeight());
            panelCartas2.add(lbl, 0); // siempre al fondo
            x += 30; // separacion entre cartas

            panelCartas2.revalidate();
            panelCartas2.repaint();

            x = 20; // posición inicial en X
            y = 10; // posición fija en Y



            //Primera Carta Crupier:
            JPanel panelCartasCrupier = jPanelCartasCrupier; // el panel exclusivo para cartas
            ImageIcon icon = new ImageIcon(getClass().getResource(cartaCrupier));
            JLabel cartacrupier = new JLabel(icon);
            panelCartasCrupier.add(cartacrupier);
            cartacrupier.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
            panelCartasCrupier.add(cartacrupier, 0); // siempre al fondo
            x = 60; // separacion entre cartas

            x += 22; // separacion entre cartas
            //Carta boca atras:
            String cartaOculta = "/resources/cartas/carta_bocaAtras.png"; // reverso de la carta
            icon = new ImageIcon(getClass().getResource(cartaOculta));
            JLabel cartaCrupier2 = new JLabel(icon);
            panelCartasCrupier.add(cartaCrupier2);
            cartaCrupier2.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());

            panelCartasCrupier.revalidate();
            panelCartasCrupier.repaint();
        }



     */
    //La idea aca es tener varios paneles para distintos jugadore.
    public void mostrarManoJugadores(String nombreJugador, List<String> rutas, String cartaCrupier, List<String> cartaJugadores, boolean esPrimeraVez, int PuntajeJugador, int PuntajeRival) {

        System.out.println("boleano es  : "+ esPrimeraVez);
        int y = 0;
        int offset = 30;

        // Panel propio y rival
        JPanel panelPropio;
        JPanel panelRival;
        JLabel labelPuntajeRival;
        JLabel labelPuntajePropio;

        System.out.println("El nombre del jugador es : "+ nombreJugador + "la vista es de :  " + Mi_Nombre);


        if (nombreJugador.equals(Mi_Nombre)) {
            panelPropio = jPanelCartasJugador;
            panelRival = jPanelCartasJugador2;
            labelPuntajeRival = jLabelPuntajeRival;
            labelPuntajePropio = jLabelPuntajeJugador;
        } else {
            panelPropio = jPanelCartasJugador2;
            panelRival = jPanelCartasJugador;
            labelPuntajeRival = jLabelPuntajeJugador;
            labelPuntajePropio = jLabelPuntajeRival;
        }

        // Limpio solo el panel propio SIEMPRE
        panelPropio.removeAll();



        int x = 0;
        if (nombreJugador.equals(nombreJugador)) {
            // Si es turno del jugador entonces muestro todas sus cartas

            for (String ruta : rutas) {
                ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
                JLabel lbl = new JLabel(icon);
                lbl.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
                panelPropio.add(lbl);
                x += offset;
            }
            panelPropio.revalidate();
            panelPropio.repaint();




            // En el panel rival muestro SOLO la oculta la primera vez
            if (esPrimeraVez) {
                panelRival.removeAll();
                String cartaOculta = "/resources/cartas/carta_bocaAtras.png";
                ImageIcon iconOculta = new ImageIcon(getClass().getResource(cartaOculta));
                JLabel lblOculta = new JLabel(iconOculta);
                lblOculta.setBounds(0, y, iconOculta.getIconWidth(), iconOculta.getIconHeight());
                panelRival.add(lblOculta);
            }
            panelRival.revalidate();
            panelRival.repaint();

        } else {
            // Si no es turno del jugador actual
            if (esPrimeraVez) {
                // Primera vez: solo carta oculta
                panelPropio.removeAll();
                String cartaOculta = "/resources/cartas/carta_bocaAtras.png";
                ImageIcon iconOculta = new ImageIcon(getClass().getResource(cartaOculta));
                JLabel lblOculta = new JLabel(iconOculta);
                lblOculta.setBounds(0, y, iconOculta.getIconWidth(), iconOculta.getIconHeight());
                panelPropio.add(lblOculta);
            } else {
                // Ya jugó: muestro las cartas que reveló
                x = 0;
                panelPropio.removeAll();
                for (String ruta : cartaJugadores) {
                    ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
                    JLabel lbl = new JLabel(icon);
                    lbl.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
                    panelPropio.add(lbl);
                    x += offset;
                }
            }
        }
        // Actualizar puntajes
        labelPuntajePropio.setText(String.valueOf(PuntajeJugador));
        //labelPuntajeRival.setText(String.valueOf(esPrimeraVez ? "?" : PuntajeRival));
        labelPuntajeRival.setText(PuntajeRival == -1 ? "?" : String.valueOf(PuntajeRival));


        panelPropio.revalidate();
        panelPropio.repaint();
        panelRival.revalidate();
        panelRival.repaint();

        // Mostrar crupier (siempre igual que antes)
        jPanelCartasCrupier.removeAll();
        x = 0;
        ImageIcon iconCrupier = new ImageIcon(getClass().getResource(cartaCrupier));
        JLabel lblCrupier = new JLabel(iconCrupier);
        lblCrupier.setBounds(x, y, iconCrupier.getIconWidth(), iconCrupier.getIconHeight());
        jPanelCartasCrupier.add(lblCrupier);

        x += offset;
        String cartaOcultaCrupier = "/resources/cartas/carta_bocaAtras.png";
        ImageIcon iconOcultaCrupier = new ImageIcon(getClass().getResource(cartaOcultaCrupier));
        JLabel lblCrupierOculta = new JLabel(iconOcultaCrupier);
        lblCrupierOculta.setBounds(x, y, iconOcultaCrupier.getIconWidth(), iconOcultaCrupier.getIconHeight());
        jPanelCartasCrupier.add(lblCrupierOculta);

        jPanelCartasCrupier.revalidate();
        jPanelCartasCrupier.repaint();
    }





    @Override
    public void mostrarManoJugadorYdeCrupier(String manoDeJugador, String manoDeCrupier) {


    }

    @Override
    public void mostrarManoJugadorYdeCrupier(String manoDeJugador, String manoDeCrupier,List<String> rutasimagenesJugador, List<String> rutasImagenesCrupier) {
        // Limpiar el panel antes de agregar nuevas cartas
        jPanelCartasJugador.removeAll();
        jPanelCartasCrupier.removeAll();
        int x = 20; // posición inicial en X
        int y = 10; // posición fija en Y
        int offset = 25; // cuanto se solapan las cartas
        //Vamos hacer apareceer las cartas

        //Cartas de Jugador.
        JPanel panelCartas = jPanelCartasJugador; // el panel exclusivo para cartas
        for (String ruta : rutasimagenesJugador) {
            ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
            JLabel lbl = new JLabel(icon);
            panelCartas.add(lbl);
            lbl.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
            x += 60; // separacion entre cartas
        }
        panelCartas.revalidate();
        panelCartas.repaint();


        //Cartas de crupier.
        JPanel panelCartasCrupier = jPanelCartasCrupier; // el panel exclusivo para cartas
        for (String ruta : rutasImagenesCrupier) {
            ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
            JLabel lbl = new JLabel(icon);
            panelCartasCrupier.add(lbl);
            lbl.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
            x += 40; // separacion entre cartas
        }
        panelCartasCrupier.revalidate();
        panelCartasCrupier.repaint();
    }


    public void mostrarManoJugadorYdeCrupierDemasJugadores(String manoDeJugador,String manoDeJugador2, String manoDeCrupier,List<String> rutasimagenesJugador, List<String> rutasImagenesCrupier, List<String> rutasimagenesJugador2) {
        // Limpiar el panel antes de agregar nuevas cartas
        jPanelCartasJugador.removeAll();
        jPanelCartasCrupier.removeAll();
        int x = 20; // posición inicial en X
        int y = 10; // posición fija en Y
        int offset = 25; // cuanto se solapan las cartas
        //Vamos hacer apareceer las cartas

        //Cartas de Jugador.
        JPanel panelCartas = jPanelCartasJugador; // el panel exclusivo para cartas
        for (String ruta : rutasimagenesJugador) {
            ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
            JLabel lbl = new JLabel(icon);
            panelCartas.add(lbl);
            lbl.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
            x += 60; // separacion entre cartas
        }
        panelCartas.revalidate();
        panelCartas.repaint();


        //Cartas de Jugador2.
        JPanel panelCartas2 = jPanelCartasJugador2; // el panel exclusivo para cartas
        for (String ruta : rutasimagenesJugador) {
            ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
            JLabel lbl = new JLabel(icon);
            panelCartas2.add(lbl);
            lbl.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
            x += 60; // separacion entre cartas
        }
        panelCartas2.revalidate();
        panelCartas2.repaint();

        //Cartas de crupier.
        JPanel panelCartasCrupier = jPanelCartasCrupier; // el panel exclusivo para cartas
        for (String ruta : rutasImagenesCrupier) {
            ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
            JLabel lbl = new JLabel(icon);
            panelCartasCrupier.add(lbl);
            lbl.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
            x += 40; // separacion entre cartas
        }
        panelCartasCrupier.revalidate();
        panelCartasCrupier.repaint();
    }



    @Override
    public void mostrarManoCrupier(String manodeCrupier, List<String> rutasImagenesCrupier) {
        jPanelCartasCrupier.removeAll();
        int x = 20; // posición inicial en X
        int y = 10; // posición fija en Y
        //Cartas de crupier.
        JPanel panelCartasCrupier = jPanelCartasCrupier; // el panel exclusivo para cartas
        for (String ruta : rutasImagenesCrupier) {
            ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
            JLabel lbl = new JLabel(icon);
            panelCartasCrupier.add(lbl);
            lbl.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
            panelCartasCrupier.add(lbl, 0); // siempre al fondo
            x += 40; // separacion entre cartas
        }
        panelCartasCrupier.revalidate();
        panelCartasCrupier.repaint();
    }


    //Podrias ser mas generico:
    public void mostrarResultado(String resultado) {
    // resultado puede ser "Ganaste", "Empataste", "Perdiste"
    JOptionPane.showMessageDialog(null, resultado, "Resultado de la partida", JOptionPane.INFORMATION_MESSAGE);
    
}
    
    

    @Override
    public void procesarEntradaUsuario(String texto, Consumer<String> callback) {

            boolean esMiTurno = this.Mi_Nombre.equals(this.NombreJugadorACtual);

            //Seria el de volver para atras al menu principal.
            button1.setEnabled(true);

            // Quitamos ActionListeners previos para evitar duplicados
            for (ActionListener al : ButtonPedir.getActionListeners()) {
                ButtonPedir.removeActionListener(al);
            }

            for (ActionListener al : ButtonPlantarse.getActionListeners()) {
                ButtonPlantarse.removeActionListener(al);
            }

            for (ActionListener al : ButtonDoblar.getActionListeners()) {
                ButtonDoblar.removeActionListener(al);
            }

            for (ActionListener al : jButtonApostar.getActionListeners()) {
                jButtonApostar.removeActionListener(al);
            }

            //Subtitulos del crupier:
            jLabelSubtitulosCrupier.setVisible(true);
            // jLabelSubtitulosCrupier.setText("Crupier: " + texto);


            //Se ingresa la apuesta.
            jButtonApostar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                        boolean esMiTurno = Mi_Nombre.equals(NombreJugadorACtual);
                        if (!esMiTurno){
                            jPanelCartasCrupier.setVisible(false);
                            return;
                        }

                        jButtonApostar.setEnabled(false);
                        //Habilitamos el texto del puntaje.
                        jLabelPuntajeJugador.setVisible(true);
                        jLabelPuntajeRival.setVisible(true);
                        jLabelPuntajeCrupier.setVisible(true);
                        //Los botones van estar  habilidatoos cunando se opima:
                        ButtonPedir.setEnabled(true);
                        ButtonPlantarse.setEnabled(true);
                        button1.setEnabled(false);
                        jMoneda50.setEnabled(false);
                        jMoneda25.setEnabled(false);
                        jMoneda100.setEnabled(false);
                        jButtonBorrarApuesta.setEnabled(true);
                        jLabelMonedas.setVisible(true);
                        jPanelCartasJugador.setVisible(true);
                        jPanelCartasCrupier.setVisible(true);

                        Float apuesta = Float.parseFloat(String.valueOf(inputApuesta));
                        if (apuesta > saldoActual) {
                            SwingUtilities.invokeLater(() -> {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "No puedes apostar más de tu saldo actual: " + saldoActual,
                                        "Error: Saldo insuficiente!",
                                        JOptionPane.WARNING_MESSAGE
                                );
                                ButtonPlantarse.setEnabled(false);
                                ButtonPedir.setEnabled(false);
                                ButtonPlantarse.setEnabled(false);
                                jMoneda50.setEnabled(true);
                                jMoneda25.setEnabled(true);
                                jMoneda100.setEnabled(true);
                                jLabelPuntajeCrupier.setVisible(false);
                                jLabelPuntajeRival.setVisible(false);
                                jLabelPuntajeJugador.setVisible(false);
                                jLabelMonedas.setVisible(false);
                            });
                            return; // No continúa
                        }
                        callback.accept(String.valueOf(inputApuesta));  // Devuelve el texto al controlador}-
                        System.out.println("estoy en aposstar");
                        //Quiero Obtener el saldo que tengo a ver si puedo tener habilitada la opcion de doblar.
                        System.out.println("saldo " + String.valueOf(saldoActual) + "se puede doblar: " + String.valueOf(inputApuesta * 2));
                        if (saldoActual >= (inputApuesta * 2)) {
                            ButtonDoblar.setEnabled(true);
                        } else {
                            ButtonDoblar.setEnabled(false);
                        }

                        for (ActionListener al : jButtonApostar.getActionListeners()) {
                            jButtonApostar.removeActionListener(al);
                        }
                        return;



                }


//jefe

            });


            // PEDIR:
            ButtonPedir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actualizarSubtitulos(controlador.obtenerNombreJugador() + ": " + "Te pido una carta, che..");
                    ButtonDoblar.setEnabled(false);
                    callback.accept("1"); // Simula que el usuario escribió "1"
                    //return false;
                }


            });

            //Plantarse.
            ButtonPlantarse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actualizarSubtitulos(controlador.obtenerNombreJugador() + ": " +"Me Planto.. no quiero mas cartas");
                    ButtonDoblar.setEnabled(false);
                    callback.accept("2"); // Simula que el usuario escribió "1"
                    // return false;
                }
            });

            //Boton Doblar.
            ButtonDoblar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actualizarSubtitulos(controlador.obtenerNombreJugador() + ": " +"Che, Doblo mi apuesta");
                    callback.accept("3"); // Simula que el usuario escribió "1"
                    // return false;
                }
            });




    }




/*
    @Override
    public void procesarEntradaUsuario(String texto, Consumer<String> callback) {
        // Verificamos si es el turno de este jugador
        boolean esMiTurno = this.Mi_Nombre.equals(this.NombreJugadorACtual);

        // Subtítulos del crupier visibles
        jLabelSubtitulosCrupier.setVisible(true);

        // Habilitar o deshabilitar botones según el turno
        jButtonApostar.setEnabled(esMiTurno);
        ButtonPedir.setEnabled(false);
        ButtonPlantarse.setEnabled(false);
        ButtonDoblar.setEnabled(false);

        // --- Listener de Apostar (se ejecuta 1 sola vez) ---
        if (jButtonApostar.getActionListeners().length == 0) {
            jButtonApostar.addActionListener(e -> {
                if (!esMiTurno) return; // ignorar si no es mi turno

                Float apuesta = Float.parseFloat(String.valueOf(inputApuesta));
                if (apuesta > saldoActual) {
                    JOptionPane.showMessageDialog(
                            null,
                            "No puedes apostar más de tu saldo actual: " + saldoActual,
                            "Error: Saldo insuficiente!",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                jButtonApostar.setEnabled(false);
                jLabelPuntajeJugador.setVisible(true);
                jLabelPuntajeCrupier.setVisible(true);

                ButtonPedir.setEnabled(true);
                ButtonPlantarse.setEnabled(true);
                jButtonBorrarApuesta.setEnabled(true);

                jPanelCartasJugador.setVisible(true);
                jPanelCartasCrupier.setVisible(true);

                // Enviar acción al controlador indicando QUIÉN apostó
                callback.accept(Mi_Nombre + ":APOSTAR:" + inputApuesta);

                // Habilitar Doblar si hay saldo suficiente
                if (saldoActual >= (inputApuesta * 2)) {
                    ButtonDoblar.setEnabled(true);
                }
            });
        }

        // --- Listener de Pedir carta ---
        if (ButtonPedir.getActionListeners().length == 0) {
            ButtonPedir.addActionListener(e -> {
                if (!esMiTurno) return;
                actualizarSubtitulos(Mi_Nombre + ": Te pido una carta, che..");
                ButtonDoblar.setEnabled(false);
                callback.accept(Mi_Nombre + ":PEDIR");
            });
        }

        // --- Listener de Plantarse ---
        if (ButtonPlantarse.getActionListeners().length == 0) {
            ButtonPlantarse.addActionListener(e -> {
                if (!esMiTurno) return;
                actualizarSubtitulos(Mi_Nombre + ": Me planto.. no quiero más cartas");
                ButtonDoblar.setEnabled(false);
                callback.accept(Mi_Nombre + ":PLANTARSE");
            });
        }

        // --- Listener de Doblar ---
        if (ButtonDoblar.getActionListeners().length == 0) {
            ButtonDoblar.addActionListener(e -> {
                if (!esMiTurno) return;
                actualizarSubtitulos(Mi_Nombre + ": Che, doblo mi apuesta");
                callback.accept(Mi_Nombre + ":DOBLAR");
            });
        }
    }
    //aca hacemos esta funcion para mostrar las cartas:



 */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button ButtonDividir;
    private java.awt.Button ButtonDoblar;
    private java.awt.Button ButtonPedir;
    private java.awt.Button ButtonPlantarse;
    private java.awt.Button button1;
    private javax.swing.JButton jButtonApostar;
    private javax.swing.JButton jButtonBorrarApuesta;
    private javax.swing.JPanel jContendorDeSubtitulo;
    private javax.swing.JPanel jContendorDeSubtituloCrupier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelFlechita;
    private javax.swing.JLabel jLabelMonedas;
    private javax.swing.JLabel jLabelNegro;
    private javax.swing.JLabel jLabelPuntajeCrupier;
    private javax.swing.JLabel jLabelPuntajeJugador;
    private javax.swing.JLabel jLabelPuntajeRival;
    private javax.swing.JLabel jLabelSaldo;
    private javax.swing.JLabel jLabelSubtitulos;
    private javax.swing.JLabel jLabelSubtitulosCrupier;
    private javax.swing.JLabel jLabelYaNoTienesSaldo;
    private javax.swing.JButton jMoneda100;
    private javax.swing.JButton jMoneda25;
    private javax.swing.JButton jMoneda50;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelCartasCrupier;
    private javax.swing.JPanel jPanelCartasJugador;
    private javax.swing.JPanel jPanelCartasJugador2;
    private javax.swing.JPanel jPanelContenedorApuesta;
    private javax.swing.JButton jbuttonRecargar;
    private javax.swing.JLabel jlabelApuesta;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
