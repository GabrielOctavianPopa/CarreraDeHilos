import javax.swing.*;

public class HiloCronometro extends Thread{
    private JLabel lblCronometro;
    private int segundos = 0;
    private boolean stop = false;

    public HiloCronometro(JLabel lblCronometro) {
        this.lblCronometro = lblCronometro;
    }

    public HiloCronometro(){

    }

    @Override
    public void run() {
        while(!stop){
            segundos++;
            //lblCronometro.setText(segundos + " segundos");
            System.out.println(segundos + " segundos");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Hilo interrumpido");
            }
        }
    }

    public void stopHilo(){
        stop = true;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
