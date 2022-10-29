package hilos;

import entidad.Jugador;
import java.awt.Robot;
import java.awt.event.KeyEvent;





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
        esperar(1000);
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
        esperar(1000);

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
        esperar(1000);

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
        esperar(1000);

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
        esperar(1000);

        
        //bloqueo al primer enemigo con la piedra
        for (int i = 0; i < 4; i++)
            izquierda();
        
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 4; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            arriba();
        
        //bloqueo la flecha que dispara al cofre
        
        for (int i = 0; i < 4; i++)
            izquierda();
        
        for (int i = 0; i < 4; i++)
            arriba();
        for (int i = 0; i < 2; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            abajo();

        //bloqueo al 2do enemigo con la piedra
        for (int i = 0; i < 8; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 4; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            derecha();
        // for (int i = 0; i < 3; i++)
        //     arriba();
        
        for (int i = 0; i < 4; i++)
            derecha();
        for (int i = 0; i < 5; i++)
            arriba();
        for (int i = 0; i < 5; i++)
            abajo();
        for (int i = 0; i < 8; i++)
            izquierda();
        for (int i = 0; i < 9; i++)
            arriba();
        for (int i = 0; i < 9; i++)
            abajo();
        for (int i = 0; i < 6; i++)
            derecha();
        for (int i = 0; i < 16; i++)
            arriba();
        //agarro el unico item
        for (int i = 0; i < 8; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            izquierda();
        
        
        tiro();
        
        for (int i = 0; i < 6; i++)
            izquierda();
        for (int i = 0; i < 20; i++)
            abajo();
        //agarro llave
        for (int i = 0; i < 2; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            derecha();
        for (int i = 0; i < 20; i++)
            arriba();
        for (int i = 0; i < 2; i++)
            izquierda();
        //voy a la salida
        for (int i = 0; i < 2; i++)
            arriba();
    }
    
    public void solucionNivel6() throws InterruptedException {
        esperar(1000);

        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 4; i++)
            izquierda();
        for (int i = 0; i < 6; i++)
            abajo();
        for (int i = 0; i < 6; i++)
            derecha();
        for (int i = 0; i < 6; i++)
            izquierda();
        for (int i = 0; i < 4; i++)
            arriba();
        //empujo caja a posicion conveniente
        for (int i = 0; i < 12; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            arriba();
        
        //agarro el 1er item
        for (int i = 0; i < 6; i++)
            derecha();
        
        abajo();
        tiro();
        arriba();
        
        for (int i = 0; i < 4; i++)
            izquierda();
        
        for (int i = 0; i < 10; i++)
            abajo();
        // for (int i = 0; i < 2; i++)
        //     izquierda();
        // for (int i = 0; i < 2; i++)
        //     abajo();
        // for (int i = 0; i < 1; i++)
        //     derecha();
        for (int i = 0; i < 8; i++)
            arriba();
        for (int i = 0; i < 12; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            abajo();
        
        //empujo 2da piedra a donde me conviene
        for (int i = 0; i < 8; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 2; i++)
            derecha();
        for (int i = 0; i < 14; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            derecha();
        
        //agarro el 2do item
        for (int i = 0; i < 4; i++)
            abajo();
        derecha();
        derecha();
        izquierda();
        izquierda();
        
        for (int i = 0; i < 6; i++)
            arriba();
        for (int i = 0; i < 2; i++)
            izquierda();
        for (int i = 0; i < 10; i++)
            arriba();
        for (int i = 0; i < 12; i++)
            izquierda();
        for (int i = 0; i < 8; i++)
            abajo();
        //agarro el 3er item
        for (int i = 0; i < 2; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 2; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 2; i++)
            derecha();
        for (int i = 0; i < 8; i++)
            abajo();
        for (int i = 0; i < 6; i++)
            izquierda();
        
        //agarro el ultimo item
        for (int i = 0; i < 4; i++)
            abajo();
        //agarro llave
        for (int i = 0; i < 4; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            arriba();
        //voy a la salida
        for (int i = 0; i < 6; i++)
            izquierda();
        
    }
    
    public void solucionNivel7() throws InterruptedException {
        esperar(1000);

        for (int i = 0; i < 2; i++)
            derecha();
        for (int i = 0; i < 4; i++)
            arriba();
        for (int i = 0; i < 8; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            izquierda();
        
        //bloqueo 1er enemigo
        for (int i = 0; i < 6; i++)
            arriba();
        
        for (int i = 0; i < 4; i++)
            derecha();
        for (int i = 0; i < 4; i++)
            abajo();
        for (int i = 0; i < 10; i++)
            derecha();
        
        //
        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 4; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 2; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            arriba();
        
        for (int i = 0; i < 4; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            izquierda();
        
        //bloqueo 2do enemigo
        for (int i = 0; i < 8; i++)
            arriba();
        for (int i = 0; i < 8; i++)
            derecha();
        for (int i = 0; i < 6; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            derecha();
        
        
        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 4; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 2; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            arriba();
        
        
        for (int i = 0; i < 6; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            izquierda();
        for (int i = 0; i < 12; i++)
            arriba();
        for (int i = 0; i < 2; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            arriba();
        
        //tapo anteultimo enemigo
        for (int i = 0; i < 6; i++)
            derecha();
        
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 6; i++)
            derecha();
        for (int i = 0; i < 4; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 2; i++)
            derecha();
        for (int i = 0; i < 4; i++)
            arriba();
        
        for (int i = 0; i < 4; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 4; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 2; i++)
            derecha();
        for (int i = 0; i < 2; i++)
            abajo();
        for (int i = 0; i < 2; i++)
            izquierda();
        for (int i = 0; i < 2; i++)
            abajo();
        
        //bloqueo al ultimo enemigo
        for (int i = 0; i < 2; i++)
            derecha();
        
        for (int i = 0; i < 2; i++)
            arriba();
        for (int i = 0; i < 12; i++)
            izquierda();
        for (int i = 0; i < 14; i++)
            abajo();
        for (int i = 0; i < 14; i++)
            derecha();
        
        //buscar item
        for (int i = 0; i < 10; i++)
            arriba();
        for (int i = 0; i < 10; i++)
            abajo();
        for (int i = 0; i < 14; i++)
            izquierda();
        //llave
        for (int i = 0; i < 16; i++)
            arriba();
        //salida
        for (int i = 0; i < 22; i++)
            abajo();
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
