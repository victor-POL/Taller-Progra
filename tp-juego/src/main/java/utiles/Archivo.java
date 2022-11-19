package utiles;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import entidad.Cosa;
import entidad.Enemigo;
import mapa.Mapa;
import mapa.MapaSpace;



public class Archivo implements NomJuegos {
	private String nombre;

	public Archivo(String nombre) {
		this.nombre = nombre;
	}

	public Mapa cargar_mapa_lolo() {
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

				Cosa itemAgarrable = new Cosa(0, new Posicion(x, y), map, true, false, "municion");

				cosas.put(itemAgarrable.getPos(), itemAgarrable);
			}

			int cantCajas = scanner.nextInt();

			for (int i = 0; i < cantCajas; i++) {
				x = scanner.nextInt();
				y = scanner.nextInt();

				Cosa obstaculoEmpujable = new Cosa(1, new Posicion(x, y), map, false, true, "obstaculo empujable");

				cosas.put(obstaculoEmpujable.getPos(), obstaculoEmpujable);
			}

			int cantEnemigos = scanner.nextInt();

			for (int i = 0; i < cantEnemigos; i++) {
				x = scanner.nextInt();
				y = scanner.nextInt();
				int orientacion = scanner.nextInt();

				Enemigo enemigo = new Enemigo(0, new Posicion(x, y), map, orientacion);

				enemigos.put(enemigo.getPos(), enemigo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return map;
	}

	// TODO
	public MapaSpace cargar_mapa_space_invaders() {
		return null;
	}

}
