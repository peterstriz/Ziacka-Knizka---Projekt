package gui;

import Pouzivatelia.Pouzivatel;
import javafx.scene.Scene;

public interface ScenaInterface {
	public Scene nastavScene(Pouzivatel aktualnyPouzivatel);

	public void nastav();

	public void funkcie();

	public void pridajPane();
}
