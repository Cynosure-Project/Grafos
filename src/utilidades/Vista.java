
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
        int i;
        String Sv, Sl;
        
        Sv = Validar.ValidarString("Ingrese vértices a agregar al grafo (Separados mediante comas)");
        JOptionPane.showMessageDialog(null, Sv, "", 1);
        ve = CadenaAvector(Validar.ValidarVertices(Sv));
        
        do {
            Sl = Validar.ValidarString(""" 
                                Ingrese las aristas del grafo y su respectivo peso (Separados mediante comas)
                                Por ejemplo: (1,2,4) (1,3,5)
                                    """);
            JOptionPane.showMessageDialog(null, Sl, "", 1);
            la = CadenaAvector(Sl);
        } while(!Validar.ValidarTripleta(ve, la));
        
        VistaCrearMatrizAdyacencia();
        VistaCrearMatrizIncidencia();
        VistaCrearVectorAdyacencia();
    }
    
    public void VistaMostrarMatrizAdyacencia() {
        G.MostrarMatriz(G.getMA());
    } 
    
    public void VistaMostrarMatrizIncidencia() {
        G.MostrarMatriz(G.getMI());
    } 
    
    public void VistaCrearMatrizAdyacencia() {
        G.CrearMA(ve, la);
    }
    
    public void VistaCrearMatrizIncidencia() {
        G.CrearMI(ve, la);
    }
    
    public void VistaCrearVectorAdyacencia() {
        G.CrearVectorAdyacencia();
    }
    
    public void VistaMostrarVectorAdyacencia() {
        G.MostrarVectorAdyacencia();
    }
    
    public void VistaDFS() {
        int[] Visitado = new int[ve.length];
        StringBuilder s = new StringBuilder("");
        int dato = Validar.ValidarInt("Ingresa el vértice desde donde quieres iniciar el recorrido");
        
        if(G.indiceVertice(dato, ve)!=-1) {
            dato = G.indiceVertice(dato, ve);
            
            G.DFS(dato, Visitado, s, ve);
            JOptionPane.showMessageDialog(null,   s, "Recorrido DFS", 3);
        } else
            JOptionPane.showMessageDialog(null, "Has ingresado un vértice que no está en el grafo", "Recorrido BFS", 0);  
    }
    
    
    
  // ----------------------------------------------------------------------------------------------------------------------------------

    private int[] CadenaAvector(String s) {
        int[] v = new int[s.length()];
        
        for (int i = 0; i < s.length(); i++)
            v[i] = s.charAt(i) - '0';
        
        return v;
    }
    
    

}
