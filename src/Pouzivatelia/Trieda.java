package Pouzivatelia;

import java.util.*;

public class Trieda {
	public List<Ziak> ziak = new ArrayList<>();
	public List<Predmet> predmet = new ArrayList<>();
	public String meno;

	public Trieda(String meno) {
		this.meno = meno;
	}

	public void pridajPredmet(String novyPredmet) {
		for (Ziak z : ziak)
			z.predmet.add(new Predmet(novyPredmet));
	}

	public void pridajZiaka(Ziak novyZiak) {
		ziak.add(novyZiak);
	}
	
	public void pridajPredmetyZiakom() {
		for (Ziak z : ziak)
			for (Predmet p : predmet)
				z.pridajPredmet(p);
	}
}
