package Pouzivatelia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ucitel extends OsobneUdaje implements Pouzivatel {
	private ObservableList<Ziak> ziaciUcitela;

	public Ucitel(String meno, String priezvisko) {
		super(meno, priezvisko);
	}

	public void nastavLogin(String username, String password) {
		super.nastavLogin(username, password);
	}

	public Boolean overLogin(String username, String password) {
		return super.overLogin(username, password);
	}

	public String vratMeno() {
		return super.vratMeno();
	}

	public String vratPriezvisko() {
		return super.vratPriezvisko();
	}
	
	public void nastavMeno(String meno) {
		super.nastavMeno(meno);
	}

	public void nastavPriezvisko(String priezvisko) {
		super.nastavPriezvisko(priezvisko);
	}

	public ObservableList<Znamka> vratZnamkyPredmetu(int i) {
		return null;//ziak.vratPredmet(i).vratZnamku();
	}

	public void pridajPredmet(Predmet predmetNovy) {

	}

//	public String vratMenoPredmetu(int i) {
//		return null;
//	}

	public void pridajZnamku(int cisloPredmetu, double hodnota, double maxHodnota, Date datum) {

	}

	public Predmet vratPredmet(int i) {
		return null;
	}

	public ObservableList<String> vratMenoPredmetov() {
		return null;
	}

	public void pridajZiakov(ObservableList<Ziak> z) {
		this.ziaciUcitela = z;
	}

	public void pridajZiaka(Ziak z) {
		this.ziaciUcitela.add(z);
	}
	
	public Ziak vratZiaka(int i) {
		return ziaciUcitela.get(i);
	}

	public ObservableList<String> vratMenoZiakov() {
		ObservableList<String> menoZiaka = FXCollections.observableArrayList();
		for (Ziak z : this.ziaciUcitela)
			menoZiaka.add(z.vratMeno() + " " + z.vratPriezvisko());
		return menoZiaka;
	}

}
