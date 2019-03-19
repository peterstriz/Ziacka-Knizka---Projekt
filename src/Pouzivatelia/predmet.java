package Pouzivatelia;

import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Predmet {
	private String meno;
	private List<Znamka> znamka = new ArrayList<>();

	public Predmet(String meno) {
		nastavMeno(meno);
	}

	public String vratMeno() {
		return this.meno;
	}

	public void nastavMeno(String meno) {
		this.meno = meno;
	}

	public void pridajNovuZnamku(String hodnota, String maxHodnota, String datum) {
		znamka.add(new Znamka(hodnota, maxHodnota, datum));
	}

	public List<Znamka> vratZnamky() {
		return this.znamka;
	}

	public ObservableList<Znamka> vratZnamku() {
		ObservableList<Znamka> znamkaObser = FXCollections.observableArrayList();
		for (Znamka z : this.znamka)
			znamkaObser.add(z);
		return znamkaObser;
	}

}
