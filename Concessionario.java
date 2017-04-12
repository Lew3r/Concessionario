import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Concessionario {

	public static void main(String[] args) {
		int ripetimenu=0;
		Scanner in = new Scanner(System.in);
		ArrayList<Proprietario> proprietari =new ArrayList<Proprietario>();
		Officina officina =new Officina();
		int i=1,menu=0;
		do
		{	
			ripetimenu=0;
			try{
		
				System.out.println("1)carica da file OtherNumber)vai al menu");
				menu= in.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("Input non valido");	
				in.nextLine();
				ripetimenu=1;
			}
		}
		while(ripetimenu!=0);
		if(menu==1)
		{
			caricafile(officina,proprietari);
		}
		while(i>0&&i<9)
		{	
			System.out.println("1)ins.proprietario 2)ins.macchina 3)compravendita 4)visionedati");
			System.out.println("5)aumenta il conto 6)GeneraPezzi 7)cambia pezzo 8)salva Other)esci ");
			i= in.nextInt();
			switch(i)
			{				
				case 1:inserisciproprietari(in,proprietari);								
				break;		
				case 2: inseriscimacchina(in,proprietari);
				break;
				case 3: compravendita(in,proprietari);
				break;
				case 4: visionedati(in,proprietari,officina);
					break;
				case 5: aumentaconto(in,proprietari);
					break;
				case 6: generapezzi(in,officina);
					break;
				case 7:cambiapezzo(in,officina,proprietari);
					break;
				case 8:salvasufile(in,officina,proprietari);
					break;
			}
				
			
				
		}
		
			
	}
	public static void caricafile(Officina officina, ArrayList<Proprietario> proprietari)
	{	
		caricaProprietari(proprietari);
		caricaAutomobili(proprietari);
		caricaOfficina(officina);		
	}
	
	public static void caricaOfficina(Officina officina)
	{	
		
			try
			{ 
				String pezzi,percentuale,costointervento;
				FileReader f;
				f=new FileReader("officina.txt");
				BufferedReader b;
			  	b=new BufferedReader(f);
			  	while(b.readLine()!=null)					
				{				
					pezzi=b.readLine();
					percentuale=b.readLine();
					costointervento=b.readLine();
					officina.aggiungipezzo(pezzi,Integer.parseInt(percentuale),Integer.parseInt(costointervento));
				}					
			}
			catch(Exception e)
			{
				System.out.println("Impossibile caricare da file");	
			}		
	}
	public static void caricaProprietari( ArrayList<Proprietario> proprietari)
	{
		try
		{ 
			String nome,cognome,conto;
			FileReader f;
			f=new FileReader("proprietario.txt");
			BufferedReader b;
		  	b=new BufferedReader(f);
			while(b.readLine()!=null)					
		  	{				
		 		nome=b.readLine();
		  		cognome=b.readLine();
		  		conto=b.readLine();
		  		Proprietario proprietario=new Proprietario(nome,cognome,Integer.parseInt(conto));
		  		proprietari.add(proprietario);
		  	}					
		  
		}
		catch(Exception e)
		{
			System.out.println("Impossibile caricare da file");	
		}		
	
	}
	public static void caricaAutomobili(ArrayList<Proprietario> proprietari)
	{
		try
		{ 
			String num,targa,modello,prezzo;
			FileReader f;
			int var=0;
			f=new FileReader("macchine.txt");
			BufferedReader b;
		  	b=new BufferedReader(f);
		  	num=b.readLine();
			while(num!=null)					
		  	{	
				if(var!=0)
		  			num=b.readLine();
				if(num!=null)
				{
					int numInt=Integer.parseInt(num);
					targa=b.readLine();
					modello=b.readLine();
					prezzo=b.readLine();
					Automobile automobile=new Automobile(targa,modello,Integer.parseInt(prezzo));
					proprietari.get(numInt).getAutomobili().add(automobile);
					var=1;
				}
		  	}					
		  
		}
		catch(Exception e)
		{
			System.out.println("Impossibile caricare da file");	
		}		
	
		
	}
	public static void salvasufile(Scanner in,Officina officina,ArrayList<Proprietario> proprietari)
	{
		System.out.println("vuoi salvare tutti i dati 1) si other no ");	
		int salva=in.nextInt();
		if(salva==1)
			salvatutto(in,officina,proprietari);
		else
			salva(in,officina,proprietari);
	}
	public static void salvatutto(Scanner in,Officina officina,ArrayList<Proprietario> proprietari)
	{
		salvaproprietario(in,proprietari);
		salvamacchine(in,proprietari);
		salvaofficina(in,officina);
	}
		public static void salvaproprietario(Scanner in,ArrayList<Proprietario> proprietari)
		{
			try {
				PrintWriter wprop = new PrintWriter(new FileWriter("proprietario.txt"));
		    
		    	    
		    for(int i=0;i<proprietari.size();i++)
		    {
		    	 wprop.println(i);
		    	 wprop.println(proprietari.get(i).getNome());
		    	 wprop.println(proprietari.get(i).getCognome());
		    	 wprop.println(proprietari.get(i).getConto()); 	    	 
		    }
		   	
		    wprop.close();
		    System.out.println("Proprietari salvati");	
			}
			catch(IOException e)
			{
				System.out.println("Imposssibile scrivere su file");	
			}
		}
		public static void salvamacchine(Scanner in,ArrayList<Proprietario> proprietari)
		{
			try {
				PrintWriter wprop = new PrintWriter(new FileWriter("macchine.txt"));
		    
		    	    
		    for(int i=0;i<proprietari.size();i++)
		    {
		    	for(int j=0;j<proprietari.get(i).getAutomobili().size();j++)
		    	{	
		    		wprop.println(i);
		    	 	wprop.println(proprietari.get(i).getAutomobili().get(j).getTarga());
		    	 	wprop.println(proprietari.get(i).getAutomobili().get(j).getModello());
		    	 	wprop.println(proprietari.get(i).getAutomobili().get(j).getPrezzo());	    	 
		    	}
		    }
		    wprop.close();
		    System.out.println("macchine salvate");	
			}
			catch(IOException e)
			{
				System.out.println("Impossibile scrivere su file");	
			}
		}
		public static void salvaofficina(Scanner in,Officina officina)
		{
			try {
				PrintWriter wprop = new PrintWriter(new FileWriter("officina.txt"));
		    
		    	    
		    for(int i=0;i<officina.getPezzi().size();i++)
		    {		    	
		    		wprop.println(i);
		    	 	wprop.println(officina.getPezzi().get(i));
		    	 	wprop.println(officina.getPercentuale().get(i));
		    	 	wprop.println(officina.getCostointervento().get(i));	    	 
		    	
		    }
		    wprop.close();
		    System.out.println("pezzimacchina salvati");	
		    
			}
			catch(IOException e)
			{
				System.out.println("Imposssibile scrivere su file");	
			}
		}
		
		
		
		
	public static void salva(Scanner in,Officina officina,ArrayList<Proprietario> proprietari)
	{	int salva;
		System.out.println("Vuoi salvare i proprietari e le macchine? 1)si other)no ");
		salva=in.nextInt();
		if(salva==1)
		{
			salvaproprietario(in,proprietari);
			salvamacchine(in,proprietari);
		}
		System.out.println("Vuoi salvare i pezzi di ricambio? 1)si other)no ");
		salva=in.nextInt();
		if(salva==1)
			salvaofficina(in,officina);			
	}
	
	public static void generapezzi(Scanner in,Officina officina)
	{	
		System.out.println("Inserisci nome pezzo macchina ");	
		String pezzo=in.next();
		Integer integer;
		try{
				System.out.println("inserisci il valore in percentuale di inalzamento valore macchina ");	
				int percentuale=in.nextInt();
				integer = new Integer(percentuale);
				System.out.println("inserisci il costo dell'intervento al "+ pezzo);	
				int costointervento=in.nextInt();
				Integer costo = new Integer(costointervento);
				officina.aggiungipezzo(pezzo,integer,costo);
				System.out.println(pezzo + " generato");
			}
			catch(Exception e)
			{
				System.out.println("Input non valido");
				in.nextLine();
			}
	}
				
		public static void cambiapezzo(Scanner in,Officina officina,ArrayList<Proprietario> proprietari)
	{	
		int indicemacchina=-1;
		int perc=0,trovato=0, indice=0,prezzo=0;
		System.out.println("Inserisci targa della macchina in cui vuoi cambiare un pezzo ");	
		String targa=in.next();
		int proprietariomacchina=trovavenditore(proprietari,targa);
		if(proprietariomacchina!=-1)
		{
			indicemacchina=trovaAuto(proprietari,proprietariomacchina,targa);		
			System.out.println("Inserisci nome del pezzo da cambiare, i pezzi  disponibili sono");
			for (String nomepezzo : officina.getPezzi())
				System.out.println(nomepezzo);
			String pezzo=in.next();
			for(int i=0;i<officina.getPezzi().size()&&trovato==0;i++)
			{
				if(officina.getPezzi().get(i).equalsIgnoreCase(pezzo))
				{
					 perc = officina.getPercentuale().get(i).intValue();
					 trovato=1;
					 prezzo=officina.getCostointervento().get(i).intValue();
					 indice=i--;
				}	
			}
			if(trovato==1)
				modificaValoreAuto(proprietari,indice,proprietariomacchina,indicemacchina,perc,prezzo);
			else
				System.out.println("Pezzo non disponibile");
		}
		else
			System.out.println("Macchina con targa "+targa+" non trovata" );	

		
		
		
	}
	public static void modificaValoreAuto(ArrayList<Proprietario> proprietari,int indice,int proprietariomacchina,int indicemacchina,int perc,int prezzo)
	{ 
		proprietari.get(proprietariomacchina).getAutomobili().get(indicemacchina).modificaprezzo(perc);
		if(proprietari.get(proprietariomacchina).getConto()<prezzo)
			System.out.println("Impossibile fare l'intervento,manca la disponibilità economica");
		else
		{	
			proprietari.get(proprietariomacchina).aggiustaConto(prezzo);
			System.out.println("Intervento riuscito");
		}
		
	}
	
	public static void aumentaconto(Scanner in,ArrayList<Proprietario> proprietari)
	{	int numProp=trovaproprietario(in,proprietari);
		if(numProp!=-1)
		{
			System.out.println("Inserisci ricapitalizzazione proprietario ");	
			int ricap=in.nextInt();
			proprietari.get(numProp).aumentaFondi(ricap);
			System.out.println("Ricapitalizzazione riuscita ");
		}
		else
			System.out.println("Proprietario non esistente ");
		
	}
	
		public static void inserisciproprietari(Scanner in,ArrayList<Proprietario> proprietari)
		{	
			int uscita;
			do
			{
				int propEsistente=0;
				System.out.println("Inserisci Nome propretario ");	
				String nome=in.next();
				System.out.println("Inserisci Cognome propretario ");	
				String cognome=in.next();
				for (Proprietario prop : proprietari)
				{
					if(prop.getNome().equalsIgnoreCase(nome)&& prop.getCognome().equalsIgnoreCase(cognome))
					{
						propEsistente=1;
					}
						
				}
				
				if(propEsistente==0)
				{	try{
							
						System.out.println("Inserisci soldi del proprietario ");
						int conto=in.nextInt();
						Proprietario proprietario=new Proprietario(nome,cognome,conto);
						proprietari.add(proprietario);
						System.out.println("Proprietario inserito");
					}
					catch(Exception e)
					{
						System.out.println("Input non valido");
						in.nextLine();
					}
				}
				else
				{
					System.out.println("Impossibile inserire proprietario,esso e' gia stato creato");
				}
				
				System.out.println("premere 0 per inserire nuovo proprietario other)menu ");
				uscita= in.nextInt();				
			}
			while(uscita==0);
		}
		public static void inseriscimacchina(Scanner in,ArrayList<Proprietario> proprietari)
		{ 	
			int uscita=0;
			do
			{
				System.out.println("Inserisci targa ");	
				String targa =in.next();
				System.out.println("Inserisci modello ");	
				String modello=in.next();
				int prezzo=0;
				try{
						System.out.println("Inserisci prezzo ");	
						prezzo =in.nextInt();
						System.out.println("Inserisci dati proprietario macchina");
						int proprietario=trovaproprietario(in,proprietari);
						if(proprietario!=-1)
						{
							Automobile automobile=new Automobile(targa,modello,prezzo);
							proprietari.get(proprietario).setAutomobili(automobile);
							System.out.println("Automobile inserita");
						}
						else
						{
							System.out.println("Proprietario non esistente, impossibile inserire macchina");
						}
						
					}
					catch(Exception e)
					{
						System.out.println("Input prezzo non valido");						
						in.nextLine();
						
						
					}	
				System.out.println("premere 0 per inserire nuova auto other)menu ");
				uscita= in.nextInt();
							
			}
			while(uscita==0);
		}
		public static void compravendita(Scanner in,ArrayList<Proprietario> proprietari)
		{	
			System.out.println("Inserisci dati acquirente");
			int acquirente=trovaproprietario(in,proprietari);
			if(acquirente==-1)
				System.out.println("Acquirente non presente");
			else
			{	
				System.out.println("Inserisci targa della macchina oggetto della trattativa ");	
				String targa= in.next();
				int venditore=trovavenditore(proprietari,targa);
				if(venditore==-1)
					System.out.println("Venditore non presente");
				else
				{			
					int  macchina = trovaAuto(proprietari,venditore,targa);
					if(macchina!=-1)
					{	
						int prezzoAuto=proprietari.get(venditore).getAutomobili().get(macchina).getPrezzo();
						if(proprietari.get(acquirente).aggiustaConto(prezzoAuto)!=-1)
						{	
							proprietari.get(venditore).aggiustaContovend(prezzoAuto);
							proprietari.get(acquirente).modificaParcoAuto(proprietari.get(venditore).getAutomobili().get(macchina),venditore,proprietari);
							System.out.println("Compravendita effettuata");
						}
						else
							System.out.println("L'acquirente non ha la disponibilità econonomica per fare l' acquisto");
					}
					else
						System.out.println(" macchina non presente nel parco auto del venditore");
					
					
				}
			}
		}
		public static void visionedati(Scanner in,ArrayList<Proprietario> proprietari,Officina officina)
		{ 
			int vs;
			System.out.println("1)visualizza tutti i proprietari 1)visualizza un proprietario 3) visualizza pezzi");	
			vs=in.nextInt();
			switch(vs)
			{
			case 1: visionatutti(in,proprietari);
				break;
			case 2: visionauno(in,proprietari);
				break;
			case 3:visualizzaPezzi(officina);
			}
					
		}
		public static void visionatutti(Scanner in,ArrayList<Proprietario> proprietari)
		{
			for (Proprietario prop : proprietari)
			{	System.out.println("il nome del proprietario è: "+ prop.getNome()+"; il cognome del proprietario è: "+ prop.getCognome()+"; il suo conto e' di "+prop.getConto());
				System.out.println("Il suo parco auto è");
				if(prop.getAutomobili().isEmpty()==false)
				{	for (Automobile aut : prop.getAutomobili())
					{
						System.out.println("la targa è "+aut.getTarga()+"; il modello è "+aut.getModello()+"; Il prezzo è "+aut.getPrezzo());
					}
				}
				else
					System.out.println("VUOTO");
			}
		}
		public static void visionauno(Scanner in,ArrayList<Proprietario> proprietari)
		{	Proprietario prop; 
			int proprietario=trovaproprietario(in,proprietari);
			if(proprietario!=-1)
			{
				prop = proprietari.get(proprietario);
				System.out.println("il nome del proprietario è: "+ prop.getNome()+"; il cognome del proprietario è: "+ prop.getCognome()+"; il suo conto e' di "+prop.getConto());
				System.out.println("Il suo parco auto è");
				if(prop.getAutomobili().isEmpty()==false)
				{
					for (Automobile aut : prop.getAutomobili())
					{
						System.out.println("la targa è "+aut.getTarga()+"; il modello è "+aut.getModello()+"; Il prezzo è "+aut.getPrezzo());
					}
				}
				else
					System.out.println("VUOTO");
			}
			else
				System.out.println("Il proprietario selezionato non esiste ");					
		}
		public static int trovaproprietario(Scanner in,ArrayList<Proprietario> proprietari)
		{ 
			System.out.println("Inserisci nome proprietario ");	
			String nome= in.next();
			System.out.println("Inserisci cognome proprietario ");	
			String cognome= in.next();
			int trovato=1;
			int i;
			for (i=0;i<proprietari.size()&& trovato == 1;i++ )
			{
				
				if(proprietari.get(i).getNome().equalsIgnoreCase(nome)&& proprietari.get(i).getCognome().equalsIgnoreCase(cognome))
						trovato=0;
			}			
			i--;
			if(trovato==0)
				return i;
			else
				return -1;
		}
		public static int trovavenditore(ArrayList<Proprietario> proprietari,String targa)
		{ 
			
			int i=0,k=-1;
			for(i=0;i< proprietari.size()&& k==-1;i++)
			{
				k=trovaAuto(proprietari,i,targa);
				if(k!=-1)
					return i;
			}
			
				return -1;
			
		}
		public static void visualizzaPezzi(Officina officina)
		{
			if(officina.getPezzi().size()!=0)
				for(String pezzo: officina.getPezzi())
					System.out.println(pezzo);
			else
				System.out.println("Non sono stati creati pezzi");
		}
		public static int trovaAuto(ArrayList<Proprietario> proprietari,int venditore,String targa)
		{ 
			int trovato=1;
			Automobile aut; 
			int i;
			for (i=0;i<proprietari.get(venditore).getAutomobili().size()&& trovato == 1;i++ )
				{	
					aut=proprietari.get(venditore).getAutomobili().get(i);
					if(aut.getTarga().equalsIgnoreCase(targa))
						trovato=0;
				}
			i--;
			if(trovato==0)
				return i;
			else
				return -1;
		}
	
}

