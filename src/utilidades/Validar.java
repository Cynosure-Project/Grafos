package utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
public class Validar {
    
    public static String ValidarString(String t) {
        String s = "";
        
        while (s.equals("")) {
            s = JOptionPane.showInputDialog(t);
            
            if (!s.matches("^[0-9,(){}\\[\\] ]+$")) {
                s = "";
                
                JOptionPane.showMessageDialog(null, "Por favor, ingresa un dato válido", "Validar", 0);
            }
        }
        
        s = s.replaceAll ("[,() {}]",  "");
        
        return s;
    }
    
    public static int ValidarInt(String t) {
        int i = 0;
        boolean b = false;
        
        while (!b) {
            try {
                i = Integer.parseInt(JOptionPane.showInputDialog(null,t, "Ingreso",3));
                b = true;
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingresa un dato válido", "Validación", 0);
            }
        }
        
        return i;
    }
    
    public static boolean ValidarAristas(int[] ve, int[] la) {
        int cont = 0;
        List<Integer> veL = Arrays.stream(ve).boxed().collect(Collectors.toList());
        
        if (la.length % 3 == 0) {
            for (int i = 0; i < la.length; i++) {
                cont++;

                if (cont % 3 != 0) {
                    if (!veL.contains(la[i])) {
                        JOptionPane.showMessageDialog(null, "Ingresaste aristas conectadas a vértices que no existen en el grafo", "Ingresa de nuevo", 0);
                        
                        return false;
                    }
                }
            }
        } else
            JOptionPane.showMessageDialog(null, "Faltaron datos al ingresar las aristas", "Ingresa de nuevo", 0);
   
        return true;
    }
    
    public static String ValidarVertices(int[] ve) {
        ArrayList<Integer> veL = new ArrayList<>();
        
        for(int i=0; i<ve.length; i++) {
            if(!veL.contains(ve[i])) {
                veL.add(ve[i]);
            }
        }
        
        String s = veL.toString();
        s = s.replaceAll ("[,\\[\\]]",  "");
        
        return s;
    }
     
}