package ZiackaKnizka;

import Pouzivatelia.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//import java.io.Serializable;
import java.util.*;

public class ZiackaKnizka /* implements Serializable */ {
	private List<Trieda> trieda = new ArrayList<>();
	private List<Pouzivatel> pouzivatel = new ArrayList<>();

	public ZiackaKnizka() {
		trieda.add(new Trieda("2.B"));
		trieda.add(new Trieda("Oktava"));

		//pouzivatel.add(new Ziak("Peter", "Striz"));
		pridajPouzivatela(new Ziak("Peter", "Striz"));
		pouzivatel(0).nastavLogin("striz98", "heslo");

		pridajPouzivatela(new Ziak("Marek", "Vajda"));
		pouzivatel(1).nastavLogin("vajda98", "abcdef");

		pridajPouzivatela(new Ziak("Juraj", "Polak"));
		pouzivatel(2).nastavLogin("polak98", "heslo");

		pridajPouzivatela(new Ucitel("Lucia", "Simova"));
		pouzivatel(3).nastavLogin("simova", "heslo");

		pridajPouzivatela(new Riaditel("Peter", "Pistek"));
		pouzivatel(4).nastavLogin("pistek", "heslo");

		vratTriedu(0).pridajZiaka(vratPouzivatelov(), "striz98", "vajda98");
		vratTriedu(1).pridajZiaka(vratPouzivatelov(), "polak98");

		vratTriedu(0).pridajPredmet("Matematika", "Slovencina");
		vratTriedu(1).pridajPredmet("Anglictina");

		ziak("striz98").pridajZnamku(0, "10", "20", getRandomDate());
		ziak("striz98").pridajZnamku(0, "9", "20", getRandomDate());
		ziak("striz98").pridajZnamku(1, "101", "40", getRandomDate());
		ziak("striz98").pridajZnamku(1, "913", "40", getRandomDate());
		ziak("vajda98").pridajZnamku(0, "15", "30", getRandomDate());
		ziak("polak98").pridajZnamku(0, "20", "30", getRandomDate());

		ucitel("simova").pridajTriedu(trieda("2.B"), trieda("Oktava"));

		ziak("striz98").pridajZnamku(0, "10", "44", getRandomDate());

	}

	public void pridajPouzivatela(Pouzivatel p) {
		pouzivatel.add(p);
	}
	
	public Pouzivatel vratPouzivatela(String username, String password) {
		for (Pouzivatel pouzivatelFor : vratPouzivatelov())
			if (pouzivatelFor != null && pouzivatelFor.overLogin(username, password))
				return (pouzivatelFor);

		return null;
	}

	public Pouzivatel vratPouzivatela(String username) {
		for (Pouzivatel pouzivatelFor : vratPouzivatelov())
			if (pouzivatelFor != null && pouzivatelFor.overUsername(username))
				return (pouzivatelFor);

		return null;
	}

	public Trieda vratTriedu(int i) {
		return trieda.get(i);
	}

	public Trieda trieda(String meno) {
		for (Trieda t : trieda) {
			if (t.vratMeno().equals(meno))
				return t;
		}
		return null;
	}

	public Pouzivatel pouzivatel(int i) {
		return vratPouzivatelov().get(i);
	}

	public Ziak ziak(String username) {
		for (Pouzivatel pouzivatelFor : vratPouzivatelov())
			if (pouzivatelFor != null && pouzivatelFor.overUsername(username))
				return ((Ziak) pouzivatelFor);
		return null;
	}

	public Ucitel ucitel(String username) {
		for (Pouzivatel pouzivatelFor : vratPouzivatelov())
			if (pouzivatelFor != null && pouzivatelFor.overUsername(username))
				return ((Ucitel) pouzivatelFor);
		return null;
	}

	public String getRandomDate() {
		int rok = randBetween(2000, 2019);
		int mesiac = randBetween(1, 12);
		int den = randBetween(1, 28);
		String date = ((den < 10 ? "0" : "") + den + "." + (mesiac < 10 ? "0" : "") + mesiac + "." + rok);

		return date;
	}

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public List<Pouzivatel> vratPouzivatelov() {
		return this.pouzivatel;
	}

	public ObservableList<Ucitel> vratUcitelov() {
		ObservableList<Ucitel> ucitelObser = FXCollections.observableArrayList();
		for (Pouzivatel p : vratPouzivatelov()) {
			if (p instanceof Ucitel)
				ucitelObser.add((Ucitel) p);
		}
		return ucitelObser;
	}

	public ObservableList<String> vratMenoTried() {
		ObservableList<String> ucitelObser = FXCollections.observableArrayList();
		for (Trieda t : trieda)
			ucitelObser.add(t.vratMeno());

		return ucitelObser;

	}
	
	public Pouzivatel vratPouzivatelaPodlaMena(String meno, String priezvisko) {
		for (Pouzivatel p  : pouzivatel)
			if (p instanceof Ziak && meno.equals(p.getMeno()) && priezvisko.equals(p.getPriezvisko()))
				return p;
		
		return null;
	}
}
