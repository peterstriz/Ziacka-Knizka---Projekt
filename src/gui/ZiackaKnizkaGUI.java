package gui;

import Pouzivatelia.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class ZiackaKnizkaGUI extends Application {

	private Pouzivatel aktualnyPouzivatel;
	private HlavnyStage hlavnyStage = HlavnyStage.getInstance();

	public void start(Stage hlavneOkno) {
		Scena scena = new Scena(new ScenaLogin());

		hlavnyStage.setStage(hlavneOkno);

		hlavnyStage.getStage().setScene(scena.nastavScene(aktualnyPouzivatel));

		hlavnyStage.getStage().setTitle("ZiackaKnizka");
		hlavnyStage.getStage().show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
