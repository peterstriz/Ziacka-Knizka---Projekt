package Pouzivatelia;

import java.util.*;

public class Trieda {
	public Ziak[] ziak;
	public String meno;
	public int pocetZiakov = 0;
	public int maxPocetZiakov;
	
	//private List<Ziak> ziak = new ArrayList<>();

	public Trieda(String meno, int maxPocetZiakov) {
		this.meno = meno;
		this.maxPocetZiakov = maxPocetZiakov;
		this.ziak = new Ziak[maxPocetZiakov];
	}
	
	public void pridajZiaka(Ziak ziakPridaj) {
		//ziak.add(ziakPridaj);
		this.ziak[pocetZiakov++] = ziakPridaj;
	}
}
