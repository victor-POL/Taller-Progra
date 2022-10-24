package utiles;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import entidad.Cosa;
import entidad.Enemigo;
import mapa.Mapa;

public class Archivo {
	private String nombre;

	public Archivo(String nombre) {
		this.nombre = nombre;
	}

	public Mapa cargarMapa() {
		Scanner scanner = null;
		Mapa map = null;

		try {
			File file = new File("src/main/resources/niveles/" + this.nombre + ".in");
			scanner = new Scanner(file);

			int x = scanner.nextInt();
			int y = scanner.nextInt();

			Posicion pos_inicial_jugador = new Posicion(x, y);

			int itemsObjetivo = scanner.nextInt();
			double[][] disenio = new double[13][13];
			Map<Posicion, Cosa> cosas = new HashMap<Posicion, Cosa>();
			Map<Posicion, Enemigo> enemigos = new HashMap<Posicion, Enemigo>();

			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 13; j++) {
					disenio[i][j] = Double.valueOf(scanner.next());
				}
			}
			
			map = new Mapa(disenio, cosas, enemigos, pos_inicial_jugador, itemsObjetivo);

			int cantCorazones = scanner.nextInt();

			for (int i = 0; i < cantCorazones; i++) {
				x = scanner.nextInt();
				y = scanner.nextInt();

				Cosa corazon = new Cosa(new Posicion(x, y), map, true, false, "municion");

				cosas.put(corazon.getPos(), corazon);
			}

			int cantCajas = scanner.nextInt();

			for (int i = 0; i < cantCajas; i++) {
				x = scanner.nextInt();
				y = scanner.nextInt();

				Cosa caja = new Cosa(1, new Posicion(x, y), map, false, true, "obstaculo empujable");

				cosas.put(caja.getPos(), caja);
			}

			int cantEnemigos = scanner.nextInt();

			for (int i = 0; i < cantEnemigos; i++) {
				x = scanner.nextInt();
				y = scanner.nextInt();

				Enemigo enemigo = new Enemigo(0, new Posicion(x, y), map);

				enemigos.put(enemigo.getPos(), enemigo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return map;
	}
}
