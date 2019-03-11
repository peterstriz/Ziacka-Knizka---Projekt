package gui;

import ZiackaKnizka.*;
import Pouzivatelia.*;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;

public class ZiackaKnizkaGUI extends Application {
	// private Button spustiZiackuKnizku = new Button("Spusti");
	// private TextField textovePole = new TextField();
	public TextArea vypis = new TextArea();
	private ScrollPane skrol = new ScrollPane();
	private TextField loginUsername = new TextField("");
	private TextField loginPassword = new TextField("");
	private Button loginSubmit = new Button("Login");
	private Button logout = new Button("Logout");

	public Pouzivatel aktualnyPouzivatel = null;

	public ZiackaKnizka ziackaKnizka = new ZiackaKnizka();

	public void start(Stage hlavneOkno) {
		int width = 800;
		int height = 600;
		hlavneOkno.setTitle("ZiackaKnizka");

		FlowPane pane = new FlowPane();

		pane.getChildren().add(loginUsername);
		pane.getChildren().add(loginPassword);
		pane.getChildren().add(loginSubmit);
		pane.getChildren().add(logout);
		pane.getChildren().add(vypis);
		skrol.setContent(pane);

		ziackaKnizka.nacitaj();

		nastavLoginOkno();
		loginOkno();

		loginSubmit.setOnAction(e -> {
			aktualnyPouzivatel = ziackaKnizka.overLogin(loginUsername.getText(), loginPassword.getText(),
					ziackaKnizka.ucitel);
			if (aktualnyPouzivatel == null)
				aktualnyPouzivatel = ziackaKnizka.overLogin(loginUsername.getText(), loginPassword.getText(),
						ziackaKnizka.ziak);

			if (aktualnyPouzivatel != null) {
				vypis.appendText(ziackaKnizka.vratCeleMeno(aktualnyPouzivatel) + "\n");

				mainOkno(aktualnyPouzivatel);
			} else {
				vypis.appendText("Nespravne prihlasovacie meno alebo heslo, skuste to znova.\n");

				vypis.setVisible(true);
			}
		});

		logout.setOnAction(e -> {
			aktualnyPouzivatel = null;
			loginOkno();
		});

		/*
		 * spustiZiackuKnizku.setOnAction(e -> { vypis.appendText("klik.\n");
		 * vypis.appendText(ziackaKnizka.vypisZiakovTriedy(ziackaKnizka.trieda[0]));
		 * 
		 * });
		 */

		hlavneOkno.setScene(new Scene(pane, width, height));
		// hlavneOkno.setScene(new Scene(skrol, width, height));
		hlavneOkno.show();
	}

	public void nastavLoginOkno() {
		loginUsername.setPromptText("Username");
		loginPassword.setPromptText("Password");
	}

	public void loginOkno() {
		loginUsername.setVisible(true);
		loginPassword.setVisible(true);
		loginPassword.setText("");
		loginSubmit.setVisible(true);
		vypis.setVisible(false);
		logout.setVisible(false);
	}

	public void mainOkno(Pouzivatel pouzivatel) {
		loginUsername.setVisible(false);
		loginPassword.setVisible(false);
		loginSubmit.setVisible(false);
		vypis.setVisible(true);
		logout.setVisible(true);

		vypis.appendText(ziackaKnizka.vypisZiakovTriedy(ziackaKnizka.trieda[0]));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
