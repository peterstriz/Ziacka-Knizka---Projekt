package Pouzivatelia;

import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Trieda {
	public List<Ziak> ziak = new ArrayList<>();
//	private List<Predmet> predmet = new ArrayList<>();
	private String meno;

	public Trieda(String meno) {
		nastavMeno(meno);
	}

	public void pridajPredmet(String novyPredmet) {
		for (Ziak z : ziak)
			z.pridajPredmet(new Predmet(novyPredmet));
	}

	public void pridajZiaka(Ziak novyZiak) {
		ziak.add(novyZiak);
	}
	
	public ObservableList<Ziak> vratZiakovTriedy (){
		ObservableList<Ziak> ziakObser = FXCollections.observableArrayList();
		for (Ziak z : this.ziak)
			ziakObser.add(z);
		return ziakObser;
	}

	public String vratMeno() {
		return meno;
	}

	public void nastavMeno(String meno) {
		this.meno = meno;
	}
	
	public List<Ziak> vratZiakov(){
		return this.ziak;
	}
}
