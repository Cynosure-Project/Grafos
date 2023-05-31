
package utilidades;

import java.util.Arrays;
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
        ve = CadenaAvector(Sv);
        
        do {
            Sl = Validar.ValidarString(""" 
                                Ingrese las aristas del grafo y su respectivo peso (Separados mediante comas)
                                Por ejemplo: (1,2,4) (1,3,5)
                                    """);
            JOptionPane.showMessageDialog(null, Sl, "", 1);
            la = CadenaAvector(Sl);
        } while(!Validar.ValidarAristas(ve, la));
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
        G.CrearMI();
    }
    
    public void VistaCrearVectorAdyacencia() {
        G.CrearVectorAdyacencia();
    }
    
    public void VistaMostrarVectorAdyacencia() {
        G.MostrarVectorAdyacencia();
    }
    
  // ----------------------------------------------------------------------------------------------------------------------------------

    private int[] CadenaAvector(String s) {
        int[] ve = new int[s.length()];
        
        for (int i = 0; i < s.length(); i++)
            ve[i] = s.charAt(i) - '0';
        
        return ve;
    }
    
    

}
