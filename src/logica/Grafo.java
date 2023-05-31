
package logica;

import java.util.ArrayList;
import java.util.Collections;
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
        MA = VerticesAmatriz(V);
        
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
    
    private int[][] VerticesAmatriz(int V[]) {
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
    
    public void CrearMI() {
        ArrayList<Integer> lista = numeroAristas();
        int f = MA.length;
        int c = lista.size()+1;
        MI = new int[f][c];
        int x = 0;
        
        for(int i=1; i<MA.length; i++) {
            MI[i][0] = i;
            
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
    
    /*private ArrayList<Integer> numeroAristas(int[][] MA) {
        ArrayList<Integer> lista = new ArrayList<>();
        
        for(int[] fila : MA) {
            for(int num : fila) {
                if(lista.indexOf(num) == -1) {
                    lista.add(num);
                }
            }
        }
        
        Collections.sort(lista);
        
        return lista;
    }*/
    
    private ArrayList<Integer> numeroAristas() {
        ArrayList<Integer> lista = new ArrayList<>();
        
        for (int i=1; i<MA.length; i++) {
            for(int j=1; j<MA[0].length; j++) {
                int num = MA[i][j];
                
                if(lista.indexOf(num)==-1 && num!=0) {
                    lista.add(num);
                }
            }
        }
        
        Collections.sort(lista);
        
        return lista;
    }
    
    private ArrayList<Integer> numeroVertices() {
        ArrayList<Integer> lista = new ArrayList<>();
        
        for (int i=0; i<MA.length; i++) {
            for(int j=1; j<MA[0].length; j++) {
                int num = MA[i][j];
                
                if(lista.indexOf(num)==-1 && num!=0) {
                    lista.add(num);
                }
            }
        }
        
        Collections.sort(lista);
        
        return lista;
    }
    
    public void CrearVectorAdyacencia() {
        VA = new Nodo[MA.length];
        
        for(int i=0; i<MA.length; i++) {
            for(int j=0; j<MA[0].length; j++) {
                if(MA[i][j] != 0) {
                    Nodo x = new Nodo(j);
                    
                    if(VA[i] == null)
                        VA[i] = x;
                    else {
                        Nodo p = VA[i];
                        
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
        int c = 0;
        
        for(int i=0; i<VA.length; i++) {
            Nodo p = VA[i];
            c++;
            s += "|" + c + "|";
            
            while(p != null) {
                s += "->" + p.getDato();
            }
            
            s += "\n";
        }
    }
    
    
}
