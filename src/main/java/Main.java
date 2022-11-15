import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends javax.swing.JFrame {
    //creacion de variables
    private JButton btnIniciar, btnStop;
    private JLabel player1, player2, player3, jLabel5, lblGanador;
    private JProgressBar progressBar1, progressBar2, progressBar3;
    private HiloMonitorJugador thJugador1, thJugador2, thJugador3;
    private HiloBarra thBarra1, thBarra2, thBarra3;
    private HiloCronometro hiloCronometro;
    private Thread[] hilos = new Thread[6];

    public Main() {
        initComponents();
        this.setVisible(true);
        thJugador1 = new HiloMonitorJugador("Jugador 1");
        thJugador2 = new HiloMonitorJugador("Jugador 2");
        thJugador3 = new HiloMonitorJugador("Jugador 3");
        thBarra1 = new HiloBarra(thJugador1, progressBar1, thJugador1.getPorcentaje());
        thBarra2 = new HiloBarra(thJugador2, progressBar2, thJugador2.getPorcentaje());
        thBarra3 = new HiloBarra(thJugador3, progressBar3, thJugador3.getPorcentaje());
        hilos[0] = thJugador1;
        hilos[1] = thJugador2;
        hilos[2] = thJugador3;
        hilos[3] = thBarra1;
        hilos[4] = thBarra2;
        hilos[5] = thBarra3;
    }

    public static void main(String args[]) {
        new Main();
    }

//    @Override
//    public void update(Observable o, Object arg) {
//        Jugador c = (Jugador) o;
//        int porcentaje = (int) arg;
//
//        switch (c.getNombre()) {
//            case "1":
//                this.progressBar1.setValue(porcentaje);
//                break;
//            case "2":
//                this.progressBar2.setValue(porcentaje);
//                break;
//            case "3":
//                this.progressBar3.setValue(porcentaje);
//                break;
//        }
//
//        if(porcentaje>=100){
//            terminar();
//            this.btnIniciar.setEnabled(true);
//            this.lblGanador.setText("Jugador " + c.getNombre());
//        }
//    }

    private void terminar(){
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].interrupt();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        btnIniciar = new JButton();
        btnStop = new JButton();
        progressBar1 = new JProgressBar();
        progressBar2 = new JProgressBar();
        progressBar3 = new JProgressBar();
        player1 = new javax.swing.JLabel();
        player2 = new JLabel();
        player3 = new JLabel();
        jLabel5 = new JLabel();
        lblGanador = new JLabel();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                hiloCronometro = new HiloCronometro();
                hiloCronometro.start();
                btnIniciar.setEnabled(false);

                for (int i = 0; i < hilos.length; i++) {
                    lblGanador.setText("");
                    btnStop.setText("Parar");
                    hilos[i].start();
                }
            }
        });

        btnStop.setText("Parar");
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String auxText = btnStop.getText();

                switch (auxText) {
                    case "Parar":
                        btnStop.setText("Reanudar");
                        for (int i = 0; i < hilos.length; i++) {
                            hilos[i].suspend();
                        }
                        hiloCronometro.suspend();
                        break;
                    case "Reanudar":
                        btnStop.setText("Parar");
                        for (int i = 0; i < hilos.length; i++) {
                            hilos[i].resume();
                        }
                        hiloCronometro.resume();
                        break;
                }
            }
        });

        progressBar1.setStringPainted(true);
        progressBar2.setStringPainted(true);
        progressBar3.setStringPainted(true);

        player1.setText("Jugador 1");
        player2.setText("Jugador 2");
        player3.setText("Jugador 3");
        jLabel5.setText("El ganador es: ");
        lblGanador.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(player1)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(progressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(player2)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(progressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(player3)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(progressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(225, 225, 225)
                                                        .addComponent(jLabel5)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lblGanador, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        )
                                )
                        )
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        //TODO JUGADOR 1
                                        .addComponent(player1))
                                .addComponent(progressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(player2))
                                .addComponent(progressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(player3))
                                .addComponent(progressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblGanador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }
}