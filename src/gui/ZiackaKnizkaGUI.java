package gui;

//"composite" na ukladanie ziakov v triedach
// 13.3. pridany zakladny choicebox na vyber predmetu v tabulke

import ZiackaKnizka.*;

import java.util.Date;

import Pouzivatelia.*;

import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.text.*;

public class ZiackaKnizkaGUI extends Application {
	private TextField loginUsername = new TextField("");
	private PasswordField loginPassword = new PasswordField();
	private Button loginSubmit = new Button("Login");
	private Text loginHlaska = new Text();
	private Text nazov = new Text();

	private Button logout = new Button("Logout");
	private TableView<Znamka> tabulkaZiak = new TableView<>();
	private ChoiceBox<String> vyberPredmetov = new ChoiceBox<String>();
	private Text vypisMenoPouzivatela = new Text();

	private ChoiceBox<String> vyberZiakovUcitela = new ChoiceBox<String>();
	private TableView<Znamka> tabulkaUcitel = new TableView<>();
	private Button logoutUcitel = new Button("Logout");
	private ChoiceBox<String> vyberPredmetovUcitel = new ChoiceBox<String>();
	private Text vypisMenoPouzivatelaUcitel = new Text();

	private Scene loginScena;
	private Scene hlavnaScenaZiak;
	private Scene hlavnaScenaUcitel;

	public Pouzivatel aktualnyPouzivatel = null;

//	Serial serial = new Serial();
	ZiackaKnizka ziackaKnizka = new ZiackaKnizka();
//	ZiackaKnizka ziackaKnizka;

	StackPane loginPane = new StackPane();
	StackPane hlavnyPaneZiak = new StackPane();
	StackPane hlavnyPaneUcitel = new StackPane();

