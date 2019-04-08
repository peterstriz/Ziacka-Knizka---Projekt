package gui;

import javafx.scene.Scene;
import pouzivatelia.Pouzivatel;

public interface ScenaInterface {
	public Scene nastavScene(Pouzivatel aktualnyPouzivatel);

	public void nastav();

	public void funkcie();

	public void pridajPane();
}
