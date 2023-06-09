
package utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import logica.Grafo;
import utilidades.Validar;

public class Vista {
    Grafo G = new Grafo();
    int[] ve;
    int[] la;

    public void VistaIngreso() {
        String Sv, Sl;
        
        Sv = Validar.ValidarString("Ingrese vértices a agregar al grafo (Separados mediante comas)");

        ve = cadenaAvector(Validar.ValidarVertices(Sv), false);
        
        do {
            Sl = Validar.ValidarString(""" 
                                Ingrese las aristas del grafo y su respectivo peso (Separados mediante comas)
                                Por ejemplo: (A,B,4) (A,C,5)
                                    """);
            la = cadenaAvector(Sl, true);
        } while(!Validar.ValidarTripleta(ve, la));
        
        G.CrearMA(ve, la);
        G.CrearMI(ve, la);
        G.CrearVectorAdyacencia();
    }
    
    public void VistaMostrarGrafo() {
        GraficoGrafo grafica = new GraficoGrafo(G.getMA());
        grafica.pintarGrafo(G.getMA());
    }
    
    public void VistaMostrarMA() {
        G.MostrarMatriz(G.getMA(), true);
    } 
    
    public void VistaMostrarMI() {
        G.MostrarMatriz(G.getMI(), false);
    }

    public void VistaMostrarVA() {
        G.MostrarVectorAdyacencia();
    }
    
    public void VistaCrearMA() {
        G.CrearMA(ve, la);
    }
    
    public void VistaCrearMI() {
        G.CrearMI(ve, la);
    }
    
    public void VistaCrearVA() {
        G.CrearVectorAdyacencia();
    }
    
    public void VistaDFS() {
        int [] Visitado = new int[ve.length];
        StringBuilder s = new StringBuilder("");
        char letra = Validar.ValidarChar("Ingresa el vértice desde donde quieres iniciar el recorrido");
        int dato = (int)letra;
       
        if(G.indiceVertice(dato, ve) != -1) {
            dato = G.indiceVertice(dato, ve);
            
            G.DFS(dato, Visitado, s, ve);
            JOptionPane.showMessageDialog(null,   s, "Recorrido DFS", 3);
        }else
            JOptionPane.showMessageDialog(null, "Has ingresado un vértice que no está en el grafo", "Recorrido BFS", 0);
    }
    
    public void VistaBFS() {
        int dato; 
        char letra = Validar.ValidarChar("Ingresa el vértice desde donde quieres iniciar el recorrido");
        dato = (int)letra;
        
        if (G.indiceVertice(dato, ve) != -1) {
            dato = G.indiceVertice(dato, ve);
            
            G.BFS(dato, ve);
        } else
            JOptionPane.showMessageDialog(null, "Has ingresado un vértice que no está en el grafo", "Recorrido BFS", 0);
    }
    
    public void VistaDistanciaMinima() {
        char letra = Validar.ValidarChar("Ingresa el vértice desde donde quieres iniciar el recorrido");
        int dato = G.indiceVertice((int)letra, ve);
       
        VistaMostrarDistanciaMinima(G.DistanciaMinima(dato), letra);
    }
    
    public void VistaMostrarDistanciaMinima(int[] v, char l) {
        String s = "";
        
        for (int i=0; i<v.length; i++) {
            s += "| " + (char)G.verticeIndice(ve, i) + " |";
        }
        
        JOptionPane.showMessageDialog(null, "Recorrido distancia minima de: " + l + " es: " + s);
        
        
    }
    
    
    
  // ----------------------------------------------------------------------------------------------------------------------------------

    private int[] cadenaAvector(String s, boolean b) {
        int[] v = new int[s.length()];
        int cont = 0;
        
        for (int i = 0; i < s.length(); i++) {
            cont++;
            
            if(cont%3==0 && b)
                v[i] = s.charAt(i)-'0';
            else
                v[i] = s.charAt(i); 
        }

        return v;
    }
    
    

}
