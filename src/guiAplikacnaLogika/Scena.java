package guiAplikacnaLogika;

import gui.ScenaInterface;
import javafx.scene.Scene;
import pouzivatelia.Pouzivatel;

/**
 * Class na premenu jednotlivych scen pomocou navrhoveho vzoru strategy.
 * 
 * @author Peter Striz
 * @see ScenaInterface
 */
public class Scena {
	/** Scena ktora bude zobrazovana. */
	private ScenaInterface strategy;

	public Scena(ScenaInterface strategy) {
		this.strategy = strategy;
	}

	/**
	 * @param aktualnyPouzivatel Prihlaseny pouzivatel.
	 * @return Vrati celu novo vygenerovanu scenu.
	 */
	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		return strategy.nastavScene(aktualnyPouzivatel);
	}

}
