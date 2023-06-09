package utilidades;
import javax.swing.JOptionPane;
import logica.Grafo;

public class Vista {

    Grafo G = new Grafo();
    int[] ve;
    int[] la;

    public void VistaIngreso() {
        String Sv, Sl;
        Sv = Validar.ValidarV("Ingrese vértices a agregar al grafo (Separados mediante comas)");
        
        ve = CadenaAvector(Validar.ValidarVertices(Sv),false);
        do
        {
            Sl = Validar.ValidarString(""" 
                                Ingrese las aristas del grafo y su respectivo peso (Separados mediante comas)
                                Por ejemplo: (1,2,4) (1,3,5)
                                    """);
            la = CadenaAvector(Sl,true);
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
        G.MostrarMatriz(G.getMA(),true);
    }

    public void VistaMatrizIncidencia() {
        G.MostrarMatriz(G.getMI(), false);
    }

    public void VistaVectorAdyacencia() {
        G.MostrarVectorAdyacencia();
        
    }
     public void VistaDFS() {
        int [] Visitado = new int[ve.length];
        StringBuilder s = new StringBuilder("");
        char letra= Validar.ValidarChar("Ingresa el vértice desde donde quieres iniciar el recorrido");
        int dato=(int)letra;
       
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
        char letra =(char) Validar.ValidarChar("Ingresa el vértice desde donde quieres iniciar el recorrido");
       int dato = G.IndiceVertice((int)letra, ve);
       
       G.DistanciaMinima(dato);

    }
    
    
    public void VistaBfs() {
        int dato; 
        char letra= Validar.ValidarChar("Ingresa el vértice desde donde quieres iniciar el recorrido");
        dato=(int)letra;
        
        if (G.IndiceVertice(dato, ve) != -1)
        {
            dato = G.IndiceVertice(dato, ve);
            G.BFS(dato, ve);

        } else
        {
            JOptionPane.showMessageDialog(null, "Has ingresado un vértice que no está en el grafo", "Recorrido BFS", 0);
        }
    }
    // ----------------------------------------------------------------------------------------------------------------------------------
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
    


}
