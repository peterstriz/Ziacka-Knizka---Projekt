package gui;

import Pouzivatelia.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ScenaUcitelHlavna extends DefaultHodnoty implements ScenaInterface {
	private Scene mojaScena;
	private VBox mojPane = new VBox();
	private ManazerUcitel mojManazer = new ManazerUcitel();

	private ChoiceBox<String> vyberZiaka = new ChoiceBox<String>();
	private TableView<Znamka> tabulka = new TableView<>();
	private ChoiceBox<String> vyberPredmetov = new ChoiceBox<String>();
	private ChoiceBox<String> vyberTriedu = new ChoiceBox<String>();
	private TextField novaHodnota = new TextField("");
	private TextField novaMaxHodnota = new TextField("");
	private TextField novyDatum = new TextField("");
	private Button novaZnamkaSubmit = new Button("Pridaù");
	private Text novaHlaska = new Text();

	private Trieda trieda;
	private Ziak ziak;
	private Predmet predmet;

	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		super.aktualnyPouzivatel = aktualnyPouzivatel;

		menu();
		nastav();
		funkcie();
		pridajPane();
		mojaScena = new Scene(mojPane, width, height);
		return mojaScena;
	}

	public void nastav() {
		tabulka.setMaxWidth(velkostPolickaX * 3 + 2);
		tabulka.setMaxHeight(2 * velkostTabulky);
		tabulka.setPlaceholder(new Label("éiadne zn·mky."));
		tabulka.setEditable(true);

		novaHodnota.setPromptText("Hodnota");
		novaHodnota.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		novaMaxHodnota.setPromptText("Max. Hodnota");
		novaMaxHodnota.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		novyDatum.setPromptText("dd.MM.yyyy");
		novyDatum.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		novaZnamkaSubmit.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		novaHlaska.setFont(new Font(14));
		novaHlaska.setWrappingWidth(velkostPolickaX * 3);
		novaHlaska.setTextAlignment(TextAlignment.CENTER);
		novaHlaska.setFill(Color.RED);
		novaHlaska.setVisible(false);

		mojPane.setAlignment(Pos.CENTER);
	}

	@SuppressWarnings("unchecked")
	public void funkcie() {
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
					try {
						predmet = ziak.vratPredmet((int) new_value);
					} catch (Exception e) {

					}
					updateTabulka();
				});

		vyberZiaka.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					try {
						ziak = trieda.getZiak((int) new_value);
					} catch (Exception e) {

					}
					vyberPredmetov.setItems(ziak.vratMenoPredmetov());
					vyberPredmetov.getSelectionModel().selectFirst();
				});

		vyberTriedu.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					try {
						trieda = ((Ucitel) aktualnyPouzivatel).vratTriedu((int) new_value);
					} catch (Exception e) {

					}
					vyberZiaka.setItems(trieda.getMenoZiakov());
					vyberZiaka.getSelectionModel().selectFirst();
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
		mojPane.getChildren().addAll(menuBar, vyberPredmetov, vyberTriedu, vyberZiaka, tabulka, novaHodnota,
				novaMaxHodnota, novyDatum, novaZnamkaSubmit, novaHlaska, informacia);
	}

	public void updateTabulka() {
		tabulka.setItems(predmet.vratZnamku());
	}

	public void novaZnamkaSubmit() {
		String novaHodnotaS = (String) novaHodnota.getText();
		String novaMaxHodnotaS = (String) novaMaxHodnota.getText();
		String novyDatumS = (String) novyDatum.getText();
		Boolean vipis = mojManazer.novaZnamkaSubmit(predmet, novaHodnotaS, novaMaxHodnotaS, novyDatumS);
		novaHlaska.setVisible(vipis);
		updateTabulka();
	}
}
