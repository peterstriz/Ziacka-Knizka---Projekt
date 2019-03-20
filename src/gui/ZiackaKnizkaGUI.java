package gui;

import Pouzivatelia.*;
import ZiackaKnizka.ZiackaKnizka;
import javafx.application.Application;
import javafx.stage.Stage;

public class ZiackaKnizkaGUI extends Application {

	private Pouzivatel aktualnyPouzivatel;
	private ZiackaKnizka ziackaKnizka = new ZiackaKnizka();

	public void start(Stage hlavneOkno) {
		Scena scena = new Scena(new LoginScena());

		hlavneOkno.setScene(scena.nastavScene(hlavneOkno, aktualnyPouzivatel, ziackaKnizka));

		hlavneOkno.setTitle("ZiackaKnizka");
		hlavneOkno.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
