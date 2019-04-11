package gui;

import javafx.scene.Scene;
import pouzivatelia.Pouzivatel;

/**
 * Interface ktory bude pre kazdu scenu implementovany.
 * 
 * @author Peter Striz
 */
public interface ScenaInterface {
	/**
	 * @param aktualnyPouzivatel Prihlaseny pouzivatel.
	 * @return Vrati aktualnu scenu.
	 */
	public Scene nastavScene(Pouzivatel aktualnyPouzivatel);

	/** Nastavi pozicie elementov v okne */
	public void nastav();

	/** Nastavi funkcie jednotlivych elementov. */
	public void funkcie();

	/** Prida elementy do pane-u. */
	public void pridajPane();
}
