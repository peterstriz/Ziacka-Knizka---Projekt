package Pouzivatelia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Trieda implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Ziak> ziak = new ArrayList<>();
	private List<String> predmet = new ArrayList<>();

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
		ObservableList<Ziak> ziakObser = FXCollections.observableArrayList();
		ziakObser.addAll(ziak);
		return ziakObser;
	}

	public ObservableList<String> getMenoZiakov() {
		ObservableList<String> menoZiaka = FXCollections.observableArrayList();
		for (Ziak z : this.ziak)
			menoZiaka.add(z.getMeno() + " " + z.getPriezvisko());
		return menoZiaka;
	}

	public void addPredmet(String... novyPredmet) {
		for (String s : novyPredmet)
			predmet.add(s);
		updatePredmety();
	}

	private void updatePredmety() {
		for (String p : predmet)
			for (Ziak z : ziak)
				z.pridajPredmet(p);

	}
}
