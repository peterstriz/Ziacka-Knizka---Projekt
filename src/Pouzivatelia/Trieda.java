package Pouzivatelia;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Trieda {
	public ObservableList<Ziak> ziak = FXCollections.observableArrayList();
	public ObservableList<String> predmet = FXCollections.observableArrayList();
	private String meno;

	public Trieda(String meno) {
		setMeno(meno);
	}

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public void addZiak(List<Pouzivatel> po, String... novyZiak) {
		for (String username : novyZiak)
			for (Pouzivatel p : po)
				if (p != null && p.overUsername(username))
					ziak.add((Ziak) p);
	}

	public void addZiak(Pouzivatel... novyZiak) {
		for (Pouzivatel z : novyZiak)
			ziak.add((Ziak) z);
		updatePredmety();
	}

	public Ziak getZiak(int i) {
		return ziak.get(i);
	}

	public ObservableList<Ziak> getZiak() {
		return ziak;
	}

	public ObservableList<String> getMenoZiakov() {
		ObservableList<String> menoZiaka = FXCollections.observableArrayList();
		for (Ziak z : this.ziak)
			menoZiaka.add(z.getMeno() + " " + z.getPriezvisko());
		return menoZiaka;
	}

	public void addPredmet(String... novyPredmet) {
		this.predmet.addAll(novyPredmet);
		updatePredmety();
	}

	private void updatePredmety() {
		for (String pr : this.predmet)
			for (Ziak z : ziak)
				z.pridajPredmet(pr);

	}
}
