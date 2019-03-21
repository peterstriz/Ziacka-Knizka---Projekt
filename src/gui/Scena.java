package gui;

import Pouzivatelia.Pouzivatel;
import javafx.scene.Scene;

public class Scena {
	private ScenaInterface strategy;

	public Scena(ScenaInterface strategy) {
		this.strategy = strategy;
	}

	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		return strategy.nastavScene(aktualnyPouzivatel);
	}

}
