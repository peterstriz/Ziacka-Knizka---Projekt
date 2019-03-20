package gui;

import Pouzivatelia.Pouzivatel;
import ZiackaKnizka.ZiackaKnizka;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface ScenaInterface {
	public Scene nastavScene(Stage hlavneOkno, Pouzivatel aktualnyPouzivatel, ZiackaKnizka ziackaKnizka);

	public void nastav();

	public void funkcie();

	public void pridajPane();
}
