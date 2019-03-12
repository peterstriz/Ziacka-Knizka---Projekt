package gui;

import ZiackaKnizka.*;

import java.awt.event.KeyEvent;

import Pouzivatelia.*;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.text.*;

public class ZiackaKnizkaGUI extends Application {
	private Text vypisMenoPouzivatela = new Text();
	private TextField loginUsername = new TextField("");
	private PasswordField loginPassword = new PasswordField();
	private Button loginSubmit = new Button("Login");
	private Button logout = new Button("Logout");
	private Text loginHlaska = new Text();
	private TableView tabulka = new TableView();
	private Scene loginScena;
	private Scene hlavnaScena;

	public Pouzivatel aktualnyPouzivatel = null;

	ZiackaKnizka ziackaKnizka = new ZiackaKnizka();

	StackPane loginPane = new StackPane();
	StackPane hlavnyPane = new StackPane();

	public void start(Stage hlavneOkno) throws Exception {
		int width = 800;
		int height = 600;

		loginScena = new Scene(loginPane, width, height);
		hlavnaScena = new Scene(hlavnyPane, width, height);

		nastavPane();
		nastavLoginOkno();
		nastavTabulku();

		loginSubmit.setOnAction(e -> {
			loginSubmit(hlavneOkno);
		});

		logout.setOnAction(e -> {
			aktualnyPouzivatel = null;
			loginPassword.setText("");
			loginHlaska.setVisible(false);
			hlavneOkno.setScene(loginScena);
		});

		loginUsername.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				loginSubmit(hlavneOkno);
		});

		loginPassword.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				loginSubmit(hlavneOkno);
		});

		loginSubmit.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				loginSubmit(hlavneOkno);
		});

		hlavneOkno.setScene(hlavnaScena);
		hlavneOkno.setTitle("ZiackaKnizka");
		hlavneOkno.show();
	}

	public void loginSubmit(Stage hlavneOkno) {
		aktualnyPouzivatel = ziackaKnizka.overLogin(loginUsername.getText(), loginPassword.getText(),
				ziackaKnizka.pouzivatel);

		if (aktualnyPouzivatel != null) {
			vypisMenoPouzivatela.setText(ziackaKnizka.vratCeleMeno(aktualnyPouzivatel));
			hlavneOkno.setScene(hlavnaScena);
		} else {
			loginHlaska.setVisible(true);
			loginHlaska.setText("Nespravne prihlasovacie meno alebo heslo, skuste to znova.\n");
		}
	}

	public void nastavTabulku() {

		TableColumn<String, Pouzivatel> column1 = new TableColumn<>("First Name");
		column1.setCellValueFactory(new PropertyValueFactory<>("datum"));

		TableColumn<String, Pouzivatel> column2 = new TableColumn<>("Last Name");
		column2.setCellValueFactory(new PropertyValueFactory<>("hodnota"));

		TableColumn<String, Pouzivatel> column3 = new TableColumn<>("Last Name");
		column3.setCellValueFactory(new PropertyValueFactory<>("maxHodnota"));

		
		tabulka.getColumns().addAll(column1, column2, column3);

		tabulka.getItems().add(new Ziak("John", "Doe"));
		tabulka.getItems().add(new Ziak("Jane", "Deer"));

		// tabulka.setMaxSize(hlavnyPane.getWidth(), 100);
	}

	public void nastavPane() {
		loginPane.getChildren().add(loginUsername);
		loginPane.getChildren().add(loginPassword);
		loginPane.getChildren().add(loginSubmit);
		loginPane.getChildren().add(loginHlaska);

		hlavnyPane.getChildren().add(vypisMenoPouzivatela);
		hlavnyPane.getChildren().add(tabulka);
		hlavnyPane.getChildren().add(logout);
	}

	public void nastavLoginOkno() {
		loginUsername.setPromptText("Username");
		loginPassword.setPromptText("Password");

		nastavPozicieVOkne();
	}

	public void nastavPozicieVOkne() {
		loginUsername.setTranslateY(-40);
		loginUsername.setMaxSize(150, 30);

		loginPassword.setTranslateY(0);
		loginPassword.setMaxSize(150, 30);

		loginSubmit.setTranslateY(40);
		loginSubmit.setMaxSize(150, 30);

		logout.setTranslateY(30 - (hlavnaScena.getHeight() / 2));
		logout.setTranslateX(hlavnaScena.getWidth() / 2 - 50);

		loginHlaska.setTranslateY(90);
		loginHlaska.setFont(new Font(14));
		loginHlaska.setWrappingWidth(200);
		loginHlaska.setTextAlignment(TextAlignment.CENTER);
		loginHlaska.setFill(Color.RED);
		loginHlaska.setVisible(false);

		tabulka.setMaxSize(hlavnaScena.getWidth() - 100, 200);
		tabulka.setTranslateY(-hlavnaScena.getHeight() / 2 + 70);

		vypisMenoPouzivatela.setWrappingWidth(200);
		vypisMenoPouzivatela.setTextAlignment(TextAlignment.RIGHT);
		vypisMenoPouzivatela.setTranslateY(70 - (hlavnaScena.getHeight() / 2));
		vypisMenoPouzivatela.setTranslateX(hlavnaScena.getWidth() / 2 - 110);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