	public void start(Stage hlavneOkno) throws Exception {
		int width = 800;
		int height = 600;

		loginScena = new Scene(loginPane, width, height);
		hlavnaScenaZiak = new Scene(hlavnyPaneZiak, width, height);
		hlavnaScenaUcitel = new Scene(hlavnyPaneUcitel, width, height);

//		Serial serial =  new Serial();
//		serial.zapamatajData();
//		ziackaKnizka = serial.vratData();

//		if (aktualnyPouzivatel instanceof Ziak)
//			((Ziak) aktualnyPouzivatel).predmet.get(0);
//			;

		nastavHlavneZiak();
		nastavHlavneUcitel();
		nastavLogin();

		loginSubmit.setOnAction(e -> {
			loginSubmit(hlavneOkno);
		});

		logout.setOnAction(e -> {
			logout(hlavneOkno);
		});

		logoutUcitel.setOnAction(e -> {
			logout(hlavneOkno);
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

		hlavneOkno.setScene(loginScena);
		hlavneOkno.setTitle("ZiackaKnizka");
		hlavneOkno.show();
	}

	public void loginSubmit(Stage hlavneOkno) {
		aktualnyPouzivatel = ziackaKnizka.overLogin(loginUsername.getText(), loginPassword.getText(),
				ziackaKnizka.pouzivatel);

		if (aktualnyPouzivatel != null) {
			vypisMenoPouzivatela.setText(ziackaKnizka.vratCeleMeno(aktualnyPouzivatel));

			if (aktualnyPouzivatel instanceof Ziak) {
				vyberPredmetov.setItems(aktualnyPouzivatel.vratMenoPredmetov());
				vyberPredmetov.getSelectionModel().selectFirst();
				hlavneOkno.setScene(hlavnaScenaZiak);
			} else if (aktualnyPouzivatel instanceof Ucitel) {
				System.out.print("test");
				vyberZiakovUcitela.setItems(((Ucitel) aktualnyPouzivatel).vratMenoZiakov());
				vyberZiakovUcitela.getSelectionModel().selectFirst();
				// vyberPredmetov.setItems(aktualnyPouzivatel.vratMenoPredmetov());
				vyberPredmetovUcitel.getSelectionModel().selectFirst();
				hlavneOkno.setScene(hlavnaScenaUcitel);
				// System.out.print("test");
			}
		} else {
			loginHlaska.setVisible(true);
		}
	}

	public void logout(Stage hlavneOkno) {
		loginPassword.setText("");
		loginHlaska.setVisible(false);
		tabulkaZiak.setItems(null);
		aktualnyPouzivatel = null;

		hlavneOkno.setScene(loginScena);
	}

	public void nastavLogin() {
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
		loginHlaska.setText("Nespravne prihlasovacie meno alebo heslo, skuste to znova.\n");

		nazov.setTranslateY(-120);
		nazov.setFont(new Font(26));
		nazov.setWrappingWidth(200);
		nazov.setTextAlignment(TextAlignment.CENTER);
		nazov.setFill(Color.ORANGE);
		nazov.setVisible(true);
		nazov.setText("Žiacka knižka");

		loginPane.getChildren().add(loginUsername);
		loginPane.getChildren().add(loginPassword);
		loginPane.getChildren().add(loginSubmit);
		loginPane.getChildren().add(loginHlaska);
		loginPane.getChildren().add(nazov);
	}

	@SuppressWarnings("unchecked")
	public void nastavHlavneZiak() {
		logout.setTranslateY(30 - (hlavnaScenaZiak.getHeight() / 2));
		logout.setTranslateX(hlavnaScenaZiak.getWidth() / 2 - 50);

		TableColumn<Znamka, Date> datumColumn = new TableColumn<>("Datum pisomky");
		datumColumn.setMinWidth(100);
		datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));

		TableColumn<Znamka, Double> hodnotaColumn = new TableColumn<Znamka, Double>("Hodnota");
		hodnotaColumn.setMinWidth(100);
		hodnotaColumn.setCellValueFactory(new PropertyValueFactory<>("hodnota"));

		TableColumn<Znamka, Double> maxHodnotaColumn = new TableColumn<Znamka, Double>("Max. Hodnota");
		maxHodnotaColumn.setMinWidth(100);
		maxHodnotaColumn.setCellValueFactory(new PropertyValueFactory<>("maxHodnota"));

		tabulkaZiak.getColumns().addAll(hodnotaColumn, maxHodnotaColumn, datumColumn);
		tabulkaZiak.setMaxSize(301, 200);
		tabulkaZiak.setTranslateY(-hlavnaScenaZiak.getHeight() / 4 + 100);
		tabulkaZiak.setPlaceholder(new Label("Nie je zvoleny ziadny predmet."));

		vypisMenoPouzivatela.setWrappingWidth(200);
		vypisMenoPouzivatela.setTextAlignment(TextAlignment.RIGHT);
		vypisMenoPouzivatela.setTranslateY(70 - (hlavnaScenaZiak.getHeight() / 2));
		vypisMenoPouzivatela.setTranslateX(hlavnaScenaZiak.getWidth() / 2 - 110);

		// https://www.geeksforgeeks.org/javafx-choicebox/
		vyberPredmetov.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					tabulkaZiak.setItems(aktualnyPouzivatel.vratZnamkyPredmetu((int) new_value));
				});
		vyberPredmetov.setTranslateY(-200);

		hlavnyPaneZiak.getChildren().add(vypisMenoPouzivatela);
		hlavnyPaneZiak.getChildren().add(tabulkaZiak);
		hlavnyPaneZiak.getChildren().add(logout);
		hlavnyPaneZiak.getChildren().add(vyberPredmetov);
	}

	int cisloZiaka;

	@SuppressWarnings("unchecked")
	public void nastavHlavneUcitel() {

		logoutUcitel.setTranslateY(30 - (hlavnaScenaZiak.getHeight() / 2));
		logoutUcitel.setTranslateX(hlavnaScenaZiak.getWidth() / 2 - 50);

		TableColumn<Znamka, Date> datumColumnUcitel = new TableColumn<>("Datum pisomky");
		datumColumnUcitel.setMinWidth(100);
		datumColumnUcitel.setCellValueFactory(new PropertyValueFactory<>("datum"));

		TableColumn<Znamka, Double> hodnotaColumnUcitel = new TableColumn<Znamka, Double>("Hodnota");
		hodnotaColumnUcitel.setMinWidth(100);
		hodnotaColumnUcitel.setCellValueFactory(new PropertyValueFactory<>("hodnota"));

		TableColumn<Znamka, Double> maxHodnotaColumnUcitel = new TableColumn<Znamka, Double>("Max. Hodnota");
		maxHodnotaColumnUcitel.setMinWidth(100);
		maxHodnotaColumnUcitel.setCellValueFactory(new PropertyValueFactory<>("maxHodnota"));

		tabulkaUcitel.getColumns().addAll(hodnotaColumnUcitel, maxHodnotaColumnUcitel, datumColumnUcitel);
		tabulkaUcitel.setMaxSize(301, 200);
		tabulkaUcitel.setTranslateY(-hlavnaScenaZiak.getHeight() / 4 + 100);
		tabulkaUcitel.setPlaceholder(new Label("Nie je zvoleny ziadny predmet."));

		vypisMenoPouzivatelaUcitel.setWrappingWidth(200);
		vypisMenoPouzivatelaUcitel.setTextAlignment(TextAlignment.RIGHT);
		vypisMenoPouzivatelaUcitel.setTranslateY(70 - (hlavnaScenaZiak.getHeight() / 2));
		vypisMenoPouzivatelaUcitel.setTranslateX(hlavnaScenaZiak.getWidth() / 2 - 110);

		// https://www.geeksforgeeks.org/javafx-choicebox/
		vyberPredmetovUcitel.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					tabulkaUcitel.setItems(
							((Ucitel) aktualnyPouzivatel).vratZiaka(cisloZiaka).vratZnamkyPredmetu((int) new_value));
				});
		vyberPredmetovUcitel.setTranslateY(-200);

		vyberZiakovUcitela.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					cisloZiaka = (int) new_value;
					vyberPredmetovUcitel
							.setItems(((Ucitel) aktualnyPouzivatel).vratZiaka(cisloZiaka).vratMenoPredmetov());
				});
		vyberZiakovUcitela.setTranslateY(-250);

		hlavnyPaneUcitel.getChildren().add(vypisMenoPouzivatelaUcitel);
		hlavnyPaneUcitel.getChildren().add(tabulkaUcitel);
		hlavnyPaneUcitel.getChildren().add(vyberZiakovUcitela);
		hlavnyPaneUcitel.getChildren().add(logoutUcitel);
		hlavnyPaneUcitel.getChildren().add(vyberPredmetovUcitel);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
