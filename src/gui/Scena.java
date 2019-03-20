package gui;

import Pouzivatelia.Pouzivatel;
import ZiackaKnizka.ZiackaKnizka;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scena {
	private ScenaInterface strategy;

	public Scena(ScenaInterface strategy) {
		this.strategy = strategy;
	}
	
//	public Scene vratScenu() {
//		return vratScenu
//	}

	public Scene nastavScene(Stage hlavneOkno, Pouzivatel aktualnyPouzivatel, ZiackaKnizka ziackaKnizka) {
		return strategy.nastavScene(hlavneOkno, aktualnyPouzivatel, ziackaKnizka);
	}

}
