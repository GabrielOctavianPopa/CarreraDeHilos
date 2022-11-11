import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class Main extends javax.swing.JFrame implements Observer {
    //creacion de variables
    private JButton btnIniciar, btnStop;
    private JLabel player1, player2, player3, jLabel5, lblGanador;
    private JProgressBar progressBar1, progressBar2, progressBar3;
    private Thread[] hilos;
    private JFrame frame;

    public Main() {
        initComponents();
        hilos = new Thread[3];
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        Jugador c = (Jugador) o;
        int porcentaje = (int) arg;

        switch (c.getNombre()) {
            case "1":
                this.progressBar1.setValue(porcentaje);
                break;
            case "2":
                this.progressBar2.setValue(porcentaje);
                break;
            case "3":
                this.progressBar3.setValue(porcentaje);
                break;
        }
        if(porcentaje>=100){
            terminar();
            this.btnIniciar.setEnabled(true);
            this.lblGanador.setText("Jugador " + c.getNombre());
        }
    }

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {
        this.btnIniciar.setEnabled(false);
        for (int i = 0; i < hilos.length; i++) {
            Jugador auxJugador = new Jugador((i+1)+"");
            auxJugador.addObserver(this);
            hilos[i] = new Thread(auxJugador);
            hilos[i].start();
        }
    }

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].stop();
        }
    }

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
        frame = new JFrame("Carrera de caballos");


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnStop.setText("Parar");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
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