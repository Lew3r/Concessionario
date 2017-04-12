
public class Automobile {
	private String targa;
	private String modello;
	private int prezzo;


public Automobile(String targa,String modello,int prezzo)
{
	this.targa=targa;
	this.modello=modello;
	this.prezzo=prezzo;
}
public void acquista()
{
	this.prezzo=this.prezzo- this.prezzo/5;
}
public String getTarga() {
	return targa;
}

public String getModello() {
	return modello;
}

public int getPrezzo() {
	return prezzo;
}
public void modificaprezzo(int percentuale)
{
	if(this.prezzo==0)
		this.prezzo=1000;
	else
		this.prezzo=this.prezzo+this.prezzo*percentuale/100;
}


}