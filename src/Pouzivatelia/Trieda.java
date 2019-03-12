package Pouzivatelia;

import java.util.*;

public class Trieda {
	public List<Ziak> ziak = new ArrayList<>();
	public String meno;

	public Trieda(String meno) {
		this.meno = meno;
	}

	public void pridajZiaka(Ziak ziakPridaj) {
		ziak.add(ziakPridaj);
	}
}
