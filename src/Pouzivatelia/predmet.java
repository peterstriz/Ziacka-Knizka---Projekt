package Pouzivatelia;

public class Predmet {
	private String meno;
	public int maxPocetZnamok;
	public Znamky[] znamka;

	public Predmet(String meno, int maxPocetZnamok) {
		this.meno = meno;
		this.maxPocetZnamok = maxPocetZnamok;
		this.znamka = new Znamky[maxPocetZnamok];
	}

	public Znamky[] vratZnamky() {
		return znamka;
	}

	public String vratMenoPredmetu() {
		return this.meno;
	}

}
