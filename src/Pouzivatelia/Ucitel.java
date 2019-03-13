package Pouzivatelia;

import java.util.Date;
import java.util.List;

import javafx.collections.ObservableList;

public class Ucitel extends OsobneUdaje implements Pouzivatel {
	public int test;

	public Ucitel(String meno, String priezvisko) {
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
//		return null;
//	}
	public ObservableList<Znamka> vratZnamkyPredmetu(int i) {
		return null;
		// return this.predmet.get(i);
	}

	public void pridajPredmet(Predmet predmetNovy) {

	}

	public String vratMenoPredmetu(int i) {
		return null;
	}

	public void pridajZnamku(int i, double hodnota, double maxHodnota, Date datum) {

	}
	
	public Predmet vratPredmet(int i) {
		return null;
	}

}
