package Pouzivatelia;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Trieda {
	public ObservableList<Ziak> ziak = FXCollections.observableArrayList();
	private String meno;

	public Trieda(String meno) {
		nastavMeno(meno);
	}

	public String vratMeno() {
		return meno;
	}

	public void nastavMeno(String meno) {
		this.meno = meno;
	}

	public void pridajZiaka(List<Pouzivatel> po, String... novyZiak) {
		for (String username : novyZiak)
			for (Pouzivatel p : po)
				if (p != null && p.overUsername(username))
					ziak.add((Ziak) p);
	}

	public void pridajZiaka(Pouzivatel... novyZiak) {
		for (Pouzivatel z : novyZiak)
			ziak.add((Ziak) z);
	}

	public Ziak vratZiaka(int i) {
		return ziak.get(i);
	}

	public ObservableList<Ziak> vratZiakov() {
		return ziak;
	}

	public ObservableList<String> vratMenoZiakov() {
		ObservableList<String> menoZiaka = FXCollections.observableArrayList();
		for (Ziak z : this.ziak)
			menoZiaka.add(z.getMeno() + " " + z.getPriezvisko());
		return menoZiaka;
	}

	public void pridajPredmet(String... novyPredmet) {
		for (String p : novyPredmet)
			for (Ziak z : ziak)
				z.pridajPredmet(new Predmet(p));
	}
}
