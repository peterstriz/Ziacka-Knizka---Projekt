package Pouzivatelia;

public class Ziak extends Pouzivatel {
	private int maxPocetPredmetov; // upravit na private v osobne udaje
	public Predmet[] predmet;

	public Ziak(String meno, String priezvisko, int maxPocetPredmetov) {
		ulozMeno(meno);
		ulozPriezvisko(priezvisko);
		this.maxPocetPredmetov = maxPocetPredmetov;
		this.predmet = new Predmet[maxPocetPredmetov];

	}

	public void nastavLogin(String username, String password) {
		
	}

}
