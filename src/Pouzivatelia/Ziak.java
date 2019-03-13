package Pouzivatelia;

import java.util.*;

import javafx.collections.ObservableList;

public class Ziak extends OsobneUdaje implements Pouzivatel {
	public List<Predmet> predmet = new ArrayList<>();

	public Ziak(String meno, String priezvisko) {
		ulozMeno(meno);
		ulozPriezvisko(priezvisko);
	}

	public void nastavLogin(String username, String password) {
		nastavUserName(username);
		nastavPassword(password);
	}

	public Boolean overUserName(String username) {
		return super.overUserName(username);
	}

	public Boolean overPassword(String username) {
		return super.overPassword(username);
	}

	public String vratMeno() {
		return super.vratMeno();
	}

	public String vratPriezvisko() {
		return super.vratPriezvisko();
	}

//	public ObservableList<Znamky> vratZnamku(List<Znamky> znamka) {
//		ObservableList<Znamky> znamkaObser = FXCollections.observableArrayList();
//		for (Znamky z : znamka)
//			znamkaObser.add(z);
//		return znamkaObser;
//	}

	public String vratMenoPredmetu(int i) {
		return this.predmet.get(i).vratMeno();
	}
	
	public Predmet vratPredmet(int i) {
		return this.predmet.get(i);
	}

	public ObservableList<Znamka> vratZnamkyPredmetu(int i) {
		return this.predmet.get(i).vratZnamku();
	}

	public void pridajPredmet(Predmet predmetNovy) {
		predmet.add(predmetNovy);
	}

	public void pridajZnamku(int  i, double hodnota, double maxHodnota, Date datum) {
		predmet.get(i).pridajNovuZnamku(hodnota, maxHodnota, datum);
	}
}
