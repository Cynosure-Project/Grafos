
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
    
public void MostrarMatriz(int[][] M, boolean b) {
        int i, j;
        String s = ""; 

        for (i = 0; i < M.length; i++)
        {
            for (j = 0; j < M[0].length; j++)
            {
                if (j == 0 || (b && i == 0)) 
                {
                    char letra = (char) M[i][j];
                    s += "[ " + String.format("%4s", Character.toString(letra)) + " ]"; 
                } else
                {
                    s += "[ " + String.format("%4d", M[i][j]) + " ]"; 
                }
            }

            s += "\n";
        }
        if(b)      
        JOptionPane.showMessageDialog(null, s, "Matriz Adyacencia", 3);
        else
          JOptionPane.showMessageDialog(null, s, "Matriz Incidencia", 3);  
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
            Nodo p = VA[i];
            if(p!=null)
            {
                p = VA[i].getLiga();
                s += "|| " + (char)VA[i].getDato() + " ||";
                do
                {
                     s += " -> " + "[ " + (char)p.getDato() + " ]";
                     p = p.getLiga();
                }while(p!=null);
            }else
            {
                 s += "|| " +(char)MA[i][0]+ " ||"; 
            }
           
            
            
            s += "\n";
        }
        
        JOptionPane.showMessageDialog(null, s,"Lista de adyacencia",3);
    }
    
 
    public void DFS(int iv /*posicion de vertice en vector*/, int[] Visitado, StringBuilder s, int[] ve) {
        Nodo p;
        int w;
        Visitado[iv] = 1;
        
        p = VA[iv+1];
        s.append(" |").append((char)p.getDato()).append("|--> ");
        
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

  public void BFS(int dato, int []ve) 
  {
    int[] visitado = new int[VA.length];
    String s="";
    Queue<Integer> cola = new LinkedList<>();
    

    visitado[dato] = 1;
    s = s+ "| "+(char)VA[dato+1].getDato()+" |--> ";
    cola.add(dato);

   while (!cola.isEmpty()) {
    int actual = cola.poll();

    Nodo p = VA[actual+1];
    while (p != null) {
        int adyacente = IndiceVertice(p.getDato(), ve);
        if (visitado[adyacente] == 0) 
        {                                           
            visitado[adyacente] = 1;
            cola.add(adyacente);
            s=s+"| "+(char)VA[adyacente+1].getDato()+ " |--> ";
        }
        p = p.getLiga();
    }
   }
   JOptionPane.showMessageDialog(null,s,"BFS",3);
  }
  
  public void DistanciaMinima(int origen) {
    int[] distancias = new int[VA.length];
    int[] indices = new int[VA.length];
    boolean[] visitado = new boolean[VA.length];

    // Inicializar las distancias y padres con valores predeterminados
    for (int i = 0; i < VA.length; i++) {
        distancias[i] = Integer.MAX_VALUE;
        indices[i] = -1;
    }

    // La distancia al nodo de origen es 0
    distancias[origen+1] = 0;

    // Encontrar la distancia mínima
    for (int i = 0; i < VA.length - 1; i++) {
        int Actual = IndiceDM(distancias, visitado);
        visitado[Actual] = true;

        for (int j = 1; j < VA.length; j++) {
            if (!visitado[j] && MA[Actual][j] != 0 && distancias[Actual] != Integer.MAX_VALUE
                    && distancias[Actual] + MA[Actual][j] < distancias[j]) {
                distancias[j] = distancias[Actual] + MA[Actual][j];
                indices[j] = Actual;
            }
        }
    }

    // Imprimir el recorrido completo
    MostrarDM(origen, distancias, indices);
}

private void MostrarDM(int origen, int[] distancias, int[] indices) {
    StringBuilder resultado = new StringBuilder();
    List<Integer> verticesRecorridos = new ArrayList<>();

    for (int i = 1; i < VA.length; i++) {
        if (distancias[i] != Integer.MAX_VALUE) {
            resultado.append("Recorrido desde ")
                    .append((char) VA[origen + 1].getDato())
                    .append(" hasta ")
                    .append((char) VA[i].getDato())
                    .append(": ");

            List<Character> camino = Camino(indices, i);
            resultado.append(camino);

            resultado.append(", Distancia: ")
                    .append(distancias[i])
                    .append("\n");

            verticesRecorridos.add(i);
        } else {
            resultado.append("No hay camino desde ")
                    .append((char) VA[origen + 1].getDato())
                    .append(" hasta ")
                    .append((char) VA[i].getDato())
                    .append("\n");
        }
    }

    // Imprimir el recorrido completo
    
    
    
    JOptionPane.showMessageDialog(null, resultado.toString(), "Recorrido Completo", 3);
}
private int[] CadenaAvector(String s, boolean b) {
        int[] v = new int[s.length()];
        int cont=0;
        for (int i = 0; i < s.length(); i++)
        {
            cont++;
            if(cont%3==0&&b)
            {
                v[i] = s.charAt(i)-'0';
            }else
            {
                v[i] = s.charAt(i);
            }
            
        }

        return v;
    }
    

private List<Character> Camino(int[] indices, int Destino) {
    List<Character> camino = new ArrayList<>();
    int Actual = Destino;

    while (Actual != -1) {
        camino.add(0, (char) VA[Actual].getDato());
        Actual = indices[Actual];
    }

    return camino;
}



private int IndiceDM(int[] distancias, boolean[] visitado) {
    int minimaDistancia = Integer.MAX_VALUE;
    int nodoMinimo = -1;

    for (int i = 1; i < VA.length; i++) {
        if (!visitado[i] && distancias[i] < minimaDistancia) {
            minimaDistancia = distancias[i];
            nodoMinimo = i;
        }
    }

    return nodoMinimo;
}



}