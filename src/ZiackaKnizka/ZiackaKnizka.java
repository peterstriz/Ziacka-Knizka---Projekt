package ZiackaKnizka;

import Pouzivatelia.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.*;

/**
 * Objekt v ktorom su ulozene vsetky triedy a pouzivatelia.
 * 
 * @author Peter Striz
 * @see ZiackaKnizkaSingleton
 */
public class ZiackaKnizka implements Serializable {
	public ZiackaKnizka() {
		nacitajZakladneUdaje();
	}

	private static final long serialVersionUID = 1L;

	/** Pole vsetkych tried. */
	private List<Trieda> trieda = new ArrayList<>();
	/** Pole vsetkych pouzivatelov. */
	private List<Pouzivatel> pouzivatel = new ArrayList<>();

	/**
	 * Metoda na nacitanie iba zopar prvotnych udajov. (Nepouzivana, vzhladom na
	 * serializaciu)
	 */
	private void nacitajZakladneUdaje() {
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

		vratTriedu(0).addZiak(vratPouzivatela("striz98"), vratPouzivatela("vajda98"));
		vratTriedu(1).addZiak(vratPouzivatela("polak98"));

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

	/**
	 * @param p Novy pouzivatel.
	 */
	public void pridajPouzivatela(Pouzivatel p) {
		pouzivatel.add(p);
	}

	/**
	 * Vytvori novu triedu podla mena.
	 * 
	 * @param s Meno novej triedy.
	 */
	public void pridajTriedu(String s) {
		trieda.add(new Trieda(s));
	}

	/**
	 * Zistuje ci pouzivatel sa nachadza v databaze.
	 * 
	 * @param username Pouzivatelske meno, ktore sa bude overovat.
	 * @param password Heslo, ktore sa bude overovat.
	 * @return Vrati <b>pouzivatela</b> pre ktoreho sa podari overit login, ak sa
	 *         nepodari, vrati <b>null</b>.
	 */
	public Pouzivatel vratPouzivatelaLogin(String username, String password) {
		for (Pouzivatel pouzivatelFor : getPouzivatel())
			if (pouzivatelFor != null && pouzivatelFor.overLogin(username, password))
				return (pouzivatelFor);

		return null;
	}

	/**
	 * Vrati pouzivatela podla pouzivatelskeho mena.
	 * 
	 * @param username Hladany pouzivatel.
	 * @return Vrati <b>pouzivatela</b> ktory sa zhoduje so vstupom, ak sa nepodari,
	 *         vrati <b>null</b>.
	 */
	public Pouzivatel vratPouzivatela(String username) {
		for (Pouzivatel pouzivatelFor : getPouzivatel())
			if (pouzivatelFor != null && pouzivatelFor.overUsername(username))
				return (pouzivatelFor);

		return null;
	}

	/**
	 * @param i Cislo triedy.
	 * @return Vrati i-tu triedu.
	 */
	public Trieda vratTriedu(int i) {
		return trieda.get(i);
	}

	/**
	 * Hlada triedu podla mena.
	 * 
	 * @param meno Meno hladanej triedy.
	 * @return Vrati <b>triedu</b> ktory sa zhoduje so vstupom, ak sa nepodari,
	 *         vrati <b>null</b>.
	 */
	private Trieda trieda(String meno) {
		for (Trieda t : trieda) {
			if (t.getMeno().equals(meno))
				return t;
		}
		return null;
	}

	/**
	 * @param i Cislo pouzivatela.
	 * @return Vrati i-teho pouzivatela.
	 */
	private Pouzivatel pouzivatel(int i) {
		return getPouzivatel().get(i);
	}

	/**
	 * Hlada ziaka podla pouzivatelskeho mena.
	 * 
	 * @param username Pouzivatelske meno hladaneho ziaka.
	 * @return Vrati <b>ziaka</b> ktory sa zhoduje so vstupom, ak sa nepodari, vrati
	 *         <b>null</b>.
	 */
	private Ziak ziak(String username) {
		for (Pouzivatel pouzivatelFor : getPouzivatel())
			if (pouzivatelFor != null && pouzivatelFor.overUsername(username))
				return ((Ziak) pouzivatelFor);
		return null;
	}

	/**
	 * Hlada ucitela podla pouzivatelskeho mena.
	 * 
	 * @param username Pouzivatelske meno hladaneho ziaka.
	 * @return Vrati <b>ucitela</b> ktory sa zhoduje so vstupom, ak sa nepodari,
	 *         vrati <b>null</b>.
	 */
	private Ucitel ucitel(String username) {
		for (Pouzivatel pouzivatelFor : getPouzivatel())
			if (pouzivatelFor != null && pouzivatelFor.overUsername(username))
				return ((Ucitel) pouzivatelFor);
		return null;
	}

	/**
	 * @return Vrati nahodny datum od 2000 - 2019.
	 */
	private String getRandomDate() {
		int rok = randBetween(2000, 2019);
		int mesiac = randBetween(1, 12);
		int den = randBetween(1, 28);
		String date = ((den < 10 ? "0" : "") + den + "." + (mesiac < 10 ? "0" : "") + mesiac + "." + rok);

		return date;
	}

	/**
	 * @param start Zaciatok intervalu.
	 * @param end   Koniec intervalu.
	 * @return Vrati nahodne cislo (int) od zaciatku az do konca.
	 */
	private int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public List<Pouzivatel> getPouzivatel() {
		return this.pouzivatel;
	}

	/**
	 * @return Vrati vsetkych ucitelov.
	 */
	public ObservableList<Ucitel> vratUcitelov() {
		ObservableList<Ucitel> ucitelObser = FXCollections.observableArrayList();
		for (Pouzivatel p : getPouzivatel()) {
			if (p instanceof Ucitel)
				ucitelObser.add((Ucitel) p);
		}
		return ucitelObser;
	}

	/**
	 * @return Vrati mena vsetkych ucitelov.
	 */
	public ObservableList<String> vratMenoUcitelov() {
		ObservableList<String> ucitelObser = FXCollections.observableArrayList();
		for (Pouzivatel p : getPouzivatel()) {
			if (p instanceof Ucitel)
				ucitelObser.add(p.vratCeleMeno());
		}
		return ucitelObser;
	}

	/**
	 * @return Vrati mena vsetkych tried.
	 */
	public ObservableList<String> vratMenoTried() {
		ObservableList<String> ucitelObser = FXCollections.observableArrayList();
		for (Trieda t : trieda)
			ucitelObser.add(t.getMeno());
		return ucitelObser;
	}

	/**
	 * @param trieda Trieda, pre ktoru sa bude zistovat ucitel.
	 * @return Vrati ucitela, ktory vyucuje danu triedu.
	 */
	public Ucitel vratUcitelaPodlaTriedy(Trieda trieda) {
		String menoTriedy = trieda.getMeno();
		for (Ucitel u : vratUcitelov())
			for (String s : u.vratMenoTried())
				if (menoTriedy.equals(s))
					return u;
		return null;
	}

	/**
	 * Hlada pouzivatela podla mena a prizviska.
	 * 
	 * @param meno       Hladane meno.
	 * @param priezvisko Hldane priezvisko.
	 * @return Vrati <b>pouzivatela</b> ktory sa zhoduje so vstupom, ak sa nepodari,
	 *         vrati <b>null</b>.
	 */
	public Pouzivatel vratPouzivatelaPodlaMena(String meno, String priezvisko) {
		for (Pouzivatel p : pouzivatel)
			if (meno.equals(p.getMeno()) && priezvisko.equals(p.getPriezvisko()))
				return p;

		return null;
	}
}
