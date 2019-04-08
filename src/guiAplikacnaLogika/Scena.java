package guiAplikacnaLogika;

import gui.ScenaInterface;
import javafx.scene.Scene;
import pouzivatelia.Pouzivatel;

public class Scena {
	private ScenaInterface strategy;

	public Scena(ScenaInterface strategy) {
		this.strategy = strategy;
	}

	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		return strategy.nastavScene(aktualnyPouzivatel);
	}

}
