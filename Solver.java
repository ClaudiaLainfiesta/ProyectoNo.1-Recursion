import java.util.*;
public class Solver {

    private ArrayList<String> caminos;
    private Maze mapa;
    private Node inicio;

    public Solver() {
        this.caminos = new ArrayList<>();
    }

    public String caminoMasCorto() {
        if (caminos.isEmpty()) {
            return "[]";
        }
        String masCorto = caminos.get(0);
        for (String camino : caminos) {
            if (camino.length() < masCorto.length()) {
                masCorto = camino;
            }
        }

        StringBuilder resultado = new StringBuilder("[");
        for (int i = 0; i < masCorto.length(); i++) {
            resultado.append(masCorto.charAt(i));
            if (i < masCorto.length() - 1) {
                resultado.append(",");
            }
        }
        resultado.append("]");
        
        return resultado.toString();
    }

    public String solve(Maze maze){
        this.mapa = maze;
        this.inicio = mapa.getStartingSpace();
        caminos = new ArrayList<>();
        busquedaSalidas(this.inicio, new ArrayList<>(), "");
        return caminoMasCorto();
    }
    
    private void busquedaSalidas(Node actual, ArrayList<Node> nodosVisitados, String camino){
        if (mapa.isExitSpace(actual.getX(), actual.getY(), actual.getZ())) {
            caminos.add(camino + "");
            return;
        }

        nodosVisitados.add(actual);
    
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
