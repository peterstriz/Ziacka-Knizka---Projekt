package Pouzivatelia;

import gui.ManazerLogin;
import java.io.Serializable;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ziak extends OsobneUdaje implements Serializable, Pouzivatel {
	private static final long serialVersionUID = 1L;

	private List<Predmet> predmet = new ArrayList<>();

	public Ziak(String meno, String priezvisko) {
		super(meno, priezvisko);
	}

	public void nastavLogin(String username, String password) {
		super.nastavLogin(username, password);
	}

	public Boolean overLogin(String username, String password) {
		return super.overLogin(username, password);
	}

	public Boolean overUsername(String username) {
		return super.overUsername(username);
	}

	public String getMeno() {
		return super.getMeno();
	}

	public String getPriezvisko() {
		return super.getPriezvisko();
	}

	public String vratCeleMeno() {
		return getMeno() + " " + getPriezvisko();
	}

	public void nastavMeno(String meno) {
		super.nastavMeno(meno);
	}

	public void nastavPriezvisko(String priezvisko) {
		super.nastavPriezvisko(priezvisko);
	}

	public Predmet vratPredmet(int i) {
		return this.predmet.get(i);
	}

	public ObservableList<Znamka> vratZnamkyPredmetu(int i) {
		return this.predmet.get(i).vratZnamku();
	}

	public ObservableList<String> vratMenoPredmetov() {
		ObservableList<String> predmetObser = FXCollections.observableArrayList();
		for (Predmet p : this.predmet)
			predmetObser.add(p.getMeno());
		return predmetObser;
	}

	public void pridajPredmet(String predmetNovy) {
		if (!overPredmet(predmetNovy))
			predmet.add(new Predmet(predmetNovy));
	}

	public void pridajZnamku(int cisloPredmetu, String hodnota, String maxHodnota, String datum) {
		predmet.get(cisloPredmetu).pridajNovuZnamku(hodnota, maxHodnota, datum);
	}

	public List<Predmet> getPredmet() {
		return this.predmet;
	}

	private Boolean overPredmet(String meno) {
		for (Predmet p : this.predmet)
			if (meno.equals(p.getMeno()))
				return true;
		return false;
	}

	public void login(ManazerLogin m) {
		m.login(this);
	}

}
