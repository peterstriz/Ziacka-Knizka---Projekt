package Pouzivatelia;

public class Predmet{
	private int ID;
	private String meno;
	public int maxPocetZnamok;
	public Znamky[] znamka;
	
	public Predmet(String meno, int maxPocetZnamok) {
		this.meno = meno;
		this.maxPocetZnamok = maxPocetZnamok;
		this.znamka =  new Znamky[maxPocetZnamok];
	}
	
}
