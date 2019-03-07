package gui;

import ZiackaKnizka.*;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;

public class ZiackaKnizkaGUI extends Application {
	private Button spustiZiackuKnizku = new Button("Spusti");
	private TextField textovePole = new TextField();
	private TextArea vypis = new TextArea();
	private ScrollPane skrol = new ScrollPane();
	private TextField loginUsername = new TextField();
	private TextField loginPasword = new TextField();
	private Button loginSubmit = new Button("submit");

	public void start(Stage hlavneOkno) {
		int width = 800;
		int height = 600;
		hlavneOkno.setTitle("ZiackaKnizka");

		FlowPane pane = new FlowPane();

		ZiackaKnizka ziackaKnizka = new ZiackaKnizka();

		pane.getChildren().add(loginUsername);
		pane.getChildren().add(loginPasword);
		pane.getChildren().add(loginSubmit);
		pane.getChildren().add(spustiZiackuKnizku);
		pane.getChildren().add(textovePole);
		pane.getChildren().add(vypis);
		skrol.setContent(pane);

		ziackaKnizka.nacitaj();
		
		//loginPassword.();

		spustiZiackuKnizku.setOnAction(e -> {
			vypis.appendText("klik.\n");
			vypis.appendText(ziackaKnizka.vypisZiakovTriedy(ziackaKnizka.trieda[0]));

		});

		hlavneOkno.setScene(new Scene(pane, width, height));
		// hlavneOkno.setScene(new Scene(skrol, width, height));
		hlavneOkno.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
