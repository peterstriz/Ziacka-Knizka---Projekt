package gui;

import Pouzivatelia.Pouzivatel;
import Pouzivatelia.Trieda;
import Pouzivatelia.Ziak;
import ZiackaKnizka.ZiackaKnizkaSingleton;

public class ManazerRiaditel {
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();

	public Boolean pridajNovehoZiaka(Trieda trieda, String meno, String priezvisko) {
		Ziak novyZiak = (Ziak) ziackaKnizka.getZiackaKnizka().vratPouzivatelaPodlaMena(meno, priezvisko);
		if (novyZiak == null || !(novyZiak instanceof Ziak)) {
			return true;
		} else {
			trieda.pridajZiaka(novyZiak);
			return false;
		}
	}

	public void pridajNovehoPouzivatela() {
		new ScenaRiaditelPridaj();
	}

	public Boolean pridajNovehoPouzivatela(String typ, String meno, String priezvisko, String username, String password) {
		Pouzivatel p = null;
		try {
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
