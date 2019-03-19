package gui;

import ZiackaKnizka.*;
import Pouzivatelia.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
	private ChoiceBox<String> vyberTrieduUcitela = new ChoiceBox<String>();
	private Text vypisMenoPouzivatelaUcitel = new Text();
	private TextField novaHodnota = new TextField("");
	private TextField novaMaxHodnota = new TextField("");
	private TextField novyDatum = new TextField("");
	private Button novaZnamkaSubmit = new Button("Pridaù");
	private Text novaHlaska = new Text();

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

		novaZnamkaSubmit.setOnAction(e -> {
			novaZnamkaSubmit(hlavneOkno);
		});

		hlavneOkno.setScene(loginScena);
		hlavneOkno.setTitle("ZiackaKnizka");
		hlavneOkno.show();
	}

	public void novaZnamkaSubmit(Stage hlavneOkno) {
		String novaHodnotaS = (String) novaHodnota.getText();
		String novaMaxHodnotaS = (String) novaMaxHodnota.getText();
		String novyDatumS = (String) novyDatum.getText();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate.parse(novyDatumS, formatter);
			Double.parseDouble(novaMaxHodnotaS);
			Double.parseDouble(novaHodnotaS);
			((Ucitel) aktualnyPouzivatel).vratTriedu(cisloTriedy).vratZiaka(cisloZiaka).vratPredmet(cisloPredmetu)
					.pridajNovuZnamku(novaHodnotaS, novaMaxHodnotaS, novyDatumS);
			setTabulka();
			novaHodnota.setText("");
			novaMaxHodnota.setText("");
		} catch (DateTimeParseException | NumberFormatException exc) {
			novaHlaska.setVisible(true);
		}
	}

	public void loginSubmit(Stage hlavneOkno) {
		aktualnyPouzivatel = ziackaKnizka.vratPouzivatela(loginUsername.getText(), loginPassword.getText(),
				ziackaKnizka.pouzivatel);

		if (aktualnyPouzivatel != null) {
			if (aktualnyPouzivatel instanceof Ziak) {
				vyberPredmetov.setItems(((Ziak) aktualnyPouzivatel).vratMenoPredmetov());
				vyberPredmetov.getSelectionModel().selectFirst();
				vypisMenoPouzivatela.setText(aktualnyPouzivatel.vratCeleMeno());
				hlavneOkno.setScene(hlavnaScenaZiak);
			} else if (aktualnyPouzivatel instanceof Ucitel) {
				vyberTrieduUcitela.setItems(((Ucitel) aktualnyPouzivatel).vratMenoTried());
				vyberTrieduUcitela.getSelectionModel().selectFirst();
				// vyberZiakovUcitela.setItems(((Ucitel) aktualnyPouzivatel).vratMenoZiakov());
				vyberZiakovUcitela.getSelectionModel().selectFirst();
				vypisMenoPouzivatelaUcitel.setText(aktualnyPouzivatel.vratCeleMeno());
				vyberPredmetovUcitel.getSelectionModel().selectFirst();
				hlavneOkno.setScene(hlavnaScenaUcitel);
			}
		} else {
			loginHlaska.setVisible(true);
		}
	}

	public void logout(Stage hlavneOkno) {
		loginPassword.setText("");
		loginHlaska.setVisible(false);
		novaHlaska.setVisible(false);
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
		nazov.setText("éiacka kniûka");

		loginPane.getChildren().add(loginUsername);
		loginPane.getChildren().add(loginPassword);
		loginPane.getChildren().add(loginSubmit);
		loginPane.getChildren().add(loginHlaska);
		loginPane.getChildren().add(nazov);
	}

	@SuppressWarnings("unchecked")
	public void nastavHlavneZiak() {
		int velkostTabulky = 150;
		int stredTabulky = 0;
		int velkostPolickaX = 100;
		int velkostPolickaY = 30;

		logout.setTranslateY(30 - (hlavnaScenaZiak.getHeight() / 2));
		logout.setTranslateX(hlavnaScenaZiak.getWidth() / 2 - 50);

		TableColumn<Znamka, String> datumColumn = new TableColumn<>("Datum pisomky");
		datumColumn.setMinWidth(100);
		datumColumn.setCellValueFactory(new PropertyValueFactory<>("datumS"));

		TableColumn<Znamka, Double> hodnotaColumn = new TableColumn<Znamka, Double>("Hodnota");
		hodnotaColumn.setMinWidth(100);
		hodnotaColumn.setCellValueFactory(new PropertyValueFactory<>("hodnotaS"));

		TableColumn<Znamka, Double> maxHodnotaColumn = new TableColumn<Znamka, Double>("Max. Hodnota");
		maxHodnotaColumn.setMinWidth(100);
		maxHodnotaColumn.setCellValueFactory(new PropertyValueFactory<>("maxHodnotaS"));

		tabulkaZiak.getColumns().addAll(hodnotaColumn, maxHodnotaColumn, datumColumn);
		tabulkaZiak.setMaxSize(velkostPolickaX * 3 + 2, velkostTabulky * 2);
		tabulkaZiak.setTranslateY(stredTabulky);
		tabulkaZiak.setPlaceholder(new Label("Ziadne znamky."));

		vypisMenoPouzivatela.setWrappingWidth(200);
		vypisMenoPouzivatela.setTextAlignment(TextAlignment.RIGHT);
		vypisMenoPouzivatela.setTranslateY(70 - (hlavnaScenaZiak.getHeight() / 2));
		vypisMenoPouzivatela.setTranslateX(hlavnaScenaZiak.getWidth() / 2 - 110);

		vyberPredmetov.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					tabulkaZiak.setItems(((Ziak) aktualnyPouzivatel).vratZnamkyPredmetu((int) new_value));
				});
		vyberPredmetov.setTranslateY(-(velkostTabulky + velkostPolickaY));

		hlavnyPaneZiak.getChildren().add(vypisMenoPouzivatela);
		hlavnyPaneZiak.getChildren().add(tabulkaZiak);
		hlavnyPaneZiak.getChildren().add(logout);
		hlavnyPaneZiak.getChildren().add(vyberPredmetov);
	}

	int cisloZiaka;
	int cisloTriedy;
	int cisloPredmetu;

	public void setTabulka() {
		tabulkaUcitel.setItems(((Ucitel) aktualnyPouzivatel).vratTriedu(cisloTriedy).vratZiaka(cisloZiaka)
				.vratZnamkyPredmetu(cisloPredmetu));
	}

	@SuppressWarnings("unchecked")
	public void nastavHlavneUcitel() {
		int velkostTabulky = 150;
		int stredTabulky = 0;
		int velkostPolickaX = 100;
		int velkostPolickaY = 30;
		int medzera = 10;

		logoutUcitel.setTranslateY(30 - (hlavnaScenaZiak.getHeight() / 2));
		logoutUcitel.setTranslateX(hlavnaScenaZiak.getWidth() / 2 - 50);

		TableColumn<Znamka, String> datumColumnUcitel = new TableColumn<>("Datum pisomky");
		datumColumnUcitel.setMinWidth(100);
		datumColumnUcitel.setCellValueFactory(new PropertyValueFactory<>("datumS"));
		datumColumnUcitel.setCellFactory(TextFieldTableCell.<Znamka>forTableColumn());

		TableColumn<Znamka, String> hodnotaColumnUcitel = new TableColumn<Znamka, String>("Hodnota");
		hodnotaColumnUcitel.setMinWidth(100);
		hodnotaColumnUcitel.setCellValueFactory(new PropertyValueFactory<>("hodnotaS"));
		hodnotaColumnUcitel.setCellFactory(TextFieldTableCell.<Znamka>forTableColumn());

		TableColumn<Znamka, String> maxHodnotaColumnUcitel = new TableColumn<Znamka, String>("Max. Hodnota");
		maxHodnotaColumnUcitel.setMinWidth(100);
		maxHodnotaColumnUcitel.setCellValueFactory(new PropertyValueFactory<>("maxHodnotaS"));
		maxHodnotaColumnUcitel.setCellFactory(TextFieldTableCell.<Znamka>forTableColumn());

		tabulkaUcitel.getColumns().addAll(hodnotaColumnUcitel, maxHodnotaColumnUcitel, datumColumnUcitel);
		tabulkaUcitel.setMaxWidth(velkostPolickaX * 3 + 2);
		tabulkaUcitel.setMaxHeight(2 * velkostTabulky);
		tabulkaUcitel.setTranslateY(stredTabulky);
		tabulkaUcitel.setPlaceholder(new Label("Ziadne znamky."));
		tabulkaUcitel.setEditable(true);

		datumColumnUcitel.setOnEditCommit((CellEditEvent<Znamka, String> event) -> {
			TablePosition<Znamka, String> pos = event.getTablePosition();

			String newHodnota = event.getNewValue();

			int row = pos.getRow();
			Znamka znamka = event.getTableView().getItems().get(row);

			znamka.setDatumS(newHodnota);
		});

		hodnotaColumnUcitel.setOnEditCommit((CellEditEvent<Znamka, String> event) -> {
			TablePosition<Znamka, String> pos = event.getTablePosition();

			String newHodnota = event.getNewValue();

			int row = pos.getRow();
			Znamka znamka = event.getTableView().getItems().get(row);

			znamka.setMaxHodnotaS(newHodnota);
		});

		maxHodnotaColumnUcitel.setOnEditCommit((CellEditEvent<Znamka, String> event) -> {
			TablePosition<Znamka, String> pos = event.getTablePosition();

			String newHodnota = event.getNewValue();

			int row = pos.getRow();
			Znamka znamka = event.getTableView().getItems().get(row);

			znamka.setHodnotaS(newHodnota);
		});

		vypisMenoPouzivatelaUcitel.setWrappingWidth(200);
		vypisMenoPouzivatelaUcitel.setTextAlignment(TextAlignment.RIGHT);
		vypisMenoPouzivatelaUcitel.setTranslateY(70 - (hlavnaScenaZiak.getHeight() / 2));
		vypisMenoPouzivatelaUcitel.setTranslateX(hlavnaScenaZiak.getWidth() / 2 - 110);

		vyberPredmetovUcitel.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					cisloPredmetu = (int) new_value;
					setTabulka();
				});
		vyberPredmetovUcitel.setTranslateY(-(velkostTabulky + velkostPolickaY));

		vyberZiakovUcitela.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					cisloZiaka = (int) new_value;
					vyberPredmetovUcitel.setItems(((Ucitel) aktualnyPouzivatel).vratTriedu(cisloTriedy)
							.vratZiaka(cisloZiaka).vratMenoPredmetov());
					vyberPredmetovUcitel.getSelectionModel().selectFirst();
				});
		vyberZiakovUcitela.setTranslateY(-(velkostTabulky + velkostPolickaY * 2 + medzera));

		vyberTrieduUcitela.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					cisloTriedy = (int) new_value;
					vyberZiakovUcitela.setItems(((Ucitel) aktualnyPouzivatel).vratTriedu(cisloTriedy).vratMenoZiakov());
					vyberZiakovUcitela.getSelectionModel().selectFirst();
				});
		vyberTrieduUcitela.setTranslateY(-(velkostTabulky + velkostPolickaY * 3 + medzera * 2));

		// tabulkaUcitel.

		novaHodnota.setPromptText("Hodnota");
		novaHodnota.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		novaHodnota.setTranslateX(stredTabulky - velkostPolickaX);
		novaHodnota.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		novaMaxHodnota.setPromptText("Max. Hodnota");
		novaMaxHodnota.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		novaMaxHodnota.setTranslateX(stredTabulky);
		novaMaxHodnota.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate localDate = LocalDate.now();
		novyDatum.setText(dtf.format(localDate));
		novyDatum.setPromptText("dd.MM.yyyy");
		novyDatum.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		novyDatum.setTranslateX(stredTabulky + velkostPolickaX);
		novyDatum.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		novaZnamkaSubmit.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		novaZnamkaSubmit.setTranslateX(stredTabulky + 2 * velkostPolickaX - medzera);
		novaZnamkaSubmit.setMaxSize(velkostPolickaX - 31, velkostPolickaY);

		novaHlaska.setTranslateY(stredTabulky + velkostTabulky + 70);
		novaHlaska.setFont(new Font(14));
		novaHlaska.setWrappingWidth(velkostPolickaX * 3);
		novaHlaska.setTextAlignment(TextAlignment.CENTER);
		novaHlaska.setFill(Color.RED);
		novaHlaska.setVisible(false);
		novaHlaska.setText("Nepodarilo sa pridaù novÈ zn·mky, skontrolujte Ëi ste ˙daje zadali v spr·vnom tvare.\n");

		hlavnyPaneUcitel.getChildren().add(vypisMenoPouzivatelaUcitel);
		hlavnyPaneUcitel.getChildren().add(tabulkaUcitel);
		hlavnyPaneUcitel.getChildren().add(vyberZiakovUcitela);
		hlavnyPaneUcitel.getChildren().add(vyberTrieduUcitela);
		hlavnyPaneUcitel.getChildren().add(logoutUcitel);
		hlavnyPaneUcitel.getChildren().add(vyberPredmetovUcitel);
		hlavnyPaneUcitel.getChildren().add(novaHodnota);
		hlavnyPaneUcitel.getChildren().add(novaMaxHodnota);
		hlavnyPaneUcitel.getChildren().add(novyDatum);
		hlavnyPaneUcitel.getChildren().add(novaZnamkaSubmit);
		hlavnyPaneUcitel.getChildren().add(novaHlaska);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
