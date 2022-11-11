import java.util.Observable;
public class Jugador extends Observable implements Runnable {
    private String nombre;
    private boolean stop = false;

    @Override
    public void run() {
        int porcentaje = 0;
        int velocidad = 0;
        int crashed = 0;

        try {
            velocidad = generaNumeroAleatorio(5, 15);
            while (porcentaje < 100 && !stop) {
                if(crashed == 10){
                    if (generaNumeroAleatorio(1, 10) > 6) {
                        if(porcentaje >= 10){
                            porcentaje = porcentaje - 10;
                        }
                        else {
                            porcentaje = 0;
                        }

                    }
                    crashed = 0;
                }

                crashed++;
                porcentaje++;

                this.setChanged();
                this.notifyObservers(porcentaje);
                this.clearChanged();

                Thread.sleep(velocidad*7);
            }
        } catch (InterruptedException ex) {
            System.out.println("Hilo interrumpido");
        }
    }

    public static int generaNumeroAleatorio(int minimo, int maximo) {
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}