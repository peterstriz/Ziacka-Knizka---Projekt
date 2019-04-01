package ZiackaKnizka;

import Pouzivatelia.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.*;

public class ZiackaKnizka implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Trieda> trieda = new ArrayList<>();
	private List<Pouzivatel> pouzivatel = new ArrayList<>();

	public ZiackaKnizka() {
		trieda.add(new Trieda("2.B"));
		trieda.add(new Trieda("Oktava"));

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

		vratTriedu(0).addZiak(vratPouzivatelov(), "striz98", "vajda98");
		vratTriedu(1).addZiak(vratPouzivatelov(), "polak98");

		vratTriedu(0).addPredmet("Matematika", "Slovencina");
		vratTriedu(1).addPredmet("Anglictina");

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

	public void pridajTriedu(String s) {
		trieda.add(new Trieda(s));
	}

	public Pouzivatel vratPouzivatelaLogin(String username, String password) {
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
			if (t.getMeno().equals(meno))
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

	private String getRandomDate() {
		int rok = randBetween(2000, 2019);
		int mesiac = randBetween(1, 12);
		int den = randBetween(1, 28);
		String date = ((den < 10 ? "0" : "") + den + "." + (mesiac < 10 ? "0" : "") + mesiac + "." + rok);

		return date;
	}

	private int randBetween(int start, int end) {
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

	public ObservableList<String> vratMenoUcitelov() {
		ObservableList<String> ucitelObser = FXCollections.observableArrayList();
		for (Pouzivatel p : vratPouzivatelov()) {
			if (p instanceof Ucitel)
				ucitelObser.add(p.vratCeleMeno());
		}
		return ucitelObser;
	}

	public ObservableList<String> vratMenoTried() {
		ObservableList<String> ucitelObser = FXCollections.observableArrayList();
		for (Trieda t : trieda)
			ucitelObser.add(t.getMeno());

		return ucitelObser;

	}

	public Ucitel vratUcitelaPodlaTriedy(Trieda trieda) {
		String menoTriedy = trieda.getMeno();
		for (Ucitel u : vratUcitelov())
			for (String s : u.vratMenoTried())
				if (menoTriedy.equals(s))
					return u;
		return null;
	}

	public Pouzivatel vratPouzivatelaPodlaMena(String meno, String priezvisko) {
		for (Pouzivatel p : pouzivatel)
			if (p instanceof Ziak && meno.equals(p.getMeno()) && priezvisko.equals(p.getPriezvisko()))
				return p;

		return null;
	}
}
