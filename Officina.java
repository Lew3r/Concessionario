import java.util.ArrayList;
public class Officina {
	private ArrayList<String> pezzi;
	private ArrayList<Integer> percentuale;
	private ArrayList<Integer> costointervento;
	
	

public Officina()
{
	this.percentuale = new ArrayList<Integer>();
	this.pezzi = new ArrayList<String>();
	this.costointervento = new ArrayList<Integer>();
}
public void aggiungipezzo(String pezzo,int percentuale,int costointervento)
{
	this.pezzi.add(pezzo);
	Integer integer = new Integer(percentuale);
	this.percentuale.add(integer);
	Integer integercosto = new Integer(costointervento);
	this.costointervento.add(integercosto);
}
public ArrayList<String> getPezzi() {
	return pezzi;
}
public ArrayList<Integer> getPercentuale() {
	return percentuale;
}
public ArrayList<Integer> getCostointervento() {
	return costointervento;
}




}

