package gui;

import Pouzivatelia.Pouzivatel;
import Pouzivatelia.Ucitel;
import Pouzivatelia.Ziak;
import ZiackaKnizka.HlavnyStage;
import ZiackaKnizka.ZiackaKnizkaSingleton;

public class ManagerLoginScena {
	private HlavnyStage hlavnyStage = HlavnyStage.getInstance();
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();

	public Boolean loginSubmit(String username, String password) {
		Pouzivatel aktualnyPouzivatel;
		aktualnyPouzivatel = ziackaKnizka.getZiackaKnizka().vratPouzivatela(username, password);
		if (aktualnyPouzivatel != null) {
			Scena scena = null;
			if (aktualnyPouzivatel instanceof Ziak) {
				scena = new Scena(new ZiakHlavnaScena());
			} else if (aktualnyPouzivatel instanceof Ucitel) {
				scena = new Scena(new UcitelHlavnaScena());
			}
			hlavnyStage.getStage().setScene(scena.nastavScene(aktualnyPouzivatel));
			return false;
		} else {
			return true;
		}
	}

}
