package guiAplikacnaLogika;

import gui.ScenaLogin;
import pouzivatelia.Pouzivatel;
import udaje.ZiackaKnizkaSingleton;

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
}
