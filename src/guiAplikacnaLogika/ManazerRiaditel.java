package guiAplikacnaLogika;

import gui.ScenaRiaditelHlavna;
import gui.ScenaRiaditelPridaj;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pouzivatelia.Pouzivatel;
import pouzivatelia.Ucitel;
import pouzivatelia.Ziak;
import udaje.Trieda;
import udaje.ZiackaKnizkaSingleton;

/**
 * Aplikacna logika stojaca za scenou pre Riaditela.
 * 
 * @author Peter Striz
 * @see ScenaRiaditelHlavna
 */
public class ManazerRiaditel {
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();
	private ObservableList<String> typPouzivatela = FXCollections.observableArrayList(Ziak.class.getSimpleName(),
			Ucitel.class.getSimpleName());
	private String typNovehoPouzivatela;

	public Boolean pridajNovehoZiaka(Trieda trieda, String meno, String priezvisko) {
		Ziak novyZiak = (Ziak) ziackaKnizka.getZiackaKnizka().vratPouzivatelaPodlaMena(meno, priezvisko);
		if (novyZiak == null || !(novyZiak instanceof Ziak)) {
			return true;
		} else {
			trieda.addZiak(novyZiak);
			return false;
		}
	}

	/** Vytvory nove okno do v ktorom sa bude pridavat pouzivatel. */
	public void pridajNovehoPouzivatela() {
		new ScenaRiaditelPridaj();
	}

	public void pridajNovuTriedu(String meno) {
		ziackaKnizka.getZiackaKnizka().pridajTriedu(meno);
	}

	public void pridajNovyPredmet(Trieda t, String meno) {
		t.addPredmet(meno);
	}

	public Boolean pridajNovehoPouzivatela(String meno, String priezvisko, String username, String password) {
		// pouzitie RTTI
		try {
			Pouzivatel p;
			Class<?> cls = Class.forName("pouzivatelia." + typNovehoPouzivatela);
			p = (Pouzivatel) cls.getDeclaredConstructor(String.class, String.class).newInstance(meno, priezvisko);
			p.nastavLogin(username, password);
			ziackaKnizka.getZiackaKnizka().pridajPouzivatela(p);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public ObservableList<String> getTypPouzivatela() {
		return typPouzivatela;
	}

	public String getTypNovehoPouzivatela() {
		return typNovehoPouzivatela;
	}

	public void setTypNovehoPouzivatela(int i) {
		this.typNovehoPouzivatela = typPouzivatela.get(i);
	}
}
