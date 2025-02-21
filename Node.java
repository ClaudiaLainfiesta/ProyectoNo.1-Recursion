import java.util.ArrayList;

public class Node{
    private static int nodesCount = 0;
    private int id;
    boolean isExit;
    int xIndex, yIndex, zIndex;
    boolean north, east, south, west, up, down, danger;

    public Node(String connections, int xIndex, int yIndex, int zIndex){
        this.north = connections.charAt(0) == 'O';
        this.west = connections.charAt(1) == 'O';
        this.south = connections.charAt(2) == 'O';
        this.east = connections.charAt(3) == 'O';
        this.up = connections.charAt(4) == 'O';
        this.down = connections.charAt(5) == 'O';
        this.danger = connections.charAt(6) == 'O';

        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.zIndex = zIndex;

        this.isExit = false;

        this.id = nodesCount;
        nodesCount += 1;
    }

    public static void resetNodesCount(){
        nodesCount = 0;
    }

    public int getId(){
        return this.id;
    }
    /**
     * Nombre de los métodos: getX, getY y getZ
     * 
     * Parámetros:
     * - Ninguno
     * 
     * Descripción:
     * Metodos que devuelven las coordenadas de cada camara (nodo).
     */
    public int getX(){
        return this.xIndex;
    }
    public int getY(){
        return this.yIndex;
    }
    public int getZ(){
        return this.zIndex;
    }
    /**
     * Nombre del método: getExit
     * 
     * Parámetros:
     * - Ninguno
     * 
     * Descripción:
     * Metodo boolena que nos dice si es la camara es la salida o no.
     */
    public boolean getExit(){
        return isExit;
    }
    /**
     * Nombre del método: isDanger
     * 
     * Parámetros:
     * - Ninguno
     * 
     * Descripción:
     * Metodo boolena que nos dice si es la camara es peligrosa o no.
     */
    public boolean isDanger(){
        return danger;
    }
}