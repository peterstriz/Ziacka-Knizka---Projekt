package Pouzivatelia;

import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Predmet {
	private String meno;
	public List<Znamka> znamka = new ArrayList<>();

	public Predmet(String meno) {
		this.meno = meno;
	}

	public List<Znamka> vratZnamky() {
		return znamka;
	}

	public String vratMeno() {
		return this.meno;
	}

	public void pridajNovuZnamku(double hodnota, double maxHodnota, Date datum) {
		znamka.add(new Znamka(hodnota, maxHodnota, datum));
	}

	public ObservableList<Znamka> vratZnamku() {
		ObservableList<Znamka> znamkaObser = FXCollections.observableArrayList();
		for (Znamka z : this.znamka)
			znamkaObser.add(z);
		return znamkaObser;
	}

}
