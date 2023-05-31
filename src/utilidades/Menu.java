
package utilidades;

import javax.swing.JOptionPane;


public class Menu {
    
    private static Vista v = new Vista();
    
    public static void MenuPrincipal() {
        int opcion;
        v.VistaIngreso();
        v.VistaCrearMatrizAdyacencia();
        v.VistaCrearMatrizIncidencia();
        v.VistaCrearVectorAdyacencia();
        
        do 
        {
            opcion = Validar.ValidarInt("""
                               Menu principal
                                  
                                1. Mostrar matriz adyacencia
                                2. Mostrar matriz incidencia
                                3. Mostrar vector adyacencia
                                0. Salir 
                                    """);

            switch(opcion)
            {
                    case 1:
                        v.VistaMostrarMatrizAdyacencia();
                        break;
                        
                    case 2:
                        v.VistaMostrarMatrizIncidencia();
                        break;
                        
                    case 3:
                        v.VistaMostrarVectorAdyacencia();
                        break;
                       
                    case 0:
                        JOptionPane.showMessageDialog(null, "Gracias por probar nuestro proyecto","Salir",3);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción incorrecta, ingresa otra opción","Opción invalida",0);
                        break;
            }
        }
        while(opcion != 0);
    }
    
    
}
