package hilos;

import entidad.Jugador;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.sound.sampled.spi.MixerProvider;




/**
 * Usar las funciones derecha, izquierda, arriba, abajo, tiro y esperar(int tiempo)
 *
 */
public class solutionThread extends Thread {

    Jugador jugador;
    String level;
    public solutionThread(Jugador jugador, String level){
        super();
        this.jugador = jugador;
        this.setName("solutionThread");
        this.level = level;
    }

    private void solucionNivel1() throws InterruptedException{
        derecha();
        derecha();
        
        for(int i = 0; i < 10; i++){
            abajo();
        }

        izquierda();
        izquierda();

        for(int i = 0; i < 8; i++){
            abajo();
        }

        for(int i = 0; i < 18; i++){
            arriba();
        }
        derecha();
        tiro();
        izquierda();
        
        for(int i = 0; i < 10; i++) {
            abajo();
        }
        
        for(int i = 0; i < 8; i++){
            derecha();
        }

        for(int i = 0; i < 8; i++){
            abajo();
        }

        for(int i = 0; i < 10; i++){
            derecha();
        }

        for(int i = 0; i < 8; i++){
            arriba();
        }

        for(int i = 0; i < 12; i++){
            izquierda();
        }

        arriba();
        arriba();

        for(int i = 0; i < 8; i++){
            arriba();
        }

        for(int i = 0; i < 12; i++){
            derecha();
        }

        abajo();
        abajo();

        derecha();
        derecha();
    }

    public void solucionNivel2() throws InterruptedException {
        
        abajo();
        abajo();

        for(int i = 0; i < 8; i++)
            derecha();

        for(int i = 0; i < 4; i++)
            abajo();
        
        derecha();
        derecha();

        for(int i = 0; i < 4; i++)
            abajo();

        for(int i = 0; i < 4; i++)
            derecha();

        abajo();
        abajo();
        
        for(int i = 0; i < 6; i++)
            derecha();

        for(int i = 0; i < 12; i++)
            arriba();

        for(int i = 0; i < 4; i++)
            abajo();

        for(int i = 0; i < 6; i++)
            izquierda();

        arriba();
        arriba();
        izquierda();

        tiro();

        izquierda();
        izquierda();


        for(int i = 0; i < 8; i++)
            arriba();


        for(int i = 0; i < 4; i++)
            izquierda();

        for(int i = 0; i < 4; i++)
            derecha();


        for(int i = 0; i < 10; i++)
            abajo();

        for(int i = 0; i < 4; i++)
            izquierda();

        for(int i = 0; i < 6; i++)
            abajo();

        for(int i = 0; i < 8; i++)
            arriba();

        for(int i = 0; i < 4; i++)
            derecha();

        for(int i = 0; i < 12; i++)
            arriba();

        
    }
    
    public void solucionNivel3() throws InterruptedException {
        
        for (int i = 0; i < 6; i++)
            arriba();
        
        for (int i = 0; i < 2; i++)
            izquierda();
        
        for (int i = 0; i < 6; i++)
            arriba();
        
        for (int i = 0; i < 16; i++)
            derecha();
        
        for (int i = 0; i < 4; i++)
            arriba();
        
        for (int i = 0; i < 2; i++)
            izquierda();
        
        abajo();
        
        arriba();
        
        for (int i = 0; i < 2; i++)
            derecha();
        
        for (int i = 0; i < 4; i++)
            abajo();
        
        for (int i = 0; i < 14; i++)
            izquierda();
        
        for (int i = 0; i < 2; i++)
            abajo();
        
        for (int i = 0; i < 2; i++)
            izquierda();
        
        for (int i = 0; i < 6; i++)
            arriba();
        
        for (int i = 0; i < 2; i++)
            derecha();
        
        for (int i = 0; i < 4; i++)
            abajo();
        
        for (int i = 0; i < 16; i++)
            derecha();
        
        for (int i = 0; i < 8; i++)
            arriba();
        
        for (int i = 0; i < 8; i++)
            izquierda();
        
        for (int i = 0; i < 8; i++)
            derecha();
        
        for (int i = 0; i < 8; i++)
            abajo();
        
        for (int i = 0; i < 16; i++)
            izquierda();
        
        for (int i = 0; i < 8; i++)
            arriba();
        
        for (int i = 0; i < 2; i++)
            izquierda();
        
        for (int i = 0; i < 2; i++)
            derecha();
        
        for (int i = 0; i < 8; i++)
            abajo();
        
        for (int i = 0; i < 16; i++)
            derecha();
        
        for (int i = 0; i < 2; i++)
            abajo();
        
        tiro();
        
        for (int i = 0; i < 8; i++)
            abajo();
        
        for (int i = 0; i < 4; i++)
            izquierda();
        
        for (int i = 0; i < 2; i++)
            abajo();
        
        for (int i = 0; i < 2; i++)
            izquierda();
        
        for (int i = 0; i < 2; i++)
            abajo();
    }

