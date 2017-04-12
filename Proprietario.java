import java.util.ArrayList;
import java.util.Scanner;

public class Proprietario {
	private String nome;
	private String cognome;
	private ArrayList<Automobile> automobili;
	private int conto;


	public Proprietario(String nome,String cognome,int conto)
	{
		this.nome=nome;
		this.cognome=cognome;
		this.conto=conto;
		this.automobili= new ArrayList<Automobile>();
		
	}
	public void modificaParcoAuto(Automobile automobile,int indicevend,ArrayList<Proprietario> proprietari)
	{	
		if(trovaproprietario(automobile,proprietari)==indicevend)
		{	
			automobile.acquista();
			this.automobili.add(automobile);
			proprietari.get(indicevend).automobili.remove(automobile);
		}
		else
		{
			System.out.println("si sta cercando di vendere una macchina di un proprietario diverso");
		}
		
	
	}
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public ArrayList<Automobile> getAutomobili() {
		return automobili;
	}
	public void setAutomobili(Automobile automobili) {
		this.automobili.add(automobili);
	}
	public int aggiustaConto(int prezzo)
	{
		if(this.conto>=prezzo )
		{
			this.conto=this.conto-prezzo;
			return this.conto;
		}
		return -1;
	}
	public void aggiustaContovend(int prezzo)
	{	
			this.conto=this.conto+prezzo;		
	}	
	public void aumentaFondi(int aumento)
	{
		this.conto=this.conto+aumento;
	}
	public int getConto() {
		return conto;
	}
	public static int trovaproprietario(Automobile automobile,ArrayList<Proprietario> proprietari)
	{
		String targa=automobile.getTarga();
		int indiceprop=Concessionario.trovavenditore(proprietari,targa);
		return indiceprop;
		
	}
}
