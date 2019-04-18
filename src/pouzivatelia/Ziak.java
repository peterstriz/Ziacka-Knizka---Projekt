package pouzivatelia;

import java.io.Serializable;
import java.util.*;

import guiAplikacnaLogika.ManazerLogin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import udaje.Predmet;
import udaje.Znamka;

/**
 * Ziak je trieda v ktorej su ulozene jednotlive predmety ziaka a jeho osobne
 * udaje.
 * 
 * @author Peter Striz
 * @see Pouzivatel
 * @see OsobneUdaje
 */
public class Ziak extends OsobneUdaje implements Serializable, Pouzivatel {
	public Ziak(String meno, String priezvisko) {
		super(meno, priezvisko);
	}

	private static final long serialVersionUID = 1L;
	/** Pole vsetkych predmetov ziaka. */
	private List<Predmet> predmet = new ArrayList<>();

	/**
	 * @param i Poradove cislo predmetu.
	 * @return Vrati i-ty prvok z pola predmetov ziaka.
	 */
	public Predmet vratPredmet(int i) {
		return this.predmet.get(i);
	}

	/**
	 * @param i Poradove cislo predmetu ktoreho bude returnovat znamky.
	 * @return Vrati vsetky znamky i-teho predmetu ziaka.
	 */
	public ObservableList<Znamka> vratZnamkyPredmetu(int i) {
		return this.predmet.get(i).vratZnamku();
	}

	/**
	 * @return Vrati mena vsetkych predmetov ziaka.
	 */
	public ObservableList<String> vratMenoPredmetov() {
		ObservableList<String> predmetObser = FXCollections.observableArrayList();
		for (Predmet p : this.predmet)
			predmetObser.add(p.getMeno());
		return predmetObser;
	}

	/**
	 * Prida novy predmet ziakovy, ale iba ak uz taky predmet nemal doteraz.
	 * 
	 * @param predmetNovy Nazov noveho predmetu.
	 */
	public void pridajPredmet(String predmetNovy) {
		if (!overPredmet(predmetNovy))
			predmet.add(new Predmet(predmetNovy));
	}

	/**
	 * Prida novu znamku do predmetu.
	 * 
	 * @param cisloPredmetu Cislo predmetu do ktoreho bude zapisovat znamku.
	 * @param hodnota       Pocet bodov z pisomky.
	 * @param maxHodnota    Maximalny pocet bodov ktory sa dal ziskat z pisomky.
	 * @param datum         Datum pisania pisomky.
	 */
	public void pridajZnamku(int cisloPredmetu, String hodnota, String maxHodnota, String datum) {
		predmet.get(cisloPredmetu).pridajNovuZnamku(hodnota, maxHodnota, datum);
	}

	/**
	 * @return Vrati vsetky predmety ziaka.
	 */
	public List<Predmet> getPredmet() {
		return this.predmet;
	}

	/**
	 * Zistuje ci ziak uz dany predmet ma.
	 * 
	 * @param meno Meno predmetu ktory zistujeme ci uz je v poli predmetov.
	 * @return <b>true</b> Predmet sa uz nachadza v poli predmetov. <b>false</b>
	 *         Dany predmet este nebol vytvoreny.
	 */
	private Boolean overPredmet(String meno) {
		for (Predmet p : this.predmet)
			if (meno.equals(p.getMeno()))
				return true;
		return false;
	}

	/**
	 * Prihlasovanie pouzivatela v ManazerLogin pomocou navrhoveho vzoru Visitor.
	 * 
	 * @see ManazerLogin
	 */
	public void login(ManazerLogin m) {
		m.login(this);
	}

}
