
package utilidades;

import javax.swing.JOptionPane;


public class Menu {
    
    private static Vista v = new Vista();
    
    public static void MenuPrincipal() {
        int opcion;
        v.VistaIngreso();
        
        do 
        {
            opcion = Validar.ValidarInt("""
                               Menu principal
                                  
                                1. Mostrar matriz adyacencia
                                2. Mostrar matriz incidencia
                                3. Mostrar vector adyacencia
                                4. Ingresar nuevo grafo
                                5. Recorridos
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
                        
                    case 4:
                        v.VistaIngreso();
                        break;
                        
                    case 5:
                        MenuRecorridos();
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
    
    public static void MenuRecorridos() {
        int opcion;
        
        do 
        {
            opcion = Validar.ValidarInt("""
                                Menu recorridos
                                  
                                1. Recorrido DFS
                                2. Recorrido 
                                3. Distancia minima
                                0. Salir 
                                    """);

            switch(opcion)
            {
                    case 1:
                       v.VistaDFS();
                        break;
                        
                    case 2:
                        
                        break;
                        
                    case 3:
                        
                        break;
                       
                    case 0:
                        JOptionPane.showMessageDialog(null, "Sera devuelto al menu principal","Salir",3);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción incorrecta, ingresa otra opción","Opción invalida",0);
                        break;
            }
        }
        while(opcion != 0);
    }
    
    
}
