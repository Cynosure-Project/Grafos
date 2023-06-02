
package logica;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JOptionPane;
import utilidades.Nodo;

public class Grafo {
    
    private int[][] MA;
    private int[][] MI;
    private Nodo[] VA;
    
    public Grafo(){}

    public Nodo[] getVA() {
        return VA;
    }
    
    public Nodo getVA(int i) {
        return VA[i];
    }

    public void setVA(Nodo[] VA) {
        this.VA = VA;
    }
    
    public void setVA(int i, Nodo x) {
        VA[i] = x;
    }

    public int[][] getMA() {
        return MA;
    }
    
    public int getMA(int i, int j) {
        return MA[i][j];
    }

    public void setMA(int[][] MA) {
        this.MA = MA;
    }
    
    public void setMA(int i, int j, int x) {
        MA[i][j] = x;
    }

    public int[][] getMI() {
        return MI;
    }
    
    public int getMI(int i, int j) {
        return MI[i][j];
    }

    public void setMI(int[][] MI) {
        this.MI = MI;
    }
    
    public void setMI(int i, int j, int x) {
        MI[i][j] = x;
    }
    
    public void CrearMA(int V[], int L[]) {
        int k;
        MA = Vertices(V);
        
        for(int i=0 ; i<L.length; i+=3) {
            for(int j=1; j<MA.length; j++) {
                if(L[i] == MA[0][j]) {
                    k = 1;
                   
                    while(L[i+1] != MA[k][0])
                        k++;
                   
                    MA[k][j]=L[i+2];
                    MA[j][k]=L[i+2];
                }
            }
        }
    }
    
    public void MostrarMatriz(int[][] M) {
        int i, j;
        String s = " ";
        
        for (i = 0; i < M.length; i++) {
            for (j = 0; j < M[0].length; j++)
                s += "[ " + String.format("%4d", M[i][j]) + " ]";
            
            s += "\n";
        }
        
        JOptionPane.showMessageDialog(null, s, "Matriz de adyacencia", 3);
    }
    
    private int[][] Vertices(int V[]) {
        int M[][] = new int [V.length+1][V.length+1];
        
        for(int i=1; i<M.length; i++) {
            M[0][i] = V[i-1]; 
            M[i][0] = V[i-1];
        }
       
        return M;
    } 
  
    
    public void CrearMI(int[] ve, int[] la) {
        ArrayList<Integer> lista = Aristas(la);
        int f = MA.length;
        int c = lista.size()+1;
        MI = new int[f][c];
        int x = 2;
        
        for(int i=1; i<MA.length; i++) {
            MI[i][0] = ve[i-1];
            
            for(int j=1; j<MA[0].length; j++) {
                if(j < MI[0].length) {
                    if(MI[0][j]==0 && j<=lista.size()) {
                        MI[0][j] = lista.get(j-1);
                    }
                }
                        
                if(MA[i][j] != 0) {
                    MI[i][lista.indexOf(MA[i][j])+1] = 1;
                }
            }
        }
    }
    
    private ArrayList<Integer> Aristas(int[] la) {
        ArrayList<Integer> lista = new ArrayList<>();
        
        for(int i=2; i<la.length; i+=3) {
            if(lista.indexOf(la[i]) == -1)
                lista.add(la[i]);
        }
        
        Collections.sort(lista);
        
        return lista;
    }
    
       public void CrearVectorAdyacencia() {
        VA = new Nodo[MA.length];
        
        for(int i=1; i<MA.length; i++) {
            for(int j=1; j<MA[0].length; j++) {
                if(MA[i][j] != 0) {
                    Nodo q = new Nodo(MA[i][0]);
                    Nodo x = new Nodo(MA[0][j]);
                    
                    if(VA[i] == null) {
                        VA[i] = q;
                        VA[i].setLiga(x);
                    } else {
                        Nodo p = VA[i].getLiga();
                        
                        while(p.getLiga() != null)
                            p = p.getLiga();
                        
                        p.setLiga(x);
                    }
                }
            }
        }
    }
    
