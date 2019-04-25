package pouzivatelia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import guiAplikacnaLogika.ManazerLogin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import udaje.Trieda;

/**
 * Ucitel je trieda v ktorej su ulozene jednotlive triedy ucitela a jeho osobne
 * udaje.
 * 
 * @author Peter Striz
 * @see OsobneUdaje
 * @see Pouzivatel
 */
public class Ucitel extends OsobneUdaje implements Serializable, Pouzivatel {
	private static final long serialVersionUID = 1L;
	/** Pole vsetkych tried ktore ucitel vyucuje. */
	private List<Trieda> trieda = new ArrayList<>();

	public Ucitel(String meno, String priezvisko) {
		super(meno, priezvisko);
	}

	/**
	 * Pridavanie tried ucitelovy.
	 * 
	 * @param novaTrieda Pole novych tried ktore budu priradene k ucitelovi.
	 */
	public void pridajTriedu(Trieda... novaTrieda) {
		for (Trieda t : novaTrieda)
			trieda.add(t);
	}

	/**
	 * @param i Cislo triedy.
	 * @return Vrati i-tu triedu ucitela.
	 */
	public Trieda vratTriedu(int i) {
		return trieda.get(i);
	}

	/**
	 * @return Vrati mena vsetkych tried ucitela.
	 */
	public ObservableList<String> vratMenoTried() {
		ObservableList<String> menoTriedy = FXCollections.observableArrayList();
		for (Trieda t : this.trieda)
			menoTriedy.add(t.getMeno());
		return menoTriedy;
	}

	/**
	 * Prihlasovanie pouzivatela v ManazerLogin pomocou navrhoveho vzoru Visitor.
	 * 
	 * @see ManazerLogin
	 */
	public void login(ManazerLogin m) {
		m.login(this);
	}
	
	public String vratMojTyp() {
		return "Ucitel";
	}

}
