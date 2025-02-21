//Clase importada.
import java.util.*;

public class Solver {

    //Decalarion de lista de todos los caminos posibles (caminos), variable que representa la cueva (mapa) y la camara de entrada (inicio).
    private ArrayList<String> caminos;
    private Maze mapa;
    private Node inicio;
    
    /**
     * Nombre del método: Solver
     * 
     * Parámetros:
     * - Ninguno.
     * 
     * Descripción:
     * Decalarion de contructor que inicializa el ArrayList caminos.
     * */
    public Solver() {
        this.caminos = new ArrayList<>();
    }
    /**
     * Nombre del método: caminoMasCorto
     * 
     * Parámetros:
     * - Ninguno.
     * 
     * Descripción:
     * Metodo que obtiene el camino mas corto encontrado dentro del ArrayList caminos
     */
    public String caminoMasCorto() {
        //En el caso de que no existan caminos devuelve un String "vacio".
        if (caminos.isEmpty()) {
            return "[]";
        }
        //Se escoge el primer camino encontrado para compararlos con los demas en las iteraciones del for y asiganandose el que vaya siendo menos al anterior.
        String masCorto = caminos.get(0);
        for (String camino : caminos) {
            if (camino.length() < masCorto.length()) {
                masCorto = camino;
            }
        }

        //Al tener el camino mas corto se le aplico el formato que pide el proeycto que es "[N,W,S,E,U,D]", es decir, iniciando con corchetes y separados por comas.
        StringBuilder resultado = new StringBuilder("[");
        for (int i = 0; i < masCorto.length(); i++) {
            resultado.append(masCorto.charAt(i));
            if (i < masCorto.length() - 1) {
                resultado.append(",");
            }
        }
        resultado.append("]");

        //Retorna el camino mas corto con el formato ya integrado.
        return resultado.toString();
    }

     /**
     * Nombre del método: solve
     * 
     * Parámetros:
     * - Maze maze: La cueva que se quiere resolver.
     * 
     * Descripción:
     * Metodo principal que manda a llamar busquedaSalidas e inciializa el mapa.
     */
    public String solve(Maze maze){
        this.mapa = maze;
        this.inicio = mapa.getStartingSpace();
        caminos = new ArrayList<>();
        busquedaSalidas(this.inicio, new ArrayList<>(), "");
        return caminoMasCorto();
    }

    /**
     * Nombre del método: busquedaSalidas
     * 
     * Parámetros:
     * - Node actual: El nodo en el que se encuentra actualmente el buscador.
     * - ArrayList<Node> nodosVisitados: Lista de nodos que ya han sido visitados.
     * - String camino: Cadena que representa el camino recorrido hasta el nodo actual.
     * 
     * Descripción:
     * Metodo recursivo que encuentra las direcciones para encontrar el camino hacia la salida.
     */
    private void busquedaSalidas(Node actual, ArrayList<Node> nodosVisitados, String camino){
        //If que se asegura si ya se encontro la salida y se termina el camino encontrado
        if (mapa.isExitSpace(actual.getX(), actual.getY(), actual.getZ())) {
            caminos.add(camino);
            return;
        }
        //Agrega las camaras(nodos) que ya han sido visitadas para no volver a pasar por ellas.
        nodosVisitados.add(actual);
        
        //If que verifica que direccion es posible y agraga al camino el String del movimiento correspondiente.
        if (mapa.moveNorth(actual) != actual && !nodosVisitados.contains(mapa.moveNorth(actual)) && !mapa.moveNorth(actual).isDanger()) {
            busquedaSalidas(mapa.moveNorth(actual), nodosVisitados, camino + "N");
        }
        if (mapa.moveSouth(actual) != actual && !nodosVisitados.contains(mapa.moveSouth(actual)) && !mapa.moveSouth(actual).isDanger()) {
            busquedaSalidas(mapa.moveSouth(actual), nodosVisitados, camino + "S");
        }
        if (mapa.moveEast(actual) != actual && !nodosVisitados.contains(mapa.moveEast(actual)) && !mapa.moveEast(actual).isDanger()) {
            busquedaSalidas(mapa.moveEast(actual), nodosVisitados, camino + "E");
        }
        if (mapa.moveWest(actual) != actual && !nodosVisitados.contains(mapa.moveWest(actual)) && !mapa.moveWest(actual).isDanger()) {
            busquedaSalidas(mapa.moveWest(actual), nodosVisitados, camino + "W");
        }
        if (mapa.moveUp(actual) != actual && !nodosVisitados.contains(mapa.moveUp(actual)) && !mapa.moveUp(actual).isDanger()) {
            busquedaSalidas(mapa.moveUp(actual), nodosVisitados, camino + "U");
        }
        if (mapa.moveDown(actual) != actual && !nodosVisitados.contains(mapa.moveDown(actual)) && !mapa.moveDown(actual).isDanger()) {
            busquedaSalidas(mapa.moveDown(actual), nodosVisitados, camino + "D");
        }
    }
    
}
