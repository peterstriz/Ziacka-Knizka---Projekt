package Pouzivatelia;

import java.io.Serializable;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Predmet je trieda v korej su ulozene znamky daneho predmetu ziaka.
 * 
 * @author Peter Striz
 *
 */
public class Predmet implements Serializable {
	private static final long serialVersionUID = 1L;
	/** Meno predmetu. */
	private String meno;
	/** Pole vsetkych znamok v danom predmete. */
	private List<Znamka> znamka = new ArrayList<>();

	public Predmet(String meno) {
		setMeno(meno);
	}

	public String getMeno() {
		return this.meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	/**
	 * Prida novu znamku do predmetu.
	 * 
	 * @param hodnota    Pocet bodov z pisomky.
	 * @param maxHodnota Maximalny pocet bodov ktory sa dal ziskat z pisomky.
	 * @param datum      Datum pisania pisomky.
	 */
	public void pridajNovuZnamku(String hodnota, String maxHodnota, String datum) {
		znamka.add(new Znamka(hodnota, maxHodnota, datum));
		utriedZnamky();
	}

	/**
	 * @return Vrati vsetky znamky predmetu.
	 */
	public ObservableList<Znamka> vratZnamku() {
		ObservableList<Znamka> znamkaObser = FXCollections.observableArrayList(znamka);
		return znamkaObser;
	}

	/**
	 * Metoda na utriedenie znamok podla datumu.
	 */
	public void utriedZnamky() {
		Collections.sort(znamka);
	}
}
