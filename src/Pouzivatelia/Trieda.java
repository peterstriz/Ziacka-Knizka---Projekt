package Pouzivatelia;

public class Trieda{
	public Ziak[] ziak;
	public String meno;
	public int pocetZiakov;
	
	public Trieda(String meno, int pocetZiakov) {
		this.meno = meno;
		this.pocetZiakov = pocetZiakov;
		this.ziak = new Ziak[pocetZiakov];
	}
}
