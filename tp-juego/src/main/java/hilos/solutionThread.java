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

        for(int i = 0; i < 8; i++){
            arriba();
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

        tiro();
        tiro();

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
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return;


    }

    // this.moverDerecha();
	// 	this.moverDerecha();

	// 	for (int i = 0; i < 10; i++)
	// 		this.moverAbajo();

	// 	this.moverIzquierda();
	// 	this.moverIzquierda();

	// 	for (int i = 0; i < 8; i++)
	// 		this.moverAbajo();

	// 	for (int i = 0; i < 8; i++)
	// 		this.moverArriba();

	// 	for (int i = 0; i < 8; i++)
	// 		this.moverDerecha();

	// 	for (int i = 0; i < 8; i++)
	// 		this.moverAbajo();


	// 	for (int i = 0; i < 10; i++)
	// 		this.moverDerecha();


	// 	for (int i = 0; i < 8; i++)
	// 		this.moverArriba();

	// 	for (int i = 0; i < 12; i++)
	// 		this.moverIzquierda();

	// 	this.moverArriba();
	// 	this.moverArriba();

	// 	this.disparar();

	// 	for (int i = 0; i < 8; i++)
	// 		this.moverArriba();

	// 	for (int i = 0; i < 12; i++)
	// 		this.moverDerecha();

	// 	this.moverAbajo();
	// 	this.moverAbajo();

	// 	this.moverDerecha();
	// 	this.moverDerecha();

}