    public void solucionNivel4() throws InterruptedException {
        
        for (int i = 0; i < 2; i++)
            arriba();
        
        for (int i = 0; i < 2; i++)
            izquierda();
        
        for (int i = 0; i < 2; i++)
            abajo();
        
        for (int i = 0; i < 2; i++)
            arriba();
        
        for (int i = 0; i < 2; i++)
            derecha();
        
        for (int i = 0; i < 4; i++)
            abajo();
        
        for (int i = 0; i < 2; i++)
            izquierda();
        
        for (int i = 0; i < 6; i++)
            arriba();
        
        for (int i = 0; i < 2; i++)
            izquierda();
        
        for (int i = 0; i < 6; i++)
            abajo();
        
        for (int i = 0; i < 2; i++)
            derecha();
        
        for (int i = 0; i < 2; i++)
            abajo();
        
        for (int i = 0; i < 6; i++)
            izquierda();
        
        for (int i = 0; i < 2; i++)
            abajo();
        
        for (int i = 0; i < 2; i++)
            izquierda();
    
        for (int i = 0; i < 6; i++)
            abajo();
        
        for (int i = 0; i < 4; i++)
            izquierda();
        
        for (int i = 0; i < 6; i++)
            arriba();
        
        tiro();
        
        for (int i = 0; i < 6; i++)
            abajo();
        
        for (int i = 0; i < 4; i++)
            derecha();
        
        for (int i = 0; i < 6; i++)
            arriba();
        
        for (int i = 0; i < 2; i++)
            derecha();
        
        for (int i = 0; i < 4; i++)
            arriba();
        
        for (int i = 0; i < 2; i++)
            derecha();
        
        for (int i = 0; i < 4; i++)
            arriba();
        
        for (int i = 0; i < 4; i++)
            izquierda();
        
        for (int i = 0; i < 2; i++)
            abajo();
        
        for (int i = 0; i < 2; i++)
            arriba();
        
        for (int i = 0; i < 4; i++)
            izquierda();
        
        for (int i = 0; i < 2; i++)
            arriba();
 
        for (int i = 0; i < 20; i++)
            derecha();
        
        for (int i = 0; i < 6; i++)
            abajo();
        
        for (int i = 0; i < 3; i++)
            tiro();
        
        for (int i = 0; i < 6; i++)
            izquierda();
        
        abajo();
        
        for (int i = 0; i < 3; i++)
            tiro();
        
        for (int i = 0; i < 12; i++)
            abajo();
        
        for (int i = 0; i < 4; i++)
            izquierda();
        
        
        for (int i = 0; i < 10; i++)
            derecha();
        
        for (int i = 0; i < 18; i++)
            arriba();
        
        for (int i = 0; i < 4; i++)
            izquierda();
        
        for (int i = 0; i < 2; i++)
            arriba();
        
    }
    
    public void solucionNivel5() throws InterruptedException {
        
        
        
    }
    
    public void solucionNivel6() throws InterruptedException {
    
    }
    
    public void solucionNivel7() throws InterruptedException {
        
    }
    
    public void abajo() throws InterruptedException {
        jugador.tocarAbajo();
        Thread.sleep(150);
    }

    public void arriba() throws InterruptedException {
        jugador.tocarArriba();
        Thread.sleep(150);
    }

    public void izquierda() throws InterruptedException {
        jugador.tocarIzquierda();
        Thread.sleep(150);
    }

    public void derecha() throws InterruptedException {
        jugador.tocarDerecha();
        Thread.sleep(150);
    }

    public void esperar(int time) throws InterruptedException {
        Thread.sleep(time);
    }

    public void tiro() throws InterruptedException {
        //robot to press X
        try{
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_X);
            Thread.sleep(150);
            r.keyRelease(KeyEvent.VK_X);
        }catch(Exception e){
            System.out.println("Error al presionar X");
        }
    }


    @Override
    public void run() {
        try {
            switch(level){
                case "nivel_1":
                    solucionNivel1();
                    break;
                case "nivel_2":
                    solucionNivel2();
                    break;
                case "nivel_3":
                    solucionNivel3();
                    break;
                case "nivel_4":
                    solucionNivel4();
                    break;
                case "nivel_5":
                    solucionNivel5();
                    break;
                case "nivel_6":
                    solucionNivel6();
                    break;
                case "nivel_7":
                    solucionNivel7();
                    break;  
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return;


    }
    
}
