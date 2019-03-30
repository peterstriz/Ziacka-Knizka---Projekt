package gui;

import Pouzivatelia.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ScenaZiakHlavna extends DefaultHodnoty implements ScenaInterface {
	private Pouzivatel aktualnyPouzivatel = null;
	private Scene mojaScena;
	private StackPane mojPane = new StackPane();

	private Button logout = new Button("Logout");
	private TableView<Znamka> tabulkaZiak = new TableView<>();
	private ChoiceBox<String> vyberPredmetov = new ChoiceBox<String>();
	private Text vypisMenoPouzivatela = new Text();

	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		this.aktualnyPouzivatel = aktualnyPouzivatel;
		mojaScena = new Scene(mojPane, width, height);

		nastav();
		funkcie();
		pridajPane();

		return mojaScena;
	}

	@SuppressWarnings("unchecked")
	public void funkcie() {
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

		vyberPredmetov.setItems(((Ziak) aktualnyPouzivatel).vratMenoPredmetov());
		vyberPredmetov.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					tabulkaZiak.setItems(((Ziak) aktualnyPouzivatel).vratZnamkyPredmetu((int) new_value));
				});
		vyberPredmetov.getSelectionModel().selectFirst();

		logout.setOnAction(e -> {
			logout();
		});

		vypisMenoPouzivatela.setText(aktualnyPouzivatel.vratCeleMeno());
	}

	public void pridajPane() {
		mojPane.getChildren().addAll(vypisMenoPouzivatela, tabulkaZiak, logout, vyberPredmetov);
	}

	public void nastav() {

		logout.setTranslateY(30 - (mojaScena.getHeight() / 2));
		logout.setTranslateX(mojaScena.getWidth() / 2 - 50);

		tabulkaZiak.setMaxSize(velkostPolickaX * 3 + 2, velkostTabulky * 2);
		tabulkaZiak.setTranslateY(stredTabulky);
		tabulkaZiak.setPlaceholder(new Label("Žiadne známky."));

		vypisMenoPouzivatela.setWrappingWidth(200);
		vypisMenoPouzivatela.setTextAlignment(TextAlignment.RIGHT);
		vypisMenoPouzivatela.setTranslateY(70 - (mojaScena.getHeight() / 2));
		vypisMenoPouzivatela.setTranslateX(mojaScena.getWidth() / 2 - 110);

		vyberPredmetov.setTranslateY(-(velkostTabulky + velkostPolickaY));

	}
}
