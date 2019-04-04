package gui;

import Pouzivatelia.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ScenaZiakHlavna extends DefaultHodnoty implements ScenaInterface {

	private Scene mojaScena;
	private VBox mojPane = new VBox();

	private TableView<Znamka> tabulkaZiak = new TableView<>();
	private ChoiceBox<String> vyberPredmetov = new ChoiceBox<String>();

	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		super.aktualnyPouzivatel = aktualnyPouzivatel;

		nastav();
		funkcie();
		pridajPane();
		mojaScena = new Scene(mojPane, width, height);
		return mojaScena;
	}

	@SuppressWarnings("unchecked")
	public void funkcie() {
		menu();

		TableColumn<Znamka, String> datumColumn = new TableColumn<>("Datum pisomky");
		datumColumn.setMinWidth(velkostPolickaX - 1);
		datumColumn.setCellValueFactory(new PropertyValueFactory<>("datumS"));

		TableColumn<Znamka, Double> hodnotaColumn = new TableColumn<Znamka, Double>("Hodnota");
		hodnotaColumn.setMinWidth(velkostPolickaX - 1);
		hodnotaColumn.setCellValueFactory(new PropertyValueFactory<>("hodnotaS"));

		TableColumn<Znamka, Double> maxHodnotaColumn = new TableColumn<Znamka, Double>("Max. Hodnota");
		maxHodnotaColumn.setMinWidth(velkostPolickaX - 1);
		maxHodnotaColumn.setCellValueFactory(new PropertyValueFactory<>("maxHodnotaS"));

		tabulkaZiak.getColumns().addAll(hodnotaColumn, maxHodnotaColumn, datumColumn);

		vyberPredmetov.setItems(((Ziak) aktualnyPouzivatel).vratMenoPredmetov());
		vyberPredmetov.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					tabulkaZiak.setItems(((Ziak) aktualnyPouzivatel).vratZnamkyPredmetu((int) new_value));
				});
		vyberPredmetov.getSelectionModel().selectFirst();
	}

	public void pridajPane() {
//		mojPane.getColumnConstraints().add(new ColumnConstraints(velkostPolickaX));
//		mojPane.setHgap(5);
//		mojPane.setVgap(5);
		mojPane.setAlignment(Pos.CENTER);

		mojPane.getChildren().addAll(menuBar, vyberPredmetov, tabulkaZiak, informacia);
	}

	public void nastav() {
		tabulkaZiak.setMaxWidth(velkostPolickaX * 3 + 2);
		tabulkaZiak.setPlaceholder(new Label("Žiadne známky."));

	}
}
