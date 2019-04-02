package gui;

import Pouzivatelia.*;
import ZiackaKnizka.ZiackaKnizkaSingleton;
import javafx.application.Application;
import javafx.stage.Stage;

public class ZiackaKnizkaGUI extends Application {
//	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();
	private Pouzivatel aktualnyPouzivatel; // = ziackaKnizka.getZiackaKnizka().vratPouzivatela("simova");
	private HlavnyStage hlavnyStage = HlavnyStage.getInstance();

	public void start(Stage hlavneOkno) {
		Scena scena = new Scena(new ScenaLogin());
//		Scena scena = new Scena(new ScenaUcitelHlavna());

		hlavnyStage.setStage(hlavneOkno);

		hlavnyStage.getStage().setScene(scena.nastavScene(aktualnyPouzivatel));

		hlavnyStage.getStage().setTitle("ZiackaKnizka");
		hlavnyStage.getStage().show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
