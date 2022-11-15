import javax.swing.*;

public class HiloBarra extends Thread{
    JProgressBar barra;
    int porcentaje = 0;
    Thread hilo;


    @Override
    public void run(){
        try{
            //mientras el porcentaje de la brrra de progreso sea menor a 100 y no se haya pulsado el boton de stop seguir avanzando
            while (barra.getValue() < 100) {
                //aumentamos el porcentaje de la barra de progreso
                barra.setValue(barra.getValue() + 1);
            }
            //cuando el porcentaje de la barra de progreso sea igual a 100 se muestra un mensaje diciendo el ganador
            if(barra.getValue() == 100){
                terminar();
                System.out.println("El ganador es: " + barra.getName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HiloBarra() {
    }

    public HiloBarra(Thread hilo, JProgressBar barra, int porcentaje) {
        this.hilo = hilo;
        this.barra = barra;
        this.porcentaje = porcentaje;
    }

    private void terminar(){
        hilo.interrupt();
    }

    public JProgressBar getBarra() {
        return barra;
    }

    public void setBarra(JProgressBar barra) {
        this.barra = barra;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
