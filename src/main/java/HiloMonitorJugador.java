public class HiloMonitorJugador extends Thread{
    int porcentaje = 0;
    int velocidad = 0;
    int crashed = 0;

    @Override
    public void run() {
        try {
            //asignarle al hilo "this.jugador" una velocidad que va de 5 a 15 milisegundos
            velocidad = generaNumeroAleatorio(5, 15);
            //mientras el porcentaje sea menor a 100 y no se haya pulsado el boton de stop seguir avanzando
            while (porcentaje < 100) {
                //cuando el contador de crashed llegue a 10
                if(crashed == 10){
                    //rollear un numero aleatorio entre 1 y 10 y si es mayor a 6 se le resta 10 al porcentaje
                    porcentaje = (generaNumeroAleatorio(1, 10) > 6) ? (porcentaje >= 10) ? porcentaje - 10 : 0 : porcentaje;
                    //reiniciamos el contador de crashed
                    crashed = 0;
                }
                //aumentamos los contadores
                crashed++;
                porcentaje++;

                //para que no vaya tan rapido multiplicamos la "velocidad" por 7
                Thread.sleep(velocidad*7);
            }
        } catch (InterruptedException ex) {
            //si el hilo es interrumpido se muestra un mensaje
            System.out.println("Hilo interrumpido");
        }
    }

    //genera un numero aleatorio entre el minimo y el maximo indicados
    public static int generaNumeroAleatorio(int minimo, int maximo) {
        return (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
    }

    public HiloMonitorJugador(String nombre) {
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getCrashed() {
        return crashed;
    }

    public void setCrashed(int crashed) {
        this.crashed = crashed;
    }
}