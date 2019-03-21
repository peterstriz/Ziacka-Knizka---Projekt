package gui;

import Pouzivatelia.*;
import ZiackaKnizka.HlavnyStage;

import javafx.beans.value.ChangeListener;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class UcitelHlavnaScena implements ScenaInterface {
	private int width = 800;
	private int height = 600;
	private Pouzivatel aktualnyPouzivatel;
	private Scene mojaScena;
	private StackPane mojPane = new StackPane();
	private HlavnyStage singleton = HlavnyStage.getInstance();
	private ManazerUcitelHlavnaScena mojManazer = new ManazerUcitelHlavnaScena();

	private ChoiceBox<String> vyberZiaka = new ChoiceBox<String>();
	private TableView<Znamka> tabulka = new TableView<>();
	private Button logoutUcitel = new Button("Logout");
	private ChoiceBox<String> vyberPredmetov = new ChoiceBox<String>();
	private ChoiceBox<String> vyberTriedu = new ChoiceBox<String>();
	private Text vypisMenoPouzivatela = new Text();
	private TextField novaHodnota = new TextField("");
	private TextField novaMaxHodnota = new TextField("");
	private TextField novyDatum = new TextField("");
	private Button novaZnamkaSubmit = new Button("Pridaù");
	private Text novaHlaska = new Text();

	private int cisloZiaka;
	private int cisloTriedy;
	private int cisloPredmetu;

	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		this.aktualnyPouzivatel = aktualnyPouzivatel;
		mojaScena = new Scene(mojPane, width, height);

		nastav();
		funkcie();
		pridajPane();

		return mojaScena;
	}

	public void nastav() {
		int velkostTabulky = 150;
		int stredTabulky = 0;
		int velkostPolickaX = 100;
		int velkostPolickaY = 30;
		int medzera = 10;

		logoutUcitel.setTranslateY(30 - (mojaScena.getHeight() / 2));
		logoutUcitel.setTranslateX(mojaScena.getWidth() / 2 - 50);

		tabulka.setMaxWidth(velkostPolickaX * 3 + 2);
		tabulka.setMaxHeight(2 * velkostTabulky);
		tabulka.setTranslateY(stredTabulky);
		tabulka.setPlaceholder(new Label("Ziadne znamky."));
		tabulka.setEditable(true);

		vypisMenoPouzivatela.setWrappingWidth(200);
		vypisMenoPouzivatela.setTextAlignment(TextAlignment.RIGHT);
		vypisMenoPouzivatela.setTranslateY(70 - (mojaScena.getHeight() / 2));
		vypisMenoPouzivatela.setTranslateX(mojaScena.getWidth() / 2 - 110);

		vyberPredmetov.setTranslateY(-(velkostTabulky + velkostPolickaY));
		vyberZiaka.setTranslateY(-(velkostTabulky + velkostPolickaY * 2 + medzera));
		vyberTriedu.setTranslateY(-(velkostTabulky + velkostPolickaY * 3 + medzera * 2));

		novaHodnota.setPromptText("Hodnota");
		novaHodnota.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		novaHodnota.setTranslateX(stredTabulky - velkostPolickaX);
		novaHodnota.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		novaMaxHodnota.setPromptText("Max. Hodnota");
		novaMaxHodnota.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		novaMaxHodnota.setTranslateX(stredTabulky);
		novaMaxHodnota.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

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

	}

	@SuppressWarnings("unchecked")
	public void funkcie() {
		vypisMenoPouzivatela.setText(aktualnyPouzivatel.vratCeleMeno());

		novyDatum.setText(mojManazer.vratDnesnyDatum());

		novaHlaska.setText("Nepodarilo sa pridaù novÈ zn·mky, skontrolujte Ëi ste ˙daje zadali v spr·vnom tvare.\n");

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
		tabulka.getColumns().addAll(hodnotaColumnUcitel, maxHodnotaColumnUcitel, datumColumnUcitel);

		hodnotaColumnUcitel.setOnEditCommit((CellEditEvent<Znamka, String> event) -> {
			TablePosition<Znamka, String> pos = event.getTablePosition();
			String newHodnotaS = event.getNewValue();
			int row = pos.getRow();
			Znamka znamka = event.getTableView().getItems().get(row);

			Boolean vipis = mojManazer.setHodnota(znamka, newHodnotaS);
			novaHlaska.setVisible(vipis);
		});

		maxHodnotaColumnUcitel.setOnEditCommit((CellEditEvent<Znamka, String> event) -> {
			TablePosition<Znamka, String> pos = event.getTablePosition();
			String newMaxHodnotaS = event.getNewValue();
			int row = pos.getRow();
			Znamka znamka = event.getTableView().getItems().get(row);

			Boolean vipis = mojManazer.setMaxHodnota(znamka, newMaxHodnotaS);
			novaHlaska.setVisible(vipis);
		});

		datumColumnUcitel.setOnEditCommit((CellEditEvent<Znamka, String> event) -> {
			TablePosition<Znamka, String> pos = event.getTablePosition();
			String newDatumS = event.getNewValue();
			int row = pos.getRow();
			Znamka znamka = event.getTableView().getItems().get(row);

			Boolean vipis = mojManazer.setDatum(znamka, newDatumS);
			novaHlaska.setVisible(vipis);
		});

		vyberPredmetov.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					cisloPredmetu = (int) new_value;
					updateTabulka();
				});

		vyberZiaka.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					cisloZiaka = (int) new_value;
					vyberPredmetov.setItems(((Ucitel) aktualnyPouzivatel).vratTriedu(cisloTriedy).vratZiaka(cisloZiaka)
							.vratMenoPredmetov());
					vyberPredmetov.getSelectionModel().selectFirst();
				});

		vyberTriedu.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					cisloTriedy = (int) new_value;
					vyberZiaka.setItems(((Ucitel) aktualnyPouzivatel).vratTriedu(cisloTriedy).vratMenoZiakov());
					vyberZiaka.getSelectionModel().selectFirst();
				});

		logoutUcitel.setOnAction(e -> {
			logout();
		});

		vyberTriedu.setItems(((Ucitel) aktualnyPouzivatel).vratMenoTried());
		vyberTriedu.getSelectionModel().selectFirst();
		vyberZiaka.getSelectionModel().selectFirst();
		vyberPredmetov.getSelectionModel().selectFirst();

		novaZnamkaSubmit.setOnAction(e -> {
			novaZnamkaSubmit();
		});

	}

	public void pridajPane() {
		mojPane.getChildren().addAll(vypisMenoPouzivatela, tabulka, vyberZiaka, vyberTriedu, logoutUcitel,
				vyberPredmetov, novaHodnota, novaMaxHodnota, novyDatum, novaZnamkaSubmit, novaHlaska);
	}

	public void updateTabulka() {
		tabulka.setItems(((Ucitel) aktualnyPouzivatel).vratTriedu(cisloTriedy).vratZiaka(cisloZiaka)
				.vratZnamkyPredmetu(cisloPredmetu));
	}

	public void novaZnamkaSubmit() {
		String novaHodnotaS = (String) novaHodnota.getText();
		String novaMaxHodnotaS = (String) novaMaxHodnota.getText();
		String novyDatumS = (String) novyDatum.getText();
		Predmet predmet = ((Ucitel) aktualnyPouzivatel).vratTriedu(cisloTriedy).vratZiaka(cisloZiaka)
				.vratPredmet(cisloPredmetu);
		Boolean vipis = mojManazer.novaZnamkaSubmit(predmet, novaHodnotaS, novaMaxHodnotaS, novyDatumS);
		novaHlaska.setVisible(vipis);
		updateTabulka();
	}

	public void logout() {
		Scena scena = new Scena(new LoginScena());
		singleton.getStage().setScene(scena.nastavScene(null));
	}

}
