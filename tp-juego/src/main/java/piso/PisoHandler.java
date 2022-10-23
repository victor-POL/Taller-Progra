package piso;

import java.util.ArrayList;
import java.util.List;

public class PisoHandler {

    private List<Piso> pisos;
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
        pisos = new ArrayList<>();
        pisos.add(new Piso("file:src/main/resources/piso/camino.png", false));
        pisos.add(new Piso("file:src/main/resources/piso/pared.png", true));
        pisos.add(new Piso("file:src/main/resources/piso/agua.png", true));
        pisos.add(new Piso("file:src/main/resources/piso/puertas/puerta_cerrada.png", true));
        pisos.add(new Piso("file:src/main/resources/piso/roca.png", true));
        pisos.add(new Piso("file:src/main/resources/piso/arbol.png", true));
        pisos.add(new Piso("file:src/main/resources/piso/cofres/cofre_cerrado.png", true));
        pisos.add(new Piso("file:src/main/resources/piso/cofres/cofre_abierto.png", false));
        pisos.add(new Piso("file:src/main/resources/piso/puertas/puerta_abierta.png", false));
    }

    // Metodos

    public Piso getPisoByPosition(int n) {
        if (pisos.size() < n)
            return null;
        return pisos.get(n);
    }
}
