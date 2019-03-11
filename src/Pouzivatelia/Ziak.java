package Pouzivatelia;

public class Ziak extends Pouzivatel {
	public int maxPocetPredmetov;
	public Predmet[] predmet;

	public Ziak(String meno, String priezvisko) {
		super(meno, priezvisko);
	}

	public void vytvorPredmety(int maxPocetPredmetov) {
		this.maxPocetPredmetov = maxPocetPredmetov;
		this.predmet = new Predmet[maxPocetPredmetov];
	}

	public void pridajPredmet(String meno, int maxPocetZnamok) {
		predmet[predmet.length] = new Predmet(meno, maxPocetZnamok);
	}

}
