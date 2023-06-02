

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
                                  
                                1. Mostrar Grafo
                                2. Matriz de Adyacencia
                                3. Matriz de Incidencia
                                4. Lista de adyacencia
                                5. Crear nuevo grafo
                                6. Recorridos
                                7. Distancia Mínima
                                0. Salir 
                                    """);

            switch(opcion)
            {
                    case 1:
                        v.VistaMostrar();
                        break;
                        
                    case 2:
                        v.VistaMatrizAdyacencia();
                        break;
                        
                    case 3:
                        v.VistaMatrizIncidencia();
                        break;
                    case 4:
                        v.VistaVectorAdyacencia();
                        break;
                    case 5:
                        v.VistaIngreso();
                        v.VistaMostrar();
                        break;
                    case 6:
                        Menu.MenuRecorridos();
                        break;
                    case 7:
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
    
    public static void MenuRecorridos(){
        int opcion;
        

        do 
        {
            opcion = Validar.ValidarInt("""
                               Menú recorridos                                
                                1. DFS
                                2. BFS
                                0. Salir 
                                    """);

            switch(opcion)
            {
                    case 1:
                        
                        break;
                        
                    case 2:
                        v.VistaBfs();
                        
                        break;
                        
                  
                    case 0:
    
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción incorrecta, ingresa otra opción","Opción invalida",0);
                        break;
            }
        }
        while(opcion != 0);
    }
    
    
    
}