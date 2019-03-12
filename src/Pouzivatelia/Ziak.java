package Pouzivatelia;

import java.util.*;

public class Ziak extends Pouzivatel {
	public List<Predmet> predmet = new ArrayList<>();
	
	public Ziak(String meno, String priezvisko) {
		super(meno, priezvisko);
	}

	public void pridajPredmet(String meno) {
		predmet.add(new Predmet(meno));
	}

}
