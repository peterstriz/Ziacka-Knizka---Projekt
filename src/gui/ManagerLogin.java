package gui;

import Pouzivatelia.*;
import ZiackaKnizka.ZiackaKnizkaSingleton;

public class ManagerLogin {
	private HlavnyStage hlavnyStage = HlavnyStage.getInstance();
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();

	public Boolean loginSubmit(String username, String password) {
		Pouzivatel aktualnyPouzivatel;
		aktualnyPouzivatel = ziackaKnizka.getZiackaKnizka().vratPouzivatela(username, password);
		if (aktualnyPouzivatel != null) {
			Scena scena = null;
			if (aktualnyPouzivatel instanceof Ziak) {
				scena = new Scena(new ScenaZiakHlavna());
			} else if (aktualnyPouzivatel instanceof Ucitel) {
				scena = new Scena(new ScenaUcitelHlavna());
			} else if (aktualnyPouzivatel instanceof Riaditel) {
				scena = new Scena(new ScenaRiaditelHlavna());
			}
			
			hlavnyStage.getStage().setScene(scena.nastavScene(aktualnyPouzivatel));
			return false;
		} else {
			return true;
		}
	}

}
