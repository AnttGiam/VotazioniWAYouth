import java.util.ArrayList;
import java.util.Random;

public class ListaCodici {
    private int Votanti;
    private ArrayList<Integer>  codici;
    
    public int getVotanti(){ return Votanti;}
    public void setVotanti(int Votanti){ this.Votanti=Votanti;}
    public ArrayList<Integer>  getCodici(){return codici;}
    //public void setCodici(int[] codici){this.codici=codici;}
    
 
    public ListaCodici() {
		super();
		codici=new ArrayList<Integer>();
	}
    
	public void generaCodici()
    {
        Random random=new Random();
        int j,codice;
        for(int i=0;i<Votanti;i++)
        {
            codice = random.nextInt(99999);
            if(i>0)
            {
                for(j=0;j<i;j++)
                {
                    if(codice==codici.get(j))
                    {
                        i--;
                        break;
                    }
                }
                if(j==i)
                    codici.add(codice);
            }
            else
                codici.add(codice);
        }
    }
}