    public void MostrarVectorAdyacencia() {
        String s = "";
        
        for(int i=1; i<VA.length; i++) {
            Nodo p = VA[i].getLiga();
            s += "|| " + VA[i].getDato() + " ||";
            
            while(p != null) {
                s += " -> " + "[ " + p.getDato() + " ]";
                p = p.getLiga();
            }
            
            s += "\n";
        }
        
        JOptionPane.showMessageDialog(null, s);
    }
    
 
    public void DFS(int iv /*posicion de vertice en vector*/, int[] Visitado, StringBuilder s, int[] ve) {
        Nodo p;
        int w;
        Visitado[iv] = 1;
        
        p = VA[iv+1];
        s.append(" |" + p.getDato() + "| ");
        
        while(p != null) {
            w = p.getDato();
            int i = IndiceVertice(w, ve);
            
            if(Visitado[i] == 0)
                DFS(i, Visitado, s, ve);
            
            p = p.getLiga();
        }
    }
    
 
     private ArrayList<Integer> vectorAlista(int[] ve) {
        ArrayList<Integer> lista = new ArrayList<>();
        
        for(int i=0; i<ve.length; i++) {
            lista.add(ve[i]);
        }
        
        return lista;
    }
 
 
    public int IndiceVertice(int v, int[] ve) {
        List<Integer> lista = vectorAlista(ve);
        
        for(int num : lista) {
            if(num == v)
                return lista.indexOf(num);
        }
        
        return -1;
    }
//
//  public String BFS(int dato) {
//    int[] visitado = new int[VA.length];
//
//    Queue<Integer> cola = new LinkedList<>();
//    StringBuilder sb = new StringBuilder();
//
//    visitado[dato] = 1;
//    sb.append(VA[dato].getDato()).append(" ");
//    cola.add(dato);
//
//   while (!cola.isEmpty()) {
//    int actual = cola.poll();
//
//    Nodo p = VA[actual];
//    while (p != null) {
//        int adyacente = IndiceVertice(dato, visitado);
//        if (visitado[adyacente] == 0) {
//            visitado[adyacente] = 1;
//            cola.add(adyacente);
//            sb.append(VA[adyacente].getDato()).append(" ");
//        }
//        p = p.getLiga();
//    }
//}
//
//
//    return sb.toString();
//}

//  public String DistanciaMinima(int origen, int destino) {
//    int[] distancia = new int[VA.length];
//    int[] previo = new int[VA.length];
//    boolean[] visitado = new boolean[VA.length];
//
//    for (int i = 0; i < VA.length; i++) {
//        distancia[i] = Integer.MAX_VALUE;
//        previo[i] = -1;
//        visitado[i] = false;
//    }
//
//    distancia[origen] = 0;
//
//    for (int i = 1; i < VA.length - 1; i++) {
//        int u = obtenerVerticeMinimo(distancia, visitado);
//        visitado[u] = true;
//
//        for (Nodo ad = VA[u]; ad != null; ad = ad.getLiga()) {
//            int v = ObtenerIndiceVertice(ad.getDato());
//            int peso = MA[u][v];
//
//            if (!visitado[v] && peso != 0 && distancia[u] != Integer.MAX_VALUE &&
//                distancia[u] + peso < distancia[v]) {
//                distancia[v] = distancia[u] + peso;
//                previo[v] = u;
//            }
//        }
//    }
//
//    return obtenerRecorrido(origen, destino, previo);
//}
//
//private String obtenerRecorrido(int origen, int destino, int[] previo) {
//    if (previo[destino] == -1) {
//        return "No existe un camino desde " + VA[origen].getDato() +
//               " hasta " + VA[destino].getDato();
//    }
//
//    StringBuilder recorrido = new StringBuilder();
//    recorrido.append("El recorrido de distancia mínima desde ");
//    recorrido.append(VA[origen].getDato());
//    recorrido.append(" hasta ");
//    recorrido.append(VA[destino].getDato());
//    recorrido.append(" es:\n");
//
//    LinkedList<Integer> camino = new LinkedList<>();
//    int actual = destino;
//    camino.addFirst(actual);
//
//    while (previo[actual] != -1) {
//        actual = previo[actual];
//        camino.addFirst(actual);
//    }
//
//    for (int i = 1; i < camino.size(); i++) {
//        recorrido.append(VA[camino.get(i)].getDato());
//
//        if (i != camino.size() - 1) {
//            recorrido.append(" -> ");
//        }
//    }
//
//    return recorrido.toString();
//}
//
//  
//
//private int obtenerVerticeMinimo(int[] distancia, boolean[] visitado) {
//    int minimo = Integer.MAX_VALUE;
//    int indiceMinimo = -1;
//
//    for (int i = 1; i < VA.length; i++) {
//        if (!visitado[i] && distancia[i] <= minimo) {
//            minimo = distancia[i];
//            indiceMinimo = i;
//        }
//    }
//
//    return indiceMinimo;
//}

 
}