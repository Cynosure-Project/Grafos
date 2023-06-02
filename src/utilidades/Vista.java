package utilidades;
import javax.swing.JOptionPane;
import logica.Grafo;

public class Vista {

    Grafo G = new Grafo();
    int[] ve;
    int[] la;

    public void VistaIngreso() {
        int i;
        String Sv, Sl;
        Sv = Validar.ValidarString("Ingrese vértices a agregar al grafo (Separados mediante comas)");
        ve = CadenaAvector(Validar.ValidarVertices(Sv));
        do
        {
            Sl = Validar.ValidarString(""" 
                                Ingrese las aristas del grafo y su respectivo peso (Separados mediante comas)
                                Por ejemplo: (1,2,4) (1,3,5)
                                    """);
            la = CadenaAvector(Sl);
        } while (!Validar.IngresoValido(ve, la));
        G.CrearMA(ve, la);
        G.CrearMI(ve, la);
        G.CrearVectorAdyacencia();
    }

    public void VistaMostrar() {

        GraficoGrafo grafica = new GraficoGrafo(G.getMA());
        grafica.pintarGrafo(G.getMA());
    }

    public void VistaMatrizAdyacencia() {
        G.MostrarMatriz(G.getMA());
    }

    public void VistaMatrizIncidencia() {
        G.MostrarMatriz(G.getMI());
    }

    public void VistaVectorAdyacencia() {
        G.MostrarVectorAdyacencia(ve);
    }
    public void VistaBfs()
    {
        int dato =Validar.ValidarInt("Ingresa el vértice desde donde quieres iniciar el recorrido");
        dato= G.ObtenerIndiceVertice(dato);
        JOptionPane.showMessageDialog(null, G.BFS(dato), "Recorrido BFS", 3);
        
    }

    // ----------------------------------------------------------------------------------------------------------------------------------
    private int[] CadenaAvector(String s) {
        int[] v = new int[s.length()];

        for (int i = 0; i < s.length(); i++)
        {
            v[i] = s.charAt(i) - '0';
        }

        return v;
    }

}
