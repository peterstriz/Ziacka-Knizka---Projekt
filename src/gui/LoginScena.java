package gui;

import Pouzivatelia.*;
import ZiackaKnizka.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginScena implements ScenaInterface {

	private int width = 800;
	private int height = 600;
	private Pouzivatel aktualnyPouzivatel;
	private ZiackaKnizka ziackaKnizka;

	private TextField loginUsername = new TextField("");
	private PasswordField loginPassword = new PasswordField();
	private Button loginSubmit = new Button("Login");
	private Text loginHlaska = new Text();
	private Text nazov = new Text();

	private Scene mojaScena;
	private StackPane mojPane = new StackPane();
	private Stage hlavneOkno;

	public Scene nastavScene(Stage hlavneOkno, Pouzivatel aktualnyPouzivatel, ZiackaKnizka ziackaKnizka) {
		this.hlavneOkno = hlavneOkno;
		this.aktualnyPouzivatel = aktualnyPouzivatel;
		this.ziackaKnizka = ziackaKnizka;
		nastav();
		funkcie();
		pridajPane();

		mojaScena = new Scene(mojPane, width, height);

		return mojaScena;
	}

	public void funkcie() {
		loginUsername.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				;// oginSubmit();
		});

		loginPassword.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				loginSubmit();
		});

		loginSubmit.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				; // loginSubmit(hlavneOkno);
		});

		loginHlaska.setText("Nesprávne prihlasovacie meno alebo heslo, skúste to znova.\n");
	}

	public void pridajPane() {
		mojPane.getChildren().add(loginUsername);
		mojPane.getChildren().add(loginPassword);
		mojPane.getChildren().add(loginSubmit);
		mojPane.getChildren().add(loginHlaska);
		mojPane.getChildren().add(nazov);
	}

	public void nastav() {
		loginUsername.setPromptText("Username");
		loginUsername.setTranslateY(-40);
		loginUsername.setMaxSize(150, 30);

		loginPassword.setPromptText("Password");
		loginPassword.setTranslateY(0);
		loginPassword.setMaxSize(150, 30);

		loginSubmit.setTranslateY(40);
		loginSubmit.setMaxSize(150, 30);

		loginHlaska.setTranslateY(90);
		loginHlaska.setFont(new Font(14));
		loginHlaska.setWrappingWidth(200);
		loginHlaska.setTextAlignment(TextAlignment.CENTER);
		loginHlaska.setFill(Color.RED);
		loginHlaska.setVisible(false);

		nazov.setTranslateY(-120);
		nazov.setFont(new Font(26));
		nazov.setWrappingWidth(200);
		nazov.setTextAlignment(TextAlignment.CENTER);
		nazov.setFill(Color.ORANGE);
		nazov.setVisible(true);
		nazov.setText("Žiacka knižka");
	}

	public void loginSubmit() {
		aktualnyPouzivatel = ziackaKnizka.vratPouzivatela(loginUsername.getText(), loginPassword.getText(),
				ziackaKnizka.pouzivatel);
//		System.out.print("["
//				+ aktualnyPouzivatel + "]" + ziackaKnizka.vratPouzivatela(loginUsername.getText(),
//						loginPassword.getText(), ziackaKnizka.pouzivatel)
//				+ "]" + aktualnyPouzivatel.vratCeleMeno() + "\n");
		if (aktualnyPouzivatel != null) {
			if (aktualnyPouzivatel instanceof Ziak) {
				Scena scena;
				scena = new Scena(new ZiakHlavnaScena());
				hlavneOkno.setScene(scena.nastavScene(hlavneOkno, aktualnyPouzivatel, ziackaKnizka));
			} else if (aktualnyPouzivatel instanceof Ucitel) {
				Scena scena;
				scena = new Scena(new UcitelHlavnaScena());
				hlavneOkno.setScene(scena.nastavScene(hlavneOkno, aktualnyPouzivatel, ziackaKnizka));
			}
		} else {
			loginHlaska.setVisible(true);
		}
	}

}
