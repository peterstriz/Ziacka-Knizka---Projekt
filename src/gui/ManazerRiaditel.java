package gui;

import Pouzivatelia.Pouzivatel;
import Pouzivatelia.Trieda;
import Pouzivatelia.Ziak;
import ZiackaKnizka.ZiackaKnizkaSingleton;

public class ManazerRiaditel {
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();

	public Boolean pridajNovehoZiaka(Trieda trieda, String meno, String priezvisko) {
		Ziak novyZiak = (Ziak) ziackaKnizka.getZiackaKnizka().vratZiakaPodlaMena(meno, priezvisko);
		if (novyZiak == null || !(novyZiak instanceof Ziak)) {
			return true;
		} else {
			trieda.addZiak(novyZiak);
			return false;
		}
	}

	public void pridajNovehoPouzivatela() {
		new ScenaRiaditelPridaj();
	}

	public void pridajNovuTriedu(String meno) {
		ziackaKnizka.getZiackaKnizka().pridajTriedu(meno);
	}

	public void pridajNovuPredmet(Trieda t, String meno) {
		t.addPredmet(meno);
	}

	public Boolean pridajNovehoPouzivatela(String typ, String meno, String priezvisko, String username,
			String password) {
		// pouzitie RTTI
		try {
			Pouzivatel p;
			Class<?> cls = Class.forName("Pouzivatelia." + typ);
			p = (Pouzivatel) cls.getDeclaredConstructor(String.class, String.class).newInstance(meno, priezvisko);
			p.nastavLogin(username, password);
			ziackaKnizka.getZiackaKnizka().pridajPouzivatela(p);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
}
