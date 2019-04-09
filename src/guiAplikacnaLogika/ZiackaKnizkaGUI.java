package guiAplikacnaLogika;

import gui.ScenaLogin;
//import gui.ScenaUcitelHlavna;
import javafx.application.Application;
import javafx.stage.Stage;
import pouzivatelia.*;
//import udaje.ZiackaKnizkaSingleton;

/**
 * Trieda na spustanie celeho GUI.
 * 
 * @author Peter Striz
 *
 */
public class ZiackaKnizkaGUI extends Application {
//	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();
	private Pouzivatel aktualnyPouzivatel;// = ziackaKnizka.getZiackaKnizka().vratPouzivatela("simova");
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
