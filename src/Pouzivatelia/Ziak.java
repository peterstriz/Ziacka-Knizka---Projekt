package Pouzivatelia;

public class Ziak extends OsobneUdaje{
	private int maxPocetPredmetov; //upravit na private v osobne udaje
	public Predmet[] predmet;
	
	
	public Ziak(String meno, String priezvisko, int maxPocetPredmetov) {
		ulozMeno(meno);
		ulozPriezvisko(priezvisko);
		this.maxPocetPredmetov = maxPocetPredmetov;
		this.predmet = new Predmet[maxPocetPredmetov];
	}
	
	
	
}
