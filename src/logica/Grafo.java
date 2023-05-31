
package logica;

import javax.swing.JOptionPane;


public class Grafo {
    
    private int [][]MA;

    public int[][] getMA() {
        return MA;
    }

    public void setMA(int[][] MA) {
        this.MA = MA;
    }
    
    public int [][] CrearMA(int V[], int L[]) 
    {
        int k;
        MA= Vertices(V);
        for(int i=0 ; i<L.length;i+=3)
        {
            for(int j=1; j<MA.length; j++)
            {
                if(L[i]==MA[0][j])
                {
                   k=1;
                   while(L[i+1]!=MA[k][0])
                   {
                       k++;
                   }
                   MA[k][j]=L[i+2];
                   MA[j][k]=L[i+2];
                }
            }
        }
        return MA;
        
    }
    
    public void Mostrar(int[][] M) {
        int i, j;
        String s = " ";
        for (i = 0; i < M.length; i++)
        {
            for (j = 0; j < M[0].length; j++)
            {
                s = s + "[ " + String.format("%4d", M[i][j]) + " ]";
            }
            s = s + "\n";
        }
        JOptionPane.showMessageDialog(null, s, "Matriz de adyacencia", 3);
    }
    
    public int[][] Vertices(int V[])
    {
        this.MA=new int[V.length+1][V.length+1];
        for(int i=1; i< MA.length;i++)
        {
            MA[0][i]=V[i-1]; 
             MA[i][0]=V[i-1];
        }
       
        
        return MA;
    }
}
