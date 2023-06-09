
package utilidades;

import javax.swing.JOptionPane;

public class Menu {
    
    private static Vista v = new Vista();
    
    public static void MenuPrincipal() {
        int opcion;
        v.VistaIngreso();
        
        do {
            opcion = Validar.ValidarInt("""
                               Menu principal
                                                                  
                            1. Mostrar Grafo
                            2. Matrices
                            3. Lista de adyacencia
                            4. Crear nuevo grafo
                            5. Recorridos
                            0. Salir 
                                    """);

            switch(opcion) {
                    case 1:
                        v.VistaMostrarGrafo();
                        break;
                        
                    case 2:
                        MenuMatrices();
                        break;
                        
                    case 3:
                        v.VistaMostrarVA();
                        break;
                        
                    case 4:
                        v.VistaIngreso();
                        v.VistaMostrarGrafo();
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
    
    public static void MenuMatrices() {
        int opcion;
        
        do {
            opcion = Validar.ValidarInt("""
                                Menu matrices
                                  
                            1. Matriz de adyacencia
                            2. Matriz de incidencia
                            0. Salir 
                                    """);

            switch(opcion) {
                    case 1:
                        v.VistaMostrarMA();
                        break;
                        
                    case 2:
                        v.VistaMostrarMI();
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
    
    public static void MenuRecorridos() {
        int opcion;
        
        do {
            opcion = Validar.ValidarInt("""
                                Menu recorridos
                                  
                                1. Recorrido DFS
                                2. Recorrido BFS
                                3. Distancia minima
                                0. Salir 
                                    """);

            switch(opcion) {
                    case 1:
                       v.VistaDFS();
                        break;
                        
                    case 2:
                        v.VistaBFS();
                        break;
                        
                    case 3:
                        v.VistaDistanciaMinima();
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
