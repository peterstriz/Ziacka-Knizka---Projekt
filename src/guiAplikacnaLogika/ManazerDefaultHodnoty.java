package guiAplikacnaLogika;

import gui.DefaultHodnoty;
import gui.ScenaLogin;
import pouzivatelia.Pouzivatel;
import udaje.ZiackaKnizkaSingleton;

/**
 * Aplikacna logika stojaca za classov DefaultHodnoty.
 * 
 * @author Peter Striz
 * @see DefaultHodnoty
 */
public class ManazerDefaultHodnoty {
	private HlavnyStage singleton = HlavnyStage.getInstance();
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();

	public void logout() {
		Scena scena = new Scena(new ScenaLogin());
		singleton.getStage().setScene(scena.nastavScene(null));
		ziackaKnizka.serializuj();
	}

	public Pouzivatel vratPouzivatelaPodlaMena(String menoInput) {
		try {
			String m = menoInput.split(" (?!.* )")[0];
			String p = menoInput.split(" (?!.* )")[1];

			return ziackaKnizka.getZiackaKnizka().vratPouzivatelaPodlaMena(m, p);
		} catch (Exception e) {

		}
		return null;
	}

	public void zmenEmail(Pouzivatel p, String e) {
		p.setEmail(e);
	}

	public boolean zmenHeslo(Pouzivatel p, String stare, String nove) {
		if (p.overPassword(stare) && nove.length() > 3) {
			p.setPassword(nove);
			return true;
		} else
			return false;
	}

}
