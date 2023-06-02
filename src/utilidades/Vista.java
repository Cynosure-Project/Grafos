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
     public void VistaDFS() {
        int[] Visitado = new int[ve.length];
        StringBuilder s = new StringBuilder("");
        
        int dato = Validar.ValidarInt("Ingresa el vértice desde donde quieres iniciar el recorrido");
        if(G.IndiceVertice(dato, ve)!=-1)
        {
            dato=G.IndiceVertice(dato, ve);
            G.DFS(dato, Visitado, s, ve);
            JOptionPane.showMessageDialog(null,   s, "Recorrido DFS", 3);
        }else
        {
            JOptionPane.showMessageDialog(null, "Has ingresado un vértice que no está en el grafo", "Recorrido BFS", 0);
        }
        
        
        
    }
    
    public void VistaDM()
    {
        int O = Validar.ValidarInt("Ingresa el vértice desde donde quieres iniciar el recorrido");
        int D =Validar.ValidarInt("Ingresa el vértice desde donde quieres iniciar el recorrido");
        //String s=G.DistanciaMinima(O, D);
        //JOptionPane.showMessageDialog(null,   s, "Distancia", 3);
    }
    
    
//   public void VistaBfs() {
//    int dato = Validar.ValidarInt("Ingresa el vértice desde donde quieres iniciar el recorrido");
//
//    if (G.ObtenerIndiceVertice(dato) != -1) 
//    {
//        dato = G.ObtenerIndiceVertice(dato);
//        String recorrido = G.BFS(dato);
//        JOptionPane.showMessageDialog(null,   recorrido, "Recorrido BFS", 3);
//    } else 
//    {
//        JOptionPane.showMessageDialog(null, "Has ingresado un vértice que no está en el grafo", "Recorrido BFS", 0);
//    }
//}
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
