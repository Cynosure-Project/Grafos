
package logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
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
        MA = verticesAmatriz(V);
        
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
    
    public void MostrarMatriz(int[][] M, boolean b) {
        int i, j;
        String s = ""; 

        for (i = 0; i < M.length; i++) {
            for (j = 0; j < M[0].length; j++) {
                if (j == 0 || (b && i == 0)) 
                {
                    char letra = (char) M[i][j];
                    s += "[ " + String.format("%4s", Character.toString(letra)) + " ]"; 
                } else
                    s += "[ " + String.format("%4d", M[i][j]) + " ]"; 
            }

            s += "\n";
        }
        
        if(b)      
            JOptionPane.showMessageDialog(null, s, "Matriz Adyacencia", 3);
        else
            JOptionPane.showMessageDialog(null, s, "Matriz Incidencia", 3);  
    }
    
    private int[][] verticesAmatriz(int V[]) {
        int M[][] = new int [V.length+1][V.length+1];
        
        for(int i=1; i<M.length; i++) {
            M[0][i] = V[i-1]; 
            M[i][0] = V[i-1];
        }
       
        return M;
    }
    
    /*public int[][] CrearMI() {
        int i = 0;
        ArrayList<Integer> lista = numeroAristas();
        int f = MA.length;
        int c = lista.size();
        int[][] MI = new int[f][c];
        
        for(int[] fila : MA) {
            for(int num : lista)
                MI[i][lista.indexOf(num)] += 1;
            
            i++;
        }
        
        return MI;
    }*/

    public void CrearMI(int[] ve, int[] la) {
        ArrayList<Integer> lista = Aristas(la);
        int f = MA.length;
        int c = lista.size()+1;
        MI = new int[f][c];
        
        for(int i=1; i<MA.length; i++) {
            MI[i][0] = ve[i-1];
            
            for(int j=1; j<MA[0].length; j++) {
                if(j < MI[0].length) {
                    if(MI[0][j]==0 && j<=lista.size())
                        MI[0][j] = lista.get(j-1);
                }
                        
                if(MA[i][j] != 0)
                    MI[i][lista.indexOf(MA[i][j])+1] = 1;
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
            
            if(VA[i] != null) {
                Nodo p = VA[i].getLiga();
                s += "|| " + (char)VA[i].getDato() + " ||";
            
                do {
                    s += " -> " + "[ " + (char)p.getDato() + " ]";
                    p = p.getLiga();
                } while(p != null);
            } else
                s += "|| " + (char)MA[i][0] + " ||";
            
            s += "\n";
        }
        
        JOptionPane.showMessageDialog(null, s);
    }
    
    public void DFS(int iv /*posicion de vertice en vector*/, int[] Visitado, StringBuilder s, int[] ve) {
        Nodo p;
        int w;
        Visitado[iv] = 1;
        
        p = VA[iv+1];
        s.append(" |").append((char)p.getDato()).append("|--> ");
        
        while(p != null) {
            w = p.getDato();
            int i = indiceVertice(w, ve);
            
            if(Visitado[i] == 0)
                DFS(i, Visitado, s, ve);
            
            p = p.getLiga();
        }
    }
    
    public int indiceVertice(int v, int[] ve) {
        ArrayList<Integer> lista = vectorAlista(ve);
        
        for(int num : lista) {
            if(num == v)
                return lista.indexOf(num);
        }
        
        return -1;
    }
    
    private ArrayList<Integer> vectorAlista(int[] ve) {
        ArrayList<Integer> lista = new ArrayList<>();
        
        for(int i=0; i<ve.length; i++)
            lista.add(ve[i]);
        
        return lista;
    }
    
    public void BFS(int dato, int []ve) {
        int[] visitado = new int[VA.length];
        String s = "";
        Queue<Integer> cola = new LinkedList<>();
        visitado[dato] = 1;
        s += "| " + (char)VA[dato+1].getDato() + " |--> ";
        cola.add(dato);

        while (!cola.isEmpty()) {
            int actual = cola.poll();

            Nodo p = VA[actual+1];

            while (p != null) {
                int adyacente = indiceVertice(p.getDato(), ve);

                if (visitado[adyacente] == 0) {                                           
                    visitado[adyacente] = 1;
                    cola.add(adyacente);
                    s=s+"| "+(char)VA[adyacente+1].getDato()+ " |--> ";
                }

                p = p.getLiga();
            }
        }
        
        JOptionPane.showMessageDialog(null,s,"BFS",3);
    }
    
    /*public String DistanciaMinima() {
        int n = MA.length-1;
        int D[][] = new int[MA.length-1][MA[0].length-1];
        
        for(int i=1; i<MA.length; i++) {
            for (int j=1; j<MA[0].length; j++) {
                D[i][j] = MA[i][j];
            }
        }

        String enlaces[][] = new String [n][n];
        String[][] aux_enlaces = new String[MA.length-1][MA.length-1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                enlaces[i][j] = "";
                aux_enlaces[i][j] = "";
            }
        }
        
        String enl_rec = "";
        
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int aux = D[i][j];
                    int aux2 = D[i][k];
                    int aux3 = D[k][j];
                    int aux4 = aux2+aux3;
                    int res = Math.min(aux,aux4);

                    if(aux != aux4) {
                        if(res == aux4) {
                            enl_rec = "";
                            aux_enlaces[i][j] = k + "";
                            enlaces[i][j] = enlaces(i,k,aux_enlaces,enl_rec) + (k+1);
                        }
                    }

                    D[i][j]= res;
                }
            }
        }

        String cadena = "";
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cadena += D[i][j] + " ";
            }
           
            cadena += "\n";
        }

        String enlacesres = "";
        
        for (int i = 0; i <n; i++) {
            for (int j = 0; j < n; j++) {
                if(i != j) {
                    if(enlaces[i][j].equals("") == true) {
                        enlacesres += " De ( " + (i+1) + " a " + (j+1) + " ) = " + "( " + (i+1) + " , " + (j+1) + " )" + "\n";
                    } else
                        enlacesres += " De ( " + (i+1) + " a " + (j+1) + " ) = ( " + (i+1) + " , " + enlaces[i][j] + " , " + (j+1) + ")\n";
                }
            }
        }

        return "las distancias minimas entre nodos son: \n" + cadena + "\nlos caminos minimos entre nodosson:\n" + enlacesres;
    }
 
    public String enlaces(int i, int k, String[][] aux_enlaces, String enl_rec) {
        if(aux_enlaces[i][k].equals("") == true) {
            return "";
        } else {
            enl_rec += enlaces(i,Integer.parseInt(aux_enlaces[i][k].toString()),aux_enlaces,enl_rec) + (Integer.parseInt(aux_enlaces[i][k].toString())+1) + " , ";
            
            return enl_rec;
        }
    }*/
    
    public int[] DistanciaMinima(int origen) {
        int numVertices = VA.length-1;
        int[] distancias = new int[numVertices];
        boolean[] visitados = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            distancias[i] = Integer.MAX_VALUE;
            visitados[i] = false;
        }

        distancias[origen] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int verticeMinDistancia = obtenerMinimaDistancia(distancias, visitados);
            visitados[verticeMinDistancia] = true;

            for (int j = 0; j < numVertices; j++) {
                if (!visitados[j] && MA[verticeMinDistancia][j] != 0 &&
                        distancias[verticeMinDistancia] != Integer.MAX_VALUE &&
                        distancias[verticeMinDistancia] + MA[verticeMinDistancia][j] < distancias[j])
                    distancias[j] = distancias[verticeMinDistancia] + MA[verticeMinDistancia][j];
            }
        }

        return distancias;
    }

    private int obtenerMinimaDistancia(int[] distancias, boolean[] visitados) {
        int numVertices = VA.length-1;
        int minimaDistancia = Integer.MAX_VALUE;
        int indiceMinimaDistancia = -1;

        for (int i = 0; i < numVertices; i++) {
            if (!visitados[i] && distancias[i] <= minimaDistancia) {
                minimaDistancia = distancias[i];
                indiceMinimaDistancia = i;
            }
        }

        return indiceMinimaDistancia;
    }
    
    public int verticeIndice(int[] ve, int n) {
        ArrayList<Integer> lista = vectorAlista(ve);

        return lista.get(n);
    }
    
   
    
}
