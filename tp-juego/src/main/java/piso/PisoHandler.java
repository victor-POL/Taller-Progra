package piso;

import java.util.HashMap;
import java.util.Map;

public class PisoHandler {

    private Map<Double, Piso> pisos;

    public static final int POSICION_PASAR_NIVEL = 2;
    public static final int POSICION_ITEM_HABILITAR_PASO_NIVEL = 6;

    private String[] nombres = { "camino", "borde", "obstaculo", "roca" };

    // Constructores

    public PisoHandler() {
        pisos = new HashMap<>();

        // Caminos, personaje puede pisar
        pisos.put(0.0, new Piso("file:src/main/resources/sprites/caminables/pasto.png", false, nombres[0]));
        pisos.put(0.1, new Piso("file:src/main/resources/sprites/caminables/pasto_con_flores.png", false, nombres[0]));
        pisos.put(0.2, new Piso("file:src/main/resources/sprites/caminables/pasto_con_charco.png", false, nombres[0]));

        // Limites, personaje no puede pisar
        pisos.put(1.0, new Piso("file:src/main/resources/sprites/limites/valla_0.png", true, nombres[1]));
        pisos.put(1.1, new Piso("file:src/main/resources/sprites/limites/valla_1.png", true, nombres[1]));
        pisos.put(1.2, new Piso("file:src/main/resources/sprites/limites/valla_2.png", true, nombres[1]));
        pisos.put(1.3, new Piso("file:src/main/resources/sprites/limites/valla_3.png", true, nombres[1]));
        pisos.put(1.4, new Piso("file:src/main/resources/sprites/limites/valla_4.png", true, nombres[1]));
        pisos.put(1.5, new Piso("file:src/main/resources/sprites/limites/valla_5.png", true, nombres[1]));
        pisos.put(1.6, new Piso("file:src/main/resources/sprites/limites/valla_6.png", true, nombres[1]));
        pisos.put(1.7, new Piso("file:src/main/resources/sprites/limites/valla_7.png", true, nombres[1]));

        // Pasar nivel deshabilitado
        pisos.put(2.0, new Piso("file:src/main/resources/sprites/limite_pasar_nivel/valla_cerrada_este.png", true,
                nombres[2]));
        pisos.put(2.1,
                new Piso("file:src/main/resources/sprites/limite_pasar_nivel/valla_cerrada_sur.png", true, nombres[2]));
        pisos.put(2.2, new Piso("file:src/main/resources/sprites/limite_pasar_nivel/valla_cerrada_oeste.png", true,
                nombres[2]));
        pisos.put(2.3, new Piso("file:src/main/resources/sprites/limite_pasar_nivel/valla_cerrada_norte.png", true,
                nombres[2]));

        // Pasar nivel habilitado
        pisos.put(3.0, new Piso("file:src/main/resources/sprites/limite_pasar_nivel/valla_abierta_este.png", false,
                nombres[2]));
        pisos.put(3.1, new Piso("file:src/main/resources/sprites/limite_pasar_nivel/valla_abierta_sur.png", false,
                nombres[2]));
        pisos.put(3.2, new Piso("file:src/main/resources/sprites/limite_pasar_nivel/valla_abierta_oeste.png", false,
                nombres[2]));
        pisos.put(3.3, new Piso("file:src/main/resources/sprites/limite_pasar_nivel/valla_abierta_norte.png", false,
                nombres[2]));

        // Pisos item pasar nivel
        pisos.put(6.0,
                new Piso("file:src/main/resources/sprites/piso_item_pasar_nivel/cofre_cerrado.png", true, nombres[2]));
        pisos.put(6.1,
                new Piso("file:src/main/resources/sprites/piso_item_pasar_nivel/cofre_abierto.png", false, nombres[2]));

        // Obstaculos, personaje no puede pisar
        pisos.put(20.0, new Piso("file:src/main/resources/sprites/obstaculos_inamovibles/roca.png", true, nombres[3]));
        pisos.put(30.0, new Piso("file:src/main/resources/sprites/obstaculos_inamovibles/arbol.png", true, nombres[2]));
        pisos.put(40.0, new Piso("file:src/main/resources/sprites/obstaculos_inamovibles/agua.png", true, nombres[0]));

        // Space Invaders

        pisos.put(69.0, new Piso("file:src/main/resources/Space_Invaders/fondo.png", false, nombres[0]));
    }

    // Metodos

    public Piso getPisoByPosition(double n) {
        return pisos.get(n);
    }

}
