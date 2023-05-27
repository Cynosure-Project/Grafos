
package utilidades;
import java.util.Arrays;
import javax.swing.JOptionPane;
import logica.Grafo;
import utilidades.Validar;


public class Vista {
    Validar v = new Validar();
    Grafo G = new Grafo();
    
    public void VistaIngreso() {
        String Sl, Sv = v.ValidarString("Ingrese vértices a agregar al grafo (Separados mediante comas)");
        int i, la [], ve[];
        JOptionPane.showMessageDialog(null, Sv, "", 0);
        Sl = v.ValidarString(""" 
                                Ingrese las aristas del grafo y su respectivo peso (Separados mediante comas)
                                Por ejemplo: (1,2,4) (1,3,5)
                                    """);                       
        JOptionPane.showMessageDialog(null, Sl, "", 0);
        
        la = CadenaAvector(Sl);
        ve = CadenaAvector(Sv);
        
        IngresoValido(ve,la);
        G.CrearMA(ve, la);     
    }
    
    public void VistaMostrar()
    {
        
    }
    
    
        private boolean IngresoValido(int[] ve, int[] la) {
        boolean b = false;
        int cont = 1;
        if (la.length % 3 == 0)
        {
            for (int i = 0; i < la.length; i++)
            {
               
                
                if (cont % 3 != 0)
                {
                    if (Arrays.asList(ve).contains(la[i]))
                    {
                        b = true;
                    } else
                    {
                        JOptionPane.showMessageDialog(null, "Ingresaste aristas conectadas a vértices que no existen en el grafo", "Ingresa de nuevo", 0);
                        return false;
                    }
                }
                cont++;
            }
        } else
        {
            JOptionPane.showMessageDialog(null, "Faltaron datos al ingresar las aristas", "Ingresa de nuevo", 0);
        }
        return b;
    }

    private int[] CadenaAvector(String s) {
        int[] ve = new int[s.length()];
        
        for (int i = 0; i < s.length(); i++) {
            ve[i] = s.charAt(i) - '0';
        }
        
        return ve;
    }

}
