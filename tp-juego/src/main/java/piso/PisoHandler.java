package piso;

import java.util.HashMap;
import java.util.Map;

public class PisoHandler {

    private Map<Double, Piso> pisos;
    public static final int CAMINO = 0;
    public static final int PARED = 1;
    public static final int AGUA = 2;
    public static final int PUERTA_CERRADA = 3;
    public static final int ROCA = 4;
    public static final int ARBOL = 5;
    public static final int COFRE_CERRADO = 6;
    public static final int COFRE_ABIERTO = 7;
    public static final int PUERTA_ABIERTA = 8;

    // Constructores

    public PisoHandler() {
        pisos = new HashMap<>();
        
        // Pasto
        pisos.put(0.0, new Piso("file:src/main/resources/piso/camino.png", false));
        pisos.put(0.1, new Piso("file:src/main/resources/piso/camino_1.png", false));
        pisos.put(0.2, new Piso("file:src/main/resources/piso/camino_2.png", false));
        
        
        // Pared
        pisos.put(1.0, new Piso("file:src/main/resources/piso/pared_0.png", true));
        pisos.put(1.1, new Piso("file:src/main/resources/piso/pared_1.png", true));
        pisos.put(1.2, new Piso("file:src/main/resources/piso/pared_2.png", true));
        pisos.put(1.3, new Piso("file:src/main/resources/piso/pared_3.png", true));
        pisos.put(1.4, new Piso("file:src/main/resources/piso/pared_4.png", true));
        pisos.put(1.5, new Piso("file:src/main/resources/piso/pared_5.png", true));
        pisos.put(1.6, new Piso("file:src/main/resources/piso/pared_6.png", true));
        pisos.put(1.7, new Piso("file:src/main/resources/piso/pared_7.png", true));
        
        pisos.put(2.0, new Piso("file:src/main/resources/piso/agua.png", true));
        pisos.put(3.0, new Piso("file:src/main/resources/piso/puertas/puerta_cerrada.png", true));
        pisos.put(4.0, new Piso("file:src/main/resources/piso/roca.png", true));
        pisos.put(5.0, new Piso("file:src/main/resources/piso/arbol.png", true));
        pisos.put(6.0, new Piso("file:src/main/resources/piso/cofres/cofre_cerrado.png", true));
        pisos.put(7.0, new Piso("file:src/main/resources/piso/cofres/cofre_abierto.png", false));
        pisos.put(8.0, new Piso("file:src/main/resources/piso/puertas/puerta_abierta.png", false));
    }

    // Metodos

    public Piso getPisoByPosition(double n) {
        return pisos.get(n);
    }
}
