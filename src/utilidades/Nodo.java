
package utilidades;


public class Nodo {
    //Atributos
    private int Dato;
    private Nodo Liga;
    
    // Métodos
    public Nodo() {
        Dato = 0;
        Liga = null;
    }
    public Nodo(int d) {
        Dato = d;
        Liga = null;
    }
    public int getDato() {
        return Dato;
    }
    public void setDato(int Dato) {
        this.Dato = Dato;
    }

    public Nodo getLiga() {
        return Liga;
    }
    public void setLiga(Nodo Liga) {
        this.Liga = Liga;
    } 
}
