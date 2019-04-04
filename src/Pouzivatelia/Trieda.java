package Pouzivatelia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Trieda je trieda v korej su ulozene predmety a jednotlivi ziaci.
 * 
 * @author Peter Striz
 *
 */
public class Trieda implements Serializable {
	private static final long serialVersionUID = 1L;
	/** Pole vsetkych ziakov v triede. */
	private List<Ziak> ziak = new ArrayList<>();
	/** Pole vsetkych mien predmetov v triede. */
	private List<String> predmet = new ArrayList<>();
	/** Meno triedy. */
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

	/**
	 * Metoda na pridavanie ziakov do triedy.
	 * 
	 * @param novyZiak Ziaci ktory budu pridany do triedy.
	 */
	public void addZiak(Pouzivatel... novyZiak) {
		for (Pouzivatel z : novyZiak)
			ziak.add((Ziak) z);
		updatePredmety();
	}

	public Ziak getZiak(int i) {
		return ziak.get(i);
	}

	/**
	 * @return Vrati vsetkych ziakov triedy.
	 */
	public ObservableList<Ziak> getZiak() {
		ObservableList<Ziak> ziakObser = FXCollections.observableArrayList(ziak);
		return ziakObser;
	}

	/**
	 * @return Vrati mena vsetkych ziakov triedy.
	 */
	public ObservableList<String> getMenoZiakov() {
		ObservableList<String> menoZiaka = FXCollections.observableArrayList();
		for (Ziak z : this.ziak)
			menoZiaka.add(z.getMeno() + " " + z.getPriezvisko());
		return menoZiaka;
	}

	/**
	 * Metoda na pridavanie predmetov do triedy.
	 * 
	 * @param novyPredmet Predmet ktory bude pridany do triedy.
	 */
	public void addPredmet(String... novyPredmet) {
		for (String s : novyPredmet)
			predmet.add(s);
		updatePredmety();
	}

	/**
	 * Kazdemu ziakovy prida vsetky predmety.
	 */
	private void updatePredmety() {
		for (String p : predmet)
			for (Ziak z : ziak)
				z.pridajPredmet(p);

	}

	/**
	 * @return Vrati mena vsetkych predmetov triedy.
	 */
	public ObservableList<String> getMenoPredmetov() {
		ObservableList<String> list = FXCollections.observableArrayList(predmet);
		return list;
	}
}
