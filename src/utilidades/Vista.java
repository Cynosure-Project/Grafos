package utilidades;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import logica.Grafo;
import utilidades.Validar;

public class Vista {

    Validar v = new Validar();
    Grafo G = new Grafo();

    public void VistaIngreso() {
        String Sl, Sv = v.ValidarString("Ingrese vértices a agregar al grafo (Separados mediante comas)");
        int i, la[], ve[];
        JOptionPane.showMessageDialog(null, Sv, "", 0);
        Sl = v.ValidarString(""" 
                                Ingrese las aristas del grafo y su respectivo peso (Separados mediante comas)
                                Por ejemplo: (1,2,4) (1,3,5)
                                    """);

        JOptionPane.showMessageDialog(null, Sl, "", 0);
        la = CadenaAvector(Sl);
        ve = CadenaAvector(Sv);
        while (!IngresoValido(ve, la))
        {
            JOptionPane.showMessageDialog(null, Sv, "Vertices ingresados", 3);
            Sl = v.ValidarString(""" 
                                Ingrese las aristas del grafo y su respectivo peso (Separados mediante comas)
                                Por ejemplo: (1,2,4) (1,3,5)
                                   """);
        }
        G.CrearMA(ve, la);
    }

    public void VistaMostrar() {

    }

    private boolean IngresoValido(int[] ve, int[] la) {
        boolean b = false;
        int cont = 0;
        List<Integer> veL = Arrays.stream(ve).boxed().collect(Collectors.toList());
        if (la.length % 3 == 0)
        {
            for (int i = 0; i < la.length; i++)
            {
                cont++;

                if (cont % 3 != 0)
                {
                    if (veL.contains(la[i]))
                    {
                        b = true;
                    } else
                    {
                        JOptionPane.showMessageDialog(null, "Ingresaste aristas conectadas a vértices que no existen en el grafo", "Ingresa de nuevo", 0);
                        return false;
                    }
                }

            }
        } else
        {
            JOptionPane.showMessageDialog(null, "Faltaron datos al ingresar las aristas", "Ingresa de nuevo", 0);
        }
        return b;
    }

    private int[] CadenaAvector(String s) {
        int[] ve = new int[s.length()];
        for (int i = 0; i < s.length(); i++)
        {
            ve[i] = s.charAt(i) - '0';

        }
        return ve;
    }

}
